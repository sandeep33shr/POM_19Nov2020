package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
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

public class ViewClientDetails extends LoadableComponent<ViewClientDetails> {
  public static HashMap<String, String> dynamicHashMap = new HashMap<>();
  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;

  public ElementLayer uielement;
  public static String thirdParty;



  @FindBy(css = "#ctl00_cntMainBody_lblViewClient")
  WebElement viewClientTitle;


  @FindBy(css = "#ctl00_cntMainBody_ctrlNewQuoteImproved_ddlProductlst")
  WebElement dropdownSelectProduct;

  @FindBy(css = "#ctl00_cntMainBody_ctrlNewQuoteImproved_btnNewQuote")
  WebElement btnNewQuote;

  @FindBy(css = "#ctl00_cntMainBody_ctrlTabIndex_lbtnMainDetail")
  WebElement policyHeaderTab;

  @FindBy(css = "#ctl00_cntMainBody_ctrlTabIndex_lbtnPolicySummary")
  WebElement summaryOfCoverTab;

  @FindBy(css = "a[href='#tab-maindetails']")
  WebElement mainDetailsTab;

  @FindBy(css = "a[href='#tab-compliancedetails']")
  WebElement complianceDetailsTab;

  @FindBy(css = "#ctl00_cntMainBody_POLICYHEADER__BUSINESSTYPE")
  WebElement dropdownBusinessType;

  @FindBy(css = "#ctl00_cntMainBody_ddlThirdParty")
  WebElement dropdownThirdParty;

  @FindBy(css = "#ctl00_cntMainBody_CUSTOMER_CATEGORY")
  WebElement dropdownCustomerCatrogry;

  @FindBy(css = "#ctl00_cntMainBody_TYPE_OF_SALE")
  WebElement dropdownTypeOfSale;

  @FindBy(css = "#ctl00_cntMainBody_METHOD_OF_SALE")
  WebElement dropdownMethodOfSale;

  @FindBy(css = "#ctl00_cntMainBody_btnNext")
  WebElement btnNext;

  @FindBy(css = "#ctl00_cntMainBody_btnSaveQuote")
  WebElement btnSave;

  @FindBy(css = "a[href='#tabpolicies']")
  WebElement quotesTab;

  @FindBy(css = "#ctl00_cntMainBody_ClientQuotesPolicies_txtPolicyNo")
  WebElement txtPolicyNo;

  @FindBy(css = "#ctl00_cntMainBody_ClientQuotesPolicies_btnSearch")
  WebElement btnSearch;

  @FindBy(css = "#ctl00_cntMainBody_ClientQuotesPolicies_btnChange")
  WebElement btnChange;

  @FindBy(
      css = "#ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions > table > tbody > tr.AspNet-GridView-Selected.grid-sel-r > td:nth-child(7)")
  WebElement txtPolicyStatus;

  @FindBy(
      css = "#ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions > table > tbody > tr.AspNet-GridView-Selected.grid-sel-r > td:nth-child(8)")
  WebElement txtEventDescription;

  // Events Tab
  @FindBy(css = "a[href='#tab-events']")
  WebElement eventsTab;

  @FindBy(css = "#ctl00_cntMainBody_ctrlClientEvents_txtPolicyCode")
  WebElement fldPolicyCode;

  @FindBy(css = "#ctl00_cntMainBody_ctrlClientEvents_btnRefresh")
  WebElement btnRefresh;

  @FindBy(css = "#ctl00_cntMainBody_ctrlClientEvents_gvEventList")
  WebElement tableEventList;

  @FindBy(
      xpath = "//*[@id='ctl00_cntMainBody_ctrlClientEvents_gvEventList']//td//span[contains(text(),'Policy made live')]/parent::td/following-sibling::td//a")
  WebElement btnDetailsLivePolicy;

  @FindBy(
      xpath = "//*[@id='ctl00_cntMainBody_ctrlClientEvents_gvEventList']//td//span[contains(text(),'Accept Renewal')]/parent::td/following-sibling::td//a")
  WebElement btnDetailsRenewal;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frameTB;

  @FindBy(css = "#ctl00_cntMainBody_lblDetailsData")
  WebElement txtDetails;

  @FindBy(
      css = "div.modal-header.bootstrap-dialog-draggable > div > div.bootstrap-dialog-close-button > button")
  WebElement btnClose;

