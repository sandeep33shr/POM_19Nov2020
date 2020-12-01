package com.ssp.Heartland.Test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.Heartland.SubModules.NBPaynow;
import com.ssp.Heartland.SubModules.RaiseInsurerPayment;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.uxp_HeartlandPages.Banking;
import com.ssp.uxp_HeartlandPages.CollectionScreen;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.Login;

public class TC_Banking_filtering_Marking extends BaseTest {

  public void checkBranchColumnAsPerSelectedFilter(WebDriver driver,
      HashMap<String, String> testData, ExtentTest extentReport) throws Exception {


    String tcId = "TC_BranchFilters";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));
   HashMap<String,String> dynamicHashMap = new HashMap<>();

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);

      bank.selectBank(testData, driver, extentReport);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.assertBranchColumnValueBasedOnSelectedFilter(driver, testData, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.close();
    }
  }

  public void checkMediaTypeColumnAsPerSelectedFilter(WebDriver driver,
      HashMap<String, String> testData, ExtentTest extentReport) throws Exception {


    String tcId = "TC_MediaTypeFilter";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);

      bank.selectBank(testData, driver, extentReport);
      bank.selectBranch(testData, driver, extentReport);
      bank.selectMediaType(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.assertMediaTypeColumnValueBasedOnSelectedFilter(driver, testData, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.close();
    }
  }

  public void checkMarkUnmarkAll(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {


    String tcId = "TC_MarkAll";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);

      bank.selectBank(testData, driver, extentReport);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.assertMarkTotalBasedOnMarkUnmarkAll(driver, extentReport);



    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.close();
    }
  }

  public void assertColumnHeaderCheckbox(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {


    String tcId = "TC_HeaderCheckbox";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);

      bank.selectBank(testData, driver, extentReport);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.assertMarkedTotalBasedOnColumnHeaderCheckbox(driver, extentReport);



    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.close();
    }
  }

  public void checkFiltersAvailabilityDynamicallyOnClickingFiltersButton(WebDriver driver,
      HashMap<String, String> testData, ExtentTest extentReport) throws Exception {


    String tcId = "TC_FiltersButton";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
      bank.checkAvailabilityOfFilters(driver, extentReport);
      bank.clickFilterButton(driver, extentReport);
      bank.checkUnavailabilityOfFilters(driver, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.close();
    }
  }


  public void checkFiltersAvailabilityDynamically(WebDriver driver,
      HashMap<String, String> testData, ExtentTest extentReport) throws Exception {


    String tcId = "TC_Filters";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);
      bank.checkAvailabilityOfFilters(driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.checkUnavailabilityOfFilters(driver, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.close();
    }
  }


  public void checkDefaultStateOfButtons(WebDriver driver, ExtentTest extentReport)
      throws Exception {


    String tcId = "TC_buttonsDefaultState";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);
      bank.checkButtonsState(driver, extentReport);



    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }

  public void checkMarkUnmarkAllBankDynamicStateLogic(WebDriver driver, ExtentTest extentReport)
      throws Exception {


    String tcId = "TC_MarkUnmarkAllBankStateDynamicLogic";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);

      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);

      Banking bank = new Banking(driver);
      bank.checkMarkUnmarkAllBankButtonStateChangeDynamically(driver, extentReport);



    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }

  public void checkAvailabilityOfPaymentDetails(WebDriver driver, ExtentTest extentReport)
      throws Exception {


    String tcId = "TC_BankPaymentDetails";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));

    // Create a hashmap to store all run time data generated.


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);
      HomePage homePage = new HomePage(driver, extentReport);

      homePage.searchClientUsingCode(testData, driver, extentReport);
      homePage.selectClient(driver, extentReport);
      NBPaynow nb = new NBPaynow();
      HashMap<String, String> dynamicHashMap = new HashMap<>();
      nb.createNewBusiness(driver, dynamicHashMap, extentReport, false, false);
      RaiseInsurerPayment ip = new RaiseInsurerPayment();
      ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("Insurer Name"), dynamicHashMap,testData,
          extentReport, true, false);
      CollectionScreen cs = new CollectionScreen(driver, extentReport);
      cs.enterDetailsForPayNow(testData, driver, extentReport, false);
      WaitUtils.waitForSpinner(driver);
      UIInteraction.closeCurrentTab(driver);
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
      bank.clickFind(driver, extentReport);
      bank.checkAvailabilityOfPaymentDetailsScreen(driver, extentReport);
      bank.verifyBankingTitle(driver, extentReport);



    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }

  public void checkAvailabilityOfCollectionDetailsAndReport(WebDriver driver, ExtentTest extentReport)
      throws Exception {


    String tcId = "TC_BankCollectionDetails";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);
      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.checkAvilabilityOfCollectionScreen(driver, extentReport);
      bank.verifyBankingTitle(driver, extentReport);
      bank.assertReportTrigger(driver, extentReport);
      
     
      


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }

  public void checkMarkedStatusFilter(WebDriver driver, ExtentTest extentReport) throws Exception {


    String tcId = "TC_MarkedStatusFilter";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();

      /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);
      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);
      bank.checkMarkedStatusFilter(driver, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }
  public void checkBankingProcess(HashMap<String,String> testData,WebDriver driver, ExtentTest extentReport) throws Exception {


    String tcId = "TC_BankingProcess";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));
    HashMap<String,String> dynamicHashMap = new HashMap<>();


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();
      
            /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);
      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
  //    bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);   
    bank.checkBankingProcess(dynamicHashMap,testData, driver, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }
  
  public void checkSmartSearch(HashMap<String,String> testData,WebDriver driver, ExtentTest extentReport) throws Exception {


    String tcId = "TC_SmartSearch";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));
    HashMap<String,String> dynamicHashMap = new HashMap<>();


    try {

      HashMap<String, String> loginDetails = getProperties();
      HashMap<String, String> config = getProperties();
      
            /****
       * 
       * Create instance of Page class LoginPage and call method to perform login
       */
      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);
      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);   
      bank.smartSearch(testData, driver, extentReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
      driver.quit();
    }
  }


}
