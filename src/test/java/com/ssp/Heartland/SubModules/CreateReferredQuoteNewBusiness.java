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
import com.ssp.uxp_HeartlandPages.NewQuoteScreens;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;


@Listeners(EmailReport.class)
public class CreateReferredQuoteNewBusiness extends BaseTest {

  @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_"+env;
    return DataUtils.testDatabyID(testcaseId, className);
  }

    public void createReferredQuote(WebDriver driver, HashMap<String,String> dynamicHashMap,ExtentTest extentReport,boolean subAgent) throws Exception {
           
            String tcId = "TC_newBusiness_referred";

            if(subAgent)
              tcId="TC_newBusiness_referred_SA";
            
            testData = getTestData(tcId);
            
            try {
                 
                 ViewClientDetails clientDetailsPage = new ViewClientDetails(driver,extentReport);
                 clientDetailsPage.createNewQuote(testData.get("ProductName"), driver, extentReport);
                 clientDetailsPage.enterPolicyHeaderData(testData,dynamicHashMap, driver, extentReport);
                 clientDetailsPage.clickNextPolicyHeader(driver);
                 
                 NewQuoteScreens nbscreen = new NewQuoteScreens(driver,extentReport).get();
                 nbscreen.switchToForm(driver);
                 nbscreen.riskScreensReferred(testData, driver, extentReport);
                 Log.softAssertThat(nbscreen.verifyQuotesPage(), "Quote Result Page Successfully loaded", 
                                 "Quote Result Page not loaded",driver, extentReport, true);
                 
                
            } 
            catch (Exception e) {
                Log.exception(e, driver, extentReport);
                
            }          

      }
}
