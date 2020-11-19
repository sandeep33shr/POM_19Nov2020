package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
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

public class HomePage extends LoadableComponent<HomePage> {

  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;
  
  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl00_RptCategorySpanText")
  WebElement homeIcon;

  @FindBy(css = "#ctl00_cntMainBody_ddlClientType")
  WebElement drpdwnClientType;

  @FindBy(css = "#ctl00_cntMainBody_txtClientName")
  WebElement txtClientName;

  @FindBy(css = "#ctl00_cntMainBody_txtClientCode")
  WebElement txtClientCode;

  @FindBy(css = "#ctl00_cntMainBody_btnSearch")
  WebElement btnFindClient;

  @FindBy(css = "#ctl00_cntMainBody_grdvSearchResults_ctl02_lnkDetails")
  WebElement btnSelectClient;

  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl02_RptCategorySpanText")
  WebElement linkPolicy;

  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl02_rptSection_ctl01_rptItem_ctl00_A1")
  WebElement linkRenewalManager;

  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl02_rptSection_ctl01_rptItem_ctl01_A1")
  WebElement linkRenewalSelection;

  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl03_RptCategorySpanText")
  WebElement linkFinance;

  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl01_rptItem_ctl01_A1")
  WebElement linkCollection;

  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl01_rptItem_ctl00_A1")
  WebElement linkPayment;
  
  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl00_rptItem_ctl00_A1")
  WebElement linkTransactions;

  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl01_rptItem_ctl02_A1")
  WebElement linkManualJournal;
  
  @FindBy(css="#ctl00_cntMainBody_chkIncludeClosedBranches")
  WebElement checkBoxIncludeClosedBranches;
  
  @FindBy(css="#ctl00_SideInfo_ClientInfoCtrl_hypClientDocsBroker")
  WebElement linkDocStore;
  
  @FindBy(css="#linkinfoSection")
  WebElement linkInfo;
  
  @FindBy(css="#infoSection > div > div > div.p.p-h-md.bg-light > a")
  WebElement linkCloseInfo;
  
  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl01_RptCategoryI")
  WebElement linkClient;
  
  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl01_rptSection_ctl00_rptItem_ctl02_A1")
  WebElement linkNewCorporateClient;
  
  @FindBy(css ="#ctl00_RibbonMenu_rptCategory_ctl01_rptSection_ctl00_rptItem_ctl01_A1")
  WebElement linkNewPersonalClient;
  
  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl02_rptItem_ctl00_A1")
  WebElement linkBankReconcilliation;

  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl00_RptCategorySpanText")
  WebElement linkHomeMenu;
  
  @FindBy(css="#ctl00_RibbonMenu_rptCategory_ctl00_rptSection_ctl00_rptItem_ctl00_A1")
  WebElement linkWorkManager;
  
  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl02_rptItem_ctl01_A1")
  WebElement linkIncomeClearing;
  
  @FindBy(css = "#ctl00_RibbonMenu_rptCategory_ctl03_rptSection_ctl02_rptItem_ctl02_A1")
  WebElement linkBanking;

  //Akshay
  
  @FindBy (xpath = "//*[@id='nav-sub-s-client']//span[contains(text(),'Find Client')]")
  WebElement linkFindClient;
  
  @FindBy (xpath ="//*[@id='nav-sub-s-accounts']//span[contains(text(),'Transactions')]")
  WebElement linkSearchTransactions;
  
  
  
  //End Akshay

