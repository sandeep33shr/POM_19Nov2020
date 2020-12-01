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

public class TC_IncomeClearing_4644 extends BaseTest {

  
  public void NBWithPayNowFullCollection(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {


    String tcId = "TC_IncomeClearing_IPCP";
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
      NBPaynow nb = new NBPaynow();
      nb.createNewBusiness(driver, dynamicHashMap, extentReport, false, false);
      homePage.navigateToTransaction(driver, extentReport);
      TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
      String docRef = searchPage.getTransactionRef(driver, extentReport);
      dynamicHashMap.put("docRef", docRef);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      String ToClearCommVal =
          searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);     
      Log.softAssertThat(ToClearCommVal.equals("0.00"), "Commission is not yet moved to clear as earning basis is IP/CP",  "Commission is moved to clear though earning basis is IP/CP", driver, extentReport, false);
      String unearnedFeeVal =
          searchPage.traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);    
      Log.softAssertThat(unearnedFeeVal.equals("0.00"), "Fee is moved to clear irrespective of earning basis is IP/CP",  "Fee is not moved to clear irrespective of earning basis is IP/CP", driver, extentReport, false);
      searchPage.closeCTVScreen(driver, extentReport);    
      searchPage.switchOutOfFrame(driver, extentReport);         
      
