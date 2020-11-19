package com.ssp.Heartland.SubModules;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.DataUtils;
import com.ssp.uxp_HeartlandPages.HomePage;
import com.ssp.uxp_HeartlandPages.PremiumDisplay;
import com.ssp.uxp_HeartlandPages.RenewalManager;
import com.ssp.uxp_HeartlandPages.RenewalSelection;

public class PerformRenewal extends BaseTest {

  @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_" + env;
    return DataUtils.testDatabyID(testcaseId, className);
  }

  public void performRenewal(WebDriver driver, String policyNo, ExtentTest extentReport)
      throws Exception {

    String tcId = "TC_Renewal";
    HashMap<String, String> testData = getTestData(tcId);
    String description = testData.get("Description");
    Log.testCaseInfo(description);
    try {

      
      HomePage homePage = new HomePage(driver, extentReport);
      homePage.navigateToRenewalSelection(driver, extentReport);

      RenewalSelection renewalSelectionPage = new RenewalSelection(driver, extentReport).get();
      Log.softAssertThat(renewalSelectionPage.verifyRenewalSelection(),
          "Navigate successfully to Renewal Selection Screen",
          "Unable to navigate to renewal selection screen", driver, extentReport, true);

      renewalSelectionPage.searchForRenewal(policyNo, driver, extentReport);
      renewalSelectionPage.selectForRenewal(driver, extentReport);
      Log.softAssertThat(renewalSelectionPage.verifyRenewalStatus(driver, extentReport),
          "Policy status successfully updated to Under Renewal",
          "Policy status not updated to Under Renewal", driver, extentReport, true);

      homePage.navigateToRenewalManager(driver, extentReport);

      RenewalManager renewalManagerPage = new RenewalManager(driver, extentReport).get();
      Log.softAssertThat(renewalManagerPage.verifyRenewalManager(),
          "Navigated successfully to Renewal Manager Screen",
          "Unable to navigate to renewal manager screen", driver, extentReport, true);

      renewalManagerPage.searchForPolicyRenewal(policyNo, driver, extentReport);
      renewalManagerPage.selectPolicy(driver, extentReport);
      PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport).get();

      Log.softAssertThat(premiumPage.verifyPremiumPage(),
          "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
          extentReport, true);

    } catch (Exception e) {
      Log.exception(e, driver, extentReport);
    }


  }
}
