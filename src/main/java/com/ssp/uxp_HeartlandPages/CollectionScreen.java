package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
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
import com.ssp.utils.heartland.Interactions;

public class CollectionScreen extends LoadableComponent<CollectionScreen> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  

  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_btnAccount")
  WebElement btnFindAccount;

  @FindBy(css = "#ctl01_cntMainBody_txtName")
  WebElement fldAccountName;

  @FindBy(css = "#ctl01_cntMainBody_btnFindNow")
  WebElement btnFindNow;

  @FindBy(css = "#ctl01_cntMainBody_grdvFindAccount_ctl02_btnSelect")
  WebElement btnSelectAccount;

  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_GISLookup_MediaType")
  WebElement dropdownMediaType;

  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_txtAmount")
  WebElement fldAmount;

  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_CollectionItem_BankAccount")
  WebElement dropdownBankAccount;

  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_btnOk")
  public WebElement btnOk;
  
  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_btnCancel")
  public WebElement btnCancel;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frameTB;

  @FindBy(xpath = "//h1[contains(text(),'Allocation')]")
  WebElement headerAllocationPage;

  @FindBy(xpath = "//*[contains(text(),'Collection Details')]")
  WebElement headercollectionScreen;
  
  @FindBy(xpath = "//*[contains(text(),'Payment Details')]")
  WebElement headerPaymentScreen;
  
  @FindBy(css = "#ctl00_cntMainBody_CollectionAllocation_btnOk")
  public WebElement btnOkAllocation;

  @FindBy(xpath = "//*[contains(text(),'Collection List')]")
  WebElement headerCollectionList;

  @FindBy(
      css = "#ctl00_cntMainBody_CollectionItems_gvCollectionItems > table > tbody > tr > td:nth-child(1)")
  public WebElement txtSRPReference;

  @FindBy(css = "#ctl00_cntMainBody_CollectionItems_btnOK")
  WebElement btnOkCollectionList;
  
  @FindBy (css="a#ctl00_cntMainBody_CollectionAllocation_confirmYNC_btnYes")
  WebElement btnYesOnUnallocatedTransactionsPopUp;
  
  @FindBy(css = "#ctl00_cntMainBody_PayNow_CollectionItem_txtAmount")
  WebElement txtfldAmount;
  
  @FindBy(css = "a#ctl00_cntMainBody_CollectionItems_confirmYNC_btnYes")
  public WebElement btnConfirmationYes;
  
  
  
  public CollectionScreen(WebDriver driver,ExtentTest report) {
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
        .withMessage("Collection screen not  loaded")
        .until(ExpectedConditions.visibilityOf(headercollectionScreen));

  }

  /****
   * 
   * verify navigation to collection screen
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyCollectionDetails() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headercollectionScreen))

      throw new Exception("Collection Details Screen is not loaded");
    return true;
  }
  /****
   * 
   * verify navigation to Payment Details screen
   * 
   * @return
   * @throws Exception
   * author Sandeep.Sharma
   */
  public boolean verifyPaymentDetails() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerPaymentScreen))

      throw new Exception("Payment Details Screen is not loaded");
    return true;
  }
  /****
   * verify navigation to allocation list screen
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyAllocationPage() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerAllocationPage))

      throw new Exception("Allocation Details page is not loaded");
    return true;
  }

  /****
   * verify navigation to collection list screen.
   * @return
   * @throws Exception
   */
  public boolean verifyCollectionList() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerCollectionList))

      throw new Exception("Collection List is not loaded");
    return true;
  }

  /****
   * 
   * To populate details on Collection Screen
   * 
   * @param testdata
   * @param quotesResult
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void enterDetails(HashMap<String, String> testdata, String amount,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFindAccount, "Find Account", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frameTB);
    Log.message("Switched in Iframe");
    UIInteraction.sendKeys(fldAccountName, "Account Name", testdata.get("Client Name"),
        driver, extentReport, false);
    UIInteraction.click(btnFindNow, "Find Now", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();
    UIInteraction.selectDropdownByVisibleText(dropdownMediaType, "Media Type",
        testdata.get("Media Type"), driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.sendKeysViaActionClass(fldAmount, "Amount", amount, driver,
        extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownBankAccount, "Bank Account", testdata.get("Bank"), driver, extentReport, true);
    UIInteraction.click(btnOk, "Ok Button", driver, extentReport, true);
    
  

  }

  /****
   * To mark the transaction
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void markTransaction(String reference, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    UIInteraction.selectPolicyCheckbox(reference, driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnOkAllocation, "OK Button Allocation", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * to read SRP reference from Collection List Screen and save it.
   * 
   * @return
   * @throws Exception
   */
  public String readSRP(WebDriver driver, ExtentTest extentReport) throws Exception {

    String reference = null;
    WaitUtils.waitForSpinner(driver);
    reference = UIInteraction.getText(txtSRPReference, "SRP Reference", driver, extentReport,true);
    UIInteraction.click(btnOkCollectionList, "OK Button Collection List", driver, extentReport,true);

    return reference;
  }

  /***
   * Getting half value of an amount
   * @param amount
   * @return
   * @author sunny.kumar
   */
  public String getHalfAmountOfValue(String amount) {
    return Interactions.getHalfOfAmountValue(amount);
  }
  
  /****
   * To click on Allocation button
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnOkAllocationButton(WebDriver driver,
      ExtentTest extentReport) throws Exception {
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnOkAllocation, "OK Button Allocation", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * To click on Yes button on Unallocated Transactions PopUp
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnYesOnUnallocatedTransactionsPopUp(WebDriver driver,
      ExtentTest extentReport) throws Exception {
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnYesOnUnallocatedTransactionsPopUp, "OK Button Allocation", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }
  
  
  
  /****
   * 
   * To populate details on Collection Screen for subAgent
   * 
   * @param testdata
   * @param quotesResult
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public void enterDetails(HashMap<String, String> testdata, String amount,String thirdParty,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFindAccount, "Find Account", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frameTB);
    Log.message("Switched in Iframe");
    UIInteraction.sendKeys(fldAccountName, "Account Name", thirdParty,
        driver, extentReport, false);
    UIInteraction.click(btnFindNow, "Find Now", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();
    UIInteraction.selectDropdownByVisibleText(dropdownMediaType, "Media Type",
        testdata.get("Media Type"), driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.sendKeysViaActionClass(fldAmount, "Amount", amount, driver,
        extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownBankAccount, "Bank Account", testdata.get("Bank"), driver, extentReport, true);
    UIInteraction.click(btnOk, "Ok Button", driver, extentReport, true);
    
  

  }
  
  /****
   * 
   * To populate details on Collection Screen using PayNow
   * 
   * @param testdata
   
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public void enterDetailsForPayNow(HashMap<String, String> testdata,
      WebDriver driver, ExtentTest extentReport,boolean partCollection) throws Exception {
       UIInteraction.selectDropdownByVisibleText(dropdownMediaType, "Media Type",
        testdata.get("Media Type"), driver, extentReport, false);
         WaitUtils.waitForSpinner(driver);
        UIInteraction.selectDropdownByVisibleText(dropdownBankAccount, "Bank Account", testdata.get("Bank"), driver, extentReport, true);
        if(partCollection==true){
          String amountValue= UIInteraction.getAttribute(fldAmount, "value", "Amount", driver, extentReport, false);
           String amountValue1= amountValue.replaceAll(",", " ").trim();
           String finalAmountString= Interactions.getHalfOfAmountValue(amountValue1);
         Double convertAmountDouble = Double.parseDouble(amountValue1);
           UIInteraction.sendKeys(fldAmount, "Amount field",(Keys.CONTROL + "a"), driver, extentReport, false);
        fldAmount.sendKeys(Keys.DELETE);
        UIInteraction.sendKeys(fldAmount, "Amount field",finalAmountString, driver, extentReport, false);
        }
      
    UIInteraction.click(btnOk, "Ok Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
     }
  
  public double getAmount(WebDriver driver , ExtentTest extentReport){
    
    double amount=0.00;
    
    try {
      amount = Double.parseDouble(
          UIInteraction.getValue(fldAmount, "Amount", driver, extentReport, false));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return amount;
    
  }
  
  public void cancelScreen(WebDriver driver , ExtentTest extentReport){
    
    btnCancel.click();
    WaitUtils.waitForSpinner(driver);
  }
 

}
