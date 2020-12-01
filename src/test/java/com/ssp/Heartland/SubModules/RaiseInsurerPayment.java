package com.ssp.Heartland.SubModules;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.DataUtils;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.PaymentScreen;


public class RaiseInsurerPayment extends BaseTest {

  @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_"+env;
    return DataUtils.testDatabyID(testcaseId, className);
  }
  public boolean raiseInsurerPayment(WebDriver driver,HashMap<String, String> testData, HashMap<String, String> dynamicHashMap, 
      ExtentTest extentReport) throws Exception {
    boolean status = false;
    try {
      HomePage homePage = new HomePage(driver, extentReport).get();

      homePage.navigateToPayment(driver, extentReport);

      PaymentScreen paymentpage = new PaymentScreen(driver, extentReport).get();


      Log.softAssertThat(paymentpage.verifyPaymentSelectAccount(),
          "Navigated successfully to Payments- Select Account",
          "Unable to navigate to Payments- Select Account", driver, extentReport, true);
      
      paymentpage.searchInsurer(dynamicHashMap, driver, extentReport);

      Log.softAssertThat(paymentpage.verifyPayments(), "Navigated successfully to Payments", "Unable to navigate to Payments", driver,
          extentReport , true);
      paymentpage.markTransction(dynamicHashMap, driver, extentReport);
      
      Log.softAssertThat(paymentpage.verifyPaymentDetails(), "Navigated successfull to Payments Details", "Unable to navigate to Payments Details", driver, extentReport, true);
      paymentpage.enterPaymentDetails(testData, driver, extentReport);
      
      status = paymentpage.verifyPaymentSelectAccount();
      
      Log.softAssertThat(status,  "Navigated successfully to Payments- Select Account",
          "Unable to navigate to Payments- Select Account", driver, extentReport, true);

    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }

    return status;
  }
  
  /****
   * To mark and pay the transaction with Commission Adjustment
   * 
   * @param quotesData
   * @param screenshot
   * @param reference - policy reference or document reference
   * @param extentReport
   * @param clientFullySettled -if client is fully settle then it will skip funding message
   * @param CommAdj - if true then it will reduce 50% Commission
   * author Sandeep.Sharma*/
  
  public void payInsurerWithCommAdj(WebDriver driver,String insurerName,HashMap<String, String> dynamicHashMap,HashMap<String, String> testData,
      ExtentTest extentReport,boolean clientFullySettled,boolean CommAdj) throws Exception {
    boolean status = false;
    try {
      HomePage homePage = new HomePage(driver, extentReport).get();

      homePage.navigateToPayment(driver, extentReport);

      PaymentScreen paymentpage = new PaymentScreen(driver, extentReport).get();
      Log.softAssertThat(paymentpage.verifyPaymentSelectAccount(),
          "Navigated successfully to Payments- Select Account",
          "Unable to navigate to Payments- Select Account", driver, extentReport, true);
      paymentpage.searchInsurerByName(insurerName, driver, extentReport);
      Log.softAssertThat(paymentpage.verifyPayments(), "Navigated successfully to Payments", "Unable to navigate to Payments", driver,
          extentReport , true);
      
      paymentpage.markAndPayTransaction(testData,dynamicHashMap.get("docRef"), driver, extentReport, clientFullySettled, CommAdj, false,true);
     
    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }

   }
  
  /****
   * To mark and pay client or SA
   * 
   * @param quotesData
   * @param screenshot
   * @param reference - policy reference or document reference
   * @param extentReport
   * @author Sandeep.Sharma*/
  
  public void payClientSA(WebDriver driver,String accountCode,HashMap<String, String> dynamicHashMap,HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {
    boolean status = false;
    try {
      HomePage homePage = new HomePage(driver, extentReport).get();

      homePage.navigateToPayment(driver, extentReport);

      PaymentScreen paymentpage = new PaymentScreen(driver, extentReport).get();
      Log.softAssertThat(paymentpage.verifyPaymentSelectAccount(),
          "Navigated successfully to Payments- Select Account",
          "Unable to navigate to Payments- Select Account", driver, extentReport, true);
      paymentpage.searchByCode(accountCode, driver, extentReport);     
      paymentpage.markAndPayTransaction(testData,dynamicHashMap.get("docRef"), driver, extentReport, true, false, true,false);
     
    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }

   }

}
