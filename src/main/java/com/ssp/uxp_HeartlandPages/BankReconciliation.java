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
import com.ssp.utils.ElementLayer;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;

public class BankReconciliation extends LoadableComponent<BankReconciliation> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  
  @FindBy(xpath ="//h1[contains(text(),'Bank Reconciliation')]")
  WebElement headerBankReconciliation;
  
  @FindBy(css="#ctl00_cntMainBody_btnFind")
  WebElement btnFind;
  
  @FindBy(css="#ctl00_cntMainBody_grdDetails_ctl02_chkMarked")
  WebElement checkboxFirstTransaction;
  
  @FindBy(css="#ctl00_cntMainBody_btnReconcile")
  WebElement btnReconcile;
  
  @FindBy(css="#ctl00_cntMainBody_confirmYNC_btnYes")
  WebElement btnConfirmYes;
  
  @FindBy(xpath="//div[@class='bootstrap-dialog-title'][contains(text(),'Bank Reconciliation Report')]")
  WebElement txtBankReconciliationReport;
  
  @FindBy(css="#ctl00_cntMainBody_btnClose")
  WebElement btnClose;
  
  public BankReconciliation(WebDriver driver, ExtentTest report) {
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
        .withMessage("Bank Reconciliation Screen not loaded")
        .until(ExpectedConditions.visibilityOf(headerBankReconciliation));

  }

  /****
   * 
   *Method to  verify navigation to bank reconciliation
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyBankReconciliation() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, headerBankReconciliation))

      throw new Exception("Bank Reconciliation Screen is not loaded");
    return true;
  }
  
  /****
   * Method to perform reconciliation
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void performReconciliation(WebDriver driver, ExtentTest extentReport) throws Exception{
    
    UIInteraction.click(btnFind, "Find button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(checkboxFirstTransaction, "Marked checkbox of first transaction", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnReconcile, "Reconcile button", driver, extentReport, true);
    UIInteraction.click(btnConfirmYes, "Confirm Yes button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }
  
  /****
   * Method to verify bank reconciliation report is loaded.
   * @return
   * @throws Exception
   *
   **/
  public boolean verifyReconciliation() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver,txtBankReconciliationReport ))

      throw new Exception("Bank Reconciliation report is loaded");
    return true;
  }
  
  /****
   * Method to close the bank reconciliation report. 
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void close(WebDriver driver, ExtentTest extentReport) throws Exception{
    
    UIInteraction.click(btnClose, "Close button", driver, extentReport, false);
  }
  
}
