package com.ssp.Heartland.Test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.Heartland.SubModules.CreateNewBusinessQuote;
import com.ssp.Heartland.SubModules.PerformCancellation;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.Login;
import com.ssp.uxp_HeartlandPages.NewQuoteScreens;
import com.ssp.uxp_HeartlandPages.PremiumDisplay;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;

public class TC_perform_cancellation extends BaseTest {


  CreateNewBusinessQuote nb = new CreateNewBusinessQuote();
  PerformCancellation cancel = new PerformCancellation();

  HashMap<String, String> dynamicHashMap = new HashMap<>();

  public void testCase(WebDriver driver,HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {


    String tcId = "TC_cancellation";
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
      
      
      nb.createNewBusiness(driver, dynamicHashMap, extentReport, false);
      // To select and read quotes
      NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);

      /***
       * Read quotes stores values into the below labels-: Annual Premium Insurer Premium Total Add
       * Ons Broker Fee Net Income
       */
      String[] labels = new String[] {"Annual Premium", "Insurer Premium", "Total Add Ons",
          "Broker Fee", "Net Income"};

      quotesPage.readQuote(dynamicHashMap, labels, driver, extentReport);
      quotesPage.saveQuote(driver, extentReport);
      quotesPage.switchOutOfForm(driver);
      PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport);

      Log.softAssertThat(premiumPage.verifyPremiumPage(),
          "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
          extentReport, true);

      String insurerName = premiumPage.getInsurerName(driver, extentReport);
      dynamicHashMap.put("Insurer Name", insurerName);

      premiumPage.confirmQuote(driver, extentReport);

      Log.softAssertThat(premiumPage.verifyTransactionPage(),
          "Transaction Confirmation Page successfully loaded",
          "Transaction Confirmation Page not loaded", driver, extentReport, true);

      String policyNo = premiumPage.storePolicyRef(driver, extentReport);
      dynamicHashMap.put("policyNo", policyNo);
      premiumPage.clickYesDocuments(driver, extentReport);
      Log.softAssertThat(clientDetailsPage.verifySelectClient(),
          "User navigated successfully to Quotes Tab in View Client Details Screen after visiting DocStore",
          "User not navigated to Quotes Tab in View Client after visiting DocStore", driver,
          extentReport, true);
      
      /****
      //Document Store Assertions
      homePage.navigateToDocStore(driver, extentReport);
      DocumentStore docStore = new DocumentStore(driver, extentReport);
      Log.softAssertThat(docStore.verifyDocStorePage(), "Navigated succesfully to doc store",
          "Not navigated to Doc Store", driver, extentReport, true);
      
      docStore.navigateToPolicy(driver, extentReport);
      
      Log.softAssertThat(docStore.verifyPolicyPage(), "Navigated successfully to policy folder",
          "Unable to navigate to policy folder", driver, extentReport, true);
      
      docStore.selectCurrentPolicyFolder(quotesResult,driver,extentReport);
      
      Log.softAssertThat(docStore.verifyDocumentGenerated(), "New Business document is generated on the policy",
          "No documents generated on the policy", driver, extentReport, true);
      
      docStore.navigateBackToPortal(driver, extentReport);
      
      homePage.closeInfoSection(driver, extentReport);
      *******/
      if (dynamicHashMap.get("policyNo") != null) {
        Log.pass("Policy successully created " + dynamicHashMap.get("policyNo"), driver,
            extentReport);
        cancel.performCancellation(driver, policyNo, dynamicHashMap, extentReport);
        /***
         * Read quotes stores values into the below labels-: Annual Premium Insurer Premium Total
         * Add Ons Broker Fee Net Income
         */
        String[] labelsCancellation = new String[] {"Cancelled Premium", "Insurer Premium Cancelled",
            "Total Add Ons Cancelled", "Broker Fee Cancelled", "Net Income Cancelled"};

        quotesPage.readQuote(dynamicHashMap, labelsCancellation, driver, extentReport);
        quotesPage.saveQuote(driver, extentReport);
        quotesPage.switchOutOfForm(driver);
        Log.softAssertThat(premiumPage.verifyPremiumPage(),
            "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
            extentReport, true);
        premiumPage.confirmQuote(driver, extentReport);
        
        /********
        //Document Store Assertions
        homePage.navigateToDocStore(driver, extentReport);
        
        Log.softAssertThat(docStore.verifyDocStorePage(), "Navigated succesfully to doc store",
            "Not navigated to Doc Store", driver, extentReport, true);
        
        docStore.navigateToPolicy(driver, extentReport);
        
        Log.softAssertThat(docStore.verifyPolicyPage(), "Navigated successfully to policy folder",
            "Unable to navigate to policy folder", driver, extentReport, true);
        
        docStore.selectCurrentPolicyFolder(quotesResult,driver,extentReport);
        
        Log.softAssertThat(docStore.verifyDocumentGenerated(), "Cancellation document is generated on the policy",
            "No documents generated on the policy", driver, extentReport, true);
        docStore.navigateBackToPortal(driver, extentReport);
        
        homePage.closeInfoSection(driver, extentReport);
        ********/
        Log.softAssertThat(clientDetailsPage.verifyQuotesTab(), "Navigated to quotes tab", "Not navigated to quotes tab", driver, extentReport, true);
        Log.softAssertThat(clientDetailsPage.verifyCancellationSuccess(), "Cancellation completed successfully",
            "Cancellation not completed on policy", driver, extentReport, true);
        
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
