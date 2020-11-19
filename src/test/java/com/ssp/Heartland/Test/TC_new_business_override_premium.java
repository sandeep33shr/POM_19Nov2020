package com.ssp.Heartland.Test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.Heartland.SubModules.CreateNewBusinessQuote;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.Login;
import com.ssp.uxp_HeartlandPages.NewQuoteScreens;
import com.ssp.uxp_HeartlandPages.PremiumDisplay;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;


public class TC_new_business_override_premium extends BaseTest {

  HashMap<String, String> quotesResult = new HashMap<>();
  CreateNewBusinessQuote nb = new CreateNewBusinessQuote();


  public void testCase(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {
    String tcId = "TC_override_premium";
    testData = getTestData(tcId);
    extentReport = addTestInfo(tcId, testData.get("Description"));
    
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
      
      homePage.searchClientUsingCode(testData, driver, extentReport);
      Log.softAssertThat(homePage.verifyClientSearch(), "Client searched successfully", 
          "Unable to search client", driver, extentReport, true);
      homePage.selectClient(driver, extentReport);
      ViewClientDetails clientDetailsPage = new ViewClientDetails(driver,extentReport);
      Log.softAssertThat(clientDetailsPage.verifySelectClient(), "Selected client successfully", 
                      "Unable to select client", driver, extentReport, true);
      
      
      nb.createNewBusiness(driver, quotesResult, extentReport, false);

      // To select and read quotes
      NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);
      
      quotesPage.createOverridePremium(driver, extentReport);

      /***
       * Read quotes stores values into the below labels(without SA policy)-: Annual Premium Insurer
       * Premium Total Add Ons Broker Fee Net Income
       */
      
      
      String[] labels = new String[] {"Annual Premium", "Insurer Premium", "Total Add Ons",
          "Broker Fee", "Net Income"};

      quotesPage.readQuote(quotesResult, labels, driver, extentReport);
      quotesPage.saveQuote(driver, extentReport);
      quotesPage.switchOutOfForm(driver);
      PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport);

      Log.softAssertThat(premiumPage.verifyPremiumPage(),
          "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
          extentReport, true);

      String insurerName = premiumPage.getInsurerName(driver, extentReport);
      quotesResult.put("Insurer Name", insurerName);

      premiumPage.confirmQuote(driver, extentReport);

      Log.softAssertThat(premiumPage.verifyTransactionPage(),
          "Transaction Confirmation Page successfully loaded",
          "Transaction Confirmation Page not loaded", driver, extentReport, true);
      String policyNo = premiumPage.storePolicyRef(driver, extentReport);
      quotesResult.put("policyNo", policyNo);
      premiumPage.clickYesDocuments(driver, extentReport);
      Log.softAssertThat(clientDetailsPage.verifySelectClient(),
          "User navigated successfully to Quotes Tab in View Client Details Screen",
          "User not navigated to Quotes Tab in View Client", driver, extentReport, true);
     
      if (quotesResult.get("policyNo") != null) {
        Log.pass("Policy successully created by overrideing premium " + quotesResult.get("policyNo"), driver,
            extentReport);

      } else {
        Log.fail("Unable to create policy", driver, extentReport);
      }
     
    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    } finally{
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
    }
  }

}
