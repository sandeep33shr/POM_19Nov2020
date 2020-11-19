package com.ssp.uxp_HeartlandPages;

import org.openqa.selenium.By;
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

public class WorkManager extends LoadableComponent<WorkManager> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  public ElementLayer uielement;

  @FindBy(xpath = "//h1[contains(text(),'Work Manager')]")
  WebElement workManagerHeader;

  
  public WorkManager(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void isLoaded() throws Error {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
    new WebDriverWait(driver, 30)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Payment screen not loaded")
        .until(ExpectedConditions.visibilityOf(workManagerHeader));
  }

  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }

    if (isPageLoaded && !driver.getTitle().contains("SSP - Pure Insurance")) {
      Log.fail("Unable to load the work manager page", driver, extentReport);
    }
    uielement = new ElementLayer(driver);

  }
  
  /*****
   * 
   * method to click on start button on work manager screen corresponding to the input passed
   * 
   * @param input
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public static void clickingOnStartButtonBasedOnInputPassed(String input, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {

    try {
      
      String beforexpath="//table//td[contains(text(),'";
      String afterxpath="')]/preceding-sibling::td//a";
      String xpath=beforexpath+input+afterxpath;

      System.out.println(xpath);
      WebElement linkStart = driver.findElement(By.xpath(xpath));

      UIInteraction.click(linkStart, "Click on start button",
          driver, extentReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while clicking button" + e);
    }
  }

  
 
}