  @FindBy(xpath="//*[@id='ctl00_cntMainBody_txtClientName']")
  WebElement txtClientName2;


  
  public HomePage(WebDriver driver, ExtentTest report) {
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
        .withMessage("SSP Broker Portal not loaded")
        .until(ExpectedConditions.visibilityOf(homeIcon));
  }

  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }

    if (isPageLoaded && !driver.getTitle().contains("SSP - Pure Insurance")) {
      Log.fail("Unable to load the home page", driver, extentReport);
    }
    new ElementLayer(driver);

  }

  /***
   * to verify if home page is loaded
   * @return
   * @throws Exception
   */
  public boolean verifyLogin() throws Exception {
    if (!WaitUtils.waitForElement(driver, homeIcon))
      throw new Exception("Home Page is not loaded");
    return true;
  }


  /***
   * to perform client search using client code
   * 
   * @param clientCode
   * @param clientType
   * @param screenshot
   * @param extendedReport
   * @throws Exception
   */
  public void searchClientUsingCode(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    UIInteraction.sendKeys(txtClientCode, "Client Code", testdata.get("Client Code"),
        driver, extentReport, false);
    UIInteraction.selectDropdownByVisibleText(drpdwnClientType, "Client Type",
        testdata.get("ClientType"), driver, extentReport, false);
    UIInteraction.clickUsingJS(btnFindClient, "Find Button", driver, extentReport, true);
  }

  /***
   * 
   * to verify client search returned result
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyClientSearch() throws Exception {
    if (!WaitUtils.waitForElement(driver, btnSelectClient))
      throw new Exception("Client search not successful");
    return true;
  }

  /***
   * 
   * click Select client button.
   * 
   * @param extentReport
   * @throws Exception
   */
  public void selectClient(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(btnSelectClient, "Select button", driver, extentReport, true);
  }

  /****
   * method to navigate to Renewal Selection screen
   * 
   * @throws Exception
   */
  public void navigateToRenewalSelection(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.click(linkPolicy, "Policy", driver, extentReport,false);
    UIInteraction.click(linkRenewalSelection, "Renewal Selection", driver, extentReport,false);

  }

  /****
   * method to navigate to Renewal Manager Screen
   * 
   * @throws Exception
   */
  public void navigateToRenewalManager(WebDriver driver, ExtentTest extentReport) throws Exception {
    
    UIInteraction.click(linkPolicy, "Policy", driver, extentReport,false);
    UIInteraction.click(linkRenewalManager, "Renewal Manager", driver, extentReport,false);
  }

  /****
   * method to navigate to Collection Screen
   * 
   * @throws Exception
   */
  public void navigateToCollection(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkFinance, "Finance", driver, extentReport,false);
    UIInteraction.click(linkCollection, "Collection", driver, extentReport,false);
  }
  /****
   * method to navigate to Payments Screen
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToPayment(WebDriver driver, ExtentTest extentReport) throws Exception {
    
    UIInteraction.click(linkFinance, "Finance", driver, extentReport, false);
    UIInteraction.click(linkPayment, "Payments", driver, extentReport, false);
  }
  /****
   * Method to navigate to transactions screen
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToTransaction(WebDriver driver, ExtentTest extentReport) throws Exception{
    
    UIInteraction.click(linkFinance, "Finance", driver, extentReport, false);
    UIInteraction.click(linkTransactions, "Transactions", driver, extentReport, false);
  }
  
  /****
   * Method to navigate to Manual Journal screen
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToManualJournal(WebDriver driver, ExtentTest extentReport) throws Exception {
    
    UIInteraction.click(linkFinance, "Finance", driver, extentReport, false);
    UIInteraction.click(linkManualJournal, "Manual Journal", driver, extentReport, false);
  }
  
  /****
   * Method to navigate to New Corporate Client
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToNewCorporateClient(WebDriver driver, ExtentTest extentReport) throws Exception{
    
    UIInteraction.click(linkClient,"Client", driver, extentReport, false);
    UIInteraction.click(linkNewCorporateClient, "New Corporate Client", driver, extentReport, false);
  }
  /*****
   * 
   * Method to perform validation on includeCLosedBranchCheckbox.
   * @param testData
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   */
  
  public boolean includeClosedBranchedCheckbox(HashMap<String, String> testData, WebDriver driver, ExtentTest extentReport) throws Exception{
    
   return UIInteraction.validateCheckBox(checkBoxIncludeClosedBranches, driver, testData.get("Include Closed branch"), extentReport);

   
  }
  /****
   * Method to navigate to DocStore.
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToDocStore(WebDriver driver,ExtentTest extentReport) throws Exception{

        Log.event("Navigate to DocStore");
        UIInteraction.click(linkInfo, "Info link", driver, extentReport,false);
        UIInteraction.click(linkDocStore, "Doc Store Link", driver, extentReport,false);
        Thread.sleep(2000);
        UIInteraction.switchToTab(driver);
  }
  
  /****
   * Method to close the info section
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void closeInfoSection(WebDriver driver, ExtentTest extentReport) throws Exception{
    
    try{
      Thread.sleep(2000);
      UIInteraction.click(linkCloseInfo, "Close info section", driver, extentReport, true);
    }
    catch(Exception e){
      throw new Exception ("Unable to close the info section "+ e);
    }
  }
  
  
  /****
   * Method to navigate to create new personal client 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
 public void navigateToNewPersonalClient(WebDriver driver, ExtentTest extentReport) throws Exception{
    
    UIInteraction.click(linkClient,"Client", driver, extentReport, false);
    UIInteraction.click(linkNewPersonalClient, "New Personal Client", driver, extentReport, false);
  }
 /*****
  * Method to navigate to bank reconciliation 
  * @param driver
  * @param extentReport
  * @throws Exception
  */
 public void navigateToBankReconciliation(WebDriver driver, ExtentTest extentReport) throws Exception {
   
   UIInteraction.click(linkFinance, "Finance", driver, extentReport, false);
   UIInteraction.click(linkBankReconcilliation, "Bank Reconciliation", driver, extentReport, true);
 }

 /****
  * Method to navigate to WorkManager screen
  * 
  * @param driver
  * @param extentReport
  * @throws Exception
  */
 public void navigateToWorkManager(WebDriver driver, ExtentTest extentReport) throws Exception {
   
   UIInteraction.click(linkHomeMenu, "Finance", driver, extentReport, false);
   UIInteraction.click(linkWorkManager, "Manual Journal", driver, extentReport, false);
 }
 
 public void enterClientName(WebDriver driver, ExtentTest extentReport) throws Exception{
   UIInteraction.sendKeys(txtClientName2, "Label in report", "Demo", driver, extentReport, true);
 }
 
 /****
  * Method to navigate to search client screen
  * @param driver
  * @param extentReport
  * @throws Exception
  */
 public void navigateToSearchClientScreen(WebDriver driver, ExtentTest extentReport) throws Exception {

   UIInteraction.click(linkClient,"Client", driver, extentReport, false);
   UIInteraction.click(linkFindClient, "Find Client", driver, extentReport, true);
      
   }
 public void navigateToIncomeClearing(WebDriver driver, ExtentTest extentReport) throws Exception {
   
   UIInteraction.click(linkFinance, "Finance", driver, extentReport, false);
   UIInteraction.click(linkIncomeClearing, "Income Clearing", driver, extentReport, false);
 }
 public void navigateToBanking(WebDriver driver, ExtentTest extentReport) throws Exception {
   
   UIInteraction.click(linkFinance, "Finance", driver, extentReport, false);
   UIInteraction.click(linkBanking, "Banking", driver, extentReport, false);
 }
}