  @FindBy(css = "#ctl00_cntMainBody_lblCode_view")
  WebElement txtCompanyCode;

  @FindBy(css = "#ctl00_cntMainBody_lblCompanyName_view")
  WebElement txtCompanyName;

  @FindBy(css = "#ctl00_cntMainBody_ddlTermsAgreed")
  WebElement dropdownTermsAgreed;

  @FindBy(css = "#ctl00_cntMainBody_lblName_view")
  WebElement txtClientName;


  @FindBy(
      xpath = "(//div[@id='ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions']/table/tbody/tr/td[2])[1]")
  WebElement coverFromDate;

  @FindBy(css = "a[href='#tabaccounts']")
  WebElement accountsTab;

  @FindBy(css = "#ctl00_cntMainBody_ctrlClientAccounts_chkOutstandingTransaction")

  public WebElement onlyShowOutstandingCheckBox;


  @FindBy(css = "#ctl00_cntMainBody_ctrlClientAccounts_btnSearch")
  WebElement btnFind;

  @FindBy(xpath = "(//a[@title='Action Menu'])[1]")
  WebElement firstActionMenuInTable;

  @FindBy(xpath = "//a[contains(text(),'View')]")
  WebElement viewMenu;

  @FindBy(
      xpath = "//div[@id='ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent']//table//td")
  List<WebElement> transactionReffColumn;
  @FindAll({@FindBy(
      xpath = "//div[@id='ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent']//tr//th")})
  List<WebElement> clientDetailsHeaderTh;


  // Akshay


  @FindBy(
      css = "#ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions > table > tbody > tr > td:nth-child(11) > strong")
  WebElement valueOfFAPCss;

  @FindAll({@FindBy(
      xpath = "//*[@id=\"ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions\"]/table/tbody/tr")})
  List<WebElement> numberOfColumnOfTransactionType;


  @FindBy(
      xpath = "//*[@id='ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions']/table/tbody/tr[1]/td[7]")
  WebElement transactionType;

  @FindBy(
      xpath = "//*[@id='ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions']/table/tbody/tr[2]/td[7]")
  WebElement transactionTypeTwo;

  @FindBy(css = "#ctl00_cntMainBody_ctrlClientAccounts_txtPolicyNumber")
  WebElement textFieldPolicyNumberAccountsTab;

  @FindBy(css = "#ctl00_cntMainBody_txtAmountDue")
  WebElement textFieldAmountOnViewTransaction;

  @FindBy(css = "#ctl00_cntMainBody_btnClose")
  WebElement buttonCloseOnViewTransactionPopUp;

  @FindBy(
      xpath = "//*[@id=\"ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent\"]/table/tbody/tr[1]/td[3]")
  WebElement transTypeOnAccountsTable;

  
  @FindBy (xpath = "//div[@id='ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent']//table")
  public WebElement clientsDetailsTable;
  
  public String clientsDetailsTableHeader = "//div[@id='ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent']//tr";

  @FindBy (xpath = "#ctl00_cntMainBody_btnCollection")
  public WebElement btnCollection;
  
  @FindBy (xpath = "//h1[contains(text(),'Account')]")
  public WebElement headerAccounts;

  @FindBy (css = "#ctl00_cntMainBody_ClientQuotesPolicies_btnChangeHeader")
  public WebElement btnChangeHeader;
  
  @FindBy (css = "input#ctl00_cntMainBody_POLICYHEADER__POLICYNUMBER")
  public WebElement textFieldPolicyNumber;


  @FindBy(css = "#ctl00_cntMainBody_ClientQuotesPolicies_btnEdit")
  WebElement btnEdit;


  @FindBy(
      xpath = "//*[@id='ctl00_cntMainBody_ClientQuotesPolicies_gvPolicyVersions']//tbody//td[contains(text(),'Live')]")
  WebElement livePolicySelection;

  @FindBy(xpath = "//*[@id=\"MainDetail-control\"]/div/ul/li[3]/a")
  WebElement tabrenewal;

  @FindBy(xpath = "//*[@id=\"ctl00_cntMainBody_POLICYHEADER__REFERREDATMTA\"]")
  WebElement checkboxReferAtMTA;

  @FindBy(xpath = "//*[@id=\"ctl00_cntMainBody_POLICYHEADER__REFERREDATRENEWAL\"]")
  WebElement checkboxReferAtRenewal;

