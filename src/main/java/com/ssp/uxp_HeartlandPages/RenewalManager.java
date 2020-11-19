package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
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
import com.ssp.utils.ElementLayer;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.utils.heartland.Interactions;

public class RenewalManager extends LoadableComponent<RenewalManager> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;


  @FindBy(css = "#ctl00_cntMainBody_pnlsearch > div > div.card-heading > h1")
  WebElement txtRenewalManager;

  @FindBy(css = "#ctl00_cntMainBody_txtPolicyNo")
  WebElement fldPolicyNo;

  @FindBy(css = "#ctl00_cntMainBody_btnFilter")
  WebElement btnFind;

  @FindBy(css = "#ctl00_cntMainBody_grdvRenQuotes_ctl02_lnkbtnSelect")
  WebElement btnDetails;

  @FindBy(css = "#ctl00_cntMainBody_btnStatus")
  WebElement btnStatus;

  @FindBy(css = "iframe[name='TB_iframeContent']")
  WebElement frameTB;
  @FindBy(css = "#ctl01_cntMainBody_RenewalStatusType")
  WebElement RenewalStatus;

  @FindBy(css = "#ctl01_cntMainBody_btnUpdateStatus")
  WebElement updatebtn;

  @FindBy(xpath = "//*[@id=\"ctl01_cntMainBody_confirmYC_btnYes\"]")
  WebElement Yesbtn;

  public String tableDetails = "//*[@id='ctl00_cntMainBody_grdvRenQuotes']//tr";

  public RenewalManager(WebDriver driver, ExtentTest report) {
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
        .withMessage("Renewal Manager not loaded")
        .until(ExpectedConditions.visibilityOf(txtRenewalManager));

  }

  /***
   * 
   * Verify whether renewal manager page is loaded.
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyRenewalManager() throws Exception {

    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, txtRenewalManager))
      throw new Exception();
    return true;
  }

  /***
   * 
   * Search for policy on Renewal manager
   * 
   * @param policyNo
   * @throws Exception
   */
  public void searchForPolicyRenewal(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.sendKeys(fldPolicyNo, "Policy No", policyNo, driver, extentReport, false);
    UIInteraction.click(btnFind, "Find Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * 
   * Select search policy on Renewal Manager
   * 
   * @throws Exception
   */
  public void selectPolicy(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnDetails, "Details button", driver, extentReport, true);


  }

  /*****
   * Method to mark policy
   * 
   * @param reference
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void markPolicy(String reference, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.selectPolicyCheckbox(reference, driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * Method to perform click on status button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Anjana.Singh
   */
  public void clickStatusButton(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnStatus, "Status button", driver, extentReport, true);
  }

  /****
   * Method to switch to frameTB
   * 
   * @param driver
   */
  public void switchToFrame(WebDriver driver) {
    driver.switchTo().frame(frameTB);
  }

  /****
   * Method to switch out of frame
   * 
   * @param driver
   */
  public void switchOutFrame(WebDriver driver) {
    driver.switchTo().defaultContent();
  }

  /****
   * Method to update status of policy 
   * @param testData
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void updateStatus(HashMap<String, String> testData, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    UIInteraction.selectDropdownByVisibleText(RenewalStatus, "Renewal Status",
        testData.get("Quote Status"), driver, extentReport, true);
    UIInteraction.click(updatebtn, "Update Status", driver, extentReport, true);
    UIInteraction.click(Yesbtn, "OK", driver, extentReport, true);


  }
/****
 * Method to read data from the table
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
   * Method to click Details button and accept alert
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean clickDetailsButtonAndAcceptAlert(String input, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    return Interactions.clickButtonAndHandleAlert(btnDetails, input, "Details button", driver,
        extentReport, true);
  }

}
