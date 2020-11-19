package com.ssp.uxp_HeartlandPages;

import java.awt.Desktop.Action;
import java.util.HashMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;

public class MTAReason extends LoadableComponent<MTAReason>{
  public static HashMap<String, String> dynamicHashMap = new HashMap<>();
  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  
  
  @FindBy(xpath="//td[contains(text(),'Change')]/following-sibling::td//input")
  WebElement radiobtnRiskChange;
  
  @FindBy(xpath="//td[contains(text(),'Cancellation')]/following-sibling::td//input")
  WebElement radiobtnCancel;
  
  @FindBy(css="#ctl00_cntMainBody_txtMTATransactionReason")
  WebElement fldTransactionReason;
  
  @FindBy(css="#ctl00_cntMainBody_btnSubmit")
  WebElement btnSubmit;
    
  @FindBy(css="#ctl00_cntMainBody_lblPageInfo")
  WebElement txtPageLabel;
  
  @FindBy(xpath="//input[@id='ctl00_cntMainBody_txtEffectiveDate']")
  WebElement mtaEffectiveDate;
  
  

  
  public MTAReason(WebDriver driver, ExtentTest report){
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);
  }
  
  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !driver.getTitle().contains("SSP - Pure Insurance")) {
      Log.fail("Unable to load the new quote screens", driver, extentReport);
    }
   new ElementLayer(driver);
    
  }

  @Override
  protected void isLoaded() throws Error {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
   
  }
  
  public boolean verifyMtaLoad()throws Exception {
    WaitUtils.waitForSpinner(driver);
    if(!WaitUtils.waitForElement(driver, txtPageLabel))
      throw new Exception("MTA reasons page not loaded");
    
    return true;
  }
  
  public void selectRiskChange(HashMap<String, String> testdata,WebDriver driver, ExtentTest extentReport) throws Exception {
    
    UIInteraction.clickRadioButton(radiobtnRiskChange, "Risk Change", driver, extentReport,false);
   // UIInteraction.sendKeys(fldTransactionReason, "Transaction Reason", testdata.get("Reason").toString(), driver, extentReport,false);
    Thread.sleep(3000);
   // btnSubmit.click();
    Thread.sleep(3000);
   // UIInteraction.click(btnSubmit, "Submit Button", driver, extentReport,true);

    UIInteraction.sendKeys(fldTransactionReason, "Transaction Reason", testdata.get("Reason"), driver, extentReport,false);
    UIInteraction.click(btnSubmit, "Submit Button", driver, extentReport,true);
     
  }
  
 public void selectCancellation(HashMap<String, String> testdata,WebDriver driver, ExtentTest extentReport) throws Exception {
    
    UIInteraction.clickRadioButton(radiobtnCancel, "Cancellation", driver, extentReport,false);

    UIInteraction.sendKeys(fldTransactionReason, "Transaction Reason", testdata.get("Reason").toString(), driver, extentReport,false);
    Thread.sleep(3000);

    UIInteraction.sendKeys(fldTransactionReason, "Transaction Reason", testdata.get("Reason"), driver, extentReport,false);

    UIInteraction.click(btnSubmit, "Submit Button", driver, extentReport,true);
     
  }
 
 public void enterMTADateBeforeCoverDate(WebDriver driver) throws Exception {
   mtaEffectiveDate.clear();
   UIInteraction.sendKeysViaActionClass(mtaEffectiveDate, "Before cover date", ViewClientDetails.dynamicHashMap.get("beforeDate"), driver, extentReport, true);
 }
  
 public boolean isAlertPresent()throws Exception {
     Alert alert = driver.switchTo().alert();
     if (alert.getText() != null) {
         Log.message("Alert present");
     }
    return true;
 }
 
 public void clickOnSubmit(WebDriver driver, ExtentTest extentReport) throws Exception {
   /*try {
     UIInteraction.click(btnSubmit, "Submit Button", driver, extentReport, true);
     WebDriverWait wait = new WebDriverWait(driver, 5);
     wait.until(ExpectedConditions.alertIsPresent());
     Alert alert = driver.switchTo().alert();
     System.out.println(alert.getText());
     alert.accept();
     Assert.assertTrue(alert.getText().contains("ERROR: Invalid login credentials."));
     } catch (Exception e) {
     System.out.println("Alert is not displayed");
     }*/
   Actions action = new Actions(driver);
   action.moveToElement(btnSubmit).click().build().perform();
   btnSubmit.click();
   Log.message("Click performed " + "Submit button", driver, extentReport, false);
 }

}