  @FindBy(css = "[href='#tab-overview']")
  WebElement overviewTab;

  @FindBy(css = "[href='#tab-additionalinformation']")
  WebElement additionalInformationTab;

  @FindBy(css = "[href='#tab-addresses']")
  WebElement addressesTab;

  @FindBy(css = "[href='#tab-contacts']")
  WebElement contactsTab;

  @FindBy(css = "[href='#tab-associates']")
  WebElement associatesTab;

  @FindBy(css = "[href='#tab-prospectpolicy']")
  WebElement prospectTab;

  @FindBy(css = "[href='#tab-taxdetails_view']")
  WebElement taxTab;

  @FindBy(css = "[href='#tab-compliance']")
  WebElement complianceTab;

  @FindBy(
      xpath = "//*[@id='ctl00_cntMainBody_pnlViewCC']/div/ul[contains(@class,'nav nav-lines nav-tabs b-danger')]//li[@class='active']//a")
  WebElement activeTab;


  @FindBy(xpath = "//*[@id='ctl00_cntMainBody_ClientQuotesPolicies_btnDetails']")
  WebElement btnDetails;

  @FindBy(css = "#linkinfoSection")
  WebElement linkInfo;

  @FindBy(css = "#ctl00_SideInfo_ClientInfoCtrl_hypClientDocsBroker")
  WebElement linkDocumentStore;
  
  @FindBy(css="#ctl00_cntMainBody_ClientSummaryCntrl_lblAccountBalance")
  WebElement txtAccountBalance;

  public ViewClientDetails(WebDriver driver, ExtentTest report) {
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
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
    new WebDriverWait(driver, 30)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("View Client Details not loaded")
        .until(ExpectedConditions.visibilityOf(viewClientTitle));
  }

