package com.ssp.uxp_HeartlandPages;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.utils.heartland.Interactions;
import com.sun.webkit.Utilities;


public class IncomeSummary extends BaseTest {

  public WebDriver driver;
  public ExtentTest extentReport;
  public WebElement element;
  public HashMap<String, String> dynamicHashMap;


  public IncomeSummary(WebDriver driver) {

    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  /***
   * Constructor
   * 
   * @param driver
   * @param webSite
   * @param report
   */
  public IncomeSummary(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);

  }

  @FindBy(
      css = "#ctl00_cntMainBody_gvIncomeSummary_ctl02_IncomeSummaryBranchDetail1_ddlBankAccount")
  WebElement drpBankAccount;

  @FindBy(
      css = "#ctl00_cntMainBody_gvIncomeSummary_ctl02_IncomeSummaryBranchDetail1_ddlOfficeAccount")
  WebElement drpOfficeAccount;

  @FindBy(
      css = "#ctl00_cntMainBody_gvIncomeSummary_ctl02_IncomeSummaryBranchDetail1_GISLookup_MediaType")
  WebElement drpMediaType;
  
  @FindBy(css = "#ctl00_cntMainBody_btnProcess")
  WebElement btnProcess;

  @FindBy(css = "#ctl00_cntMainBody_btnBack")
  WebElement btnBack;

  @FindBy(css = "#ctl00_cntMainBody_gvIncomeSummary_ctl02_IncomeSummaryBranchDetail1_lblBankAmount")
  WebElement lblBankAccountAmount;

  @FindBy(
      css = "#ctl00_cntMainBody_gvIncomeSummary_ctl02_IncomeSummaryBranchDetail1_lblOfficeAmount")
  WebElement lblOffcAccountAmount;

  public void selectAccountsdropdown(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) {

    try {
      UIInteraction.selectDropdownByVisibleText(drpBankAccount, "Select Bank account",
          testdata.get("BankAC"), driver, extentReport, false);
      UIInteraction.selectDropdownByVisibleText(drpOfficeAccount, "Select Office account",
          testdata.get("OfficeAC"), driver, extentReport, false);
    } catch (Exception e) {
     
      e.printStackTrace();
    }
  }

  public void selectMediaTypedropdown(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) {

    try {
      UIInteraction.selectDropdownByVisibleText(drpMediaType, "Select media type",
          testdata.get("Media Type"), driver, extentReport, false);
    
    } catch (Exception e) {
     
      e.printStackTrace();
    }
  }
  public void clickProcessButton(WebDriver driver) {

    btnProcess.click();
    WaitUtils.waitForSpinner(driver);
  }

  public void assertBankAccountsAmount(HashMap<String,String> dynamicHashMap,WebDriver driver, ExtentTest extentReport) {
    String bankAmount = null;
    String officeAmount = null;
    boolean status = false;
    System.out.println( dynamicHashMap.get("markedTotal"));
    try {
      
      bankAmount= UIInteraction.getText(lblBankAccountAmount, "Bank Account Amount", driver, extentReport, true);
      officeAmount= UIInteraction.getText(lblOffcAccountAmount, "Office Account Amount", driver, extentReport, true);
      
    } catch (Exception e) {

      e.printStackTrace();
      
    }
    
    String markedTotal = dynamicHashMap.get("markedTotal");
    if (Double.parseDouble(markedTotal) > 0) {

      if (Double.parseDouble(markedTotal) == (Double.parseDouble(bankAmount)) * (-1)) {
        status = true;
      } else
        status = false;
      Log.softAssertThat(status, "Bank Account amount is appering correctly with correct sign",
          "Bank Account amount is not appering correctly with correct sign", driver, extentReport,
          true);
      if (Double.parseDouble(markedTotal) == Double.parseDouble(officeAmount)) {
        status = true;
      } else
        status = false;
      Log.softAssertThat(status, "Office Account amount is appering correctly with correct sign",
          "Office Account amount is not appering correctly with correct sign", driver, extentReport,
          true);
    }
    
    else {
      System.out.println(Double.parseDouble(markedTotal)+"==="+Double.parseDouble(officeAmount));
      if (Double.parseDouble(markedTotal) == Double.parseDouble(officeAmount)) {
        status = true;
      } else
        status = false;
      Log.softAssertThat(status, "Office Account amount is appering correctly with correct sign",
          "Office Account amount is not appering correctly with correct sign", driver, extentReport,
          true);
      System.out.println((Double.parseDouble(markedTotal)*(-1))+"==="+Double.parseDouble(bankAmount));
      if ((Double.parseDouble(bankAmount)==Double.parseDouble(markedTotal) *(-1))) {
        status = true;
      } else
        status = false;
      Log.softAssertThat(status, "Bank Account amount is appering correctly with correct sign",
          "Bank Account amount is not appering correctly with correct sign", driver, extentReport,
          true);
    }

  }
}


