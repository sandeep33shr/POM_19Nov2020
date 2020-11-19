package com.ssp.uxp_HeartlandPages;

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

public class RenewalSelection extends LoadableComponent<RenewalSelection> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;


  @FindBy(xpath = "//h1[contains(text(),'Renewal Selection')]")
  WebElement txtRenewalSelection;

  @FindBy(css = "#ctl00_cntMainBody_txtReference")
  WebElement fldReference;

  @FindBy(css = "#ctl00_cntMainBody_btnFindNow")
  WebElement btnFind;

  @FindBy(css = "#ctl00_cntMainBody_grdvSearchResults_ctl02_lnkSelect")
  WebElement btnSelect;


  @FindBy(xpath = "//*[@id='ctl00_cntMainBody_grdvSearchResults']//tbody//tr//td[3]")
  WebElement txtRenewalStatus;


  public RenewalSelection(WebDriver driver,ExtentTest report) {
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
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);

    new WebDriverWait(driver, 30)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Renewal Selection not loaded")
        .until(ExpectedConditions.visibilityOf(txtRenewalSelection));

  }

  /****
   * 
   * verify navigate to renewal selection screen
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyRenewalSelection() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, txtRenewalSelection))

      throw new Exception("Renewal Selection Page is not loaded");
    return true;
  }

  /***
   * Search policy for renewal
   * 
   * @param policyNo
   * @param screenshot
   * @throws Exception
   */
  public void searchForRenewal(String policyNo, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    Log.event("Search for renewal");
    UIInteraction.sendKeys(fldReference, "Reference", policyNo, driver, extentReport, false);
    UIInteraction.click(btnFind, "Find Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * Select policy for renewal
   * 
   * @param screenshot
   * @throws Exception
   */
  public void selectForRenewal(WebDriver driver, ExtentTest extentReport) throws Exception {

    Log.event("Select for Renewal");
    UIInteraction.click(btnSelect, "Select button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * 
   * To verify renewal status
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyRenewalStatus(WebDriver driver, ExtentTest extentReport) throws Exception {

    WaitUtils.waitForSpinner(driver);
    if (!UIInteraction.getText(txtRenewalStatus, "Renewal Status", driver, extentReport, true)
        .equalsIgnoreCase("Under Renewal"))
      throw new Exception();
    return true;
  }

}