  /*****
   * Verify Selected Client is loaded
   * 
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifySelectClient() throws Exception {
    if (!WaitUtils.waitForElement(driver, viewClientTitle))
      throw new Exception("View Client Details is not loaded");
    return true;
  }

  /****
   * Verify Quotes Tab in View Clients is loaded
   * 
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyQuotesTab() throws Exception {
    if (!WaitUtils.waitForElement(driver, quotesTab))
      throw new Exception("Quotes tab in View Client Details not loaded");
    return true;
  }

  /****
   * Method to create new quote
   * 
   * @param productName
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void createNewQuote(String productName, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.selectDropdownByVisibleText(dropdownSelectProduct, "Select Product", productName,
        driver, extentReport, false);
    UIInteraction.clickUsingJS(btnNewQuote, "New Quote Button", driver, extentReport, true);

  }

  /*****
   * Method to enter policy header data
   * 
   * @param testdata
   * @param dynamicHashMap
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public HashMap<String, String> enterPolicyHeaderData(HashMap<String, String> testdata,
      HashMap<String, String> dynamicHashMap, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    if (testdata.get("Third Party") != "") {
      UIInteraction.selectDropdownByIndex(dropdownThirdParty, "Third Party",
          testdata.get("Third Party"), driver, extentReport, true);
      dynamicHashMap.put("Third Party", UIInteraction.getSelectedDropdownText(dropdownThirdParty,
          "Third Party", driver, extentReport, false));
      thirdParty = dynamicHashMap.get("Third Party");

    }
    UIInteraction.selectDropdownByIndex(dropdownBusinessType, "Business Type",
        testdata.get("Business Source"), driver, extentReport, false);
    UIInteraction.clickUsingJS(complianceDetailsTab, "Compliance Tab", driver, extentReport, true);
    UIInteraction.selectDropdownByVisibleText(dropdownCustomerCatrogry, "Customer Category",
        testdata.get("CustomerCategory"), driver, extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownTypeOfSale, "Type of Sale",
        testdata.get("TypeOfSale"), driver, extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownMethodOfSale, " Method of Sale",
        testdata.get("MethodOfSale"), driver, extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownTermsAgreed, "Terms Agreed", "No", driver,
        extentReport, false);
    return dynamicHashMap;

  }

  /****
   * To search quote on Quotes tab in View Client details
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void searchQuotes(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    Thread.sleep(2000);
    UIInteraction.sendKeys(txtPolicyNo, "Policy No", policyNo, driver, extentReport, false);
    UIInteraction.clickUsingJS(btnSearch, "Search", driver, extentReport, true);
    Thread.sleep(5000);
  }

  /****
   * to verify the success of MTA performed.
   * 
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyMTASuccess() throws Exception {
    if (!UIInteraction.getText(txtPolicyStatus, "Policy Status", driver, extentReport, true)
        .equalsIgnoreCase("MTA Permanent"))
      throw new Exception();
    return true;
  }

  /*****
   * to verify Cancellation performed successfully.
   * 
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyCancellationSuccess() throws Exception {
    if (!UIInteraction.getText(txtPolicyStatus, "Policy Status", driver, extentReport, true)
        .equalsIgnoreCase("MTA Cancelled"))
      throw new Exception();
    return true;
  }

  /****
   * to verify success of Renewal
   * 
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyRenewalSuccess() throws Exception {
    if (!UIInteraction.getText(txtEventDescription, "Event description", driver, extentReport, true)
        .contains("Accept Renewal"))
      throw new Exception();
    return true;
  }

  /*****
   * Navigate to Events Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToEventsTab(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(eventsTab, "Events Tab", driver, extentReport, true);
  }

  /*****
   * Search policy in events tab
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void searchForPolicyInEvents(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.sendKeys(fldPolicyCode, "Policy no", policyNo, driver, extentReport, false);
    UIInteraction.clickUsingJS(btnRefresh, "Refresh button", driver, extentReport, true);
  }

  /****
   * Verify events displayed
   * 
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyEventsDisplayed() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, tableEventList))
      throw new Exception("Event list table not loaded after refresh");

    return true;
  }

  /****
   * Verify details for new business
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyDetailsForNewBusiness(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnDetailsLivePolicy, "Details button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frameTB);

    if (!UIInteraction.getText(txtDetails, "Details", driver, extentReport, true)
        .equalsIgnoreCase("Policy made live"))
      throw new Exception("Event description is not 'Policy made live'");

    driver.switchTo().defaultContent();
    UIInteraction.clickUsingJS(btnClose, "Close", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    return true;
  }

  /****
   * Verify details for Renewal
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyDetailsForRenewal(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnDetailsRenewal, "Details button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frameTB);

    if (!UIInteraction.getText(txtDetails, "Details", driver, extentReport, true)
        .contains("Accept Renewal"))
      throw new Exception("Event description is not 'Accept Renewal'");

    driver.switchTo().defaultContent();
    UIInteraction.clickUsingJS(btnClose, "Close", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    return true;
  }

  /****
   * Method to get client details- corporate client
   * 
   * @param clientData
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void getCustomerDetails(HashMap<String, String> clientData, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    String companyCode =
        UIInteraction.getText(txtCompanyCode, "Company Code", driver, extentReport, true);
    clientData.put("Client Code", companyCode);

    String companyName =
        UIInteraction.getText(txtCompanyName, "Company Name", driver, extentReport, true);
    clientData.put("Client Name", companyName);
  }

  /****
   * Method to get client details -personal client
   * 
   * @param clientData
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void getPersonalClientDetails(HashMap<String, String> clientData, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    String clientCode =
        UIInteraction.getText(txtCompanyCode, "Personal Client Code", driver, extentReport, true);
    clientData.put("Personal Client Code", clientCode);

    String clientName =
        UIInteraction.getText(txtClientName, "Personal Client Name", driver, extentReport, true);
    clientData.put("Personal Client Name", clientName);
  }

  /****
   * Method to navigate to Accounts tab in View Client Details
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */

  public void navigateToAccountsTab(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(accountsTab, "Accounts Tab", driver, extentReport, false);
  }

