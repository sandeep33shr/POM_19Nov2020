package com.ssp.Heartland.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.Heartland.SubModules.NBPaynow;
import com.ssp.Heartland.SubModules.RaiseInsurerPayment;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.uxp_HeartlandPages.BankReconciliation;
import com.ssp.uxp_HeartlandPages.Banking;
import com.ssp.uxp_HeartlandPages.CollectionScreen;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.Login;
import com.ssp.uxp_HeartlandPages.TransactionScreen;

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
     bank.verifyMarkedItemReport(driver, extentReport);
      
     
      


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

  public void checkBankingEnquiriesAndCTV_US39162(HashMap<String,String> testData,WebDriver driver, ExtentTest extentReport) throws Exception {


    String tcId = "TC_BankingEnquiriesAndCTV";
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
      searchPage.viewAllocation(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      String SRP1= searchPage.getTransactionCreditTransReffNo(driver, extentReport);
      dynamicHashMap.put("SRP1", SRP1);
      searchPage.closeViewAllocationcreen(driver, extentReport);
              
      
      homePage.navigateToBanking(driver, extentReport);
      Banking bank = new Banking(driver);
      bank.selectBranch(testData, driver, extentReport);
      bank.clickFind(driver, extentReport);   
      UIInteraction.openCTVBasedOnReference(dynamicHashMap.get("SRP1"), driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.clickAmountAllocatedLink(driver, extentReport);
      searchPage.switchToFrameCTV(driver, extentReport);
      String debitTRef=searchPage.getTransactionDebitTransReffNo(driver, extentReport);
      Log.softAssertThat(debitTRef.contains("SND"), "Original SRP is showing allocation between SND and SRP", "Original SRP is not showing allocation between SND and SRP", driver, extentReport, true);
      searchPage.closeViewAllocationcreen(driver, extentReport);
      searchPage.switchOutOfFrame(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
      bank.clickUnmarkAll(driver, extentReport);
      UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("SRP1"), driver, extentReport);
      bank.clickBank(driver, extentReport);
      WaitUtils.waitForSpinner(driver);
      CollectionScreen cs= new CollectionScreen(driver, extentReport);
       cs.enterCollectionPaymentDetailsForBanking(testData, driver, extentReport);
       UIInteraction.closeCurrentTab(driver);
              
      //********** Banking Completed*************/   
       
       
      HomePage homepage = new HomePage(driver, extentReport);
      homepage.navigateToTransaction(driver, extentReport);     
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.navigateToTransactionsTab(driver, extentReport);
      searchPage.searchViaTransactionRef(dynamicHashMap.get("SRP1"), driver, extentReport);
      searchPage.performSearch(driver, extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("SRP1"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.clickAmountAllocatedLink(driver, extentReport);
      searchPage.switchToFrameCTV(driver, extentReport);
      String debitTRef1=searchPage.getTransactionDebitTransReffNo(driver, extentReport);
      Log.softAssertThat(debitTRef1.contains("SND"), "Original SRP is showing allocation between SND and SRP", "Original SRP is not showing allocation between SND and SRP", driver, extentReport, true);
      searchPage.closeViewAllocationcreen(driver, extentReport);
      searchPage.switchOutOfFrame(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      String BankingDate=searchPage.traverseCTVScreen("Banking Date", "Details", driver, extentReport);
      Date date = new Date();  
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
      String strDate= formatter.format(date);  
      System.out.println(strDate+"==="+BankingDate );  
      Log.softAssertThat(BankingDate.equalsIgnoreCase(strDate),"Banking Date is stamped with current date", "Banking is not stamped with current date", driver, extentReport, true);   
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
      WaitUtils.waitForSpinner(driver);      
      
      searchPage.selectActionMenu(driver, dynamicHashMap.get("SRP1"), extentReport);
      searchPage.viewAllocation(driver, extentReport);
      searchPage.switchToFrameCTV(driver, extentReport);
      String SRP2= searchPage.getTransactionCreditTransReffNoWhenUnDOCheckboxesNotAvailable(driver, extentReport);
      dynamicHashMap.put("SRP2", SRP2);
      searchPage.closeViewAllocationcreen(driver, extentReport);
      searchPage.switchOutOfFrame(driver, extentReport);
      searchPage.newSearch(driver, extentReport);
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.navigateToTransactionsTab(driver, extentReport);
      searchPage.searchViaTransactionRef(dynamicHashMap.get("SRP2"), driver, extentReport);
      searchPage.performSearch(driver, extentReport);
      searchPage.assertAccountCode("CBA", driver, extentReport);  
      searchPage.selectActionMenu(driver, dynamicHashMap.get("SRP2"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.clickAmountAllocatedLink(driver, extentReport);
      searchPage.switchToFrameCTV(driver, extentReport);
      String debitTRef2=searchPage.getTransactionDebitTransReffNo(driver, extentReport);
      System.out.println(debitTRef2+"=="+dynamicHashMap.get("SRP1"));
      boolean status=false;
      if(debitTRef2.equalsIgnoreCase(dynamicHashMap.get("SRP1"))){
        status=true;
      }else status=false;
      Log.softAssertThat(status, "Final SRP is showing allocation between SRP1 and SRP2", "Final SRP is not showing allocation between SRP1 and SRP2", driver, extentReport, true);
      searchPage.closeViewAllocationcreen(driver, extentReport);
      searchPage.switchOutOfFrame(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      searchPage.checkUnavailabilityOfBankingDateOnCTV(driver, extentReport);
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
      
      homePage.navigateToBankReconciliation(driver, extentReport);
      BankReconciliation bankRec=new BankReconciliation(driver, extentReport);
      bankRec.selectBank(testData, driver, extentReport);
      bankRec.pressFind(driver, extentReport);
      UIInteraction.markTransctionsBasedOnRef(dynamicHashMap.get("SRP2"), driver, extentReport);
      bankRec.pressReconcile(driver, extentReport);
      bankRec.pressYesConfirmation(driver, extentReport);
      bankRec.switchToReport(driver);
      bankRec.close(driver, extentReport);
      bankRec.switchOutOfReport(driver, extentReport);
      
      homepage.navigateToTransaction(driver, extentReport);     
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.navigateToTransactionsTab(driver, extentReport);
      searchPage.searchViaTransactionRef(dynamicHashMap.get("SRP2"), driver, extentReport);
      searchPage.performSearch(driver, extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("SRP2"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      String recDate=searchPage.traverseCTVScreen("Reconciled Date", "Details", driver, extentReport);    
      Log.softAssertThat(recDate.equalsIgnoreCase(strDate),"Reconciled Date of newly generated SRP/SPY is stamped with current date", "Reconciled Date of newly generated SRP/SPY is not stamped with current date", driver, extentReport, true);
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
      searchPage.newSearch(driver, extentReport);
      searchPage.uncheckOutstandingCheckBox(driver, extentReport);
      searchPage.navigateToTransactionsTab(driver, extentReport);
      searchPage.searchViaTransactionRef(dynamicHashMap.get("SRP1"), driver, extentReport);
      searchPage.performSearch(driver, extentReport);
      searchPage.selectActionMenu(driver, dynamicHashMap.get("SRP1"), extentReport);
      searchPage.viewTransaction(driver, extentReport);
      searchPage.switchToCTVScreen(driver);
      String recDate1=searchPage.traverseCTVScreen("Reconciled Date", "Details", driver, extentReport);
      Log.softAssertThat(recDate1.equalsIgnoreCase(strDate),"Reconciled Date of original transaction is stamped with current date", "Reconciled Date of original transaction is not stamped with current date", driver, extentReport, true);
      searchPage.closeCTVScreen(driver, extentReport);
      searchPage.switchOutOfCTVScreen(driver, extentReport);
      

    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    } finally {
      Log.testCaseResult(extentReport);
      Log.endTestCase(extentReport);
     // driver.quit();
    }
  }
  

}