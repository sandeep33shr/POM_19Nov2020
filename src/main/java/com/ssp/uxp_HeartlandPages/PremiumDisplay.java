package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.utils.heartland.Interactions;


public class PremiumDisplay extends LoadableComponent<PremiumDisplay> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;

  @FindBy(xpath = "//h1[contains(text(),'Summary of Cover')]")
  WebElement premiumDisplayTitle;

  @FindBy(css = "#ctl00_cntMainBody_MultiRisk1_grdvRisk_ctl02_lblInsurerName")
  WebElement txtInsurerName;
  
  @FindBy(css = "#ctl00_cntMainBody_MultiRisk1_grdvRisk_ctl03_lblInsurerName")
  WebElement txt1stAddOnInsurerName;

  @FindBy(xpath = "//*[@id='ctl00_cntMainBody_MultiRisk1_grdvRisk']//tr[2]//td[3]")
  WebElement txtAddOn1Name;

  @FindBy(css = "#ctl00_cntMainBody_btnBuy")
  WebElement btnConfirm;

  @FindBy(css = "#ctl00_cntMainBody_btnPrint")
  WebElement btnInviteRenewal;

  @FindBy(xpath = "//a[@title='Action Menu']")
  WebElement linkActionMenu;

  @FindBy(css = "#ctl00_cntMainBody_MultiRisk1_grdvRisk_ctl02_lnkbtnEdit")
  WebElement linkEdit;

  @FindBy(css = "#ctl00_cntMainBody_MultiRisk1_grdvRisk_ctl02_lnkbtnEditQuote")
  WebElement linkEditQuote;

  @FindBy(css = "#ctl00_cntMainBody_lblTransactionHeading")
  WebElement transactionConfirmationTitle;

  @FindBy(css = "#linkinfoSection")
  WebElement linkInfo;

  @FindBy(css = "#ctl00_SideInfo_ClientInfoCtrl_lblPolicyRef")
  WebElement txtPolicyRef;

  @FindBy(css = "#infoSection > div > div > div.p.p-h-md.bg-light > a")
  WebElement linkCloseInfo;

  @FindBy(css = "button.yes")
  WebElement btnYes;

  @FindBy(css = "button.no")
  WebElement btnNo;

  @FindBy(css = "#ctl00_cntMainBody_RenewalWhatIf_btnWhatIf")
  WebElement btnWhatIf;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frame_TB;

  @FindBy(css = "#ctl00_cntMainBody_ddlWhatIf")
  WebElement dropdownWhatIfReason;

  @FindBy(css = "#ctl00_cntMainBody_txtDescription")
  WebElement fldWhatIfDescription;

  @FindBy(css = "#ctl00_cntMainBody_btnCreate")
  WebElement btnCreate;

  @FindBy(css = "#ctl00_cntMainBody_btnCancel")
  WebElement btnCancel;


  @FindBy(xpath = "//td[contains(text(),'Test_Addon')]")
  WebElement addOnTextInTable;


  @FindBy(css = "#ctl00_cntMainBody_confirmYNC_btnYes")
  WebElement btnYesOnPopUp;

  // End Akshay

  // Manisha
  @FindBy(xpath = "//*[@id=\"ctl00_cntMainBody_btnSaveQuote\"]")
  WebElement btnSaveQuote;

  @FindBy(xpath = "//*[@id=\"lipolicies\"]/a")
  WebElement policyNavigator;
  // End Manisha
  
  @FindBy(css = "#ctl00_cntMainBody_rblPaymentMethods_1")
  WebElement payNow;
  
  @FindBy(css = "#ctl00_cntMainBody_rblPaymentMethods_0")
  WebElement invoice;

  
 public String riskTableDetails = "//*[@id='ctl00_cntMainBody_MultiRisk1_grdvRisk']//tr";



  public PremiumDisplay(WebDriver driver, ExtentTest report) {
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
      Log.fail("Unable to load the quote result page", driver, extentReport);
    }
    new ElementLayer(driver);

  }

  @Override
  protected void isLoaded() throws Error {
    isPageLoaded = true;

  }

  /****
   * Method to verify premium page is loaded
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyPremiumPage() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, premiumDisplayTitle))
      throw new Exception("Premium Display page  is not loaded");
    return true;

  }

  /*****
   * Method to perform click on Confirm quote button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void confirmQuote(WebDriver driver, ExtentTest extentReport) throws Exception {

    WaitUtils.waitForelementToBeClickable(driver, btnConfirm, "Element now clickable");
    UIInteraction.clickConfirmQuote(btnConfirm, "Confirm Button", driver, extentReport, true);

  }
/****
 * Method to click invite renewal button
 * @param driver
 * @param extentReport
 * @throws Exception
 */
  public void inviteRenewal(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnInviteRenewal, "Invite Renewal", driver, extentReport, true);


  }
