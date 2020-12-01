package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;


public class IncomeClearing extends BaseTest {

  public WebDriver driver;
  public ExtentTest extentReport;
  public WebElement element;
  public HashMap<String, String> dynamicHashMap = new HashMap<>();
  
  

  public IncomeClearing(WebDriver driver) {

    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = "#ctl00_cntMainBody_btnFind")
  WebElement btnFind;

  @FindBy(css = "#ctl00_cntMainBody_grdvResultIncomeClearing th:nth-child(11)")
  WebElement headerColumnPostingDate;

  @FindBy(css = "#ctl00_cntMainBody_grdvResultIncomeClearing tr")
  List<WebElement> allRows;

  @FindBy(how = How.CSS, using = "#ctl00_cntMainBody_grdvResultIncomeClearing td:nth-child(10)")
  List<WebElement> clmTransRef;

  @FindBy(css = "#ctl00_cntMainBody_grdvResultIncomeClearing td:nth-child(1)")
  List<WebElement> allChkboxes;

  @FindBy(css = "#ctl00_cntMainBody_grdvResultIncomeClearing th")
  List<WebElement> headerColumns;
  
  @FindBy(css = "#ctl00_cntMainBody_txtTotalMarked")
  WebElement fldMarkedTotal;

  @FindBy(css = "#ctl00_cntMainBody_chkAll")
  WebElement chkAll;
  
  @FindBy(css = "#ctl00_cntMainBody_chkCommision")  
  WebElement chkComm;
  
  @FindBy(css = "#ctl00_cntMainBody_chkFee")
  WebElement chkFEE;
  
  @FindBy(css = "#ctl00_cntMainBody_btnUnmarkAll")
  WebElement btnUnmarkAll;
  
  @FindBy(css = "#ctl00_cntMainBody_btnProcess")
  WebElement btnProcess;

  /***
   * Constructor
   * 
   * @param driver
   * @param webSite
   * @param report
   */
  public IncomeClearing(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);

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
  public void clickFind() {
    try {
      UIInteraction.click(btnFind, "Click Find button", driver, extentReport, false);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  public void clickUnmarkAll() {
    try {
      if (btnUnmarkAll.getAttribute("disabled") == null) {
        UIInteraction.click(btnUnmarkAll, "Click UnmarkAll button", driver, extentReport, false);
        WaitUtils.waitForSpinner(driver);
      }
          
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  public void clickProcessButton() {
    try {
      
      if (btnProcess.getAttribute("disabled") == null) {
        UIInteraction.click(btnProcess, "Click Process button", driver, extentReport, false);
        WaitUtils.waitForSpinner(driver);
      }
          
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void sortByPostingDate() {
    try {
      UIInteraction.click(headerColumnPostingDate, "sort by posting date", driver, extentReport,
          false);
      WaitUtils.waitForSpinner(driver);
      UIInteraction.click(headerColumnPostingDate, "sort by posting date", driver, extentReport,
          false);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
 
 public void checkAllChekbox(WebDriver driver, ExtentTest extentReport){
   
   chkAll.click();
   }
 public void checkCommissionChekbox(WebDriver driver, ExtentTest extentReport){
   
   chkComm.click();
   }
 public void checkFeeChekbox(WebDriver driver, ExtentTest extentReport){
   
   chkFEE.click();
   }
 public void assertMarkedValueWrtCTV(HashMap<String,String> dynamicHashMap,String docRef,String value,WebDriver driver, ExtentTest extentReport){
   
   String markedTotal=null;
   boolean status=false;
   double markedVal=0.00;
   
   try {
    markedTotal=UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false);
  WaitUtils.waitForSpinner(driver);
    dynamicHashMap.put("markedTotal", markedTotal);
    System.out.println(dynamicHashMap.get("markedTotal"));
    
  } catch (Exception e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
  }
     
   
   if(markedTotal.equals(value)){
     status=true;
   }else status=false;
   Log.softAssertThat(status, "Marked Total is equal to CTV value of toclear field", "Marked Total is not equal to CTV value of toclear field", driver, extentReport, true);
   
   String viewLink= "//td[contains(text(),'".concat(docRef).concat("')]/following-sibling::td/a");
   System.out.println(viewLink);
   WebElement view = driver.findElement(By.xpath(viewLink));
    try {
    UIInteraction.clickUsingJS(view, "Clicking", driver, extentReport, false);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
   
   TransactionScreen searchPage = new TransactionScreen(driver, extentReport);
   WaitUtils.waitForSpinner(driver);
   searchPage.switchToCTVScreen(driver);
    status= searchPage.checkCommissionHyperlinkAvailability(driver, extentReport);
   try {
    searchPage.closeCTVScreen(driver, extentReport);
    WaitUtils.waitForSpinner(driver);
    searchPage.switchOutOfCTVScreen(driver, extentReport);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  Log.softAssertThat(status, "By default Income tab of CTV is displayed on clicking view via Income clearing", "By default Income tab of CTV is not displayed on clicking view via Income clearing", driver, extentReport, false);
  }
   
   
 }

 



