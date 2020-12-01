
package com.ssp.utils.heartland;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;
import java.util.Properties;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;


/******
 * Interactions class created for Heartland specific methods
 *
 */
public class Interactions {
  Properties prop = new Properties();

  static DecimalFormat twoDecimals = new DecimalFormat("####0.00");

  /***
   * Method to calculate the annual premium
   * 
   * @param dynamicHashMap
   * @author Shweta.Saigal
   * @return
   */
  public static String calculateAnnualPremium(HashMap<String, String> dynamicHashMap) {

    double annualPremium = Double.parseDouble(dynamicHashMap.get("Insurer Premium"))
        + Double.parseDouble(dynamicHashMap.get("Broker Fee"));
    return (twoDecimals.format(annualPremium));

  }
  /****
   * Method to calculate the Broker Commission
   * 
   * @param dynamicHashMap-- values read from quotes result
   * @param config
   * @param policyType
   * @return
   * @author Shweta.Saigal
   */
  public static String calculateBrokerCommission(HashMap<String, String> dynamicHashMap,
      HashMap<String, String> config, String policyType) {

    double percentage = 0.00;

    switch (policyType) {
      case "New Business":
        percentage = Double.parseDouble(config.get("BrokerCommNB"));
        break;
      case "MTA":
        percentage = Double.parseDouble(config.get("BrokerCommMTA"));
        break;
      case "Renewal":
        percentage = Double.parseDouble(config.get("BrokerCommRenewal"));
        break;
      case "Cancellation":
        percentage = Double.parseDouble(config.get("BrokerCommCancellation"));
        break;
      default:
        Log.message("Error in the policy type you have passed");
    }

    double premiumDueNet = Double.parseDouble(calculatePremiumDueNet(dynamicHashMap));
    double brokerComm = premiumDueNet * (percentage / 100);

    Log.message("Calculated Broker Comm is :" + twoDecimals.format(brokerComm));
    return (twoDecimals.format(brokerComm));

  }

  /*****
   * Method to calculate the premium due net amount
   * 
   * @param dynamicHashMap
   * @return
   * @author Shweta.Saigal
   */
  public static String calculatePremiumDueNet(HashMap<String, String> dynamicHashMap) {

    double premiumDueNet = (Double.parseDouble(dynamicHashMap.get("Annual Premium"))
        - Double.parseDouble(dynamicHashMap.get("Broker Fee"))) * 100 / 112;

    Log.message("Calculate Premium Due Net is :" + twoDecimals.format(premiumDueNet));
    return (twoDecimals.format(premiumDueNet));
  }

  /****
   * Method to calculate the Sub Agent commission
   * 
   * @param dynamicHashMap
   * @param config
   * @param policyType
   * @return
   * @author Shweta.Saigal
   */
  public static String calculateSubAgentCommission(HashMap<String, String> dynamicHashMap,
      HashMap<String, String> config, String policyType) {
    double percentage = 0.00;

    switch (policyType) {
      case "New Business":
        percentage = Double.parseDouble(config.get("SubAgentCommNB"));
        break;
      case "MTA":
        percentage = Double.parseDouble(config.get("SubAgentCommMTA"));
        break;
      case "Renewal":
        percentage = Double.parseDouble(config.get("SubAgentCommRenewal"));
        break;
      case "Cancellation":
        percentage = Double.parseDouble(config.get("SubAgentCommNBCancellation"));
        break;
      default:
        Log.message("Error in the policy type you have passed");
    }

    double subAgentComm =
        Double.parseDouble(calculateBrokerCommission(dynamicHashMap, config, policyType))
            * (percentage / 100);

    Log.message("Calculate SubAgent Comm is :" + twoDecimals.format(subAgentComm));
    return (twoDecimals.format(subAgentComm));
  }

  /****
   * Method to calculate net income
   * 
   * @param dynamicHashMap
   * @param config
   * @param policyType
   * @param subAgent
   * @return
   * @author Shweta.Saigal
   */
  public static String calculateNetIncome(HashMap<String, String> dynamicHashMap,
      HashMap<String, String> config, String policyType, boolean subAgent) {
    double brokerComm =
        Double.parseDouble(calculateBrokerCommission(dynamicHashMap, config, policyType));
    double subAgentComm = 0.00;

    if (subAgent)
      subAgentComm =
          Double.parseDouble(calculateSubAgentCommission(dynamicHashMap, config, policyType));

    double netIncome =
        brokerComm - subAgentComm + Double.parseDouble(dynamicHashMap.get("Broker Fee"));

    Log.message("Calculated Net Income  is :" + twoDecimals.format(netIncome));
    return (twoDecimals.format(netIncome));
  }