  /*****
   * Method to uncheck Only show outstanding checkbox
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void unCheckOnlyShowOutStandingAndClick(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.click(onlyShowOutstandingCheckBox, "Only Show outstanding checkBox", driver,
        extentReport, false);
    UIInteraction.click(btnFind, "Only Show outstanding checkBox", driver, extentReport, false);

    Thread.sleep(3000);
  }

  public void clickedOnCTVScreenBasedOnTransRefOnViewClientDetailsPage(WebDriver driver,
      String text, ExtentTest extentReport, String label) throws Exception {
    try {
      boolean status = false;
      int index = 0;
      System.out.println(transactionReffColumn.size());
      for (int i = 0; i < transactionReffColumn.size(); i++) {
        System.out.println(transactionReffColumn.get(i).getText());
        if (transactionReffColumn.get(i).getText().contains(text)) {

          index = index + 16;
          String accountColumn =
              "//div[@id='ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent']//table"
                  + "//td[" + index + "]";
          WebElement actionMenu = driver.findElement(By.xpath(accountColumn));
          actionMenu.click();
          Actions action = new Actions(driver);
          action.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
          status = true;
          break;
        }
      }


    } catch (NoSuchElementException e) {
      throw new Exception(e);
    }
  }


  public void beforeTodaysDate(WebDriver driver, ExtentTest extentReport) throws Exception {

    String todaysDate =
        UIInteraction.getText(coverFromDate, "Policy start date", driver, extentReport, false);
    String val[] = todaysDate.split("/");
    String val1 = val[0];
    System.out.println("val1 " + val1);
    int val2 = Integer.parseInt(val1) - 1;
    if (Integer.toString(val2).contains("0")) {
      val2 = 30;
    }
    String previousDate = val2 + "/" + val[1] + "/" + val[2];
    System.out.println("Yesterday's date " + previousDate);
    dynamicHashMap.put("beforeDate", previousDate);

  }
  
  /*****
   * Method to perform click on change button
   * @param driver
   * @throws Exception
   */
  public void clickOnChangeBtn(WebDriver driver) throws Exception {
    WaitUtils.waitForSpinner(driver);
    WaitUtils.waitForElement(driver, btnChange);
    UIInteraction.clickUsingJS(btnChange, "Change btn", driver, extentReport, false);
  }

  /****
   * 
   * Verify Indicator 'O' under FAP column
   * 
   * @input -> Pass 'O' as input
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyIndicatorUnderFAPColumn(WebDriver driver, ExtentTest extentReport,
      String input) throws Exception {
    try {
      boolean status = false;

      if (valueOfFAPCss.getText().trim().contains(input)) {
        status = true;
      }
      return status;
    } catch (NoSuchElementException e) {
      throw new Exception(e);
    }
  }

  /****
   * 
   * Verify order of the policies after MTA
   * 
   * @input -> Pass Transaction type column input as: "MTA Quotation Permanent" if MTA quote to be
   *        on top
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyMTAPolicyOrder(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {
    try {

      boolean status = false;
      WaitUtils.waitForElement(driver, transactionType);

      if (numberOfColumnOfTransactionType.size() == 2) {

        if (transactionType.getText().contains(input)) {
          status = true;

        }
      }

      else if (numberOfColumnOfTransactionType.size() == 3) {

        if (transactionType.getText().contains(input)
            && transactionTypeTwo.getText().contains(input)) {
          status = true;
        }

      }

      return status;

    } catch (NoSuchElementException e) {
      throw new Exception(e);
    }
  }

  /****
   * 
   * Send Policy number in Policy Number field under Acconts tab
   * 
   * @policyNo
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void policyNumberTextField(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.sendKeys(textFieldPolicyNumberAccountsTab, "Policy no", policyNo, driver,
        extentReport, false);
  }

  /****
   * 
   * To verify the account posting
   * 
   * @param input
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void verifyAccountPosting(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {

    boolean status = false;
    int index = 0;

    index = index + 16;
    String accountColumn =
        "//div[@id='ctl00_cntMainBody_ctrlClientAccounts_gvClientAccountParent']//table" + "//td["
            + index + "]";
    WebElement actionMenu = driver.findElement(By.xpath(accountColumn));
    actionMenu.click();
    Actions action = new Actions(driver);
    action.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();

    if (textFieldAmountOnViewTransaction.getAttribute("value").equalsIgnoreCase(input)) {

      status = true;
    }

    Log.softAssertThat(status, "Amount is correct", "Amount is Incorrect", driver, extentReport,
        true);

    UIInteraction.click(buttonCloseOnViewTransactionPopUp, "Close button", driver, extentReport,
        false);

  }

  /****
   * 
   * Read SND, SED, SEC transaction from search transaction table
   * 
   * @input -> Reference name starts with SND, SED, SEC
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public String readTransaction(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {
    String reference = null;
    WaitUtils.waitForSpinner(driver);

    if (transTypeOnAccountsTable.getText().trim().contains(input)) {

      reference = UIInteraction.getText(transTypeOnAccountsTable, "Transaction Reference", driver,
          extentReport, true);

    }
    return reference;
  }



  /******
   * Method to click on Edit Button
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Manisha.Sharma
   */
  public void searchQuotesForMTAEdit(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    Thread.sleep(2000);
    searchQuotes(policyNo, driver, extentReport);
    clickOnEditBtn(driver);
    Thread.sleep(2000);
  }

