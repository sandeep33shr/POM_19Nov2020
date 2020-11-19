package com.ssp.Heartland.Test;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.IncomeClearing;
import com.ssp.uxp_HeartlandPages.IncomeSummary;
import com.ssp.uxp_HeartlandPages.Login;

public class TC_IncomeClearing_4644 extends BaseTest{
 
  public void NBWithPayNowFullCollection(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {

    
      String tcId = "TC_IncomeClearing_IPCP";
      testData = getTestData(tcId);
      extentReport = addTestInfo(tcId, testData.get("Description"));
      
      //Create a hashmap to store all run time data generated.
     
      
      try {

        HashMap<String, String> loginDetails = getProperties();
        HashMap<String, String> config = getProperties();

         /****
          * 
          * Create instance of Page class LoginPage  and call method to perform login
          */
        Login loginPage = new Login(driver, loginDetails.get("URL"), extentReport);
        loginPage.loginToSSP(loginDetails, driver, extentReport);
        
      HomePage homePage = new HomePage(driver, extentReport);
 /*     homePage.searchClientUsingCode(testData, driver, extentReport);
      homePage.selectClient(driver, extentReport);            
      NBPaynow nb = new NBPaynow();
      HashMap<String, String> dynamicHashMap = new HashMap<>();     
      nb.createNewBusiness(driver, dynamicHashMap, extentReport, false,false);
      RaiseInsurerPayment ip = new RaiseInsurerPayment();
      ip.payInsurer(driver, testData, dynamicHashMap.get("Insurer Name"), dynamicHashMap, extentReport,true);
      CollectionScreen cs = new CollectionScreen(driver, extentReport);
      cs.enterDetailsForPayNow(testData, driver, extentReport, false);
     UIInteraction.closeCurrentTab(driver);
      ip.payInsurer(driver, testData, dynamicHashMap.get("AddOn Insurer Name"), dynamicHashMap, extentReport,true);
      cs.enterDetailsForPayNow(testData, driver, extentReport, false);
      UIInteraction.closeCurrentTab(driver);
      homePage.navigateToTransaction(driver, extentReport);
      TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);
      String docRef = searchPage.getTransactionRef(driver, extentReport);
      dynamicHashMap.put(docRef, docRef);
      System.out.println("Document ref is ---"+ docRef);
      searchPage.clickOnActionMenu(driver, extentReport);
      searchPage.viewTransaction(driver, extentReport);    
      searchPage.switchToCTVScreen(driver);
      String ToClearComm = searchPage.traverseCTVScreen("ToClear Commission", "Income", driver, extentReport);
       dynamicHashMap.put(ToClearComm, ToClearComm);
       String ToClearFee = searchPage.traverseCTVScreen("ToClear Fee", "Income", driver, extentReport);
      dynamicHashMap.put(ToClearFee, ToClearFee);
       searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);*/
      homePage.navigateToIncomeClearing(driver, extentReport);
      IncomeClearing ic = new IncomeClearing(driver, extentReport);
      ic.checkAllChekbox(driver, extentReport);
      ic.checkCommissionChekbox(driver, extentReport);
      ic.clickFind();
      ic.sortByPostingDate();
      ic.clickUnmarkAll();
   /*   UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get(docRef), driver, extentReport);
      ic.assertMarkedValueWrtCTV(dynamicHashMap.get(docRef), dynamicHashMap.get(ToClearComm), driver, extentReport);*/
      
      UIInteraction.markTransctionsBasedOnRef("SND0000197309", driver, extentReport);
      ic.assertMarkedValueWrtCTV("SND0000197309", "32.00", driver, extentReport);
      ic.clickProcessButton();
      IncomeSummary is=new IncomeSummary(driver,extentReport);
      is.assertBankAccountsAmount(driver, extentReport);
      is.clickProcessButton(driver);
      
      
     
      
    } catch (Exception e) {
        Log.exception(e, driver, extentReport);
    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
    }
  }
  
  

}