        RaiseInsurerPayment ip = new RaiseInsurerPayment(); 
        ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("Insurer Name"), dynamicHashMap,testData, extentReport, true, false);
        CollectionScreen cs
        = new CollectionScreen(driver, extentReport);
        cs.enterDetailsForPayNow(testData, driver,
        extentReport, false);
        UIInteraction.closeCurrentTab(driver);
        ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("AddOn Insurer Name"), dynamicHashMap,testData, extentReport, true, false);
        cs.enterDetailsForPayNow(testData, driver, extentReport, false);
        UIInteraction.closeCurrentTab(driver);
      homePage.navigateToTransaction(driver, extentReport);
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      String unearnedComm =
          searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);
      dynamicHashMap.put("unearnedComm", unearnedComm);
      System.out.println(dynamicHashMap.get("ToClearComm"));
      String ToClearComm =
          searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);
      dynamicHashMap.put("ToClearComm", ToClearComm);
      System.out.println(dynamicHashMap.get("ToClearComm"));
      String earnedComm =
          searchPage.traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
      dynamicHashMap.put("earnedComm", earnedComm);
      System.out.println(dynamicHashMap.get("earnedComm"));
      String unearnedFee =
          searchPage.traverseCTVScreen("UnEarned Fee", "Income", driver, extentReport);
      dynamicHashMap.put("unearnedFee", unearnedFee);
      System.out.println(dynamicHashMap.get("unearnedFee"));
      String ToClearFee =
          searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);
      dynamicHashMap.put("ToClearFee", ToClearFee);
      System.out.println(dynamicHashMap.get("ToClearFee"));
      String earnedFee =
          searchPage.traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
      dynamicHashMap.put("earnedFee", earnedFee);
      System.out.println(dynamicHashMap.get("earnedFee"));
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
          
      homePage.navigateToIncomeClearing(driver, extentReport);
      IncomeClearing ic = new IncomeClearing(driver, extentReport);
      ic.checkAllChekbox(driver, extentReport);
      ic.checkCommissionChekbox(driver, extentReport);
      ic.clickFind();
      ic.sortByPostingDate();
      ic.clickUnmarkAll();
      UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
      ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
          dynamicHashMap.get("ToClearComm"), driver, extentReport);
      ic.clickProcessButton();

      IncomeSummary is = new IncomeSummary(driver, extentReport);
      is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
      is.selectAccountsdropdown(testData, driver, extentReport);
      is.selectMediaTypedropdown(testData, driver, extentReport);
      is.clickProcessButton(driver);
      UIInteraction.closeCurrentTab(driver);

      ic.checkAllChekbox(driver, extentReport);
      ic.checkFeeChekbox(driver, extentReport);
      ic.clickFind();
      ic.sortByPostingDate();
      ic.clickUnmarkAll();
      UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
      ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
          dynamicHashMap.get("ToClearFee"), driver, extentReport);

      ic.clickProcessButton();
      is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
      is.selectAccountsdropdown(testData, driver, extentReport);
      is.selectMediaTypedropdown(testData, driver, extentReport);
      is.clickProcessButton(driver);
      UIInteraction.closeCurrentTab(driver);

      homePage.navigateToTransaction(driver, extentReport);
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, false);
      searchPage.closeCTVScreen(driver, extentReport);
      WaitUtils.waitForSpinner(driver);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.viewAllocation(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.markTransactionAndClickOnUnDoButtonPostIncomeEarning(dynamicHashMap.get("policyNo"), driver,
          extentReport, true);
      searchPage.switchOutOfFrame(driver, extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, true);
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);

      homePage.navigateToIncomeClearing(driver, extentReport);
      ic.checkAllChekbox(driver, extentReport);
      ic.checkCommissionChekbox(driver, extentReport);
      ic.clickFind();
      ic.sortByPostingDate();
      ic.clickUnmarkAll();
      UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
      String ToClearCommReversal="-"+dynamicHashMap.get("ToClearComm");
      ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
          ToClearCommReversal, driver, extentReport);
      ic.clickProcessButton();
      is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
      is.selectAccountsdropdown(testData, driver, extentReport);
      is.selectMediaTypedropdown(testData, driver, extentReport);
      is.clickProcessButton(driver);
      UIInteraction.closeCurrentTab(driver);
      ic.checkAllChekbox(driver, extentReport);
      ic.checkFeeChekbox(driver, extentReport);
      ic.clickFind();
      ic.sortByPostingDate();
      ic.clickUnmarkAll();
      UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
      String ToClearFeeReversal="-"+dynamicHashMap.get("ToClearFee");
      ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
          ToClearFeeReversal, driver, extentReport);
      ic.clickProcessButton();
      is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
      is.selectAccountsdropdown(testData, driver, extentReport);
      is.selectMediaTypedropdown(testData, driver, extentReport);
      is.clickProcessButton(driver);
      UIInteraction.closeCurrentTab(driver);



      homePage.navigateToTransaction(driver, extentReport);
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.assertIncomePostClearingReversals(dynamicHashMap, driver, extentReport);
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);



    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
    }
  }
    public void AdditionalMTAWithNegativeAddon(WebDriver driver, HashMap<String, String> testData,
        ExtentTest extentReport) throws Exception {


      String tcId = "TC_AdditonalMTANegativeAddon_IPCP";
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
        NBPaynow nb = new NBPaynow();
        nb.createNewBusiness(driver, dynamicHashMap, extentReport, false, false);
        ViewClientDetails vd = new ViewClientDetails(driver, extentReport);
        vd.searchQuotesForMTA(dynamicHashMap.get("policyNo"), driver, extentReport);         
        
        PerformMTAQuote mta = new PerformMTAQuote();
        mta.performMTA(driver, dynamicHashMap.get("policyNo"), dynamicHashMap, extentReport);     
        NewQuoteScreens nbscreen = new NewQuoteScreens(driver, extentReport).get();
        nbscreen.expandAddOnSection(driver, extentReport);
        nbscreen.removeAddOn(driver);
        nbscreen.enterRemoveAddonDetailsOnEditBrokerAddOnScreen(driver, extentReport, testData.get("AddonRevisedPremium"), testData.get("InsurerComm"),  testData.get("SAComm"),false);
        nbscreen.saveAddOn(driver);
        
        NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);
        quotesPage.saveQuote(driver, extentReport);
        quotesPage.switchOutOfForm(driver);
        PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport);
        Log.softAssertThat(premiumPage.verifyPremiumPage(),
            "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
            extentReport, true);
        premiumPage.selectPayNow(driver, extentReport);
        String insurerName = premiumPage.getInsurerName(driver, extentReport);
        dynamicHashMap.put("Insurer Name", insurerName);
        String AddOninsurerName = premiumPage.getAddOnInsurerName(driver, extentReport);
        dynamicHashMap.put("AddOn Insurer Name", AddOninsurerName);
        premiumPage.confirmQuote(driver, extentReport);
        CollectionScreen collecton = new CollectionScreen(driver, extentReport);
        collecton.enterDetailsForPayNow(testData, driver, extentReport, false);

        
        /*------MTA Done*/
        
        homePage.navigateToTransaction(driver, extentReport);
        TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef("SED%", driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        String docRef = searchPage.getTransactionRef(driver, extentReport);
        dynamicHashMap.put("docRef", docRef);
        System.out.println(dynamicHashMap.get("docRef"));
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String ToClearCommVal =
            searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);     
        Log.softAssertThat(ToClearCommVal.equals("0.00"), "Commission is not yet moved to clear as earning basis is IP/CP",  "Commission is moved to clear though earning basis is IP/CP", driver, extentReport, false);
        String unearnedFeeVal =
            searchPage.traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);    
        Log.softAssertThat(unearnedFeeVal.equals("0.00"), "Fee is moved to clear irrespective of earning basis is IP/CP",  "Fee is not moved to clear irrespective of earning basis is IP/CP", driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);    
        searchPage.switchOutOfFrame(driver, extentReport);         
        
          RaiseInsurerPayment ip = new RaiseInsurerPayment(); 
          ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("Insurer Name"), dynamicHashMap,testData, extentReport, true, false);
          CollectionScreen cs
          = new CollectionScreen(driver, extentReport);
          cs.enterDetailsForPayNow(testData, driver,
          extentReport, false);
          UIInteraction.closeCurrentTab(driver);
          ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("AddOn Insurer Name"), dynamicHashMap,testData, extentReport, true, false);
          cs.enterDetailsForPayNow(testData, driver, extentReport, false);
          UIInteraction.closeCurrentTab(driver);
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef(dynamicHashMap.get("docRef"), driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String unearnedComm =
            searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedComm", unearnedComm);
        System.out.println(dynamicHashMap.get("ToClearComm"));
        String ToClearComm =
            searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearComm", ToClearComm);
        System.out.println(dynamicHashMap.get("ToClearComm"));
        String earnedComm =
            searchPage.traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("earnedComm", earnedComm);
        System.out.println(dynamicHashMap.get("earnedComm"));
        String unearnedFee =
            searchPage.traverseCTVScreen("UnEarned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedFee", unearnedFee);
        System.out.println(dynamicHashMap.get("unearnedFee"));
        String ToClearFee =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearFee", ToClearFee);
        System.out.println(dynamicHashMap.get("ToClearFee"));
        String earnedFee =
            searchPage.traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("earnedFee", earnedFee);
        System.out.println(dynamicHashMap.get("earnedFee"));
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        homePage.navigateToIncomeClearing(driver, extentReport);

        IncomeClearing ic = new IncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearComm"), driver, extentReport);
        ic.clickProcessButton();

        IncomeSummary is = new IncomeSummary(driver, extentReport);
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);

        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearFee"), driver, extentReport);

        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);

        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef(dynamicHashMap.get("docRef"), driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);
        WaitUtils.waitForSpinner(driver);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewAllocation(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.markTransactionAndClickOnUnDoButtonPostIncomeEarning(dynamicHashMap.get("docRef"), driver,
            extentReport, true);
        searchPage.switchOutOfFrame(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, true);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);

        homePage.navigateToIncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        String ToClearCommReversal="-"+dynamicHashMap.get("ToClearComm");
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            ToClearCommReversal, driver, extentReport);
        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        String ToClearFeeReversal="-"+dynamicHashMap.get("ToClearFee");
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            ToClearFeeReversal, driver, extentReport);
        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);



        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef(dynamicHashMap.get("docRef"), driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertIncomePostClearingReversals(dynamicHashMap, driver, extentReport);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
 
       
      } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
    }
    }
    
    public void ReturnMTA(WebDriver driver, HashMap<String, String> testData,
        ExtentTest extentReport) throws Exception {


      String tcId = "TC_ReturnMTA_IPCP";
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
        NBPaynow nb = new NBPaynow();
        nb.createNewBusiness(driver, dynamicHashMap, extentReport, false, false);
           
        
        PerformRPMTAQuote mta = new PerformRPMTAQuote();
        mta.performMTA(driver, dynamicHashMap.get("policyNo"), dynamicHashMap, extentReport);     
        NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);
        quotesPage.saveQuote(driver, extentReport);
        quotesPage.switchOutOfForm(driver);
        PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport);
        Log.softAssertThat(premiumPage.verifyPremiumPage(),
            "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
            extentReport, true);
        String insurerName = premiumPage.getInsurerName(driver, extentReport);
        dynamicHashMap.put("Insurer Name", insurerName);
        premiumPage.selectInvoice(driver, extentReport);
        premiumPage.confirmQuote(driver, extentReport);
        

        
        /*-----Return-MTA Done*/
        
        homePage.navigateToTransaction(driver, extentReport);
        TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef("SEC%", driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        String docRef = searchPage.getTransactionRef(driver, extentReport);
        dynamicHashMap.put("docRef", docRef);
        String accountCode=searchPage.getAccountCode(driver, extentReport);
        dynamicHashMap.put("accountCode", accountCode);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String ToClearCommVal =
            searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);     
        Log.softAssertThat(ToClearCommVal.equals("0.00"), "Commission is not yet moved to clear as earning basis for credit transaction is client Pays and client is not settled yet",  "Commission is moved to clear without settling client for earning basis client Pays", driver, extentReport, true);
        String toClearFeeVal =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);    
        Log.softAssertThat(toClearFeeVal.equals("0.00"), "Fee is not moved to clear without settling client for earning basis client Pays",  "Fee is moved to clear without settling client for earning basis client Pays", driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);    
        searchPage.switchOutOfFrame(driver, extentReport);         
        
               
          RaiseInsurerPayment ip = new RaiseInsurerPayment(); 
       // setting client through insurer payment 
          ip.payClientSA(driver, dynamicHashMap.get("accountCode"), dynamicHashMap,testData, extentReport);
          CollectionScreen cs
          = new CollectionScreen(driver, extentReport);
          cs.enterDetailsForPayNow(testData, driver,
          extentReport, false);
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef(dynamicHashMap.get("docRef"), driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String unearnedComm =
            searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedComm", unearnedComm);
        System.out.println(dynamicHashMap.get("ToClearComm"));
        String ToClearComm =
            searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearComm", ToClearComm);
        System.out.println(dynamicHashMap.get("ToClearComm"));
        String earnedComm =
            searchPage.traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("earnedComm", earnedComm);
        System.out.println(dynamicHashMap.get("earnedComm"));
        String unearnedFee =
            searchPage.traverseCTVScreen("UnEarned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedFee", unearnedFee);
        System.out.println(dynamicHashMap.get("unearnedFee"));
        String ToClearFee =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearFee", ToClearFee);
        System.out.println(dynamicHashMap.get("ToClearFee"));
        String earnedFee =
            searchPage.traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("earnedFee", earnedFee);
        System.out.println(dynamicHashMap.get("earnedFee"));
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        homePage.navigateToIncomeClearing(driver, extentReport);

        IncomeClearing ic = new IncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearComm"), driver, extentReport);
        ic.clickProcessButton();

        IncomeSummary is = new IncomeSummary(driver, extentReport);
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);

        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearFee"), driver, extentReport);

        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);

        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef(dynamicHashMap.get("docRef"), driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);
        WaitUtils.waitForSpinner(driver);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewAllocation(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.markTransactionAndClickOnUnDoButtonPostIncomeEarning(dynamicHashMap.get("docRef"), driver,
            extentReport, true);
        searchPage.switchOutOfFrame(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("docRef"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);

        homePage.navigateToIncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
         ic.clickProcessButton();
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.clickProcessButton();
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);



        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.navigateToTransactionsTab(driver, extentReport);
        searchPage.searchViaTransactionRef(dynamicHashMap.get("docRef"), driver, extentReport);
        searchPage.performSearch(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertIncomePostClearingReversals(dynamicHashMap, driver, extentReport);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
 
       
      } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
    }
    }

    public void NBWithPayNowFullCollection_ClientPays(WebDriver driver, HashMap<String, String> testData,
        ExtentTest extentReport) throws Exception {


      String tcId = "TC_IncomeClearing_CP";
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
        NBPaynow nb = new NBPaynow();
        nb.createNewBusiness(driver, dynamicHashMap, extentReport, false, false);
        homePage.navigateToTransaction(driver, extentReport);
        TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        String docRef = searchPage.getTransactionRef(driver, extentReport);
        dynamicHashMap.put("docRef", docRef);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String unearnedCommVal =
            searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);     
        Log.softAssertThat(unearnedCommVal.equals("0.00"), "Commission is moved to clear as earning basis is client Pays",  "Commission is not moved to clear though earning basis is client pays", driver, extentReport, false);
        String unearnedFeeVal =
            searchPage.traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);    
        Log.softAssertThat(unearnedFeeVal.equals("0.00"), "Fee is moved to clear as client is fully settled",  "Fee is not moved to clear even though client is fully settled", driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);    
        searchPage.switchOutOfFrame(driver, extentReport);         
        
          RaiseInsurerPayment ip = new RaiseInsurerPayment(); 
          ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("Insurer Name"), dynamicHashMap,testData, extentReport, true, true);
          CollectionScreen cs
          = new CollectionScreen(driver, extentReport);
          cs.enterDetailsForPayNow(testData, driver,
          extentReport, false);
          UIInteraction.closeCurrentTab(driver);
          ip.payInsurerWithCommAdj(driver, dynamicHashMap.get("AddOn Insurer Name"), dynamicHashMap,testData, extentReport, true, true);
          cs.enterDetailsForPayNow(testData, driver, extentReport, false);
          UIInteraction.closeCurrentTab(driver);
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String unearnedComm =
            searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedComm", unearnedComm);
        String ToClearComm =
            searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearComm", ToClearComm);
        String earnedComm =
            searchPage.traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("earnedComm", earnedComm);
        String unearnedFee =
            searchPage.traverseCTVScreen("UnEarned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedFee", unearnedFee);
        String ToClearFee =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearFee", ToClearFee);
       String earnedFee =
            searchPage.traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("earnedFee", earnedFee);
      
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        homePage.navigateToIncomeClearing(driver, extentReport);

        IncomeClearing ic = new IncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearComm"), driver, extentReport);
        ic.clickProcessButton();

        IncomeSummary is = new IncomeSummary(driver, extentReport);
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);

        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearFee"), driver, extentReport);

        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);

        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);
        WaitUtils.waitForSpinner(driver);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewAllocation(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.markTransactionAndClickOnUnDoButtonPostIncomeEarning(dynamicHashMap.get("policyNo"), driver,
            extentReport, true);
        searchPage.switchOutOfFrame(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncome(dynamicHashMap, driver, extentReport, true);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);

        homePage.navigateToIncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        String ToClearCommReversal="-"+dynamicHashMap.get("ToClearComm");
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            ToClearCommReversal, driver, extentReport);
        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        String ToClearFeeReversal="-"+dynamicHashMap.get("ToClearFee");
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            ToClearFeeReversal, driver, extentReport);
        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);



        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertIncomePostClearingReversals(dynamicHashMap, driver, extentReport);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);



      } catch (Exception e) {
        Log.exception(e, driver, extentReport);
      } finally {
        Log.testCaseResult(extentReport);
        Log.endTestCase(extentReport);
      }
      
      
    }

    public void NBWithPayNowPartCollection_ClientPays(WebDriver driver, HashMap<String, String> testData,
        ExtentTest extentReport) throws Exception {


      String tcId = "TC_IncomeClearing_PartCollection_CP";
      testData = getTestData(tcId);
      extentReport = addTestInfo(tcId, testData.get("Description"));
      HashMap<String, String> dynamicHashMap = new HashMap<>();
      HashMap<String, Double> dynamicHashMapD = new HashMap<>();
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
        nb.createNewBusiness(driver, dynamicHashMap, extentReport, false, true);
        homePage.navigateToTransaction(driver, extentReport);
        TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        String docRef = searchPage.getTransactionRef(driver, extentReport);
        dynamicHashMap.put("docRef", docRef);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String unearnedCommVal =
            searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);     
        Log.softAssertThat(!unearnedCommVal.equals("0.00"), "Part Commission is moved to clear as earning basis is client Pays and part premium has been collected",  "Part Commission is not moved to clear even though part premium has been collected", driver, extentReport, false);
        String toClearFeeVal =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);    
        Log.softAssertThat(toClearFeeVal.equals("0.00"), "Fee is not moved to clear as fee portion is not yet collected",  "Fee is moved to clear even though client is not fully settled", driver, extentReport, false);
        
        String unearnedComm =
            searchPage.traverseCTVScreen("UnEarned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedComm", unearnedComm);
        double unearnedCommD=Double.parseDouble(unearnedComm);
        dynamicHashMapD.put("unearnedCommD", unearnedCommD);
        String ToClearComm =
            searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearComm", ToClearComm);
        double ToClearCommD=Double.parseDouble(ToClearComm);
        dynamicHashMapD.put("ToClearCommD", ToClearCommD);
        String earnedComm =
            searchPage.traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
        dynamicHashMap.put("earnedComm", earnedComm);
        double earnedCommD=Double.parseDouble(earnedComm);
        dynamicHashMapD.put("earnedCommD", earnedCommD);
        String unearnedFee =
            searchPage.traverseCTVScreen("UnEarned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("unearnedFee", unearnedFee);
        double unearnedFeeD=Double.parseDouble(unearnedFee);
        dynamicHashMapD.put("unearnedFeeD", unearnedFeeD);
        String ToClearFee =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);
        dynamicHashMap.put("ToClearFee", ToClearFee);
        double ToClearFeeD=Double.parseDouble(ToClearFee);
        dynamicHashMapD.put("ToClearFeeD", ToClearFeeD);
       String earnedFee =
            searchPage.traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
        dynamicHashMap.put("earnedFee", earnedFee);
        double earnedFeeD=Double.parseDouble(earnedFee);
        dynamicHashMapD.put("earnedFeeD", earnedFeeD);
        searchPage.closeCTVScreen(driver, extentReport);    
        searchPage.switchOutOfFrame(driver, extentReport);    
        homePage.navigateToIncomeClearing(driver, extentReport);
        IncomeClearing ic = new IncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            dynamicHashMap.get("ToClearComm"), driver, extentReport);
        ic.clickProcessButton();

        IncomeSummary is = new IncomeSummary(driver, extentReport);
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
        
        
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncomeClientPays(dynamicHashMapD, driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);
        WaitUtils.waitForSpinner(driver);
        searchPage.switchOutOfCTVScreen(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewAllocation(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.markTransactionAndClickOnUnDoButtonPostIncomeEarning(dynamicHashMap.get("policyNo"), driver,
            extentReport, false);
        searchPage.switchOutOfFrame(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFullyearnedIncomeClientPays(dynamicHashMapD, driver, extentReport, true);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);

        homePage.navigateToIncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkCommissionChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        String ToClearCommReversal="-"+dynamicHashMap.get("ToClearComm");
        ic.assertMarkedValueWrtCTV(dynamicHashMap, dynamicHashMap.get("docRef"),
            ToClearCommReversal, driver, extentReport);
        ic.clickProcessButton();
        is.assertBankAccountsAmount(dynamicHashMap, driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertIncomePostClearingReversalsClientPays(dynamicHashMapD, driver, extentReport);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);



      } catch (Exception e) {
        Log.exception(e, driver, extentReport);
      } finally {
        Log.testCaseResult(extentReport);
        Log.endTestCase(extentReport);
      }

}
    public void ClientPaysCollection_FullCommPart_PartFee_FullFee(WebDriver driver, HashMap<String, String> testData,
        ExtentTest extentReport) throws Exception {


      String tcId = "TC_ClientPaysCollection_FullCommPart_PartFee_FullFee";
      testData = getTestData(tcId);
      extentReport = addTestInfo(tcId, testData.get("Description"));
      HashMap<String, String> dynamicHashMap = new HashMap<>();
      HashMap<String, Double> dynamicHashMapD = new HashMap<>();
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
       
        CreateNewBusinessQuote nb=new CreateNewBusinessQuote();
        nb.createNewBusiness(driver, dynamicHashMap, extentReport, false);
        
        NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);
        double brokerFee=Double.parseDouble(quotesPage.getBrokerFeeFromSelectedQuotePanel(driver, extentReport)) ; 
        
        if(brokerFee>0){
        quotesPage.addKeyCareAddOn(driver, extentReport);
        quotesPage.saveQuote(driver, extentReport);
        quotesPage.switchOutOfForm(driver);
        PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport);
        Log.softAssertThat(premiumPage.verifyPremiumPage(),
            "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
            extentReport, true);
        premiumPage.selectPayNow(driver, extentReport);
        String insurerName = premiumPage.getInsurerName(driver, extentReport);
        dynamicHashMap.put("Insurer Name", insurerName);
        String AddOninsurerName = premiumPage.getAddOnInsurerName(driver, extentReport);
        dynamicHashMap.put("AddOn Insurer Name", AddOninsurerName);
        premiumPage.confirmQuote(driver, extentReport);
        CollectionScreen collecton = new CollectionScreen(driver, extentReport);
        collecton.enterAmountAfterSubtractingSomeAMount(testData, brokerFee, driver, extentReport,false);
        Log.softAssertThat(premiumPage.verifyTransactionPage(),
            "Transaction Confirmation Page successfully loaded",
            "Transaction Confirmation Page not loaded", driver, extentReport, true);
        String policyNo = premiumPage.storePolicyRef(driver, extentReport);
        dynamicHashMap.put("policyNo", policyNo);
        premiumPage.clickYesDocuments(driver, extentReport);        
        
        if (dynamicHashMap.get("policyNo") != null)
          Log.pass("Policy successfully created" + dynamicHashMap.get("policyNo"),driver, extentReport);
               
        
        homePage.navigateToTransaction(driver, extentReport);
        TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        String docRef = searchPage.getTransactionRef(driver, extentReport);
        dynamicHashMap.put("docRef", docRef);
        String accountCode=searchPage.getAccountCode(driver, extentReport);
        dynamicHashMap.put("accountCode", accountCode);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        String toClearFeeVal =
            searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);    
        Log.softAssertThat(toClearFeeVal.equals("0.00"), "Fee is not moved to clear as fee portion is not yet collected",  "Fee is moved to clear even though client is not fully settled", driver, extentReport, false);
               
          searchPage.closeCTVScreen(driver, extentReport);    
        searchPage.switchOutOfFrame(driver, extentReport);  
        homePage.navigateToCollection(driver, extentReport);
        
        CollectionScreen cs
        = new CollectionScreen(driver, extentReport);
       String partFee= Double.toString((brokerFee*60)/100);
       double partFeeD=Double.parseDouble(partFee);
       dynamicHashMap.put("partFee", partFee);
       dynamicHashMapD.put("partFeeD", partFeeD);
        cs.enterDetailsOnCollectionDetails(testData, dynamicHashMap.get("accountCode"),partFee, driver, extentReport);
        cs.markTransaction(dynamicHashMap.get("docRef"), driver, extentReport);
       cs.pressOKOnCollectionList(driver, extentReport);
        
        /*
         * Asserting post part fee collection with respect to  toClear field
*/        
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        String osAmount=searchPage.getOustandingAmount(driver, extentReport);
        dynamicHashMap.put("osAmount", osAmount);
        double osAmountD=Double.parseDouble(osAmount);
        dynamicHashMapD.put("osAmountD", osAmountD);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        double toClearFeeValPostPartFeeCollection =
           Double.parseDouble(searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport));  
        boolean status =false;
        if(toClearFeeValPostPartFeeCollection==dynamicHashMapD.get("partFeeD")){
          status=true;
        }else status=false;
        Log.softAssertThat(status, "Part Fee is moved to clear as part fee portion is collected",  "Part Fee is not moved to clear even though part fee is collected", driver, extentReport, false);
         searchPage.closeCTVScreen(driver, extentReport);    
        searchPage.switchOutOfFrame(driver, extentReport);  
        
        
        homePage.navigateToIncomeClearing(driver, extentReport);
        IncomeClearing ic = new IncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);  
        ic.clickProcessButton();

        IncomeSummary is = new IncomeSummary(driver, extentReport);
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
       
        //Remaining fee collection
        
        homePage.navigateToCollection(driver, extentReport);                
        cs.enterDetailsOnCollectionDetails(testData, dynamicHashMap.get("accountCode"),dynamicHashMap.get("osAmount"), driver, extentReport);
        cs.markTransaction(dynamicHashMap.get("docRef"), driver, extentReport);
         cs.pressOKOnCollectionList(driver, extentReport);
        
    // undo earned fee
        
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewAllocation(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.markTransactionAndClickOnUnDoButtonPostIncomeEarning(dynamicHashMap.get("partFee"), driver,
            extentReport, false);
        searchPage.switchOutOfFrame(driver, extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFeeIncomePostUndoClientPays(dynamicHashMapD, driver, extentReport, false);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);

        homePage.navigateToIncomeClearing(driver, extentReport);
        ic.checkAllChekbox(driver, extentReport);
        ic.checkFeeChekbox(driver, extentReport);
        ic.clickFind();
        ic.sortByPostingDate();
        ic.clickUnmarkAll();
        UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("docRef"), driver, extentReport);
        ic.clickProcessButton();
        is.selectAccountsdropdown(testData, driver, extentReport);
        is.selectMediaTypedropdown(testData, driver, extentReport);
        is.clickProcessButton(driver);
        UIInteraction.closeCurrentTab(driver);
        
        // assert after clearing reversal JN
        
        homePage.navigateToTransaction(driver, extentReport);
        searchPage.uncheckOutstandingCheckBox(driver, extentReport);
        searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.selectActionMenu(driver, dynamicHashMap.get("policyNo"), extentReport);
        searchPage.viewTransaction(driver, extentReport);
        searchPage.switchToCTVScreen(driver);
        searchPage.assertFeeIncomePostUndoClientPays(dynamicHashMapD, driver, extentReport, true);
        searchPage.closeCTVScreen(driver, extentReport);
        searchPage.switchOutOfCTVScreen(driver, extentReport);

        }else Log.message("This case will be executed for positive broker fee and currently it's negative");

      } catch (Exception e) {
        Log.exception(e, driver, extentReport);
      } finally {
        Log.testCaseResult(extentReport);
        Log.endTestCase(extentReport);
      }

}

}