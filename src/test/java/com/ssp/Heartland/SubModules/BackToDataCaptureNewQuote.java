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

@Listeners(EmailReport.class)
public class BackToDataCaptureNewQuote extends BaseTest {
  
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_"+env;
    return DataUtils.testDatabyID(testcaseId, className);
  }

    public void createNewQuote(WebDriver driver, HashMap<String,String> dynamicHashMap,ExtentTest extentReport) throws Exception {
           
            String tcId = "TC_newBusiness";
            testData = getTestData(tcId);
            
            try {
                 
              NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);
              quotesPage.switchToForm(driver);
              quotesPage.backToDataCaptureAndNewQuote(driver, extentReport);
              quotesPage.switchOutOfForm(driver);

              // Back to risk screens to overwrite existing quote and save
              quotesPage.switchToForm(driver);
              
              quotesPage.riskScreens(testData, driver, extentReport);
            } 
            catch (Exception e) {
                Log.exception(e, driver, extentReport);
                
            }          

      }
}