  /*****
   * Method to calculate premium ipt
   * 
   * @param dynamicHashMap
   * @param config
   * @return
   * @author Shweta.Saigal
   */
  public static String calculatePremiumIPT(HashMap<String, String> dynamicHashMap,
      HashMap<String, String> config) {

    double premiumIPT = Double.parseDouble(calculatePremiumDueNet(dynamicHashMap))
        * (Double.parseDouble(config.get("IPT")) / 100);

    Log.message("Calculated Premium IPT is :" + twoDecimals.format(premiumIPT));
    return (twoDecimals.format(premiumIPT));
  }

  /*****
   * Method to calculate premium due gross
   * 
   * @param dynamicHashMap
   * @param config
   * @return
   * @author Shweta.Saigal
   */
  public static String calculatePremiumDueGross(HashMap<String, String> dynamicHashMap,
      HashMap<String, String> config) {

    double premiumDueGross = Double.parseDouble(calculatePremiumIPT(dynamicHashMap, config))
        + Double.parseDouble(calculatePremiumDueNet(dynamicHashMap));

    Log.message("Calculated Premium Due Gross is :" + twoDecimals.format(premiumDueGross));

    return (twoDecimals.format(premiumDueGross));

  }

  /***
<<<<<<< HEAD
   * This method is to get table data
=======
   * This method is to validate table data
   * 
>>>>>>> branch 'master' of http://gitlab.siriusfs.com/shweta.saigal/p1-pom-framework.git
   * @param tableRowsLocator, path of all table rows
   * @param columnOneName, name of first column header
   * @param columnOneValue, value of first column
   * @param columnTwoName, name of second coulmn header
   * @param outputValue, expected value label
   * @param driver
   * @param extentedReport
   * @return
   * @throws Exception
   * @author sunny.kumar
   */
  public static String getTableData(String tableRowsLocator, String columnOneName,
      String columnOneValue, String columnTwoName, String label, WebDriver driver,
      ExtentTest extentedReport) throws Exception {
    try {
      int indexI = -1;
      int indexJ = -1;
      String header = null;
      int indexDeffPositive = -100;
      int indexDeffNegative = -100;
      String expectedValue = null;

      List<WebElement> allRows = driver.findElements(By.xpath(tableRowsLocator));
      String tableHeaderLocator = tableRowsLocator + "//th";
      List<WebElement> allHeaders = driver.findElements(By.xpath(tableHeaderLocator));

      for (int i = 0; i < allHeaders.size(); i++) {
        header = allHeaders.get(i).getText();
        if (allHeaders.get(i).getText().equalsIgnoreCase(columnOneName)) {
          indexI = i + 1;
          Log.message(header + " header index number:" + indexI);
          break;
        }
      }
      for (int i = 0; i < allHeaders.size(); i++) {
        header = allHeaders.get(i).getText().toString();
        if (allHeaders.get(i).getText().equalsIgnoreCase(columnTwoName)) {
          indexJ = i + 1;
          Log.message(header + " header index number:" + indexJ);
          break;
        }
      }
      if (indexI < indexJ) {
        indexDeffPositive = indexJ - indexI;
      } else {
        indexDeffNegative = indexI - indexJ;
        indexDeffNegative = -indexDeffNegative;
      }

      for (int row = 1; row < allRows.size(); row++) {
        int newIndex = indexI;
        String tempRowLocator = tableRowsLocator + "[" + row + "]" + "//td[" + newIndex + "]";
        Log.message("Temp locator: " + tempRowLocator);
        if ((driver.findElement(By.xpath(tempRowLocator)).getText().contains(columnOneValue))) {

          if (indexDeffPositive == -100)
            newIndex = indexI + indexDeffNegative;
          else
            newIndex = indexI + indexDeffPositive;

          String tempXpath = tableRowsLocator + "[" + row + "]" + "//td[" + newIndex + "]";
          expectedValue = driver.findElement(By.xpath(tempXpath)).getText();
          Log.message("Expected value: " + expectedValue);
          break;
        }
      }
      return expectedValue;
    } catch (NoSuchElementException e) {
      Log.fail("Error while validating table data for " + label, extentedReport);
      throw new Exception("No Element Found " + e);
    }
  }