  /*******
   * Method to select live version of a policy
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Manisha.Sharma
   */
  public void searchLivePolicyForMTA(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    Thread.sleep(2000);
    searchQuotes(policyNo, driver, extentReport);
    UIInteraction.clickUsingJS(livePolicySelection, "Search Live Version of Policy", driver,
        extentReport, false);
    Thread.sleep(2000);
    clickOnChangeBtn(driver);
    Thread.sleep(2000);

  }

  /****
   * Method to search policy for MTA
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void searchQuotesForMTA(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    searchQuotes(policyNo, driver, extentReport);
    clickOnChangeBtn(driver);
  }

  /*****
   * Method to click on edit button
   * 
   * @param driver
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void clickOnEditBtn(WebDriver driver) throws Exception {
    WaitUtils.waitForSpinner(driver);
    WaitUtils.waitForElement(driver, btnEdit);
    UIInteraction.clickUsingJS(btnEdit, "Edit btn", driver, extentReport, false);
  }

  /****
   * Click next button on policy header
   * 
   * @param driver
   * @throws Exception
   */
  public void clickNextPolicyHeader(WebDriver driver) throws Exception {
    UIInteraction.clickUsingJS(btnNext, "NextButton", driver, extentReport, true);

  }

  public void checkReferAtMTACheckbox(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(tabrenewal, "Renewal Tab", driver, extentReport, false);
    UIInteraction.click(checkboxReferAtMTA, "Reffered At MTA checkbox", driver, extentReport,
        false);
    UIInteraction.clickUsingJS(btnNext, "NextButton", driver, extentReport, true);

  }

  /***
   * Checkbox to check on the Refer At MTA checkbox
   * 
   * @param testdata
   * @param dynamicHashMap
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   */
  public void checkReferAtRenewalCheckbox(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(tabrenewal, "Renewal Tab", driver, extentReport, false);
    UIInteraction.click(checkboxReferAtRenewal, "Reffered At Renewal checkbox", driver,
        extentReport, false);
    UIInteraction.clickUsingJS(btnNext, "NextButton", driver, extentReport, true);

  }



  /***
   * Method to navigate to Overview Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToOverviewTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(overviewTab, "Overview Tab", driver, extentReport, true);
  }


  /***
   * Method to navigate to Additional Info Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToAdditionalInformationTab(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.clickUsingJS(additionalInformationTab, "Additional Tab", driver, extentReport,
        true);
  }

  /***
   * Method to navigate to Address Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */

