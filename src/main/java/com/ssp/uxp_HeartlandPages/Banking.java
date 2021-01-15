package com.ssp.uxp_HeartlandPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import com.ssp.utils.heartland.Interactions;


public class Banking extends BaseTest {

  public WebDriver driver;
  public ExtentTest extentReport;
  public WebElement element;
  public HashMap<String, String> dynamicHashMap = new HashMap<>();


  public Banking(WebDriver driver) {

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
  public Banking(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);

  }

  @FindBy(css = "#ctl00_cntMainBody_preBankDropdown")
  WebElement drpPreBank;

  @FindBy(xpath = "//h1[contains(text(),'Banking')]")
  WebElement headerBanking;

  @FindBy(css = "#ctl00_cntMainBody_ddlBranch")
  WebElement drpBranch;

  @FindBy(css = "#ctl00_cntMainBody_ddlCollectionPayment")
  WebElement drpCollectionPaymentType;

  @FindBy(css = "#ctl00_cntMainBody_GISLookup_MediaType")
  WebElement drpMediaType;

  @FindBy(css = "#ctl00_cntMainBody_ddlMarkedStatus")
  WebElement drpMediaStatus;

  @FindBy(css = "#ctl00_cntMainBody_ddlMarkedStatus")
  WebElement drpMarkedStatus;

  @FindBy(css = "#ctl00_cntMainBody_txtDateTo")
  WebElement txtPostingDateTo;

  @FindBy(css = "#ctl00_cntMainBody_btnClear")
  WebElement btnClear;

  @FindBy(css = "#ctl00_cntMainBody_btnFind")
  WebElement btnFind;

  @FindBy(css = "#ctl00_cntMainBody_btnShowHideFilters")
  WebElement btnFilter;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank tr")
  List<WebElement> allRows;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank td:nth-child(2)")
  List<WebElement> branchColumn;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank td:nth-child(7)")
  List<WebElement> mediaTypeColumn;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank > div > a")
  List<WebElement> linkPages;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank td:nth-child(5)")
  List<WebElement> columnAmount;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank_ctl01_chkSelectAll")
  WebElement headerChkbox;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank td:nth-child(1)")
  List<WebElement> allChkBoxes;

  @FindBy(css = "#ctl00_cntMainBody_upPreBankFilter> div>div")
  List<WebElement> allFilters;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank td:nth-child(6)")
  List<WebElement> clmTransRef;
  
  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank td:nth-child(4)")
  List<WebElement> clmAccountCode;

  @FindBy(css = "#ctl00_cntMainBody_txtTotalMarked")
  WebElement fldMarkedTotal;

  @FindBy(css = "#ctl00_cntMainBody_btnMarkAll")
  WebElement btnMarkAll;

  @FindBy(css = "#ctl00_cntMainBody_btnUnmarkAll")
  WebElement btnUnmarkAll;

  @FindBy(css = "#ctl00_cntMainBody_btnProcess")
  WebElement btnBank;

  @FindBy(css = "#ctl00_cntMainBody_btnReport")
  WebElement btnReport;

  @FindBy(css = "#ctl00_cntMainBody_btnClose")
  WebElement btnClose;

  @FindBy(css = "#ctl00_cntMainBody_lblFindButtonMessage")
  WebElement lblFindButtonMessage;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank_ctl02_chkMarked")
  WebElement chk1stItem;

  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank tr:nth-child(1)  td:nth-child(6)")
  WebElement transRef1stRow;
  
  @FindBy(css = "#ctl00_cntMainBody_grdvPreBank tr:nth-child(2)  td:nth-child(6)")
  WebElement transRef2ndRow;
  
  @FindBy(css = "#ctl00_cntMainBody_ClientSearch_txtSearch")
  WebElement txtClientSmartSearch;
  
  @FindBy(css = "#ctl00_cntMainBody_ClientSearch_btnClear")
  WebElement btnCrossClientSmartSearch;
  
  @FindBy(css = "li.ui-menu-item>a")
  List<WebElement> listSmartSearch;
  
