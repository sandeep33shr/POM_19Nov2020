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
import com.ssp.uxp_HeartlandPages.CollectionScreen;
import com.ssp.uxp_HeartlandPages.NewQuoteScreens;
import com.ssp.uxp_HeartlandPages.PremiumDisplay;
import com.ssp.uxp_HeartlandPages.ViewClientDetails;


@Listeners(EmailReport.class)
public class NBPaynow extends BaseTest {

  @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_"+env;
    return DataUtils.testDatabyID(testcaseId, className);
  }

    public void createNewBusiness(WebDriver driver, HashMap<String,String> dynamicHashMap,ExtentTest extentReport,boolean subAgent,boolean partCollection) throws Exception {
           
            String tcId = "TC_newBusiness_PayNow";

            if(subAgent)
              tcId="TC_newBusiness_SA_PayNow";
            
            testData = getTestData(tcId);
            
            try {
              
             
                 
                 ViewClientDetails clientDetailsPage = new ViewClientDetails(driver,extentReport);
                 clientDetailsPage.createNewQuote(testData.get("ProductName"), driver, extentReport);
                 clientDetailsPage.enterPolicyHeaderData(testData,dynamicHashMap, driver, extentReport);
                 clientDetailsPage.clickNextPolicyHeader(driver);
                 
                 NewQuoteScreens nbscreen = new NewQuoteScreens(driver,extentReport).get();
                 nbscreen.switchToForm(driver);
                 nbscreen.riskScreens(testData, driver, extentReport);
                 NewQuoteScreens quotesPage = new NewQuoteScreens(driver, extentReport);
                 
                 quotesPage.addKeyCareAddOn(driver, extentReport);
              //   String addOn1Value = quotesPage.getAddOnValue(config.get("AddOn1"), driver, extentReport);
               //  driver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
                 quotesPage.saveQuote(driver, extentReport);
                 quotesPage.switchOutOfForm(driver);

                 PremiumDisplay premiumPage = new PremiumDisplay(driver, extentReport);
                 Log.softAssertThat(premiumPage.verifyPremiumPage(),
                     "Summary of Cover Page Successfully loaded", "Summary of cover page not loaded", driver,
                     extentReport, true);
                 premiumPage.selectPayNow(driver, extentReport);
                 String insurerName = premiumPage.getInsurerName(driver, extentReport);
                 dynamicHashMap.put("Insurer Name", insurerName);
                 String AddOninsurerName = premiumPage.getInsurerName(driver, extentReport);
                 dynamicHashMap.put("AddOn Insurer Name", AddOninsurerName);
                 premiumPage.confirmQuote(driver, extentReport);
                 CollectionScreen collecton = new CollectionScreen(driver, extentReport);
                 collecton.enterDetailsForPayNow(testData, driver, extentReport, partCollection);
                 Log.softAssertThat(premiumPage.verifyTransactionPage(),
                     "Transaction Confirmation Page successfully loaded",
                     "Transaction Confirmation Page not loaded", driver, extentReport, true);
                 String policyNo = premiumPage.storePolicyRef(driver, extentReport);
                 dynamicHashMap.put("policyNo", policyNo);
                 premiumPage.clickYesDocuments(driver, extentReport);
                 
                 if (dynamicHashMap.get("policyNo") != null)
                   Log.pass("Policy successfully created" + dynamicHashMap.get("policyNo"),
                       driver, extentReport);
                 
                
            } 
            catch (Exception e) {
                Log.exception(e, driver, extentReport);
                
            }          

      }
}
