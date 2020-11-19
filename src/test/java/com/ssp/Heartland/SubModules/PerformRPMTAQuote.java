package com.ssp.Heartland.SubModules;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.EmailReport;
import com.ssp.support.Log;
import com.ssp.utils.DataUtils;
import com.ssp.uxp_HeartlandPages.MTAReason;
import com.ssp.uxp_HeartlandPages.NewQuoteScreens;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;


@Listeners(EmailReport.class)
public class PerformRPMTAQuote extends BaseTest {
	HashMap<String, String> quotesData = new HashMap<>();
	
  @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_" + env;
    return DataUtils.testDatabyID(testcaseId, className);
  }

  public void performMTA(WebDriver driver, String policyNo, HashMap<String, String> dynamicHashMap,
      ExtentTest extentReport) throws Exception {

    String tcId = "TC_MTA_RP";
    HashMap<String, String> testData = getTestData(tcId);
    String description = testData.get("Description");

    Log.testCaseInfo(description);


    try {

      ViewClientDetails clientDetailsPage = new ViewClientDetails(driver, extentReport);
      clientDetailsPage.searchQuotesForMTA(policyNo, driver, extentReport);

      MTAReason mtaPage = new MTAReason(driver, extentReport).get();
      Log.softAssertThat(mtaPage.verifyMtaLoad(), "MTA Reasons Page loaded",
          "MTA Reason page not loaded", driver, extentReport, true);
      mtaPage.selectRiskChange(testData, driver, extentReport);

      NewQuoteScreens nbscreen = new NewQuoteScreens(driver, extentReport).get();
      nbscreen.switchToForm(driver);
      nbscreen.riskScreensMTA(testData, driver, extentReport);
      Log.softAssertThat(nbscreen.verifyQuotesPage(), "Quote Result Page Successfully loaded",
          "Quote Result Page not loaded", driver, extentReport, true);

    } catch (Exception e) {
      Log.exception(e, driver, extentReport);

    }
  }
}


