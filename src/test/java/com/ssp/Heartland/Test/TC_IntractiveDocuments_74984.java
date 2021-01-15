package com.ssp.Heartland.Test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.Heartland.SubModules.CreateNewBusinessQuote;
import com.ssp.Heartland.SubModules.NBPaynow;
import com.ssp.Heartland.SubModules.PerformMTAQuote;
import com.ssp.Heartland.SubModules.PerformRPMTAQuote;
import com.ssp.Heartland.SubModules.RaiseInsurerPayment;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.uxp_HeartlandPages.CollectionScreen;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.IncomeClearing;
import com.ssp.uxp_HeartlandPages.IncomeSummary;
import com.ssp.uxp_HeartlandPages.Login;
import com.ssp.uxp_HeartlandPages.NewQuoteScreens;
import com.ssp.uxp_HeartlandPages.PremiumDisplay;
import com.ssp.uxp_HeartlandPages.TransactionScreen;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;

public class TC_IntractiveDocuments_74984 extends BaseTest {

  
  public void iDoc(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {


    String tcId = "TC_IDOC";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));
    HashMap<String, String> dynamicHashMap = new HashMap<>();

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
      ViewClientDetails clDetails= new ViewClientDetails(driver, extentReport);
      clDetails.clickLetterWriting(driver, extentReport);
     clDetails.generateIntractiveDocument(driver, extentReport);
      
     
    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
     // driver.quit();
    }
  }
   }