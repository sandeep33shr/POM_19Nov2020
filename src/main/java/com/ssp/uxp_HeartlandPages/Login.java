package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;


public class Login extends LoadableComponent<Login> {

  public final WebDriver driver;
  public final ExtentTest extentReport;
  private boolean isPageLoaded;
  private String sspURL;

  @FindBy(css = "[title='Login']")
  WebElement loginTitle;

  @FindBy(css = "#username")
  WebElement fldUsername;

  @FindBy(css = "#password")
  WebElement fldPassword;

  @FindBy(css = "#loginModal > div > div > div.card-footer > button")
  WebElement btnLogin;
  
  @FindBy(css="#ctl00_cntMainBody_txtClientName")
  WebElement fieldName;

  

  /***
   * Constructor
   * 
   * @param driver
   * @param webSite
   * @param report
   */
  public Login(WebDriver driver, String webSite, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    sspURL = webSite;
    PageFactory.initElements(driver, this);
    Log.message("URL is :" + sspURL);
  }


  @Override
  /***
   * To load page wait
   */
  protected void isLoaded() throws Error {


    WaitUtils.waitForPageLoad(driver);
    
    
    if (!isPageLoaded) {
      Assert.fail();
    }

    if (isPageLoaded && !driver.getTitle().contains("Login")) {
      Log.fail("SSP Login Page did not open up. Site might be down.", driver, extentReport);
    }

  }

  @Override
  protected void load() {
    isPageLoaded = true;
  }

  /****
   * 
   * method to perform login
   * 
   * @param username
   * @param password
   * @param screenShot
   * @param extentedReport
   * @throws Exception
   */
  public void loginToSSP(HashMap<String, String> testData, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    if (sspURL != null)
       driver.get(sspURL);
    
    UIInteraction.sendKeys(fldUsername, "Username", testData.get("Username"), driver, extentReport,
        false);
    UIInteraction.sendKeys(fldPassword, "Password", testData.get("Password"), driver, extentReport,
        false);
    UIInteraction.clickUsingJS(btnLogin, "Login", driver, extentReport, true);

  }


}
