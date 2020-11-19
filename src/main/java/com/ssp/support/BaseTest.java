package com.ssp.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;
import com.ssp.utils.DataUtils;
import com.ssp.utils.FileUtils;


/******
 * BaseTest contains methods that will run BeforeSuite, BeforeMethod, AfterMethod and AfterSuite. 
 * It also has the method defined to load the properties file and read the login credentials and URL.
 * It also has the intiliasation of the variables, method to add test info in all test cases.
 * 
 * BaseTest is being inherited by all the test cases.
 * @author Shweta.Saigal
 *
 */
public class BaseTest {


  protected static ExtentReports extent;
  protected String website = null;
  protected String browser = null;
  protected WebDriver driver = null;
  protected ExtentTest extentReport = null;
  protected HashMap<String, String> testData = new HashMap<>();

  /****
   * Before suite will run always before the suite begins making it the first method in execution.
   */
  @BeforeSuite(alwaysRun = true)
  public static void beforeSuite() {

    extent = new ExtentReports("target/surefire-reports/TestReport.html", true,
        DisplayOrder.OLDEST_FIRST, NetworkMode.ONLINE);
    Reporter.getCurrentTestResult().getTestContext().getSuite().setAttribute("policy_number", "");
  }

  /*
   * After suite will be responsible to close the report properly at the end This one will be called
   * in the end making it the last method to be called in test execution
   */


  /***
   * 
   * Method to add test info in all test cases.
   * 
   * @param testCaseId
   * @param testDesc
   * @return
   */
  public static ExtentTest addTestInfo(String testCaseId, String testDesc) {
    String test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform");
    
    String description = "[Environment : "+ env +" ][Browser : "+ browser + " ][Platform : " + platform + " ] " + testDesc;
    
    return Log.testCaseInfo(testCaseId + " [" + test + "]", description, test);
  }

  /***
   * Method to get data for all test cases from the excel sheet.
   * 
   * @param testcaseId
   * @return
   */
  public HashMap<String, String> getTestData(String testcaseId) {
    String className = null;
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");

    if (testcaseId.contains("Validation"))
      className = "Heartland_Validations_" + env;
    else
      className = "Heartland_TestCases_" + env;
    return DataUtils.testDatabyID(testcaseId, className);
  }

  /***
   * Methods to write to excel sheet
   * 
   * @param testcaseId
   * @param Values
   * @throws Exception
   */
  public void setTestData(String testcaseId, HashMap<String, String> values, String columnName)
      throws Exception {
    String className = null;
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");

    if (testcaseId.contains("Validation"))
      className = "Heartland_Validations_" + env;
    else
      className = "Heartland_TestCases_" + env;

    DataUtils.testDatabyID(testcaseId, className, values, columnName);
  }

  /****
   * 
   * Method to close the browser instance and delete all cookies. 
   * 
   */
  @AfterMethod(alwaysRun = true)
  public void closeAll() {
    
     driver.manage().deleteAllCookies();
     //driver.quit();
  }

  /*****
   * 
   * Method to initialise the global variables.
   * Will always run before each method  
   * 
   * @param context
   * @throws IOException
   */
  @BeforeMethod(alwaysRun = true)

  public void init(ITestContext context){
    browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("browser");
    website = context.getCurrentXmlTest().getParameter("website");
    driver = WebDriverFactory.get(browser);
  }

  /****
   * 
   * Will always run after the Suite to perform clean up and create the report folder.
   */
  @AfterSuite(alwaysRun = true)
  public void afterSuite() {
    extent.flush();
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    File reportFolder = new File("test-reports/TestReports");
    File environmentFolder = new File("test-reports/TestReports");
    
    File reportSourceFile = new File("target/surefire-reports/TestReport.html");
    File reportScreenshotFile = new File("target/surefire-reports/ScreenShot");
    File reportScreenshotSourceFile = new File("test-output/ScreenShot");
    try {
      environmentFolder = FileUtils.createEnvironmentFolder(reportFolder, env);
      reportFolder = FileUtils.createReportFolder(reportFolder, env);
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    String reportDestFolder = reportFolder + File.separator + "TestReport.html";
    File reportDestFile = new File(reportDestFolder);
    String screenshotFolder = reportFolder + File.separator + "ScreenShot";
    File screenshotDestFolder = new File(screenshotFolder);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

    try {
      FileUtils.copyFile(reportSourceFile, reportDestFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileUtils.copyFolder(reportScreenshotSourceFile, reportScreenshotFile);
      FileUtils.copyFolder(reportScreenshotFile, screenshotDestFolder);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /****
   * 
   * Method to read the properties file and 
   * @return HashMap containing the values read from the properties file.
   */

  public static HashMap<String, String> getProperties() {

    HashMap<String, String> properties = new HashMap<>();
    File propFile= null;
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    if(env.equalsIgnoreCase("SIT"))
      propFile = new File(System.getProperty("user.dir")+"/src/main/resources/configurations/config_SIT.properties");
    else if(env.equalsIgnoreCase("IDE"))
      propFile = new File(System.getProperty("user.dir")+"/src/main/resources/configurations/config_IDE.properties");
    else if(env.equalsIgnoreCase("SysTest"))
      propFile = new File(System.getProperty("user.dir")+"/src/main/resources/configurations/config_SysTest.properties");
    else if(env.equalsIgnoreCase("Dev2"))
      propFile = new File(System.getProperty("user.dir")+"/src/main/resources/configurations/config_Dev2.properties");
    
    
    FileInputStream fileInput = null;
    try {
      fileInput = new FileInputStream(propFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Properties prop = new Properties();

    // load properties file
    try {
      prop.load(fileInput);
    } catch (IOException e) {
      e.printStackTrace();
    }

      Log.message("Properties file loaded for " + env);
      System.out.println("The key set is : " + prop.keySet());
      System.out.println("The entry set is :"+ prop.entrySet());
      
      /****
       * Loop to put the properties in the hashmap.
       */
      for(Object keys: prop.keySet()){
        String k = (String) keys;
        properties.put(k, prop.getProperty(k));
        
      }
      
    return properties;
  }

}