  /***
   * This method is to get multiline textbox (textarea) attibutes
   * 
   * @param element
   * @param multilineTextboxArttibutes (tagName = textarea, rows= 4, cols=50)
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @return
   * @throws Exception
   * @author sunny.kumar
   */
  public static String[] getMultilineTextBox(WebElement element,
      String[] multilineTextboxArttibutes, WebDriver driver, ExtentTest extentedReport)
      throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);

      multilineTextboxArttibutes[0] = element.getTagName();
      multilineTextboxArttibutes[1] = element.getAttribute("rows");
      multilineTextboxArttibutes[2] = element.getAttribute("cols");

      return multilineTextboxArttibutes;
    } catch (NoSuchElementException e) {
      Log.fail("Error while fetching miltiline text box element properties", extentedReport);
      throw new Exception("No Element Found to enter text" + e);
    }
  }

  /***
   * Convert date format from dd/MM/yyyy to DayName Month Day HH:MM:SS IST YEAR (Mon Jun 15 00:00:00
   * IST 2020)
   * 
   * @param date (dd/MM/yyyy)
   * @return (Mon Jun 15 00:00:00 IST 2020)
   * @throws ParseException
   * @author sunny.kumar
   */
  public static Date convertDateFormat(String date) throws ParseException {
    Date expectedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
    System.out.println(date + "\t" + expectedDate);
    return expectedDate;
  }

  /***
   * Getting number of decimals value of an element
   * 
   * @param element
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @return
   * @throws Exception
   * @author sunny.kumar
   */
  public static int getNumberOfDecimalsOfAnElement(WebElement element, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      String tempValue = element.getText();
      String[] tempData = tempValue.split("\\.");
      String expectedValue = tempData[1];
      int expectedCountedDecimalNum = expectedValue.length();
      return expectedCountedDecimalNum;
    } catch (NoSuchElementException e) {
      Log.fail("Error while validating decimal number of an amount value passed.", extentedReport);
      throw new Exception("No Element Found " + e);
    }
  }

  /***
   * This method will return half value of an amount
   * 
   * @param amount
   * @return
   * @author sunny.kumar
   */
  public static String getHalfOfAmountValue(String amount) {
    Double temp = Double.valueOf(amount);
    Double halfValue = temp / 2;
    return String.valueOf(halfValue);
  }

  /***
   * This method is for textarea field when user has to enter data in multiple lines
   * 
   * @param element
   * @param input
   * @param driver
   * @param extentedReport
   * @throws Exception
   * @author sunny.kumar
   */
  public static void enteringDataInAMultilineTextboxOrTextarea(WebElement element, String input,
      WebDriver driver, ExtentTest extentedReport) throws Exception {
    try {
      Actions actions = new Actions(driver);
      actions.moveToElement(element).build().perform();
      String inputData[] = input.split("#");
      for (int i = 0; i < inputData.length; i++) {
        String temp = inputData[i];
        actions.click(element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME)
            .keyUp(Keys.SHIFT).sendKeys(temp).perform();
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.ENTER);
      }
    } catch (NoSuchElementException e) {
      Log.fail("Error while entering text in the expected textarea/multiline textbox",
          extentedReport);
      throw new Exception("No Element Found" + e);
    }
  }


  /***
   * To resize the browser
   * 
   * @param driver
   * @param extentReport
   * @author Akshay.Saini
   */
  public void browserWindowsResize(WebDriver driver) {

    int width = Integer.parseInt(prop.getProperty("browserWidth"));
    int height = Integer.parseInt(prop.getProperty("browserHeight"));
    Dimension d = new Dimension(width, height);
    // Resize current window to the set dimension
    driver.manage().window().setSize(d);
  }

  /***
   * To Maximize the browser
   * 
   * @param driver
   * @param extentReport
   * @author Akshay.Saini
   */
  public static void browserWindowMaximize(WebDriver driver) {

    driver.manage().window().maximize();
  }


  /**
   * To compare two element's location
   * 
   * @param element
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   * @author Akshay.Saini
   */
  public static Point getLocationOfAWebElement(WebElement element, WebDriver driver, String label,
      ExtentTest extentReport) throws Exception {

    try {

      return element.getLocation();

    } catch (NoSuchElementException e) {
      Log.fail("Fail to fetch the location of : " + label, driver, extentReport, true);
      throw new Exception("No Element Found to assert" + e);
    }

  }

  /**
   * To compare two element's location element1 -> pass the element's location which should be first
   * element2 -> pass the element's location which should be second
   * 
   * @param validMessage
   * @param errorMessage
   * @param extentedReport
   * @param rightAligned -> yes if we need to check the element is right aligned
   * @param screenshot
   * @throws Exception
   * @author Akshay.Saini
   */
  public static boolean getTwoLocationCompare(Point locationValueOfElement1,
      Point locationValueOfElement2, WebDriver driver, boolean rightAligned,
      ExtentTest extentedReport) throws Exception {

    boolean status = false;

    try {

      if (!rightAligned) {

        if (locationValueOfElement1.getY() < locationValueOfElement2.getY()) {

          status = true;
        }

      }

      else if (rightAligned) {

        if (locationValueOfElement1.getX() < locationValueOfElement2.getX()) {

          status = true;
        }


      }

      return status;

    } catch (NoSuchElementException e) {
      Log.fail("Fail to achieve expected result : ", driver, extentedReport, true);
      throw new Exception("No Element Found to assert" + e);
    }
  }

  /**
   * To compare elements are in single Row or not element1 element2
   * 
   * @param extentedReport
   * @param singleRow -> yes if we need to check the elements are in single row
   * @param screenshot
   * @throws Exception
   * @author Akshay.Saini
   */
  public static boolean compareTwoLocationsOfElementOnSingleRowOrNot(Point locationValueOfElement1,
      Point locationValueOfElement2, WebDriver driver, ExtentTest extentedReport, boolean singleRow)
      throws Exception {
    try {
      boolean status = false;

      if (singleRow) {

        if (locationValueOfElement1.getY() == locationValueOfElement2.getY()) {

          status = true;
        }

      } else if (!singleRow) {

        if (locationValueOfElement1.getY() != locationValueOfElement2.getY()) {
          status = true;
        }

      }
      return status;

    } catch (NoSuchElementException e) {
      Log.fail(" ", driver, extentedReport, true);
      throw new Exception("No Element Found to assert" + e);
    }
  }

  /**
   * To Mouse Hover on an element and get tooltip element
   * 
   * @param extentedReport
   * @param label
   * @param input
   * @param screenshot
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean mouseHoverOnAnWebElementAndVerifyText(WebElement element, WebDriver driver,
      String label, String input, ExtentTest extentedReport) throws Exception {
    try {
      boolean status = false;

      Actions action = new Actions(driver);

      action.moveToElement(element).perform();

      String actualTooltip = element.getAttribute("title");

      if (actualTooltip.equalsIgnoreCase(input)) {

        status = true;
      }

      return status;

    } catch (NoSuchElementException e) {
      Log.fail("Error Mouse hover on " + label + " is unsuccessful", extentedReport);
      throw new Exception("No Element Found " + e);
    }
  }

  /***
   * Validate particular value not present in table header.
   * 
   * @param element
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @author Bhavesh.KumarSingh
   */

  public static boolean validateTableHeaderValueNotContains(WebElement element, String input,
      WebDriver driver) throws Exception {
    boolean status = false;
    try {

      WebElement myDriver = element;
      List<WebElement> allActualElements = myDriver.findElements(By.tagName("th"));
      System.out.println(allActualElements.size());
      for (int i = 0; i < allActualElements.size(); i++) {
        System.out.println(allActualElements.get(i).getText());
        if (!allActualElements.get(i).getText().contains(input)) {
          status = true;
        } else
          break;
      }
      return status;
    } catch (NoSuchElementException e) {
      Log.fail("Error while comparing value from header ");
      throw new Exception("Error while reading in value " + e);
    }

  }

  /***
   * Validate particular element is link or not.
   * 
   * @param element
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @author Bhavesh.KumarSingh
   */

  public static boolean validateLink(WebElement element) throws Exception {
    boolean status = false;
    try {
      if (element.getAttribute("href") != null) {
        status = true;
      }
      return status;
    } catch (Exception e) {
      Log.fail("Element not found");
      throw new Exception("Error while finding element" + e);
    }

  }

  /***
   * Click on element and handle alert if present.
   * 
   * @param element
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @author Bhavesh.KumarSingh
   */

  public static void clickOnElementAndHandleAlert(WebElement element, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {

      Actions action = new Actions(driver);
      if (element.isDisplayed()) {

        action.moveToElement(element).click().build().perform();
        UIInteraction.acceptAlert(driver);
      }
    } catch (NoSuchElementException e) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
      element.click();
      UIInteraction.acceptAlert(driver);
      Log.fail("Fail to achieve expected result : ");
      throw new Exception("No Element Found to assert" + e);
    }
  }


  /***
   * Validate all value present in particular column in table(validate if particular column has
   * similar value).
   * 
   * @param List<element>
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @author Bhavesh.KumarSingh
   */

  public static boolean validateSameValuePresentInPerticularColumn(List<WebElement> element,
      String input, WebDriver driver) throws Exception {
    boolean status = false;
    try {
      for (int i = 0; i < element.size(); i++) {
        if (element.get(i).getText().equals(input)) {
          status = true;
        } else
          break;

      }
      return status;
    } catch (NoSuchElementException e) {
      Log.fail("Error validating value from list");
      throw new Exception("Error while validating value " + e);

    }
  }

  /***
   * Compare all drop down values with input .
   * 
   * @param element
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @author Bhavesh.KumarSingh
   */

  public static boolean validateDropdownAllValues(WebElement element, String input,
      WebDriver driver) throws Exception {
    boolean status = false;
    try {
      List<String> expectedElementList = new ArrayList<String>();
      String[] inputElements = input.split(",");
      for (int i = 0; i < inputElements.length; i++) {
        expectedElementList.add(inputElements[i]);
      }
      System.out.println(expectedElementList);
      WaitUtils.waitForElement(driver, element);
      Select dropDown = new Select(element);
      List<WebElement> allOptions = dropDown.getOptions();
      List<String> allOptionsInList = new ArrayList<String>();
      for (WebElement ele : allOptions) {
        allOptionsInList.add(ele.getText().trim());
      }
      System.out.println(allOptionsInList);
      System.out.println(expectedElementList);
      Collections.sort(allOptionsInList);
      Collections.sort(expectedElementList);
      if (allOptionsInList.equals(expectedElementList)) {
        status = true;

      }

    } catch (NoSuchElementException e) {
      Log.fail("Error validating value from list");
      throw new Exception("Error while validating value " + e);

    }
    return status;
  }

  /***
   * Divide premium based on input passed and enter in text field for partial payments
   * 
   * @param element
   * @param input
   * @param driver
   * @param extentReport
   * @return
   * @author Bhavesh.KumarSingh
   */
  public static void divideAmountAndPassinTextField(WebElement element, String input,
      int percentage, WebDriver driver) throws Exception {
    try {
      String premiumAmount = input;
      double actPremiumAmount = Double.parseDouble(premiumAmount);
      double actualPremiumAmount = actPremiumAmount / percentage;

      if (element.isDisplayed()) {
        element.clear();
        Thread.sleep(2000);
        element.sendKeys(String.valueOf(actualPremiumAmount));
        element.sendKeys(Keys.TAB);
      }
    } catch (NoSuchElementException e) {
      Log.fail("Error while entering value in text field.");
      throw new Exception("Elemnt not found " + e);

    }
  }

  /***
   * To enter random policy number
   * 
   * @param element
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public static void enteringRandomPolicyGenratedNumber(WebElement element, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      Random rand = SecureRandom.getInstanceStrong();
      WaitUtils.waitForElement(driver, element);
      int randomNumber = rand.nextInt(100000000);
      String tempPolicyGenerated = String.valueOf(randomNumber);
      element.clear();
      element.sendKeys(tempPolicyGenerated);
      element.sendKeys(Keys.TAB);
      WaitUtils.waitForSpinner(driver);
      Log.message("Entered random generated policy number ", driver, extentReport, screenshot);
    } catch (NoSuchElementException e) {
      Log.fail("Error while entering random policy no field ", driver, extentReport, true);
      throw new Exception("Error in entering random policy no in field " + e);
    }
  }


  /*****
   * 
   * Validate drop down values sorting order(Ascending order)
   * 
   * @param input
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */

  public static boolean validateSortingOrderOfOptionsInADropDown(WebElement element,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {
    boolean status = false;
    try {

      Select dropDownList = new Select(element);
      List<String> actualdropDownList = new ArrayList<String>();
      List<WebElement> dropDownListValue = dropDownList.getOptions();
      for (WebElement ele : dropDownListValue) {
        String data = ele.getText().trim();
        actualdropDownList.add(data);
      }
      List<String> temp = new ArrayList<String>();
      temp.addAll(actualdropDownList);
      Collections.sort(temp);
      if (actualdropDownList.equals(temp)) {
        status = true;
      }


    } catch (Exception e) {
      throw new Exception("Error while sorting options in dropdown" + e);
    }
    return status;

  }

  /*****
   * 
   * Validate particular value in entire table
   * 
   * @param input
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public static boolean validateTableValuesNotContains(WebElement element, String input,
      WebDriver driver, ExtentTest extentReports) throws Exception {
    try {
      boolean status = false;
      WebElement myDriver = element;
      List<WebElement> tableHeaderList = myDriver.findElements(By.tagName("td"));
      for (int i = 0; i < tableHeaderList.size(); i++) {
        System.out.println(tableHeaderList.get(i).getText());
        if (!tableHeaderList.get(i).getText().contains(input)) {

          status = true;
        } else
          break;

      }
      return status;
    } catch (NoSuchElementException e) {
      Log.fail("Error validating value in table");
      throw new Exception("Error while validating table values " + e);

    }
  }

  /******
   * 
   * Select the checkbox based on policynumber and transref
   * 
   * @param driver
   * @param policyNo
   * @param extentReport
   * @param transref
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */

  public static void selectPolicyCheckboxBasedOnTransrefAndPolicyNumber(WebDriver driver,
      String policyNo, String transref, ExtentTest extentReport, boolean screenshot)
      throws Exception {

    try {
      String policyCheckbox =
          "//table//td[contains(text(),'" + policyNo + "')]/preceding-sibling::td[contains(text(),'"
              + transref + "')]/preceding-sibling::td//input[@type='checkbox']";

      System.out.println(policyCheckbox);
      WebElement checkboxSelectPolicy = driver.findElement(By.xpath(policyCheckbox));

      UIInteraction.clickRadioButton(checkboxSelectPolicy, "Select the checkbox of the policy",
          driver, extentReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while selecting checkbox of the mentioned policy " + e);
    }

  }

  /******
   * 
   * Check checkbox is selected based on policynumber
   * 
   * @param driver
   * @param policyNo
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public static boolean checkCheckboxIsSelectedBasedOnPolicyNumber(WebDriver driver,
      String policyNo, ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      Boolean status = true;
      String policyCheckbox = "//table//td[contains(text(),'" + policyNo
          + "')]/preceding-sibling::td//input[@type='checkbox']";
      WebElement checkboxSelectPolicy = driver.findElement(By.xpath(policyCheckbox));
      if (UIInteraction.isSelected(checkboxSelectPolicy)) {
        status = true;
      } else {
        status = false;
      }
      return status;
    } catch (Exception e) {
      throw new Exception("Checkbox is not selected " + e);
    }
  }

  /******
   * 
   * Enter amount in textbox based on policynumber
   * 
   * @param driver
   * @param policyNo
   * @param extentReport
   * @param input
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public static void enterAmountBasedOnPolicyNumber(WebDriver driver, String policyNo, String input,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      String textbox = "//table//td[contains(text(),'" + policyNo
          + "')]/following-sibling::td//input[contains(@class,'FormatDecimal')]";
      WebElement textboxPolicy = driver.findElement(By.xpath(textbox));
      textboxPolicy.clear();
      UIInteraction.sendKeysViaActionClass(textboxPolicy, "text entered successfully", input,
          driver, extentReport, screenshot);
      textboxPolicy.sendKeys(Keys.TAB);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e) {
      throw new Exception("text not entered successfully" + e);
    }
  }
  /****
   * Method to validate pagination exists and the no of pages.
   * 
   * @param driver
   * @param extentReport
   * @return
   * @author Shweta.Saigal
   */
  public static List<WebElement> pagination(WebDriver driver, ExtentTest extentReport){
    
    String paginationPath ="//div[@class='AspNet-GridView-Pagination AspNet-GridView-Bottom grid-pgr']";
    
    WebElement paginationTable = driver.findElement(By.xpath(paginationPath));

    List<WebElement> pages = paginationTable.findElements(By.tagName("a"));
    Log.message("No of pages displayed : "+ pages.size());
    
    return pages;
    
    
  }
  /****
   * Click on button, validate alert message if any and accept alert
   * 
   * @param element
   * @param alertText
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Shweta.Saigal
   */
  public static boolean clickButtonAndHandleAlert(WebElement element, String alertText,String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    
    boolean status = false;
    try {
      WaitUtils.waitForElement(driver, element);
      Actions action = new Actions(driver);
      try {
        action.moveToElement(element).click().build().perform();
      } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).click().build().perform();
      }
        status = UIInteraction.getTextAndAcceptAlert(driver, alertText);
      Log.message("Click performed " + label, driver, extentReport, screenshot);

    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport, true);
      throw new Exception("Error in performing click on " + label + e);
    }
    return status;

  } 

  /******
   * 
    * Check textField enabled status.
    * @param driver
    * @param element
    * @param extentReport
    * @param screenshot
    * @throws Exception
    * @author Bhavesh.KumarSingh
   */
    public static boolean textFieldEnabledStatus(WebDriver driver,WebElement element) throws Exception {
      try {
      Boolean status=true;
      if(element.isEnabled())
      {
        status=true;
      }
      else{
        status=false;
      }
      return status;
    } catch (Exception e) {
      throw new Exception("Checkbox is not selected " + e);
    }
  }
    /***
     * Select text and copy
     * 
     * @param element
     * @param label
     * @param text
     * @param driver
     * @param extentReport
     * @param screenshot
     * @throws Exception
     * @author Bhavesh.KumarSingh
     */
    public static void selectTextAndCopy(WebElement element, String label, WebDriver driver) throws Exception {
      try {
        WaitUtils.waitForElement(driver, element);
        element.sendKeys(Keys.CONTROL,"a");
        element.sendKeys(Keys.CONTROL,"c");
        element.clear();
        Log.message("Text copied " + " in field " + label, driver);
      } catch (NoSuchElementException e) {
        Log.fail("Error while copying text " + label, driver);
        throw new Exception("Error in copying text " + label + e);
      }
    }
    
    /***
     * paste copied text
     * 
     * @param element
     * @param label
     * @param text
     * @param driver
     * @param extentReport
     * @param screenshot
     * @throws Exception
     * @author Bhavesh.KumarSingh
     */
    public static void pasteCopiedText(WebElement element, String label, WebDriver driver) throws Exception {
      try {
        WaitUtils.waitForElement(driver, element);
        element.click();
        element.sendKeys(Keys.CONTROL,"v");
        element.sendKeys(Keys.TAB);
        Log.message("Text pasted " + " in field " + label, driver);
      } catch (NoSuchElementException e) {
        Log.fail("Error while pasting text " + label, driver);
        throw new Exception("Error in pasting text " + label + e);
      }
    }
    
    /***
     * This method is to get table data
     * @param tableRowsLocator, path of all table rows
     * @param columnOneName, name of first column header
     * @param columnOneValue, value of first column
     * @param columnTwoName, name of second coulmn header
     * @param outputValue, expected value label
     * @param driver
     * @param extentedReport
     * @return
     * @throws Exception
     * @author sunny.kumar
     */
    public static String getTableDataIncludesHiddenRows(String tableRowsLocator, String columnOneName, String columnOneValue, String columnTwoName, String label,
        WebDriver driver, ExtentTest extentedReport) throws Exception {
    try {
        int indexI = -1;
        int indexJ = -1;
        String header = null;
        int indexDeffPositive = -100;
        int indexDeffNegative = -100;
        String expectedValue = null;
        
        List<WebElement> allRows = driver.findElements(By.xpath(tableRowsLocator));
        String tableHeaderLocator = tableRowsLocator + "//th";
        List<WebElement> allHeaders = driver.findElements(By.xpath(tableHeaderLocator));
      
        for (int i = 0; i < allHeaders.size(); i++) {
          header = allHeaders.get(i).getText();
          if (allHeaders.get(i).getText().equalsIgnoreCase(columnOneName)) {
            indexI = i+1;
            Log.message(header + " header index number:" +indexI);
            break;
          }
        }
        for (int i = 0; i < allHeaders.size(); i++) {
          header = allHeaders.get(i).getText().toString();
          if (allHeaders.get(i).getText().equalsIgnoreCase(columnTwoName)) {
            indexJ = i+1;
            Log.message(header + " header index number:" +indexJ);
            break;
          }
        }
        if(indexI < indexJ) {
          indexDeffPositive = indexJ - indexI;
        }
        else {
          indexDeffNegative = indexI - indexJ;
          indexDeffNegative = -indexDeffNegative;
        }
        
        for(int row=1; row< allRows.size(); row=row+2) {
          int newIndex = indexI;
          String tempRowLocator = tableRowsLocator + "["+row+"]"+"//td["+newIndex+"]";
          Log.message("Temp locator: "+ tempRowLocator);
          if((driver.findElement(By.xpath(tempRowLocator)).getText().contains(columnOneValue))) {
               
             if(indexDeffPositive==-100)
               newIndex = indexI + indexDeffNegative;
             else
               newIndex = indexI + indexDeffPositive;
              
             String tempXpath =  tableRowsLocator + "[" + row + "]"+"//td[" + newIndex + "]";
             expectedValue = driver.findElement(By.xpath(tempXpath)).getText();
             Log.message("Expected value: " + expectedValue);
             break;
          }
         }
        return expectedValue;
        } catch (NoSuchElementException e) {
          Log.fail("Error while validating table data for " + label, extentedReport);
          throw new Exception("No Element Found " + e);
        }
      }
    /***
    * To enter random policy number
    * @param element
    * @param driver
    * @param extentReport
    * @param screenshot
    * @throws Exception
    * @author Akshay.Saini
    */
    public static String enteringRandomPolicyGenratedNumber(WebDriver driver, ExtentTest extentReport) throws Exception {
    String tempNumberGenerated;
    try {
    Random rand = SecureRandom.getInstanceStrong();
    int randomNumber = rand.nextInt(100000000);
    tempNumberGenerated = String.valueOf(randomNumber);
    Log.message("Random number generated", driver, extentReport);
    } catch (NoSuchElementException e) {
    Log.fail("Error while entering random policy no field ", driver, extentReport, true);
    throw new Exception("Error in entering random policy no in field "+ e);
    }
    return tempNumberGenerated;
    }


     
  /***
   * To enter random policy number
   * @param element
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Akshay.Saini
   */
  public String generateRandomNumber(WebDriver driver, ExtentTest extentReport) throws Exception {
    String tempNumberGenerated;
    try {
      Random rand = SecureRandom.getInstanceStrong();
      
      int randomNumber = rand.nextInt(100000000);
      tempNumberGenerated = String.valueOf(randomNumber);
      
      Log.message("Random number  generated", driver, extentReport);
    } catch (NoSuchElementException e) {
      Log.fail("Error while entering random policy no field ", driver, extentReport, true);
      throw new Exception("Error in entering random policy no in field "+ e);
    }
    return tempNumberGenerated;

  }

  /***
   * Get Sum Of grid
   * @param columnAmount - this is amount column
   * @param pagesLink - this is list of page hyperlink
   * @param allRowsList - this is list of all rows
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public static double sumOfGrid(List<WebElement> columnAmount,List<WebElement> pagesLink,List<WebElement> allRowsList,WebDriver driver, ExtentTest extentReport) {
    WaitUtils.waitForSpinner(driver);
    int i = -1;

    double sum = 0;
    if (allRowsList.isEmpty()) {
      Log.softAssertThat(false, "", "No results found for the criteria entered", driver);
    } else {
      do {
        for (WebElement ele : columnAmount) {
          double amount = Double.parseDouble(ele.getText());
          System.out.println(amount);

          sum = sum + amount;
          System.out.println(sum);
        }
        i++;
        if (i < pagesLink.size()) {
          pagesLink.get(i).click();
          WaitUtils.waitForSpinner(driver);

        }
      } while (i < pagesLink.size());
    }
    return sum;
  }
  
    
  
}