  public void navigateToAddressesTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(addressesTab, "Addresses Tab", driver, extentReport, true);
  }


  /***
   * Method to navigate to Contacts Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToContactsTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(contactsTab, "Contacts Tab", driver, extentReport, true);
  }


  /***
   * Method to navigate to Associate Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToAssociatesTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(associatesTab, "Associates Tab", driver, extentReport, true);
  }


  /***
   * Method to navigate to Prospect Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToProspectTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(prospectTab, "Prospect Tab", driver, extentReport, true);
  }


  /***
   * Method to navigate to Tax Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToTaxTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(taxTab, "Tax Tab", driver, extentReport, true);
  }


  /***
   * Method to navigate to Complaince Tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Ravi.Saggu
   */
  public void navigateToComplianceTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(complianceTab, "Compliance Tab", driver, extentReport, true);
  }

  /****
   * Get active tab
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Ravi.Saggu
   */
  public String getActiveTab(WebDriver driver, ExtentTest extentReport) throws Exception {
    return UIInteraction.getText(activeTab, "Active Tab", driver, extentReport, true);
  }

  /****
   * Method to click details button on Quotes?policies tab in view client detail
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shashank.Agrawal
   */
  public void clickDetailsButton(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(btnDetails, "Details button in Quotes/Policies tab", driver,
        extentReport, false);
  }


    /***
     * This method is used to get a column data from a table
     * @param tableRowsLocator
     * @param columnOneName
     * @param columnOneValue
     * @param columnTwoName
     * @param expectedValue
     * @param driver
     * @param extentReport
     * @return
     * @throws Exception
     * @author Bhavesh.KumarSingh
     */
    public String validateClientDetailsTableData(String tableRowsLocator, String columnOneName, String columnOneValue, String columnTwoName, String expectedValue,
        WebDriver driver, ExtentTest extentReport) throws Exception {
      return Interactions.getTableDataIncludesHiddenRows(tableRowsLocator, columnOneName, columnOneValue, columnTwoName, expectedValue, driver, extentReport);
    }
    
    /****
     * 
     * Navigate to policy header screen from Quote/Policy tab
     * 
     * @param extentReport
     * @author Bhavesh.KumarSingh
     */
    public void clickOnChangeHeader(WebDriver driver) throws Exception {

      UIInteraction.click(btnChangeHeader, "Change header button", driver, extentReport, false);
    }
    
    /****
     * 
     * @param return
     * @throws Exception
     * @author Bhavesh.KumarSingh
     */
    
    public boolean verifyPolicyHeaderTab(WebDriver driver) throws Exception {
      if (!WaitUtils.waitForElement(driver, policyHeaderTab))
        throw new Exception("Policy header tab in View Client Details not loaded");
      return true;
    }
   
    /****
     * 
     * Navigate to Policies tab
     * 
     * @param extentReport
     * @author Bhavesh.KumarSingh
     */
    
    public void clickOnSaveBtn(WebDriver driver, ExtentTest extentReport) throws Exception {

      UIInteraction.click(btnSave, "Save button", driver, extentReport, false);
    }


  /****
   * 
   * Read SND transaction from search transaction table
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public String readSND(WebDriver driver, ExtentTest extentReport) throws Exception {

    String reference = null;
    WaitUtils.waitForSpinner(driver);

    if (transTypeOnAccountsTable.getText().trim().contains("SND")) {

      reference = UIInteraction.getText(transTypeOnAccountsTable, "SND Reference", driver,
          extentReport, true);

    }
    return reference;
  }

  /****
   * 
   * Read SED transaction from search transaction table
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public String readSED(WebDriver driver, ExtentTest extentReport) throws Exception {

    String reference = null;
    WaitUtils.waitForSpinner(driver);

    if (transTypeOnAccountsTable.getText().trim().contains("SED")) {

      reference = UIInteraction.getText(transTypeOnAccountsTable, "SED Reference", driver,
          extentReport, true);

    }
    return reference;
  }

  /****
   * 
   * Read SEC transaction from search transaction table
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public String readSEC(WebDriver driver, ExtentTest extentReport) throws Exception {

    String reference = null;
    WaitUtils.waitForSpinner(driver);

    if (transTypeOnAccountsTable.getText().trim().contains("SEC")) {

      reference = UIInteraction.getText(transTypeOnAccountsTable, "SEC Reference", driver,
          extentReport, true);

    }

    return reference;
  }

  /****
   * 
   * Navigate to Policies tab
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void navigateToPoliciesTab(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(quotesTab, "Policies Tab", driver, extentReport, false);
  }

  /****
   * 
   * Click on ChangeHeaderButton
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnChangeHeaderButton(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnChangeHeader, "Change Header button", driver, extentReport,
        false);

  }

  /****
   * 
   * Enter Policy number manual
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void changePolicyNumberManual(WebDriver driver, ExtentTest extentReport, String policyNo)
      throws Exception {

    UIInteraction.sendKeys(textFieldPolicyNumber, "Policy No update", policyNo, driver,
        extentReport, false);

  }

  /****
   * 
   * Click on Save Button
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnSaveButton(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(btnSave, "Save button", driver, extentReport, false);

  }

  /****
   * To Click on Info link
   * 
   * @param driver
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnInfoLink(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(linkInfo, "Info Section", driver, extentReport, false);
    Thread.sleep(2000);

  }

  /****
   * To Click on Document link
   * 
   * @param driver
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnDocumentLink(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(linkDocumentStore, "Document link", driver, extentReport, false);
    Thread.sleep(2000);

  }
  /****
   * Method to navigate to quotes tab
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToQuotesTab(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(quotesTab, "Quotes Tab", driver, extentReport, false);
  }

  /****
   * 
   * Method to read account balance
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public String getAccountBalance(WebDriver driver, ExtentTest extentReport) throws Exception {
    
    return UIInteraction.getText(txtAccountBalance, "Account Balance", driver, extentReport, true);
  }
}
