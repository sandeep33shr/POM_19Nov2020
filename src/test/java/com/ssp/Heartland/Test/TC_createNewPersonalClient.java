package com.ssp.Heartland.Test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.uxp_HeartlandPages.CreatePersonalClient;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.Login;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;

public class TC_createNewPersonalClient extends BaseTest {

  HashMap<String, String> dyamicHashMap = new HashMap<>();

  public void testCase(WebDriver driver,HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {

    String tcId = "TC_create_personal_client";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description").toString());

    try {

      /***
       * getProperties method defined in base class to read URL, Username and Password from the
       * properties file based on the environment being used.
       * Keys defined are- URL, UserName and Password.
       */
      HashMap<String, String> loginDetails = getProperties();

      Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
      loginPage.loginToSSP(loginDetails, driver, extentReport);
      
      HomePage homePage = new HomePage(driver, extentReport);
      Log.softAssertThat(homePage.verifyLogin(), "Login successful, navigated to Home Page",
          "Unable to login", driver, extentReport, true);
      homePage.navigateToNewPersonalClient(driver, extentReport);
      
      CreatePersonalClient createClient = new CreatePersonalClient(driver, extentReport);
      createClient.createPersonalClient(driver, extentReport);
      
      ViewClientDetails clientDetailsPage = new ViewClientDetails(driver, extentReport);
      
      clientDetailsPage.getPersonalClientDetails(dyamicHashMap, driver, extentReport);
      
      
    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }finally{
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
    }
  }



}
