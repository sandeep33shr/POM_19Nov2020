package com.ssp.Heartland.SubModules;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.DataUtils;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.TransactionScreen;

public class SearchTransactions extends BaseTest {
 @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_"+env;
    return DataUtils.testDatabyID(testcaseId, className);
  }
  public void searchTransactionsUsingPolicyNo(WebDriver driver, HashMap<String, String> dynamicHashMap, 
      ExtentTest extentReport) throws Exception {

    String tcId = "TC_SearchTransactions";
    HashMap<String, String> testData = getTestData(tcId);
    String description = testData.get("Description");
    
    Log.testCaseInfo(description);

    try {
      HomePage homePage = new HomePage(driver, extentReport).get();
      
      homePage.navigateToTransaction(driver, extentReport);
      
      TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
      
      searchPage.searchAccountViaClientName(driver, testData,extentReport);
      searchPage.searchViaPolicyNo(driver, dynamicHashMap.get("policyNo"), extentReport);

    }
    catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }
  }

}
