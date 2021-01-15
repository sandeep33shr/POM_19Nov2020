package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
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

public class TransactionScreen extends LoadableComponent<TransactionScreen> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;



  @FindBy(xpath = "//h1[contains(text(),'Search Transaction')]")
  WebElement headerSearchTransaction;

  @FindBy(css = "#ctl00_cntMainBody_btnAccountCode")
  WebElement btnFindAccount;

  @FindBy(css = "#ctl01_cntMainBody_txtName")
  WebElement fldAccountName;

  @FindBy(css = "#ctl01_cntMainBody_btnFindNow")
  WebElement btnFindNow;

  @FindBy(xpath = "//*[@id='ctl01_cntMainBody_grdvFindAccount']/table//tr[1]//td[1]")
  WebElement txtAccountCode;

  @FindBy(css = "#ctl01_cntMainBody_grdvFindAccount_ctl02_btnSelect")
  WebElement btnSelectAccount;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frame_TB;

  @FindBy(css = "#ctl00_cntMainBody_chkShowOutStandingTransaction")
  WebElement checkBoxOutStandingTransaction;

  // Tab Reference
  @FindBy(css = "a[href='#tab-Reference']")
  WebElement tabReference;

  @FindBy(css = "#ctl00_cntMainBody_txtPolicyNumber")
  WebElement fldPolicyNo;

  // Tab Transaction
  @FindBy(css = "a[href='#tab-Document']")
  WebElement tabTransaction;

  @FindBy(css = "#ctl00_cntMainBody_txtDocumentRef")
  WebElement fldTransactionRef;

  @FindBy(css = "#ctl00_cntMainBody_btnFindNow")
  public WebElement btnFind;

  @FindBy(css = "a#ctl00_cntMainBody_btnNewsearch")
  WebElement btnClear;

  @FindBy(xpath = "//a[contains(text(),'View')]")
  WebElement linkView;

  @FindBy(xpath = "//a[contains(text(),'Drill Transaction')]")
  WebElement linkDrillTransaction;

  // CTV Screen traversing
  @FindBy(css = "a[href='#tabClientSubAgent']")
  WebElement tabClientSubAgent;

  @FindBy(css = "#ctl00_cntMainBody_txtPremium")
  WebElement txtPremium;

  @FindBy(css = "#ctl00_cntMainBody_txtFees")
  WebElement txtFees;

  @FindBy(css = "#ctl00_cntMainBody_txtSubAgentCommPer")
  WebElement txtSubAgentComm;

  @FindBy(css = "#ctl00_cntMainBody_txtAmountDue")
  WebElement txtAmountDue;

  @FindBy(css = "a[href='#tab-Disbursement']")
  WebElement tabInsurerAddOn;

  @FindBy(css = "#ctl00_cntMainBody_txtAmountDueInsurer")
  WebElement txtAmountDueInsurer;

  @FindBy(css = "a[href='#tabdetails']")
  WebElement tabDetails;

  @FindBy(css = "#liIncomeHistory > a")
  WebElement tabIncome;

  @FindBy(css = "#ctl00_cntMainBody_txtCommission")
  WebElement txtCommission;

  @FindBy(css = "#ctl00_cntMainBody_txtNetIncome")
  WebElement txtNetIncome;

  /*
   * @FindBy( css =
   * "div.modal-header.bootstrap-dialog-draggable > div > div.bootstrap-dialog-close-button > button"
   * ) WebElement btnClose;
   */

  @FindBy(css = "#ctl00_cntMainBody_btnClose")
  WebElement btnClose;

  @FindBy(css = "#ctl00_cntMainBody_txtUnearnedCommission")
  WebElement txtUnearnedIncome;

  @FindBy(css = "#ctl00_cntMainBody_gvGetTransactiondetails")
  WebElement searchTransactionTable;

  @FindBy(xpath = "//a[@id='ctl00_cntMainBody_btnCancel']")
  WebElement closeBtn;

  @FindBy(css = "#ctl00_cntMainBody_ddlBranchCode")
  WebElement branchDropDown;

  @FindBy(css = "input#ctl00_cntMainBody_txtAccountCode")
  WebElement accountCode;

  @FindBy(css = "#ctl00_cntMainBody_gvWorkManager >table >tbody >tr >td:nth-child(8)")
  List<WebElement> columnNameType;


  @FindBy(css = "#ctl00_cntMainBody_txtAccountCode")
  WebElement textFieldAccountCode;

  @FindBy(xpath = "(//*[@title='Action Menu'])[1]")
  WebElement actionMenuFor1stTran;

  @FindBy(css = "#ctl00_cntMainBody_gvGetTransactiondetails_ctl02_lblhypViewAllocation")
  WebElement viewAllocationOption;

  @FindBy(css = "#ctl00_cntMainBody_gvCredits_ctl02_chkselectedTransactionCredit")
  WebElement checkboxCreditSection;

  @FindBy(css = "#ctl00_cntMainBody_gvDebit_ctl02_chkselectedTransactionDebit")
  WebElement checkboxDebitSection;

  @FindBy(css = "#ctl00_cntMainBody_confirmYNC_btnYes")
  WebElement buttonYesOnPopUp;

  @FindBy(css = "#ctl00_cntMainBody_txtDisbursements")
  WebElement txtDisbursements;

  @FindBy(
      xpath = "//a[@title='Action Menu' and @aria-expanded='true']/..//a[contains(@id,'lblhypViewAllocation')]")
  public WebElement linkViewAllocation;

  @FindBy(css = "#ctl00_cntMainBody_btnReverseAllocation")
  WebElement btnUndo;

  @FindBy(css = "ctl00_cntMainBody_btnHistory")
  WebElement btnReversed;

  @FindBy(css = "#ctl00_cntMainBody_confirmYNC_btnYes")
  WebElement btnYes;

  @FindBy(xpath = "//a[@id='ctl00_cntMainBody_btnCancel']")
  public WebElement btnCloseDrillDoc;

  @FindBy(css = "#ctl00_cntMainBody_txtUnearnedCommission")
  WebElement txtUnEarnedComission;

  @FindBy(css = "#ctl00_cntMainBody_txtToClearCommission")
  WebElement txtToClearComission;

  @FindBy(css = "#ctl00_cntMainBody_txtEarnedCommission")
  WebElement txtEarnedComission;

  @FindBy(css = "#ctl00_cntMainBody_txtUnearnedFee")
  WebElement txtUnEarnedFee;

  @FindBy(css = "#ctl00_cntMainBody_txtToClearFee")
  WebElement txtToClearFee;

  @FindBy(css = "#ctl00_cntMainBody_txtEarnedFee")
  WebElement txtEarnedFee;

  public String tblTransactionDetails =
      "//div[@id='ctl00_cntMainBody_gvGetTransactiondetails']//tr";

  public String tblDrillDocRefTransactions =
      "//div[@id='ctl00_cntMainBody_gvDocRefTransactions']//tr";

  @FindBy(css = "a[href='#tab-Amount']")
  WebElement tabAmount;

  @FindBy(css = "#ctl00_cntMainBody_CurrencyType")
  WebElement dropdownCurrency;


  @FindBy(xpath = "//*[@id='ctl00_cntMainBody_gvCredits']/table/tbody/tr/td[2]")
  WebElement transactionSRPNo;
  
  @FindBy(css = "#ctl00_cntMainBody_gvDebit td:nth-child(1)")
  WebElement transactionDebitNo;
  
  @FindBy(css = "#ctl00_cntMainBody_gvCredits td:nth-child(1)")
  WebElement transactionCreditNo;

  @FindBy(css = "#ctl00_cntMainBody_btnClose")
  WebElement btnViewScreenClose;

  @FindBy(css = "#ctl00_cntMainBody_gvGetTransactiondetails tr:nth-child(1) td:nth-child(4)")
  WebElement transRefColValOfSearchTrans;
  
  @FindBy(css = "#ctl00_cntMainBody_gvGetTransactiondetails tr:nth-child(1) td:nth-child(3)")
  WebElement accountCodeColValOfSearchTrans;
  
  @FindBy(css = "#ctl00_cntMainBody_gvGetTransactiondetails tr:nth-child(1) td:nth-child(11)")
  WebElement outstandingAmount;

  @FindBy(css = "#ctl00_cntMainBody_CommissionHistory")
  WebElement lnkCTVCommission;

  @FindBy(css = "#ctl00_cntMainBody_gvDebit td:nth-child(1)")
  List<WebElement> debitSectionTRefWHenUndoCHeckboxesNotDisplayed;

  @FindBy(css = "#ctl00_cntMainBody_gvCredits td:nth-child(1)")
  List<WebElement> creditSectionTRefWHenUndoCHeckboxesNotDisplayed;
  
  @FindBy(css = "#ctl00_cntMainBody_gvDebit td:nth-child(2)")
  WebElement debitSectionTRef;

  @FindBy(css = "a#ctl00_cntMainBody_confirmYNC1_btnYes")
  WebElement btnYesFundingWarning;

  @FindBy(xpath = "/html/body/form/div[3]/div/div[1]/div/div/div[4]/a[1]")
  WebElement btnYesReversalWarning;
  
  @FindBy(css = "#ctl00_cntMainBody_txtReconciledDate")
  WebElement txtReconciledDate;
  
  @FindBy(css = "#ctl00_cntMainBody_txtBankingDate")
  WebElement txtBankingDate;
  
  @FindBy(css = "a#ctl00_cntMainBody_hLinkAmountAllocated")
  WebElement lnkAmtAllocated;
  
  @FindBy(css = "#ctl00_cntMainBody_tabdetails label")
  List<WebElement> allLabelsDetailsTabCTV;
  
  @FindBy(css = "#ctl00_cntMainBody_lblBankingDate")
  WebElement lblBankingDate;
  
  public TransactionScreen(WebDriver driver, ExtentTest report) {

    this.driver = driver;
    this.extentReport = report;
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
        .withMessage("Search transaction screen not loaded")
        .until(ExpectedConditions.visibilityOf(headerSearchTransaction));

  }

  /***
   * Method to validate that search transaction screen is loaded successfully.
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyTransactionsScreen() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerSearchTransaction))

      throw new Exception("Search transaction screen is not loaded");
    return true;
  }

  /*****
   * 
   * to search the account code when client name is given and select that account.
   * 
   * @param driver
   * @param testData
   * @param quotesData
   * @param extentReport
   * @throws Exception
   */
  public void searchAccountViaClientName(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFindAccount, "Find Account button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frame_TB);
    UIInteraction.sendKeys(fldAccountName, "Account Name", testData.get("Client Name"), driver,
        extentReport, false);
    UIInteraction.click(btnFindNow, "Find now button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();
  }

  /*****
   * Method to uncheck the outstanding checkbox
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void uncheckOutstandingCheckBox(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickRadioButton(checkBoxOutStandingTransaction,
        "Uncheck show only outstanding transactions checkbox", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /*****
   * To search policy when we have the policy reference no
   * 
   * @param driver
   * @param quotesData
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void searchViaPolicyNo(WebDriver driver, String policyNo, ExtentTest extentReport)
      throws Exception {

    UIInteraction.click(tabReference, "Reference Tab", driver, extentReport, false);
    UIInteraction.sendKeys(fldPolicyNo, "Policy No", policyNo, driver, extentReport, true);
    UIInteraction.click(btnFind, "Find transactions", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  /******
   * Select action menu using reference
   * 
   * @param driver
   * @param reference
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */

  public void selectActionMenu(WebDriver driver, String reference, ExtentTest extentReport)
      throws Exception {
    UIInteraction.selectActionMenu(reference, driver, extentReport, true);


  }

  /****
   * Click view link in the action menu
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * 
   */
  public void viewTransaction(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkView, "View CTV", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * Click drill transaction in the action menu
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void drillTransaction(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkDrillTransaction, "Drill transaction", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * Method to traverse the drill transaction table
   * 
   * 
   * @param quotesData
   * @param label -- can be Client Code, Sub Agent Code or Insurer Code for which you need to
   *        traverse the column
   * @param columnHeader -- column header which you want to traverse
   * @param driver
   * @param extentReport
   * @return -- returns the value read from the column header against the AccountCode specified.
   * @throws Exception
   */
  public String traverseDrillTable(HashMap<String, String> quotesData, String label,
      String columnHeader, WebDriver driver) {

    int index = 0;
    int i = 0;
    String cellTextLocator = null;
    String tableLocator = "//table[@class ='grid']";
    WebElement table = driver.findElement(By.xpath(tableLocator));

    table.findElements(By.tagName("tr"));
    List<WebElement> columns = table.findElements(By.tagName("th"));
    for (i = 0; i < columns.size(); i++) {
      if (columns.get(i).getText().equalsIgnoreCase(columnHeader)) {
        index = i - 1;
        break;
      }

    }
    if (label.equalsIgnoreCase("GROSS")) {
      cellTextLocator =
          "//table[@class ='grid']//td[contains(text(),'".concat(quotesData.get(label))
              .concat("')]/following-sibling::td[").concat(Integer.toString(index - 1)).concat("]");
    } else
      cellTextLocator =
          "//table[@class ='grid']//td[contains(text(),'".concat(quotesData.get(label))
              .concat("')]/following-sibling::td[").concat(Integer.toString(index)).concat("]");
    WebElement cell = driver.findElement(By.xpath(cellTextLocator));
    return cell.getText();
  }

  /*****
   * Method to traverse the CTV screen
   * 
   * @param label -- the field you wish to read value from
   * @param tabHeader -- the tab on which that field is present - Client/Sub Agent, Insurer,
   *        Details, Income
   * @param driver
   * @param extentReport
   * @return -- returns the attribute value from read from that field.
   * @throws Exception
   */
  public String traverseCTVScreen(String label, String tabHeader, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    String value = null;

    switch (tabHeader) {
      case "Client/SubAgent":
        UIInteraction.click(tabClientSubAgent, "Client/Sub Agent Tab", driver, extentReport, false);

        if (label.equalsIgnoreCase("Premium"))
          value = UIInteraction.getValue(txtPremium, "Premium", driver, extentReport, true);
        else if (label.equalsIgnoreCase("Amount Due"))
          value = UIInteraction.getValue(txtAmountDue, "Amount Due", driver, extentReport, true);
        else if (label.equalsIgnoreCase("Fees"))
          value = UIInteraction.getValue(txtFees, "Fees", driver, extentReport, true);
        else if (label.equalsIgnoreCase("Sub Agent Comm"))
          value =
              UIInteraction.getValue(txtSubAgentComm, "Sub Agent Comm", driver, extentReport, true);
        break;

      case "Insurer/AddOn/Disbursement":
        UIInteraction.click(tabInsurerAddOn, "Insurer/AddOn/Disbursement Tab", driver, extentReport,
            false);

        if (label.equalsIgnoreCase("Amount Due Insurer"))
          value = UIInteraction.getValue(txtAmountDueInsurer, "Amount due insurer", driver,
              extentReport, true);

        break;
      case "Details":
        UIInteraction.click(tabDetails, "Details Tab", driver, extentReport, false);
        if (label.equalsIgnoreCase("Reconciled Date"))
          value = UIInteraction.getValue(txtReconciledDate, "Reconciled Date", driver, extentReport, true); 
        if (label.equalsIgnoreCase("Banking Date"))
          value = UIInteraction.getValue(txtBankingDate, "Banking Date", driver, extentReport, true);
               
        break; 

      case "Income":
        UIInteraction.click(tabIncome, "Income tab", driver, extentReport, false);

        if (label.equalsIgnoreCase("Commission"))
          value = UIInteraction.getValue(txtCommission, "Commission", driver, extentReport, true);
        else if (label.equalsIgnoreCase("Net Income"))
          value = UIInteraction.getValue(txtNetIncome, "Net Income", driver, extentReport, true);
        else if (label.equalsIgnoreCase("Disbursement"))
          value =
              UIInteraction.getValue(txtDisbursements, "Disbursement", driver, extentReport, true);
        else if (label.equalsIgnoreCase("UnEarned Commission"))
          value = UIInteraction.getValue(txtUnEarnedComission, "UnEarned Commission", driver,
              extentReport, true);
        else if (label.equalsIgnoreCase("ToClear Commission"))
          value = UIInteraction.getValue(txtToClearComission, "ToClear Commission", driver,
              extentReport, true);
        else if (label.equalsIgnoreCase("Earned Commission"))
          value = UIInteraction.getValue(txtEarnedComission, "Earned Commission", driver,
              extentReport, true);
        else if (label.equalsIgnoreCase("UnEarned Fee"))
          value =
              UIInteraction.getValue(txtUnEarnedFee, "UnEarned Fee", driver, extentReport, true);
        else if (label.equalsIgnoreCase("ToClear Fee"))
          value = UIInteraction.getValue(txtToClearFee, "ToClear Fee", driver, extentReport, true);
        else if (label.equalsIgnoreCase("Earned Fee"))
          value = UIInteraction.getValue(txtEarnedFee, "Earned Fee", driver, extentReport, true);
        break;
      default:
        Log.message("Incorrect tab header please check ");
    }

    return value;

  }

  /***
   * Assert if the checkbox is selected
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   */
  public boolean onlyOutstandingTransactionCheckbox(HashMap<String, String> testData,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    return UIInteraction.validateCheckBox(checkBoxOutStandingTransaction, driver,
        testData.get("Only outstanding checkbox"), extentReport);

  }

  /****
   * 
   * Click performed on find button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void performSearch(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickRadioButton(btnFind, "Find transactions", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /***
   * Switching to 'TB_iframeContent' frame on CTV screen
   * 
   * @param driver
   * 
   */
  public void switchToCTVScreen(WebDriver driver) {
    driver.switchTo().frame(frame_TB);
    WaitUtils.waitForSpinner(driver);
  }


  public void switchOutOfCTVScreen(WebDriver driver, ExtentTest extentReport) throws Exception {
    driver.switchTo().defaultContent();
    // UIInteraction.click(btnClose, "Close CTV Screen", driver, extentReport, true);
  }


  /****
   * Click view link in the action menu
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author sunny.kumar
   */
  public void viewAllocation(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(linkViewAllocation, "View Allocation", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * To mark transaction and click on Un-Do button on View Allocation Screen
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author sunny.kumar
   */
  public void markTransactionAndClickOnUnDoButton(String reference, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    UIInteraction.selectPolicyCheckbox(reference, driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnUndo, "UN-DO Button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnYes, "YES Button", driver, extentReport, true);
    if (btnYes.isDisplayed()) {
      UIInteraction.click(btnYes, "YES Button", driver, extentReport, true);
    }
    driver.switchTo().defaultContent();
    WaitUtils.waitForSpinner(driver);
  }
  
  /****
   * To mark transaction and click on Un-Do button with reversal warning message on View Allocation Screen
   * 
   * @param quotesData
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public void markTransactionAndClickOnUnDoButtonPostIncomeEarning(String reference, WebDriver driver,
      ExtentTest extentReport,boolean isFunded) throws Exception {
    UIInteraction.selectPolicyCheckbox(reference, driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnUndo, "UN-DO Button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    if(isFunded==true){
    UIInteraction.click(btnYesFundingWarning, "YES Button Funding Warning", driver, extentReport, true);
    }
    UIInteraction.click(btnYesReversalWarning, "YES Button Reversal Warning", driver, extentReport, true);
    driver.switchTo().defaultContent();
    WaitUtils.waitForSpinner(driver);
  }


  /***
   * Getting half value of an amount
   * 
   * @param amount
   * @return
   * @author sunny.kumar
   */
  public String getHalfAmountOfValue(String amount) {
    return Interactions.getHalfOfAmountValue(amount);
  }

  /****
   * method to select action menu on any page
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author sunny.kumar
   */
  public String getOSAmountOfATransactionOnSearchTransactionPageBasedOnPolicyNumber(String policyNo,
      WebDriver driver, ExtentTest extentReport) throws Exception {

    try {
      String osAmountLocator =
          "//table//td[contains(text(),'" + policyNo + "')]/following-sibling::td[3]";
      WebElement osAmount = driver.findElement(By.xpath(osAmountLocator));
      return UIInteraction.getText(osAmount, "OS Amount", driver, extentReport, true);
    } catch (Exception e) {
      throw new Exception("Error while selecting Action Menu " + e);
    }
  }

  /***
   * This method is used to get a column data from a table
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
   * @author sunny.kumar
   */
  public String getTableData(String tableRowsLocator, String columnOneName, String columnOneValue,
      String columnTwoName, String expectedValue, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    return Interactions.getTableData(tableRowsLocator, columnOneName, columnOneValue, columnTwoName,
        expectedValue, driver, extentReport);
  }

  public void enterTextInAccountCode(WebDriver driver, HashMap<String, String> testData,
      ExtentTest extentReport) throws Exception {

    UIInteraction.sendKeys(textFieldAccountCode, "Account Code",
        testData.get("Client Name").toString(), driver, extentReport, false);

    UIInteraction.click(checkBoxOutStandingTransaction,
        "Uncheck show only outstanding transactions checkbox", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnFind, "Find transactions", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);


  }

  /****
   * To mark the transaction
   * 
   * @param reference
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void markTransaction(String reference, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.selectPolicyCheckbox(reference, driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /***
   * Search the Transaction using SubAgent
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Sakshi
   */
  public void searchAccountViaSubAgent(WebDriver driver, HashMap<String, String> testData,
      HashMap<String, String> quotesData, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnFindAccount, "Find Account button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frame_TB);

    UIInteraction.sendKeys(fldAccountName, "Account Name", ViewClientDetails.thirdParty, driver,
        extentReport, false);
    UIInteraction.click(btnFindNow, "Find now button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    quotesData.put("Client Code",
        UIInteraction.getText(txtAccountCode, "Account Code", driver, extentReport, true));
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();
  }

  /***
   * Click on Find button on search transaction screen
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Sakshi
   */
  public void clickOnFindButton(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFind, "Find button", driver, extentReport, false);

  }

  /***
   * <<<<<<< HEAD To Switch to frame in CTV screen
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Sakshi
   */
  public void switchToFrameCTV(WebDriver driver, ExtentTest extentReport) throws Exception {

    driver.switchTo().frame(frame_TB);

  }

  /***
   * To get unearned income on CTV screen
   * 
   * ======= To get unearned income on CTV screen >>>>>>> branch 'master' of
   * http://gitlab.siriusfs.com/shweta.saigal/p1-pom-framework.git
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Sakshi
   */
  public String getUnearnedIncome(WebDriver driver, ExtentTest extenReport) throws Exception {

    return (UIInteraction.getValue(txtUnearnedIncome, "Unearned Income", driver, extenReport,
        true));
  }

  /***
   * To click on income tab on CTV screen
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Sakshi
   */
  public void clickOnIncomeTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(tabIncome, "Income tab", driver, extentReport, false);

  }


  /***
   * To switch out from frame
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Sakshi
   */
  public void switchOutOfFrame(WebDriver driver, ExtentTest extentReport) throws Exception {
    driver.switchTo().defaultContent();
  }


  public void closeCTVScreen(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnClose, "Close CTV screen", driver, extentReport, false);

  }

  /******
   * 
   * Select the checkbox based on policynumber and transref
   * 
   * @param driver
   * @param reference
   * @param extentReport
   * @throws Exception
   * @author Sakshi
   */
  public void selectPolicyCheckboxBasedOnTransrefAndPolicyNumber(WebDriver driver, String policyNo,
      String transref, ExtentTest extentReport) throws Exception {
    Interactions.selectPolicyCheckboxBasedOnTransrefAndPolicyNumber(driver, policyNo, transref,
        extentReport, true);

  }

  /******
   * 
   * Enter amount in textbox based on policynumber
   * 
   * @param driver
   * @param reference
   * @param extentReport
   * @throws Exception
   * @author Sakshi
   */
  public void enterAmountBasedOnPolicyNumber(WebDriver driver, String policyNo, String input,
      ExtentTest extentReport) throws Exception {
    Interactions.enterAmountBasedOnPolicyNumber(driver, policyNo, input, extentReport, true);
  }

  /******
   * 
   * To convert positive value to negative and negative value to positive
   * 
   * @param driver
   * @param reference
   * @param extentReport
   * @throws Exception
   * @author Sakshi
   */
  public String convertPostiveValueToNegativeAndNegValueToPositive(String value, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    String value1 = UIInteraction.convertPostiveValueToNegativeAndNegValueToPositive(value, driver,
        extentReport, true);
    return value1;
  }

  /******
   * 
   * Addition of 2 numbers
   * 
   * @param driver
   * @param reference
   * @param extentReport
   * @throws Exception
   * @author Sakshi
   */
  public String additionOfTwoNumbers(String input, String input1, WebDriver driver,
      ExtentTest extentReport, String label) throws Exception {
    String value = UIInteraction.additionOfTwoNumbers(input, input1, driver, extentReport, true,
        "Addition happened");
    return value;
  }

  /****
   * 
   * Click on Action menu and click on View Allocation link
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnActionMenuAndViewAllocation(WebDriver driver, ExtentTest extentReport)
      throws Exception {


    UIInteraction.click(actionMenuFor1stTran, "Click on Action Menu", driver, extentReport, true);


    UIInteraction.click(viewAllocationOption, "Click on View Allocation Menu", driver, extentReport,
        true);


  }

  /****
   * 
   * Click on Action menu
   * 
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public void clickOnActionMenu(WebDriver driver, ExtentTest extentReport) throws Exception {


    UIInteraction.click(actionMenuFor1stTran, "Click on Action Menu", driver, extentReport, true);
  }


  /****
   * 
   * Click on UNDO transaction button
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void undoTransaction(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(checkboxCreditSection, "Check credit section checkbox", driver,
        extentReport, true);

    UIInteraction.click(btnUndo, "Click on UNDO button", driver, extentReport, true);

    Thread.sleep(4000);

    UIInteraction.click(buttonYesOnPopUp, "Click on Yes button", driver, extentReport, true);


  }

  /***
   * Click on ok button
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   */

  public void clickOnFindBtn(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnFind, "Find btn", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  // Test methods--------------------------------------------------------------------
  public boolean validateTableHeader(String input, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    boolean status =
        Interactions.validateTableHeaderValueNotContains(searchTransactionTable, input, driver);
    return status;
  }

  public boolean validateLinkPresent(String input, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    boolean status = Interactions.validateLink(closeBtn);
    return status;
  }

  public boolean validateBranchDropDown(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    boolean status = Interactions.validateSortingOrderOfOptionsInADropDown(branchDropDown, driver,
        extentReport, true);
    return status;
  }

  public void passPremium(String input, int percentage, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    Interactions.divideAmountAndPassinTextField(accountCode, input, percentage, driver);

  }

  public boolean validateMultipleDropDownValue(String input, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    boolean status = Interactions.validateDropdownAllValues(branchDropDown, input, driver);
    return status;
  }

  public void enterRandomPolicy(WebDriver driver, ExtentTest extentReport) throws Exception {

    Interactions.enteringRandomPolicyGenratedNumber(accountCode, driver, extentReport, true);
  }

  public boolean validateSimilarValuePresentInColumn(String input, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    boolean status =
        Interactions.validateSameValuePresentInPerticularColumn(columnNameType, input, driver);
    return status;
  }

  /*****
   * 
   * to search the account code with SA and select that account.
   * 
   * @param driver
   * @param testData
   * @param quotesData
   * @param extentReport
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public void searchAccountViaClientName(WebDriver driver, HashMap<String, String> testData,
      String thirdParty, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnFindAccount, "Find Account button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frame_TB);
    UIInteraction.sendKeys(fldAccountName, "Account Name", thirdParty, driver, extentReport, false);
    UIInteraction.click(btnFindNow, "Find now button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelectAccount, "Select Account", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();
  }

  /***
   * This method is used to get credit transaction reference no on view allocation
   * 
   * @param driver
   * @param extentReport
   * @author Bhavesh.KumarSingh
   */
  public String getTransactionCreditTransReffNo(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    String transactionNo = UIInteraction.getText(transactionSRPNo, "Credit transaction no", driver,
        extentReport, true);
    return transactionNo;
  }
  
  /***
   * This method is used to get debit transaction reference no on view allocation when undo check boxes are not available
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */
  public String getTransactionDebitTransReffNo(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    String transactionNo = UIInteraction.getText(transactionDebitNo, "Debit transaction no", driver,
        extentReport, true);
    return transactionNo;
  }
  
  /***
   * This method is used to get Credit transaction reference no on view allocation when undo check boxes are not available
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */
  public String getTransactionCreditTransReffNoWhenUnDOCheckboxesNotAvailable(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    String transactionNo = UIInteraction.getText(transactionCreditNo, "Credit transaction no", driver,
        extentReport, true);
    return transactionNo;
  }

  /***
   * To switch out from frame
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public void closeViewAllocationcreen(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnViewScreenClose, "Close view allocation screen", driver, extentReport,
        false);
    driver.switchTo().defaultContent();

  }


  /****
   * Method to search using transaction reference
   * 
   * @param reference
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void searchViaTransactionRef(String reference, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.sendKeys(fldTransactionRef, "Transaction reference", reference, driver,
        extentReport, true);
  }


  /****
   * Method to get transaction reference from Search Transaction screen
   * 
   * @param reference
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public String getTransactionRef(WebDriver driver, ExtentTest extentReport) throws Exception {
    return UIInteraction.getText(transRefColValOfSearchTrans, "get document ref", driver,
        extentReport, false);
  }

  /****
   * Method to get Account code col val on search transaction
   * 
   * @param reference
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public String getAccountCode(WebDriver driver, ExtentTest extentReport) throws Exception {
    return UIInteraction.getText(accountCodeColValOfSearchTrans, "get Account code", driver,
        extentReport, false);
  }

  /****
   * Method to get O/S Amount val on search transaction
   * 
   * @param reference
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public String getOustandingAmount(WebDriver driver, ExtentTest extentReport) throws Exception {
    return UIInteraction.getText(outstandingAmount, "get o/s amount", driver,
        extentReport, false);
  }
  /*****
   * Method to navigate to transactions tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void navigateToTransactionsTab(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.click(tabTransaction, "Transaction tab", driver, extentReport, false);
  }

  /*****
   * Method to navigate to Amount tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void navigateToAmountTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(tabAmount, "Amount tab", driver, extentReport, false);
  }

  /*****
   * Method to verify the options present in currency drop-down on Amounts tab and that only Pounds
   * Sterling is available
   * 
   * @param text
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyCurrencyDropdownOptions(String text, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    boolean status = false, count = false;

    List<WebElement> currency = UIInteraction.getAllOptionsInDropdown(dropdownCurrency,
        "Currency dropdown", driver, extentReport);

    if (currency.size() == 2)
      count = true;
    for (WebElement itr : currency) {

      if (itr.getText().equalsIgnoreCase(text))
        status = true;

    }

    return (count && status);
  }

  /*****
   * Method to verify income tab availability by checking commission hyperlink
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */
  public boolean checkCommissionHyperlinkAvailability(WebDriver driver, ExtentTest extentReport) {

    boolean status = false;

    if (lnkCTVCommission.isDisplayed() == true) {
      status = true;
    } else
      status = false;

    return status;
  }

  /*****
   * Method to verify values of Earned commission and Earned Fee
   * 
   * @param driver
   * @param extentReport
   * @afterUndo if true then it will check contra amount in to clear and earned field
   * @author Sandeep.Sharma
   */

  public void assertFullyearnedIncome(HashMap<String, String> dynamicHashMap, WebDriver driver,
      ExtentTest extentReport, boolean afterUndo) {

    String earnedComm = null;
    String unearnedComm = null;
    String toClearComm = null;
    String earnedFee = null;
    String unearnedFee = null;
    String toClearFee = null;
    boolean status = false;
    try {
      earnedComm = traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
      unearnedComm = traverseCTVScreen("Unearned Commission", "Income", driver, extentReport);
      toClearComm = traverseCTVScreen("Toclear Commission", "Income", driver, extentReport);
      earnedFee = traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
      unearnedFee = traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);
      toClearFee = traverseCTVScreen("Toclear Fee", "Income", driver, extentReport);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (afterUndo == false) {

      if (earnedComm.equals(dynamicHashMap.get("ToClearComm")) && unearnedComm.equals(dynamicHashMap.get("unearnedComm"))
          && toClearComm.equals("0.00")) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Full Commission has been earned sucessfully",
          "Commission has not been earned", driver, extentReport, true);

      if (earnedFee.equals(dynamicHashMap.get("ToClearFee")) && unearnedFee.equals(dynamicHashMap.get("unearnedFee"))
          && toClearFee.equals("0.00")) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Full Fee has been earned sucessfully", "Fee has not been earned",
          driver, extentReport, true);
    }

    if (afterUndo == true) {
      System.out.println((dynamicHashMap.get("ToClearComm")));
      System.out.println(earnedComm);
      System.out.println(unearnedComm);
      System.out.println(toClearComm);
      System.out.println("-"+earnedComm);
      
      
      
        if (earnedComm.equals(dynamicHashMap.get("ToClearComm"))
          && unearnedComm.equals(earnedComm) && toClearComm.equals("-"+earnedComm)) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Full Commission has been reverted with contra",
          "Commission has not been reverted with contra", driver, extentReport, true);     
      

      if (earnedFee.equals(dynamicHashMap.get("ToClearFee")) && unearnedFee.equals(earnedFee)
          && toClearFee.equals("-"+earnedFee)) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Full Fee has been reverted with contra",
          "Fee has not been reverted wtih contra", driver, extentReport, true);


    }


  }

  /*****
   * Method to verify values of Earned commission and Earned Fee with Client Pays earning basis
   * 
   * @param driver
   * @param extentReport
   * @afterUndo if true then it will check contra amount in to clear and earned field
   * @author Sandeep.Sharma
   */

  public void assertFullyearnedIncomeClientPays(HashMap<String, Double> dynamicHashMapD, WebDriver driver,
      ExtentTest extentReport, boolean afterUndo) {

    double earnedCommVal = 0.00;
    double unearnedCommVal = 0.00;
    double toClearCommVal = 0.00;
    double earnedFeeVal = 0.00;
    double unearnedFeeVal = 0.00;
    double toClearFeeVal = 0.00;
    String earnedComm = null;
    String unearnedComm = null;
    String toClearComm = null;
    String earnedFee = null;
    String unearnedFee = null;
    String toClearFee = null;
    boolean status = false;
    try {
      earnedComm = traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
      unearnedComm = traverseCTVScreen("Unearned Commission", "Income", driver, extentReport);
      toClearComm = traverseCTVScreen("Toclear Commission", "Income", driver, extentReport);
      earnedFee = traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
      unearnedFee = traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);
      toClearFee = traverseCTVScreen("Toclear Fee", "Income", driver, extentReport);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    earnedCommVal=Double.parseDouble(earnedComm);
    unearnedCommVal=Double.parseDouble(unearnedComm);
    toClearCommVal=Double.parseDouble(toClearComm);
    earnedFeeVal=Double.parseDouble(earnedFee);
    unearnedFeeVal=Double.parseDouble(unearnedFee);
    toClearFeeVal=Double.parseDouble(toClearFee);
    
    

    if (afterUndo == false) {

      if (unearnedCommVal==dynamicHashMapD.get("unearnedCommD") && toClearCommVal==0.00
          && earnedCommVal==(dynamicHashMapD.get("ToClearCommD"))) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Commission has been earned sucessfully",
          "Commission has not been earned", driver, extentReport, true);

      if (earnedFeeVal==0.00 && toClearFeeVal==0.00
          && unearnedFeeVal==(dynamicHashMapD.get("unearnedFeeD"))) {
        status = true;
      } else
        status = false;
      Log.softAssertThat(status, "Full Fee has been earned sucessfully", "Fee has not been earned",
          driver, extentReport, true);
    }

    if (afterUndo == true) {     
     
        if (earnedCommVal==(dynamicHashMapD.get("ToClearCommD")) && toClearCommVal==(earnedCommVal*(-1)) && unearnedCommVal==(dynamicHashMapD.get("unearnedCommD"))+earnedCommVal){
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Full Commission has been reverted with contra",
          "Commission has not been reverted with contra", driver, extentReport, true);     
      

      if (earnedFeeVal==0.00 && toClearFeeVal==0.00 && unearnedFeeVal==dynamicHashMapD.get("unearnedFeeD")){
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Full Fee has been reverted with contra",
          "Fee has not been reverted wtih contra", driver, extentReport, true);


    }


  }

  /*****
   * Method to verify values of Earned Fee with Client Pays earning basis
   * 
   * @param driver
   * @param extentReport
   * @afterUndo if true then it will check contra amount in to clear and earned field
   * @author Sandeep.Sharma
   */

  public void assertFeeIncomePostUndoClientPays(HashMap<String, Double> dynamicHashMapD, WebDriver driver,
      ExtentTest extentReport,boolean PostClearingReversal) {

   
    double earnedFeeVal = 0.00;
    double unearnedFeeVal = 0.00;
    double toClearFeeVal = 0.00;
  
    String earnedFee = null;
    String unearnedFee = null;
    String toClearFee = null;
    boolean status = false;
  
         
if(PostClearingReversal==false){
  try {
    earnedFee = traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
    unearnedFee = traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);
    toClearFee = traverseCTVScreen("Toclear Fee", "Income", driver, extentReport);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  earnedFeeVal=Double.parseDouble(earnedFee);
  unearnedFeeVal=Double.parseDouble(unearnedFee);
  toClearFeeVal=Double.parseDouble(toClearFee);

     if (earnedFeeVal==dynamicHashMapD.get("partFeeD") && toClearFeeVal==(dynamicHashMapD.get("osAmountD")-dynamicHashMapD.get("partFeeD")) && unearnedFeeVal==dynamicHashMapD.get("partFeeD")){
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "part Fee has been reverted with contra",
          "Part Fee has not been reverted wtih contra", driver, extentReport, true);
}
if(PostClearingReversal==true){
  try {
    earnedFee = traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
    unearnedFee = traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);
    toClearFee = traverseCTVScreen("Toclear Fee", "Income", driver, extentReport);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  earnedFeeVal=Double.parseDouble(earnedFee);
  unearnedFeeVal=Double.parseDouble(unearnedFee);
  toClearFeeVal=Double.parseDouble(toClearFee);
  if(earnedFeeVal==dynamicHashMapD.get("osAmountD") && toClearFeeVal==0.00 && unearnedFeeVal==dynamicHashMapD.get("partFeeD")){
  status = true;
}else status = false;
Log.softAssertThat(status, "Earned Fee has been reverted and toclear fee has been earned",
    "Earned Fee has not been reverted", driver, extentReport, true);
}

  
 }

  
  /*****
   * Method to verify income fields after clearing reversal journals from income clearing
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */
  public void assertIncomePostClearingReversals(HashMap<String, String> dynamicHashMap,
      WebDriver driver, ExtentTest extentReport) {

    String earnedComm = null;
    String unearnedComm = null;
    String toClearComm = null;
    String earnedFee = null;
    String unearnedFee = null;
    String toClearFee = null;
    boolean status = false;
    try {
      earnedComm = traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
      unearnedComm = traverseCTVScreen("Unearned Commission", "Income", driver, extentReport);
      toClearComm = traverseCTVScreen("toClear Commission", "Income", driver, extentReport);
      earnedFee = traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
      unearnedFee = traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);
      toClearFee = traverseCTVScreen("toClear Fee", "Income", driver, extentReport);
    } catch (Exception e) {

      e.printStackTrace();
    }

    if (earnedComm.equals("0.00") && unearnedComm.equals(dynamicHashMap.get("ToClearComm"))
        && toClearComm.equals("0.00")) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "Full Commission has been reverted sucessfully",
        "Commission has not been reverted", driver, extentReport, true);

    if (earnedFee.equals("0.00") && unearnedFee.equals(dynamicHashMap.get("ToClearFee"))
        && toClearFee.equals("0.00")) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "Full Fee has been reverted sucessfully",
        "Fee has not been reverted", driver, extentReport, true);
  }

  public void assertIncomePostClearingReversalsClientPays(HashMap<String, Double> dynamicHashMapD,
      WebDriver driver, ExtentTest extentReport) {

    String earnedComm = null;
    String unearnedComm = null;
    String toClearComm = null;
    String earnedFee = null;
    String unearnedFee = null;
    String toClearFee = null;
    double earnedCommVal = 0.00;
    double unearnedCommVal = 0.00;
    double toClearCommVal = 0.00;
    double earnedFeeVal = 0.00;
    double unearnedFeeVal = 0.00;
    double toClearFeeVal = 0.00;
    boolean status = false;
    try {
      earnedComm = traverseCTVScreen("Earned Commission", "Income", driver, extentReport);
      unearnedComm = traverseCTVScreen("Unearned Commission", "Income", driver, extentReport);
      toClearComm = traverseCTVScreen("toClear Commission", "Income", driver, extentReport);
      earnedFee = traverseCTVScreen("Earned Fee", "Income", driver, extentReport);
      unearnedFee = traverseCTVScreen("Unearned Fee", "Income", driver, extentReport);
      toClearFee = traverseCTVScreen("toClear Fee", "Income", driver, extentReport);
    } catch (Exception e) {

      e.printStackTrace();
    }
    earnedCommVal=Double.parseDouble(earnedComm);
    unearnedCommVal=Double.parseDouble(unearnedComm);
    toClearCommVal=Double.parseDouble(toClearComm);
    earnedFeeVal=Double.parseDouble(earnedFee);
    unearnedFeeVal=Double.parseDouble(unearnedFee);
    toClearFeeVal=Double.parseDouble(toClearFee);

    if (earnedCommVal==0.00 && toClearCommVal==0.00
        && unearnedCommVal==(dynamicHashMapD.get("unearnedCommD")+(dynamicHashMapD.get("ToClearCommD")))) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "Full Commission has been reverted sucessfully",
        "Commission has not been reverted", driver, extentReport, true);

    if (earnedFeeVal==0.00 && toClearFeeVal==0.00
        && unearnedFeeVal==(dynamicHashMapD.get("unearnedFeeD")+(dynamicHashMapD.get("ToClearFeeD")))) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "Full Fee has been reverted sucessfully",
        "Fee has not been reverted", driver, extentReport, true);
  }

  /*****
   * Method to get transaction from view allocation screen based on allocated transactions for Banking-US specific method
   * 
   * @amount based on this amount transaction ref will be returned
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */

  public String getTransactionRefBasedOnProcessedBankingRecords(
      HashMap<String, String> dynamicHashMap, WebDriver driver,
      ExtentTest extentReport) {

    String Reference = null;
    String ref = null;

    double markedAmount = Double.parseDouble(dynamicHashMap.get("markedTotal")) * (-1);
    System.out.println(markedAmount);


    if (markedAmount > 0) {
      for (WebElement ele : debitSectionTRefWHenUndoCHeckboxesNotDisplayed) {
        System.out.println(ele.getText());
        if (!ele.getText().equalsIgnoreCase(dynamicHashMap.get("ref1"))
            && !ele.getText().equalsIgnoreCase(dynamicHashMap.get("ref2"))) {
          ref = ele.getText();
        }
      }

    } else {

      for (WebElement ele : creditSectionTRefWHenUndoCHeckboxesNotDisplayed) {
        System.out.println(ele.getText());
        if (!ele.getText().equalsIgnoreCase(dynamicHashMap.get("ref1"))
            && !ele.getText().equalsIgnoreCase(dynamicHashMap.get("ref2"))) {
          ref = ele.getText();
        }

      }

    }
    return ref;
  }

  
  /*****
   * Method to clear search and preform new search
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */

  public void newSearch(WebDriver driver, ExtentTest extentReport) {

    try {
      UIInteraction.click(btnClear, "click clear", driver, extentReport, false);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    WaitUtils.waitForSpinner(driver);
  }

  /*****
   * Method to check if particular account is available or not
   * 
   * @param driver
   * @param extentReport
   * @param account
   * @author Sandeep.Sharma
   */

  public void assertAccountCode(String account, WebDriver driver, ExtentTest extentReport) {

    String accountcode = "//td[contains(text(),'".concat(account).concat("')]");
    System.out.println(accountcode);
    WebElement element = driver.findElement(By.xpath(accountcode));
    boolean status = false;
    if (element.getText().equalsIgnoreCase(account)) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "Transaction is posted in account -> " + account,
        "Transaction is not posted in account -> " + account, driver, extentReport, true);

  }
  /*****
   * Method to click Amt. Allocatd hyperlink of CTV screen
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */

  public void clickAmountAllocatedLink(WebDriver driver, ExtentTest extentReport) {

    try {
      UIInteraction.click(lnkAmtAllocated, "click AmtAllocated link", driver, extentReport, false);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    WaitUtils.waitForSpinner(driver);
  }
  
  /*****
   * Method to check not availability of Banking Date on CTV Details tab
   * 
   * @param driver
   * @param extentReport
   * @author Sandeep.Sharma
   */

 public void checkUnavailabilityOfBankingDateOnCTV(WebDriver driver, ExtentTest extentReport){
     
   try {
    UIInteraction.click(tabDetails, "Details tab", driver, extentReport, false);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
   UIInteraction.checkUnavailabilityOfElement("Banking Date", allLabelsDetailsTabCTV, driver, extentReport);
 }

}
