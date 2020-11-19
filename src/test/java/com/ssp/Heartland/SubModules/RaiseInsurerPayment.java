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
  
  // Author :Sandeep Sharma (pay Insurer method)
  
  public void payInsurer(WebDriver driver,HashMap<String, String> testData,String insurerName,HashMap<String, String> dynamicHashMap,
      ExtentTest extentReport,boolean clientFullySettled) throws Exception {
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
      
      paymentpage.markTransctionWhenClientFullySettled(dynamicHashMap, driver, extentReport, clientFullySettled);
         
      Log.softAssertThat(status,  "Trasaction marked sucessfully",
          "Unable to mark transaction", driver, extentReport, true);

    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }

   }
}
