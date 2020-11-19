package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class PaymentScreen extends LoadableComponent<PaymentScreen> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  

  @FindBy(css = "#ctl00_cntMainBody_btnAccount")
  WebElement btnFindAccount;

  @FindBy(css = "#ctl01_cntMainBody_txtName")
  WebElement fldAccountName;

  @FindBy(css = "#ctl01_cntMainBody_btnFindNow")
  WebElement btnFindNow;

  @FindBy(css = "#ctl01_cntMainBody_grdvFindAccount_ctl02_btnSelect")
  WebElement btnSelectAccount;

  @FindBy(css = "#ctl00_cntMainBody_btnOk")
  WebElement btnOk;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frame_TB;
  
 @FindBy(xpath = "//h1[contains(text(),'Payments')]")
  WebElement headerPayments;

  @FindBy(xpath = "//*[contains(text(),'Payment - Select Account')]")
  WebElement headerAccountSearch;
 
  @FindBy(css="#ctl00_cntMainBody_btnFindNow")
  WebElement btnFindPayments;
  
  @FindBy(css="#ctl00_cntMainBody_btnPay")
  WebElement btnPay;

  @FindBy(xpath="//*[contains(text(),'Payment Details')]")
  WebElement headerPaymentDetails;
  
  @FindBy(css="#ctl00_cntMainBody_PayNow_CollectionItem_GISLookup_MediaType")
  WebElement dropdownMediaType;
  
  @FindBy(css="#ctl00_cntMainBody_PayNow_CollectionItem_btnOk")
  WebElement btnOkPaymentDetails;
  
  @FindBy(css="#ctl00_cntMainBody_confirmYNC_btnYes")
  WebElement btnConfirmWarningYes;
  
  public PaymentScreen(WebDriver driver,ExtentTest report) {
    this.driver = driver;
    this.extentReport= report;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }

  new ElementLayer(driver);
  }

  @Override
  protected void isLoaded() throws Error {
    WaitUtils.waitForSpinner(driver);
    new WebDriverWait(driver, 30)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Payment screen not loaded")
        .until(ExpectedConditions.visibilityOf(headerAccountSearch));

  }

  /****
   * 
   * verify navigate to collection screen
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyPaymentSelectAccount() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerAccountSearch))

      throw new Exception("Payment- Select Account is not loaded");
    return true;
  }

  /****
   * verify navigate to allocation list screen
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyPayments() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerPayments))
      throw new Exception("Payments Screen is not loaded");
    return true;
  }
  
  public boolean verifyPaymentDetails() throws Exception{
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerPaymentDetails))
      throw new Exception("Payments Details is not loaded");
    return true;
  }
 
  /****
   * 
   * To searchInsurer
   * 
   * @param testdata
   * @param quotesResult
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void searchInsurer(HashMap<String, String> dynamicHashMap,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFindAccount, "Find Account", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frame_TB);
    Log.message("Switched in Iframe");
    UIInteraction.sendKeys(fldAccountName, "Account Name", dynamicHashMap.get("Insurer Name"),
        driver, extentReport, false);
    UIInteraction.click(btnFindNow, "Find Now", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();    
   
  }
  
  /****
   * 
   * To search Addon Insurer
   * 
   * @param testdata
   * @param quotesResult
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void searchInsurerByName(String insurerName,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFindAccount, "Find Account", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frame_TB);
    Log.message("Switched in Iframe");
    UIInteraction.sendKeys(fldAccountName,  "Account Name", insurerName, driver, extentReport, false);
    UIInteraction.click(btnFindNow, "Find Now", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();    
   
  }

  /****
   * To mark the transaction
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void markTransction(HashMap<String, String> dynamicHashMap, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    
    UIInteraction.click(btnFindPayments, "Find button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectPolicyCheckbox(dynamicHashMap.get("policyNo"), driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnPay, "Pay button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnConfirmWarningYes, "Confirm Yes", driver, extentReport, true);
  }
  
  /****
   * To mark the transaction when client is fully settled
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * author Sandeep.Sharma
   */
  public void markTransctionWhenClientFullySettled(HashMap<String, String> dynamicHashMap, WebDriver driver,
      ExtentTest extentReport,boolean clientFullySettled) throws Exception {
    
    UIInteraction.click(btnFindPayments, "Find button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectPolicyCheckbox(dynamicHashMap.get("policyNo"), driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnPay, "Pay button", driver, extentReport, clientFullySettled);
    if(clientFullySettled==false){
      WaitUtils.waitForSpinner(driver);
      UIInteraction.click(btnConfirmWarningYes, "Confirm Yes", driver, extentReport, true);
    }
    
  }

  public void enterPaymentDetails(HashMap<String, String> testdata, WebDriver driver, ExtentTest extentReport) throws Exception{
    
    UIInteraction.selectDropdownByVisibleText(dropdownMediaType, "Media Type", testdata.get("Media Type"), driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnOkPaymentDetails, "OK button Payments Detail", driver, extentReport, true);
  }

}