  @FindBy(css="#lblReportName")
  WebElement txtBankingReportHeadingReport;
  
  
  
  
  public void selectBank(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) {
    try {
      UIInteraction.selectDropdownByVisibleText(drpPreBank, "Select Pre Bank", testdata.get("Bank"),
          driver, extentReport, false);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

  }

  public void selectBranch(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) {
    try {


      UIInteraction.selectDropdownByVisibleText(drpBranch, "Select Branch", testdata.get("Branch"),
          driver, extentReport, false);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public void selectMediaType(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) {
    try {


      UIInteraction.selectDropdownByVisibleText(drpMediaType, "Select MediaType",
          testdata.get("Media Type"), driver, extentReport, false);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public void selectCollectPayType(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) {
    try {
      UIInteraction.selectDropdownByVisibleText(drpCollectionPaymentType,
          "Select Collection Payment Type", testdata.get("Collect. / Pay. Type"), driver,
          extentReport, false);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

  }

  public void clickFind(WebDriver driver, ExtentTest extentReport) {
    try {
      UIInteraction.click(btnFind, "Find BUtton", driver, extentReport, false);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

  }
  
  public void clickUnmarkAll(WebDriver driver, ExtentTest extentReport) {
    try {
      if(btnUnmarkAll.getAttribute("disabled") == null){
      UIInteraction.click(btnUnmarkAll, "UnmarkAll BUtton", driver, extentReport, false);
      WaitUtils.waitForSpinner(driver);
      }
     
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
  }
  
  public void clickBank(WebDriver driver, ExtentTest extentReport) {
    try {
      UIInteraction.click(btnBank, "Bank BUtton", driver, extentReport, false);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }


  }
  public void clickFilterButton(WebDriver driver, ExtentTest extentReport) {
    try {
      UIInteraction.click(btnFilter, "Filter Button", driver, extentReport, false);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }


  }

  public boolean assertMediaTypeColumnValueBasedOnSelectedFilter(WebDriver driver,
      HashMap<String, String> testdata, ExtentTest extentReport) {
    WaitUtils.waitForSpinner(driver);
    int i = -1;
    boolean status = false;
    if (allRows.isEmpty()) {
      Log.softAssertThat(false, "", "No results found for the criteria entered", driver,
          extentReport, false);
    } else {
      do {
        for (WebElement element : mediaTypeColumn) {


          if (!element.getText().equalsIgnoreCase(testdata.get("Media Type"))) {
            status = false;
          } else
            status = true;

        }
        i++;
        if (i < linkPages.size()) {
          linkPages.get(i).click();
          WaitUtils.waitForSpinner(driver);

        }
      } while (i < linkPages.size());
    }
    Log.softAssertThat(status, "All records are as per selected filter",
        "All records in grid are not as per selected filter", driver, extentReport, false);
    return status;
  }

  public boolean assertBranchColumnValueBasedOnSelectedFilter(WebDriver driver,
      HashMap<String, String> testdata, ExtentTest extentReport) {
    WaitUtils.waitForSpinner(driver);
    int i = -1;
    boolean status = false;
    if (allRows.isEmpty()) {
      Log.softAssertThat(false, "", "No results found for the criteria entered", driver);
    } else {
      do {
        for (WebElement element : branchColumn) {
          System.out.println(element.getText());
          System.out.println(testData);
          if (!element.getText().equalsIgnoreCase(testdata.get("Branch"))) {
            status = false;
          } else
            status = true;
                }
        i++;
        if (i < linkPages.size()) {
          linkPages.get(i).click();
          WaitUtils.waitForSpinner(driver);

        }
      } while (i < linkPages.size());
    }
    Log.softAssertThat(status, "All records are as per selected filter",
        "All records in grid are not as per selected filter",driver,extentReport,false);
    return status;
  }

  public boolean checkButtonsState(WebDriver driver, ExtentTest extentReport) {

    boolean status = false;
    String MrkALL = btnMarkAll.getAttribute("disabled");
    String UnmrkALL = btnUnmarkAll.getAttribute("disabled");
    String bank = btnBank.getAttribute("disabled");
    String report = btnReport.getAttribute("disabled");
    String close = btnClose.getAttribute("disabled");
    String find = btnFind.getAttribute("disabled");
    String clear = btnClear.getAttribute("disabled");
    String filters = btnFilter.getAttribute("disabled");

    if (MrkALL.equals("true") && UnmrkALL.equals("true") && bank.equals("true")
        && report.equals("true") && close == null && find == null && clear == null
        && filters == null) {
      status = true;
    } else
      status = false;


    Log.softAssertThat(status, "Default state of buttons are correct",
        "Default state of buttons are Incorrect", driver, extentReport, false);
    return status;

  }

  public boolean assertMarkTotalBasedOnMarkUnmarkAll(WebDriver driver, ExtentTest extentReport) {

    boolean status = false;
    double markedTotal = 0.00;

    try {
      if (btnMarkAll.getAttribute("disabled") == null) {
        btnMarkAll.click();
        WaitUtils.waitForSpinner(driver);
      }

      markedTotal = Double.parseDouble(
          UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    double sum = 0;
    if (allRows.isEmpty()) {
      Log.softAssertThat(false, "", "No results found for the criteria entered", driver);
    } else {

      double sumOfMarkedItems =
          Interactions.sumOfGrid(columnAmount, linkPages, allRows, driver, extentReport);
      System.out.println("sumOfMarkedItems" + sumOfMarkedItems);

      if (Math.floor(sumOfMarkedItems * 100) / 100 == markedTotal) {
        status = true;

      } else
        status = false;
      Log.softAssertThat(status, "Marked total is equal to sum of all marked items",
          "Marked total is not equal to sum of all marked items", driver, extentReport, false);
      WaitUtils.waitForSpinner(driver);
      driver.findElement(By.tagName("body")).sendKeys(Keys.HOME);
      btnUnmarkAll.click();
      WaitUtils.waitForSpinner(driver);
      try {
        markedTotal = Double.parseDouble(
            UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
      } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      if (markedTotal == 0.00) {
        status = true;
      } else
        status = false;
    }

    Log.softAssertThat(status, "After clicking UnmarkAll marked total set to 0.00",
        "After clicking UnmarkAll marked total is not set to 0.00", driver, extentReport, false);
    return status;

  }

  public boolean assertMarkedTotalBasedOnColumnHeaderCheckbox(WebDriver driver,
      ExtentTest extentReport) {

    boolean status = false;
    double markedTotal = 0.00;

    try {
      if (btnMarkAll.getAttribute("disabled") == null) {
        btnMarkAll.click();
        WaitUtils.waitForSpinner(driver);
      }
      btnUnmarkAll.click();
      WaitUtils.waitForSpinner(driver);
      headerChkbox.click();
      WaitUtils.waitForSpinner(driver);
      markedTotal = Double.parseDouble(
          UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));

      System.out.println("markedTotal---" + markedTotal);
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    double sumOfMarkedItems = 0;
    if (allRows.isEmpty()) {
      Log.softAssertThat(false, "", "No results found for the criteria entered", driver);
    } else {

      for (WebElement ele : columnAmount) {
        double amount = Double.parseDouble(ele.getText());
        System.out.println(amount);

        sumOfMarkedItems = sumOfMarkedItems + amount;

      }
      System.out.println("sumOfMarkedItems" + sumOfMarkedItems);
      if (sumOfMarkedItems == markedTotal) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "Sum of amount column is matches with Marked Total",
          "Sum of amount column is not matches with Marked Total", driver, extentReport, false);
    }

    for (WebElement chkbox : allChkBoxes) {
      System.out.println(chkbox.isSelected());
      if (chkbox.isSelected() == false) {
        status = true;
      } else
        status = false;
    }
    Log.softAssertThat(status, "All checkboxes of 1st page are checked",
        "All checkboxes of 1st page are not checked", driver, extentReport, false);

    headerChkbox.click();
    WaitUtils.waitForSpinner(driver);
    try {
      markedTotal = Double.parseDouble(
          UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
      if (markedTotal == 0.00) {
        status = true;
      } else
        status = false;
      Log.softAssertThat(status, "After clicking UnmarkAll Marked total is set to 0.00",
          "After clicking UnmarkAll Marked total is not set to 0.00", driver, extentReport, false);
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return status;
  }

  public boolean checkAvailabilityOfFilters(WebDriver driver, ExtentTest extentReport) {
    boolean status = false;
    for (WebElement ele : allFilters) {
      if (ele.isDisplayed() == true) {
        status = true;
      } else
        status = false;
    }
    Log.softAssertThat(status, "All filters are displayed", "All filters are not displayed", driver,
        extentReport, false);
    return status;


  }

  public boolean checkUnavailabilityOfFilters(WebDriver driver, ExtentTest extentReport) {
    boolean status = false;
    for (WebElement ele : allFilters) {
      if (ele.isDisplayed() == false) {
        status = true;
      } else
        status = false;
    }
    Log.softAssertThat(status, "All filters are hidden", "All filters are not hidden", driver,
        extentReport, false);
    return status;

  }


  public boolean checkMarkUnmarkAllBankButtonStateChangeDynamically(WebDriver driver,
      ExtentTest extentReport) {

    boolean status = false;
    String MrkALL = btnMarkAll.getAttribute("disabled");
    String UnmrkALL = btnUnmarkAll.getAttribute("disabled");
    String bank = btnBank.getAttribute("disabled");
    String report = btnReport.getAttribute("disabled");
    String close = btnClose.getAttribute("disabled");
    String find = btnFind.getAttribute("disabled");
    String clear = btnClear.getAttribute("disabled");

    if (MrkALL.equals("true") && UnmrkALL.equals("true") && bank.equals("true")) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "By Default MarkAll,Bank and Unmark All are disabled",
        "By Default MarkAll,Bank and Unmark All are enabled", driver, extentReport, false);

    clickFind(driver, extentReport);
    WaitUtils.waitForSpinner(driver);
    if (MrkALL.equals("true")) {
      btnUnmarkAll.click();
      WaitUtils.waitForSpinner(driver);
      btnFilter.click();
      clickFind(driver, extentReport);
      WaitUtils.waitForSpinner(driver);
    }

    if (btnMarkAll.getAttribute("disabled") == null) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "On clicking Find MarkAll state changed to enabled",
        "On clicking Find MarkAll state remains disabled", driver, extentReport, false);

    if (btnMarkAll.getAttribute("disabled") == null) {
      btnMarkAll.click();
      WaitUtils.waitForSpinner(driver);
    }
    if (MrkALL.equals("true") && btnUnmarkAll.getAttribute("disabled") == null
        && btnBank.getAttribute("disabled") == null) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status,
        "On clicking MarkAll , MarkAll state changed to disabled and Bank,UnmarkAll gets enabled",
        "On clicking MarkAll button , MarkAll state remains enabled and Bank, UnmarkAll remains disabled",
        driver, extentReport, false);

    btnUnmarkAll.click();
    WaitUtils.waitForSpinner(driver);
    if (btnMarkAll.getAttribute("disabled") == null && UnmrkALL.equals("true")
        && bank.equals("true")) {
      status = true;
    } else
      status = false;


    Log.softAssertThat(status,
        "On pressing unmarkAll button ,MarkAll gets enabled and Bank,UnmarkAll gets disabled",
        "On pressing unmarkAll button ,MarkAll remains disabled and Bank,UnmarkAll remains enabled",
        driver, extentReport, false);

    btnMarkAll.click();
    WaitUtils.waitForSpinner(driver);
    btnFilter.click();
    if (btnBank.getAttribute("disabled") == null) {
      try {
        UIInteraction.selectDropdownByVisibleText(drpMediaType, "Select media type", "Cash", driver,
            extentReport, false);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      WaitUtils.waitForSpinner(driver);
      System.out.println(lblFindButtonMessage.getText().trim());
      System.out.println("Please select FIND to continue");
      if (bank.equals("true") && lblFindButtonMessage.getText().trim()
          .equalsIgnoreCase("Please select FIND to continue")) {
        status = true;
      } else
        status = false;

      Log.softAssertThat(status, "On changing filter Bank button gets disabled with a message",
          "On changing filter Bank button doesn't gets disabled with a message", driver,
          extentReport, false);
    }


    return status;

  }

  public void checkAvailabilityOfPaymentDetailsScreen(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    if (btnUnmarkAll.getAttribute("disabled") == null) {
      btnUnmarkAll.click();
      WaitUtils.waitForSpinner(driver);
    }

    String SPY_xpath =
        "//table//td[contains(text(),'SPY')]/preceding-sibling::td//input[@type='checkbox']";

    WebElement element = driver.findElement(By.xpath(SPY_xpath));

    UIInteraction.clickRadioButton(element, "Click SPY", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

    double markedTotal = 0.00;

    try {
      markedTotal = Double.parseDouble(
          UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (markedTotal < 0.00) {
      btnBank.click();
      WaitUtils.waitForSpinner(driver);

    }

    else
      Log.softAssertThat(false, "Already Marked SRP is greater than SPY ",
          "Already Marked SRP is greater than SPY", driver, extentReport, false);

    CollectionScreen cs = new CollectionScreen(driver, extentReport);
    boolean status = cs.verifyPaymentDetails();

    Log.softAssertThat(status, "PaymentDetails screen is loaded",
        "PaymentDetails screen is not loaded", driver, extentReport, false);

    double amount = cs.getAmount(driver, extentReport);

    if (markedTotal * (-1) == amount) {
      status = true;
    } else
      status = false;
    Log.softAssertThat(status,
        "Amount on Collection/Payment details screen matches with Marked Total amount of Banking screen",
        "Amount on Collection/Payment details screen does not matches with Marked Total amount of Banking screen",
        driver, extentReport, false);

    cs.cancelScreen(driver, extentReport);



  }


  public boolean verifyBankingTitle(WebDriver driver, ExtentTest extentReport) throws Exception {
    WaitUtils.waitForSpinner(driver);
    boolean status = false;
    System.out.println(headerBanking.getText().trim());
    if (headerBanking.getText().trim().equalsIgnoreCase("BANKING")) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status, "Banking screen is loaded", "Banking screen is not loaded", driver,
        extentReport, false);
    return status;



  }

  public void checkAvilabilityOfCollectionScreen(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    if (btnUnmarkAll.getAttribute("disabled") == null) {
      btnUnmarkAll.click();
      WaitUtils.waitForSpinner(driver);
    }

    double markedTotal = 0.00;
    boolean status = false;
    String SRP_xpath =
        "//table//td[contains(text(),'SRP')]/preceding-sibling::td//input[@type='checkbox']";

    WebElement element = driver.findElement(By.xpath(SRP_xpath));
    UIInteraction.clickRadioButton(element, "Click SRP", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    try {
      String markedAMount=UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false).replaceAll(",", "");
      System.out.println(markedAMount);
      
      markedTotal = Double.parseDouble(markedAMount);
      System.out.println(markedTotal);
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    CollectionScreen cs = new CollectionScreen(driver, extentReport);

    btnBank.click();
    if (markedTotal > 0) {

      cs.verifyCollectionDetails();
    }

    double amount = cs.getAmount(driver, extentReport);
    if (markedTotal < 0) {
      markedTotal = markedTotal * (-1);
    }


    if (markedTotal == amount) {
      status = true;
    } else
      status = false;
    Log.softAssertThat(status,
        "Amount on Collection/Payment details screen matches with Marked Total amount of Banking screen",
        "Amount on Collection/Payment details screen does not matches with Marked Total amount of Banking screen",
        driver, extentReport, false);
    cs.bankDisabledFieldsOnCollection(driver, extentReport);
    
    status=cs.checkUnavailabilityOfPreBank(driver, extentReport);
    Log.softAssertThat(status, "PreBank is not available under Bank dropdown", "PreBank is available under Bank dropdown", driver, extentReport, false);
    cs.cancelScreen(driver, extentReport);


  }

  public void checkMarkedStatusFilter(WebDriver driver, ExtentTest extentReport) {

    boolean status = false;
    double markedTotal = 0.00;

    if (btnUnmarkAll.getAttribute("disabled") == null) {
      btnUnmarkAll.click();
      WaitUtils.waitForSpinner(driver);
    }

    int totalRows = allRows.size();
    if (totalRows > 1) {
      chk1stItem.click();
      WaitUtils.waitForSpinner(driver);
    }
    btnFilter.click();
    WaitUtils.waitForSpinner(driver);

    try {
      UIInteraction.selectDropdownByVisibleText(drpMarkedStatus, "Marked Status", "Marked", driver,
          extentReport, false);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    clickFind(driver, extentReport);
    if (allRows.size() == 2) {
      status = true;
    } else
      status = false;

    Log.softAssertThat(status,
        "On Selecting Marked filter, only marked items are appearing in result grid",
        "On Selecting Marked filter, Unmarked items are also appearing in result grid", driver,
        extentReport, false);

    btnFilter.click();
    WaitUtils.waitForSpinner(driver);

    try {
      UIInteraction.selectDropdownByVisibleText(drpMarkedStatus, "Marked Status", "Un-Marked",
          driver, extentReport, false);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    clickFind(driver, extentReport);
    WaitUtils.waitForSpinner(driver);

    try {
      markedTotal = Double.parseDouble(
          UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (markedTotal == 0.00) {
      status = true;
    } else
      status = false;
    Log.softAssertThat(status,
        "On Selecting UnMarked filter, only Unmarked items are appearing in result grid",
        "On Selecting UnMarked filter, Marked items are also appearing in result grid", driver,
        extentReport, false);

  }
  
   public void checkBankingProcess(HashMap<String,String> dynamicHashMap,HashMap<String,String> testData,WebDriver driver, ExtentTest extentReport){
    
    String ref=null;
    List<String> references = new ArrayList<String>() ;
    String ref1 =null;
    String ref2 =null;
    String markedTotal=null;
       
    if (btnUnmarkAll.getAttribute("disabled") == null) {
      btnUnmarkAll.click();
    }
    
    for (int i=0;i<2;i++){
      
       ref = clmTransRef.get(i).getText();
       references.add(ref);
          
    }  
    
if(references.get(0).contains("(J)")){
  ref1 = references.get(0).replace("(J)", "");
}else ref1 =references.get(0);
dynamicHashMap.put("ref1", ref1);
if(references.get(1).contains("(J)")){
  ref2 = references.get(1).replace("(J)", "");
}else ref2 =references.get(1);
dynamicHashMap.put("ref2", ref2);
WaitUtils.waitForSpinner(driver);


try {
  String chk1 = "//table//td[contains(text(),'".concat(ref1)
      .concat("')]/preceding-sibling::td//input[@type='checkbox']");
  
  WebElement ele1 = driver.findElement(By.xpath(chk1));
  UIInteraction.clickUsingJS(ele1, "clicking chk1", driver, extentReport, false);
  WaitUtils.waitForSpinner(driver);
  
  String chk2 = "//table//td[contains(text(),'".concat(ref2)
      .concat("')]/preceding-sibling::td//input[@type='checkbox']");

      WebElement ele2 = driver.findElement(By.xpath(chk2));
  
  UIInteraction.clickUsingJS(ele2, "clicking chk2", driver, extentReport, false);
  WaitUtils.waitForSpinner(driver);
  
   markedTotal=UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, true).replaceAll(",", "");
   dynamicHashMap.put("markedTotal", markedTotal);
  UIInteraction.click(btnBank, "Click Bank", driver, extentReport, false);
  WaitUtils.waitForSpinner(driver);
  CollectionScreen cs= new CollectionScreen(driver, extentReport);
  cs.enterDetailsForPayNow(testData, driver, extentReport, false);
  
 verifyBankingCollectionPaymentProcessReport(driver, extentReport);
  
  HomePage homepage = new HomePage(driver, extentReport);
  homepage.navigateToTransaction(driver, extentReport);
  TransactionScreen searchPage = new TransactionScreen(driver, extentReport).get();
  searchPage.uncheckOutstandingCheckBox(driver, extentReport);
  searchPage.navigateToTransactionsTab(driver, extentReport);
  searchPage.searchViaTransactionRef(dynamicHashMap.get("ref1"), driver, extentReport);
  searchPage.performSearch(driver, extentReport);
  searchPage.selectActionMenu(driver, dynamicHashMap.get("ref1"), extentReport);
  searchPage.viewAllocation(driver, extentReport);
  searchPage.switchToFrameCTV(driver, extentReport);
  String tRef= searchPage.getTransactionRefBasedOnProcessedBankingRecords(dynamicHashMap, driver, extentReport);
  searchPage.closeViewAllocationcreen(driver, extentReport);
  searchPage.switchOutOfFrame(driver, extentReport);
  searchPage.newSearch(driver, extentReport);
  searchPage.uncheckOutstandingCheckBox(driver, extentReport);
  searchPage.navigateToTransactionsTab(driver, extentReport);
  searchPage.searchViaTransactionRef(tRef, driver, extentReport);
  searchPage.performSearch(driver, extentReport);
  searchPage.assertAccountCode("CBA", driver, extentReport);  
  
} catch (Exception e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
}

  }

  public void smartSearch(HashMap<String,String> testData,WebDriver driver, ExtentTest extentReport){
    
    if (btnUnmarkAll.getAttribute("disabled") == null) {
      try {
        UIInteraction.click(btnUnmarkAll, "click unmark All button", driver, extentReport, true);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      WaitUtils.waitForSpinner(driver);
    }
    
  UIInteraction.selectValAutoSuggestionList(txtClientSmartSearch, listSmartSearch, testData.get("SmartSearch"), testData.get("Client Code"), driver, extentReport);
  int i = -1;
  boolean status = false;
  double markedTotal1=0.00;
  double markedTotal2=0.00;
  

    do {
      for (WebElement element : clmAccountCode) {
       if (!element.getText().equalsIgnoreCase(testData.get("Client Code"))) {
          status = false;
        } else
          status = true;
              }
      i++;
      if (i < linkPages.size()) {
        linkPages.get(i).click();
        WaitUtils.waitForSpinner(driver);

      }
    } while (i < linkPages.size());
  
  Log.softAssertThat(status, "All records are as per selected Smart Search",
      "All records in grid are not as per selected Smart Search filter",driver,extentReport,false);
  
  try {
    UIInteraction.click(btnMarkAll, "clicking MarkAll", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    markedTotal1 = Double.parseDouble(
        UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
    UIInteraction.click(btnCrossClientSmartSearch, "clearing smart search filter", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    markedTotal2 = Double.parseDouble(
        UIInteraction.getValue(fldMarkedTotal, "Marked Total", driver, extentReport, false));
    
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  
  if(markedTotal1==markedTotal2){
    status =true;
  }else status=false;
  
  Log.softAssertThat(status, "On clearing smart search filter ,marked total remain same as earlier", "On clearing smart search filter ,marked total doesn't remain same as earlier", driver, extentReport, true);
  try {
    UIInteraction.click(btnFilter, "clicking filter button", driver, extentReport, false);
    UIInteraction.selectDropdownByVisibleText(drpMarkedStatus, "Marked Status", "All",
        driver, extentReport, false);
    UIInteraction.click(btnFind, "click find button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.click(btnUnmarkAll, "click unmark All button", driver, extentReport, true);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  WaitUtils.waitForSpinner(driver);
      
  }
  
  /****
   * Method to verify Marked Item report is loaded.
   * @return
   * @throws Exception
   *
   **/
  public void verifyMarkedItemReport(WebDriver driver, ExtentTest extentReport){
    String reportTitle =null;
    btnReport.click();
       try {
      UIInteraction.switchToTab(driver);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
     reportTitle= UIInteraction.getText(txtBankingReportHeadingReport, "Marked Item", driver, extentReport, true);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println(reportTitle);
    Log.softAssertThat(reportTitle.equalsIgnoreCase("Items Marked for Banking Report"), "Marked Item Report loaded sucessfully", "Marked Item Report is not loaded sucessfully", driver, extentReport, true);
    try {
      UIInteraction.closeCurrentTab(driver);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  /****
   * Method to verify BankingCollectionPaymentProcessReport is loaded.
   * @return
   * @throws Exception
   *
   **/
  public void verifyBankingCollectionPaymentProcessReport(WebDriver driver, ExtentTest extentReport){
    String reportTitle =null;
      try {
      UIInteraction.switchToTab(driver);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
     reportTitle= UIInteraction.getText(txtBankingReportHeadingReport, "Banking Collection Payment Process Report", driver, extentReport, true);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println(reportTitle);
    Log.softAssertThat(reportTitle.equalsIgnoreCase("Banking CollectionPayment Process"), "Banking Collection Payment Process Report loaded sucessfully", "Banking Collection Payment Process Report is not loaded sucessfully", driver, extentReport, true);
    try {
      UIInteraction.closeCurrentTab(driver);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}


