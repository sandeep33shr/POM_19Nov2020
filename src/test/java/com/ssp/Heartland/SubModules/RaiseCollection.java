package com.ssp.Heartland.SubModules;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.DataUtils;
import com.ssp.uxp_HeartlandPages.CollectionScreen;
import com.ssp.uxp_HeartlandPages.HomePage;

public class RaiseCollection extends BaseTest {
  
  @Override
  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = "Heartland_Modules_"+env;
    return DataUtils.testDatabyID(testcaseId, className);
  }
   
  public String raiseCollection(WebDriver driver,HashMap<String,String> testData, HashMap<String,String> dynamicHashMap, ExtentTest extentReport) throws Exception {
          
      String referenceSRP = null;
          
          try{
            HomePage homePage = new HomePage(driver, extentReport).get();
            
            homePage.navigateToCollection(driver, extentReport);
            
            CollectionScreen collectionScreenPage = new CollectionScreen(driver, extentReport).get();
            Log.softAssertThat(collectionScreenPage.verifyCollectionDetails(), "Successfully navigated to Collection Screen", 
                              "Not navigated to collection screen",driver, extentReport, true);
           
            collectionScreenPage.enterDetails(testData, dynamicHashMap.get("Annual Premium"), driver, extentReport);
            Log.softAssertThat(collectionScreenPage.verifyAllocationPage(), "Successfully navigated to Allocation Screen", 
                              "Not navigated to Allocation Screen",driver, extentReport, true);
            
            collectionScreenPage.markTransaction(dynamicHashMap.get("policyNo"),driver, extentReport);
            Log.softAssertThat(collectionScreenPage.verifyCollectionList(), "Successfully navigated to Collection List Screen",
                               "Not navigated to Collection Screen",driver,extentReport, true);
            referenceSRP =collectionScreenPage.readSRP(driver, extentReport);
          }  
          catch (Exception e) {
              Log.exception(e, driver, extentReport);
          }
           
          return referenceSRP;
  }
}