/****
 * Method to read in Insurer Name for policy
 * 
 * @param driver
 * @param extenReport
 * @return
 * @throws Exception
 */
  public String getInsurerName(WebDriver driver, ExtentTest extenReport) throws Exception {

    return (UIInteraction.getText(txtInsurerName, "Insurer Name", driver, extenReport, true));
  }
  /****
   * Method to read in 1st Addon Insurer Name for policy
   * 
   * @param driver
   * @param extenReport
   * @return
   * @throws Exception
   */
    public String getAddOnInsurerName(WebDriver driver, ExtentTest extenReport) throws Exception {

      return (UIInteraction.getText(txt1stAddOnInsurerName, "Add-On Insurer Name", driver, extenReport, true));
    }
  /***
   * to click on Edit quote link
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void editQuote(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkActionMenu, "Action Menu", driver, extentReport, true);
    UIInteraction.click(linkEditQuote, "Edit Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  public String getAddOnName(WebDriver driver, ExtentTest extentReport) throws Exception {

    return (UIInteraction.getText(txtAddOn1Name, "Add On 1 Name", driver, extentReport, true));
  }



  public boolean verifyAddOnName(String addOnName, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    return UIInteraction.getText(txtAddOn1Name, "Add On Name", driver, extentReport, true)
        .equalsIgnoreCase(addOnName);

  }

  /****
   * 
   * to verify transaction confirmation page is loaded.
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyTransactionPage() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, transactionConfirmationTitle))
      throw new Exception("Transaction Confirmation page  is not loaded");
    return true;

  }

  /****
   * To store the policy ref no.
   * 
   * @param driver
   * @param screenshot
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public String storePolicyRef(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkInfo, "Info Section", driver, extentReport, false);
    String policyNo = UIInteraction.getText(txtPolicyRef, "Policy Ref", driver, extentReport, true);
    UIInteraction.click(linkCloseInfo, "Close Info Section", driver, extentReport, false);
    return policyNo;

  }

  /***
   * click yes button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */


  public void clickYesDocuments(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(btnYes, "Yes Button", driver, extentReport, true);

  }
/****
 * Method to create what-if quote
 * 
 * @param driver
 * @param extentReport
 * @param testData
 * @throws Exception
 * @author Shweta.Saigal
 */
  public void createWhatIfQuote(WebDriver driver, ExtentTest extentReport,
      HashMap<String, String> testData) throws Exception {
    UIInteraction.click(btnWhatIf, "What if button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frame_TB);
    UIInteraction.selectDropdownByIndex(dropdownWhatIfReason, "What if reason",
        testData.get("WhatIf Reason"), driver, extentReport, false);
    UIInteraction.sendKeys(fldWhatIfDescription, "What if description",
        testData.get("WhatIf Description"), driver, extentReport, true);
    UIInteraction.click(btnCreate, "Create button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();

    Thread.sleep(2000);



  }

  public void confirmQuotePopUp(WebDriver driver, ExtentTest extentReport) throws Exception {

    WaitUtils.waitForelementToBeClickable(driver, btnConfirm, "Element now clickable");
    UIInteraction.clickConfirmQuote(btnYesOnPopUp, "Confirm Button", driver, extentReport, true);

  }

  /****
   * 
   * Click on confirm quote includes the handling of the alert
   * 
   * @param driver
   * @param input -> need to pass the alert pop up message
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void getTextOfAlertMessage(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {

    WaitUtils.waitForelementToBeClickable(driver, btnConfirm, "Element now clickable");
    UIInteraction.getTextOfAlertAndAcceptAlert(btnConfirm, input, "Confirm Button", driver,
        extentReport, true);
  }

  // public void getTextOfAlertMessage(WebDriver driver, ExtentTest extentReport) throws Exception {
  //
  // WaitUtils.waitForelementToBeClickable(driver, btnConfirm, "Element now clickable");
  // UIInteraction.getTextOfAlertAndAcceptAlert(btnConfirm, "Confirm Button", driver, extentReport,
  // true);
  //
  //
  // }

  public boolean verifyAddedAddOn() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, addOnTextInTable))
      throw new Exception("Add on added in policy");
    return true;

  }
/****
 * Method to click edit in Action menu
 * 
 * @param driver
 * @param extentReport
 * @throws Exception
 */
  public void edit(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(linkActionMenu, "Action Menu", driver, extentReport, true);
    UIInteraction.click(linkEdit, "Edit", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

  }

  /***
   * Method to click on SAVE QUOTE button on SOC screen
   * 
   * @author Manisha.Sharma
   */
  public void clickOnSaveQuoteOnSOC(WebDriver driver, ExtentTest extentReport) throws Exception {

    WaitUtils.waitForelementToBeClickable(driver, btnSaveQuote, "Element now clickable");
    UIInteraction.click(btnSaveQuote, "Save Quote Button", driver, extentReport, false);
    UIInteraction.click(policyNavigator, "Quotes/Policy page", driver, extentReport, false);

  }

  /****
   * Verify if the what-if button is disabled.
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyWhatIfButtonDisabled(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    return (UIInteraction.validateDisabledButton(btnWhatIf, "What if button", driver, extentReport)
        .equalsIgnoreCase("true"));
  }

  /****
   * Verify if the invite renewal button is disabled.
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyInviteRenewalButtonDisabled(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    return (UIInteraction
        .validateDisabledButton(btnInviteRenewal, "Invite renewal button", driver, extentReport)
        .equalsIgnoreCase("true"));
  }
  /****
   * Method to get table data
   *  
   * @param tableRowsLocator
   * @param columnOneName
   * @param columnOneValue
   * @param columnTwoName
   * @param expectedValue
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public String getTableData(String tableRowsLocator, String columnOneName, String columnOneValue,
      String columnTwoName, String expectedValue, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    return Interactions.getTableData(tableRowsLocator, columnOneName, columnOneValue, columnTwoName,
        expectedValue, driver, extentReport);
    
      }
  /****
   * Method to select Pay Now
   *  
   * @param locator
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Sandeep.sharma
   */
  public void selectPayNow(WebDriver driver, ExtentTest extentReport)
      throws Exception {

   UIInteraction.clickRadioButton(payNow, "click Paynow", driver, extentReport, false);
  }
  
  /****
   * Method to select invoice
   *  
   * @param locator
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Sandeep.sharma
   */
  public void selectInvoice(WebDriver driver, ExtentTest extentReport)
      throws Exception {

   UIInteraction.clickRadioButton(invoice, "click invoice", driver, extentReport, false);
   
  }

}



