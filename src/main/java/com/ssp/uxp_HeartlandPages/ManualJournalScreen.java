package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import java.util.List;
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

public class ManualJournalScreen extends LoadableComponent<ManualJournalScreen> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;


  @FindBy(xpath = "//h1[contains(text(),'Manual Journal')]")
  WebElement headerManaulJournal;

  @FindBy(css = "#ctl00_cntMainBody_txtComments")
  WebElement fldTransactionReason;

  @FindBy(css = "#ctl00_cntMainBody_btnAdd")
  WebElement btnAdd;

  @FindBy(css = "#ctl00_cntMainBody_btnFinish")
  WebElement btnPost;

  @FindBy(css = "#ctl00_cntMainBody_btnCancel")
  WebElement btnCancel;

  @FindBy(css = "#ctl00_cntMainBody_btnAccountCode")
  WebElement btnSearch;

  @FindBy(css = "#ctl01_cntMainBody_txtName")
  WebElement fldAccountName;

  @FindBy(css = "#ctl01_cntMainBody_btnFindNow")
  WebElement btnFindNow;

  @FindBy(css = "#ctl01_cntMainBody_grdvFindAccount_ctl02_btnSelect")
  WebElement btnSelect;

  @FindBy(css = "#ctl00_cntMainBody_PMLookup_CurrencyType")
  WebElement dropdownCurrency;

  @FindBy(css = "#ctl00_cntMainBody_txtAmount")
  WebElement fldAmount;

  @FindBy(css = "#ctl00_cntMainBody_txtPolicyRef")
  WebElement fldPolicyRef;

  @FindBy(css = "#ctl00_cntMainBody_btnOk")
  WebElement btnOk;

  @FindBy(css = "#divGenerateRef > div > div > div.modal-body")
  WebElement txtJournalRef;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frameTB;

  @FindBy(css = "#ctl01_cntMainBody_txtShortName")
  WebElement fldAccountCode;

  @FindBy(css = "#ctl00_cntMainBody_PMLookup_DocumentType")
  WebElement dropdownType;


  @Override
  protected void isLoaded() throws Error {
    isPageLoaded = true;

  }

  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !driver.getTitle().contains("SSP - Pure Insurance")) {
      Log.fail("Unable to load the manual journal screen", driver, extentReport);
    }
    new ElementLayer(driver);


  }

  /****
   * Constructor of class
   * 
   * @param driver
   * @param report
   */
  public ManualJournalScreen(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);

  }

  /****
   * Verify manual journal page is loaded
   * 
   * @return
   */
  public boolean verifyManualJournal() {
    WaitUtils.waitForSpinner(driver);

    new WebDriverWait(driver, 60)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Manual Journal screen not loaded")
        .until(ExpectedConditions.visibilityOf(headerManaulJournal));
    return true;
  }

  /*****
   * Method to create a transaction
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public String createTransaction(HashMap<String, String> testData, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    String journalRef;
    UIInteraction.selectDropdownByVisibleText(dropdownType, "Transaction type",
        testData.get("Transaction Type"), driver, extentReport, true);
    
    UIInteraction.sendKeys(fldTransactionReason, "Tranaction Reason",
        testData.get("Transaction reason"), driver, extentReport, true);
    UIInteraction.click(btnAdd, "Add button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSearch, "Search Icon", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frameTB);
    UIInteraction.sendKeys(fldAccountCode, "Account Code", testData.get("Client Code"), driver,
        extentReport, false);
    UIInteraction.click(btnFindNow, "Find now button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelect, "Select Account Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();

    UIInteraction.sendKeysViaActionClass(fldAmount, "Amount", testData.get("Amount"), driver,
        extentReport, true);

    if (testData.get("Currency") != "")
      UIInteraction.selectDropdownByVisibleText(dropdownCurrency, "Currency",
          testData.get("Currency"), driver, extentReport, false);

    UIInteraction.click(btnOk, "Ok Button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnAdd, "Add button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSearch, "Search Icon", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().frame(frameTB);
    UIInteraction.sendKeys(fldAccountName, "Account Name", testData.get("Second Account Name"),
        driver, extentReport, false);
    UIInteraction.click(btnFindNow, "Find now button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnSelect, "Select Account Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();
    UIInteraction.click(btnOk, "Ok Button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnPost, "Post button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    journalRef = UIInteraction.getText(txtJournalRef, "Journal Ref", driver, extentReport, true);

    UIInteraction.click(btnOk, "Ok button", driver, extentReport, false);

    return journalRef;

  }

  /****
   * Validate that the drop-down list Type contains the text passed as option or not.
   * 
   * @param text
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   */
  public boolean verifyTypeDropdownListOptions(String text, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    boolean status;
    List<WebElement> options =
        UIInteraction.getAllOptionsInDropdown(dropdownType, "Type dropdown", driver, extentReport);

    for (WebElement itr : options) {

      if (itr.getText().equalsIgnoreCase(text))
        status = true;
    }
    return true;
  }

  public boolean verifyCurrencyDropdownOptions(String text, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    boolean status = false, count = false;

    List<WebElement> currency = UIInteraction.getAllOptionsInDropdown(dropdownCurrency,
        "Currency dropdown", driver, extentReport);

    if (currency.size() == 1)
      count = true;
    for (WebElement itr : currency) {

      if (itr.getText().equalsIgnoreCase(text))
        status = true;

    }

    return (count && status);
  }


  public void clickAddButton(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnAdd, "Add button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }


}
