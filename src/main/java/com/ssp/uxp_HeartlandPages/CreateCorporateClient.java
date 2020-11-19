package com.ssp.uxp_HeartlandPages;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.openqa.selenium.Keys;
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
import com.ssp.utils.ElementLayer;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;

public class CreateCorporateClient extends LoadableComponent<CreateCorporateClient> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  
  @FindBy(xpath = "//*[contains(text(),'Add Corporate Client')]")
  WebElement headerAddCorporateClient;

  @FindBy(css = "#ctl00_cntMainBody_txtCompanyName")
  WebElement fldCompanyName;

  @FindBy(css = "#ctl00_cntMainBody_txtMainContact")
  WebElement fldMainContact;

  @FindBy(css = "#ctl00_cntMainBody_ddlAccountExecutive")
  WebElement dropdownAccountExecutive;

  @FindBy(css = "#ctl00_cntMainBody_txtSalutation")
  WebElement fldSalutation;

  @FindBy(css = "a[href='#tab-editadditionalinformation']")
  WebElement tabAdditionalInfo;

  @FindBy(css = "#ctl00_cntMainBody_GISLookup_TurnOver")
  WebElement dropdownTurnover;

  @FindBy(css = "a[href='#tab-editaddresses']")
  WebElement tabAddress;

  @FindBy(css = "#ctl00_cntMainBody_Addresses_hypAddress")
  WebElement btnAddAddress;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frameTB;

  @FindBy(css = "#ctl00_cntMainBody_txtLookup_PostCode")
  WebElement fldPostCode;

  @FindBy(css = "#ctl00_cntMainBody_btnFindAddress")
  WebElement btnFindAddress;

  @FindBy(css = "#ctl00_cntMainBody_selAddressList")
  WebElement dropdownSelectAddress;

  @FindBy(css = "#ctl00_cntMainBody_btnAddAddress")
  WebElement btnAdd;

  @FindBy(css = "a[href='#tab-editcontacts']")
  WebElement tabContacts;

  @FindBy(css = "#ctl00_cntMainBody_Contact_ddlPreferredCorrespondenceType")
  WebElement dropdownCorrespondenceType;

  @FindBy(css = "#ctl00_cntMainBody_Contact_hypContact")
  WebElement btnAddContact;

  @FindBy(css = "#ctl00_cntMainBody_GISContactType")
  WebElement dropdownContactType;

  @FindBy(css = "#ctl00_cntMainBody_txtNumber")
  WebElement fldEmail;

  @FindBy(css = "#ctl00_cntMainBody_btnAddContacts")
  WebElement btnAddContactInFrame;

  @FindBy(css = "#ctl00_cntMainBody_btnSubmit")
  WebElement btnSubmit;


  public CreateCorporateClient(WebDriver driver,ExtentTest report) {
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
        .withMessage("Add Corporate Client not loaded")
        .until(ExpectedConditions.visibilityOf(headerAddCorporateClient));

  }

  /****
   * To create a corporate client.
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void createCorporateClient(WebDriver driver, ExtentTest extentReport) throws Exception {

    
 
    
    

    UIInteraction.click(tabAdditionalInfo, "Additional Info", driver, extentReport, false);
    UIInteraction.selectDropdownByIndex(dropdownTurnover, "Turnover Amount", "2", driver,
        extentReport, true);

    UIInteraction.click(tabAddress, "Addresses", driver, extentReport, false);
    UIInteraction.click(btnAddAddress, "Add Address", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

    driver.switchTo().frame(frameTB);
    UIInteraction.sendKeys(fldPostCode, "Postcode", "B37 7YE", driver, extentReport, false);
    fldPostCode.sendKeys(Keys.TAB);
    UIInteraction.click(btnFindAddress, "Find Address", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectDropdownByIndex(dropdownSelectAddress, "Select Address", "1", driver,
        extentReport, true);
    Thread.sleep(2000);
    UIInteraction.click(btnAdd, "Add", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();

    UIInteraction.click(tabContacts, "Contacts", driver, extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownCorrespondenceType, "Correspondence Type",
        "Email", driver, extentReport, false);
    UIInteraction.click(btnAddContact, "Add Contact", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

    driver.switchTo().frame(frameTB);
    UIInteraction.selectDropdownByVisibleText(dropdownContactType, "Contact Type", "Email", driver,
        extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.sendKeys(fldEmail, "Email", "sanity@gmail.com", driver, extentReport, true);
    UIInteraction.click(btnAddContactInFrame, "Add", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    driver.switchTo().defaultContent();

    UIInteraction.click(btnSubmit, "Submit Button", driver, extentReport, true);

  }
  
  public void enterBasicDetails(HashMap<String, String> testdata, WebDriver driver, ExtentTest extentReport) throws Exception {
    
    Timestamp timestamp = new Timestamp(new Date().getTime());
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yy");
    
    UIInteraction.sendKeys(fldCompanyName, "Company Name",
        "Sanity " + dateFormat.format(timestamp) , driver, extentReport, false);
    UIInteraction.sendKeys(fldMainContact, "Main Contact", "Sanity", driver, extentReport, false);
    UIInteraction.selectDropdownByIndex(dropdownAccountExecutive, "Account Executive", "1", driver,
        extentReport, false);
    UIInteraction.sendKeys(fldSalutation, "Salutation", "Miss", driver, extentReport, true);
  }
}
