package com.ssp.utils;

import java.text.SimpleDateFormat;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;

/****
 * UiInteraction class contains methods for performing common UI Interaction actions such as click,
 * sendKeys, drop down handling, radio button checkbox selection etc. which will be imported in all
 * Page classes to perform various WebElement actions.
 * 
 * @author Shweta.Saigal
 *
 */
public class UIInteraction {


  /***
   * To enter text
   * 
   * @param element
   * @param label
   * @param text
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void sendKeys(WebElement element, String label, String text, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      element.clear();
      element.sendKeys(text);
      Log.message("Entered text " + text + " in field " + label, driver, extentReport, screenshot);
    } catch (NoSuchElementException e) {
      Log.fail("Error while entering text in field " + label, driver, extentReport, true);
      throw new Exception("Error in entering text in field " + label + e);
    }
  }

  /****
   * 
   * To enter text using action class
   * 
   * @param element
   * @param label
   * @param text
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void sendKeysViaActionClass(WebElement element, String label, String text,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      Actions actions = new Actions(driver);
      actions.moveToElement(element).doubleClick().build().perform();
      actions.sendKeys(Keys.BACK_SPACE).build().perform();
      actions.sendKeys(text).build().perform();


      Log.message("Entered text " + text + " in field " + label, driver, extentReport, screenshot);
    } catch (NoSuchElementException e) {
      Log.fail("Error while entering text in field " + label, driver, extentReport, true);
      throw new Exception("Error in entering text in field " + label + e);
    }
  }

  /****
   * To perform click on buttons and links using action class
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */

  public static void click(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {

    try {
      WaitUtils.waitForElement(driver, element);
      Actions action = new Actions(driver);
      try {
        action.moveToElement(element).click().perform();

      } catch (Exception e) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).click().perform();
      }
      Log.message("Click performed " + label, driver, extentReport, screenshot);
    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport, true);
      throw new Exception("Error in performing click on " + label + e);
    }
  }

  /****
   * 
   * To perform click using JS
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void clickUsingJS(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {

    try {
      WaitUtils.waitForElement(driver, element);
      GenericUtils.moveToElementJS(driver, element);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", element);
      Log.message("Click performed using JS " + label, driver, extentReport, screenshot);

    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport, true);
      throw new Exception("Error in performing click on " + label + e);
    }
  }


  /****
   * 
   * To select value by visible text in dropdown
   * 
   * @param element
   * @param label
   * @param text
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void selectDropdownByVisibleText(WebElement element, String label, String text,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {

    try {
      WaitUtils.waitForElement(driver, element);
      Actions action = new Actions(driver);
      try {
        action.moveToElement(element).build().perform();
      } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).build().perform();
      }

      Select options = new Select(element);
      options.selectByVisibleText(text);
      Log.message("Selected value " + text + " in dropdown field " + label, driver, extentReport,
          screenshot);

    } catch (NoSuchElementException e) {
      Log.fail("Error while selecting dropdown by visible text " + label, driver, extentReport,
          true);
      throw new Exception("Error while selecting dropdown by visible text " + label + e);
    }
  }



  /****
   * To select value by index in drop down
   * 
   * @param element
   * @param label
   * @param text
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void selectDropdownByIndex(WebElement element, String label, String text,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      Actions action = new Actions(driver);
      try {
        action.moveToElement(element).build().perform();
      } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).build().perform();
      }

      Select options = new Select(element);
      options.selectByIndex(Integer.parseInt(text));
      Log.message("Selected value " + text + " in dropdown field " + label, driver, extentReport,
          screenshot);

    } catch (Exception e) {

      throw new Exception("Error while selecting dropdown by index value " + label + e);
    }

  }

  /***
   * 
   * To get text from web element
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */

  public static String getText(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      try {
        Log.message("Text read from the field is::" + element.getText() + " field " + label, driver,
            extentReport, screenshot);
      } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Log.message("Text read from the field is::" + element.getText() + " field " + label, driver,
            extentReport, screenshot);
      }

      return (element.getText());

    } catch (NoSuchElementException e) {
      Log.fail("Error while reading value from field " + label, driver, extentReport, true);
      throw new Exception("Error while reading in value " + label + e);

    }
  }

  /****
   * To switch to new tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public static void switchToTab(WebDriver driver) throws Exception {

    try {
      List<String> newTab = new ArrayList<>(driver.getWindowHandles());
      driver.switchTo().window(newTab.get(1));
    } catch (NoSuchElementException e) {
      throw new Exception(e);
    }
  }

  /***
   * To switch back to default tab
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public static void closeCurrentTab(WebDriver driver) throws Exception {
    try {

      List<String> newTab = new ArrayList<>(driver.getWindowHandles());      
      driver.switchTo().window(newTab.get(1)).close();      
      driver.switchTo().window(newTab.get(0));
    } catch (NoSuchElementException e) {
      throw new Exception(e);
    }
  }

  /****
   * 
   * Click on conform quote includes the handling of the alert
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void clickConfirmQuote(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      Actions action = new Actions(driver);
      try {
        action.moveToElement(element).click().build().perform();
      } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).click().build().perform();
      }
      UIInteraction.acceptAlert(driver);
      Log.message("Click performed " + label, driver, extentReport, screenshot);

    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport, true);
      throw new Exception("Error in performing click on " + label + e);
    }
  }

  /****
   * 
   * To accept alerts
   * 
   * @param driver
   */
  public static void acceptAlert(WebDriver driver) {
    try {
      Thread.sleep(3000);
      WebDriverWait wait = new WebDriverWait(driver, 5);
      if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
        Alert alert = driver.switchTo().alert();
        if (alert != null) {
          alert.accept();

          Log.message("Alert Handled");
        }
      }

    } catch (Exception e) {
      Log.message("Alert not present");
    }
  }

  /****
   * 
   * To return the value selected in the drop down
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */
  public static String getSelectedDropdownText(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      Select options = new Select(element);

      Log.message("Selected value " + options.getFirstSelectedOption().getText()
          + " in dropdown field " + label, driver, extentReport, screenshot);
      return (options.getFirstSelectedOption().getText());

    } catch (Exception e) {

      throw new Exception("Error while selecting dropdown by index value " + label + e);
    }
  }

  /****
   * method to select action menu on any page
   * 
   * @param reference
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void selectActionMenu(String reference, WebDriver driver, ExtentTest extentReport,
      boolean screenshot) throws Exception {



    try {

      String actionMenuLocator =
          "//table//td[contains(text(),'".concat("')]/following-sibling::td[contains(text(),'")
              .concat(reference).concat("')]/following-sibling::td//a[@title='Action Menu']");
      WebElement actionMenu = driver.findElement(By.xpath(actionMenuLocator));
      UIInteraction.click(actionMenu, "Action Menu", driver, extentReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while selecting Action Menu " + e);
    }
  }



  /*****
   * 
   * method to select checkbox corresponding to the mentioned policy
   * 
   * @param policyNo
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */

  public static void selectPolicyCheckbox(String reference, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {



    try {
      String policyCheckbox = "//table//td[contains(text(),'".concat(reference)
          .concat("')]/preceding-sibling::td//input[@type='checkbox']");



      System.out.println(policyCheckbox);

      Log.message(policyCheckbox);

      WebElement checkboxSelectPolicy = driver.findElement(By.xpath(policyCheckbox));



      UIInteraction.clickRadioButton(checkboxSelectPolicy, "Select the checkbox of the policy",
          driver, extentReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while selecting checkbox of the mentioned policy " + e);
    }



  }

  /****
   * 
   * To switch to the document opened
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public static void switchToDocument(WebDriver driver, ExtentTest extentReport) throws Exception {

    try {
      Log.message("Document opened: " + driver.getTitle(), extentReport);
      driver.close();

    } catch (NoSuchElementException e) {
      throw new Exception(e);
    }
  }

  /****
   * 
   * To get attribute value of an element
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */
  public static String getValue(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      Log.message("Value read is " + element.getAttribute("value") + " from field " + label, driver,
          extentReport, screenshot);
      return (element.getAttribute("value"));

    } catch (NoSuchElementException e) {
      Log.fail("Error while reading value from field " + label, driver, extentReport, true);
      throw new Exception("Error while reading in value " + label + e);

    }
  }

  /****
   * Validation to check if radio button/checkbox is selected
   * 
   * @param element
   * @param driver
   * @param label
   * @param extentReport
   * @return
   * @throws Exception
   */
  public static boolean isSelected(WebElement element) {

    boolean status = false;

    if (element.isSelected())
      status = true;
    return status;
  }

  /*******
   * 
   * Validation to check is radio button/checkbox is not selected
   * 
   * @param element
   * @param driver
   * @param label
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */
  public static boolean isNotSelected(WebElement element) {
    boolean status = true;

    if (element.isSelected())
      status = false;

    return status;
  }

  /*****
   * Validate if a field is mandatory or not.
   * 
   * @param element
   * @param driver
   * @param label
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */
  public static boolean isMandatory(WebElement element) {
    boolean status = false;

    if (element.isDisplayed() && element.getAttribute("innerHTML").contains("* Required field"))
      status = true;
    return status;
  }

  /****
   * Validation performed on check box
   * 
   * @param element
   * @param driver
   * @param text
   * @param extentReport
   * @return
   * @throws Exception
   */
  public static boolean validateCheckBox(WebElement element, WebDriver driver, String text,
      ExtentTest extentReport) throws Exception {
    boolean status = false;

    String[] performAction = text.split(",");

    for (int i = 0; i < performAction.length; i++) {
      if (performAction[i].contains("Validate")) {
        if (performAction[i].contains("Checked")) {
          status = UIInteraction.isSelected(element);
          Log.softAssertThat(status, "Checkbox is selected", "Checkbox is not selected", driver,
              extentReport, true);
        } else if (performAction[i].contains("Unchecked")) {
          status = UIInteraction.isNotSelected(element);
          Log.softAssertThat(status, "Checkbox is not selected", "Checkbox is selected", driver,
              extentReport, true);
        }
      } else if (performAction[i].contains("Check")) {
        if (UIInteraction.isNotSelected(element))
          UIInteraction.clickUsingJS(element, "Select checkbox", driver, extentReport, true);
        status = true;

        Log.softAssertThat(status, "Checkbox successfully selected", "Unable to select checkbox",
            driver, extentReport, true);
      } else if (performAction[i].contains("Uncheck")) {
        if (UIInteraction.isSelected(element))
          UIInteraction.clickUsingJS(element, "Deselect checkbox", driver, extentReport, true);
        status = true;

        Log.softAssertThat(status, "Checkbox deselected successfully",
            "Unable to deselect checkbox", driver, extentReport, true);
      }
    }

    return status;
  }

  /****
   * Validation performed for radio button
   * 
   * @param elements
   * @param driver
   * @param text
   * @param extentReport
   * @return
   * @throws Exception
   */
  public static boolean validateRadioButton(List<WebElement> elements, WebDriver driver,
      String text, ExtentTest extentReport) throws Exception {
    boolean status = false;

    String[] performAction = text.split(",");

    for (int i = 0; i < performAction.length; i++) {
      if (performAction[i].contains("Validate")) {
        if (performAction[i].contains("Yes")) {
          Log.message("String contains yes");
          for (WebElement element : elements) {
            if (element.getAttribute("checked") != null) {
              String selectedValue = UIInteraction.getValue(element,
                  "Default value of radio button", driver, extentReport, false);
              if (selectedValue.equalsIgnoreCase("1"))
                status = true;
              Log.softAssertThat(status, "Default value is Yes as Expected",
                  "Default value is not Yes", driver, extentReport, true);
            }
          }
        } else if (performAction[i].contains("No")) {
          Log.message("String contains No");
          for (WebElement element : elements) {
            if (element.getAttribute("checked") != null) {
              String selectedValue = UIInteraction.getValue(element,
                  "Default value of radio button", driver, extentReport, false);
              if (selectedValue.equalsIgnoreCase("0"))
                status = true;

              Log.softAssertThat(status, "Default value is No as Expected",
                  "Default value is not No", driver, extentReport, true);
            }
          }
        }
      } else if (performAction[i].contains("Yes")) {
        for (WebElement element : elements) {
          if (element.getAttribute("value").equalsIgnoreCase("1")) {
            UIInteraction.click(element, "Select Yes radio button", driver, extentReport, true);
            status = true;

            Log.softAssertThat(status, "Yes value is selected", "Yes value is not selected", driver,
                extentReport, true);
          }
        }
      } else if (performAction[i].contains("No")) {
        for (WebElement element : elements) {
          if (element.getAttribute("value").equalsIgnoreCase("0")) {
            UIInteraction.click(element, "Select No radio button", driver, extentReport, true);
            status = true;

            Log.softAssertThat(status, "No value is selected", "No value is not selected", driver,
                extentReport, true);
          }
        }
      }
    }
    return status;
  }

  /*****
   * 
   * Method to perform click on elements such as radio button, check boxes where style is in-line so
   * that the driver does not wait for element to be present
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @throws Exception
   */
  public static void clickRadioButton(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport, boolean screenshot) throws Exception {

    try {
      Thread.sleep(1000);
      Actions action = new Actions(driver);
      try {
        action.moveToElement(element).click().perform();
      } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).click().perform();
      }
      Log.message("Click performed " + label, driver, extentReport, screenshot);

    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport, true);
      throw new Exception("Error in performing click on " + label + e);
    }
  }

  public static void pressF12(String label, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    try {
      Robot robot = new Robot();
      robot.keyPress(KeyEvent.VK_F12);
      Thread.sleep(1000);
      robot.keyRelease(KeyEvent.VK_F12);

    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport);
      throw new Exception("Error in performing click on " + label + e);
    }
  }

  /*****
   * Validate if a field is disabled.
   * 
   * @param element
   * @param driver
   * @param input1
   * @param input2
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */
  public static boolean validateTextFieldSataus(WebElement element, WebDriver driver, String input,
      ExtentTest extentReport, boolean screenshot) throws Exception {
    boolean status = false;

    if (element.isDisplayed() && element.getAttribute(input).equals(true))
      status = true;
    return status;
  }


  /******
   * 
   * To convert positive value to negative and negative value to positive
   * 
   * @param driver
   * @param value
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public static String convertPostiveValueToNegativeAndNegValueToPositive(String value,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      if (value.contains("-")) {
        value = value.substring(1, value.length());
      } else {
        value = "-" + value;
      }
      return value;

    } catch (Exception e) {
      throw new Exception("Value not converted successfully" + e);
    }
  }

  /******
   * 
   * Addition of 2 numbers on same page
   * 
   * @param driver
   * @param element
   * @param element1
   * @param extentReport
   * @param label
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public static String additionOfTwoNumbers(String input, String input1, WebDriver driver,
      ExtentTest extentReport, boolean screenshot, String label) throws Exception {
    try {

      Double sum = Double.parseDouble(input) + Double.parseDouble(input1);
      Log.message("Addition happened " + label, driver, extentReport, screenshot);
      return Double.toString(sum);

    } catch (Exception e) {
      Log.fail("Error in Addition of 2 numbers " + label, driver, extentReport, true);
      throw new Exception("Addition not happened successfully" + e);
    }
  }

  /******
   * 
   * To check checkbox is checked for entire table
   * 
   * @param driver
   * @param element
   * @param extentReport
   * @param label
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public static boolean checkCheckBoxCheckedForEntireTable(List<WebElement> element,
      WebDriver driver, ExtentTest extentReport, boolean screenshot, String label)
      throws Exception {
    try {
      Boolean status = false;
      for (int i = 0; i < element.size(); i++) {
        if (element.get(i).isSelected()) {
          status = true;
        } else {
          status = false;
          break;
        }
      }
      return status;

    } catch (Exception e) {
      Log.fail("All checkboxes are not checked in the table" + label, driver, extentReport, true);
      throw new Exception("Checkboxes not checked successfully" + e);
    }

  }

  /******
   * 
   * To get the total of amount for entire row
   * 
   * @param driver
   * @param element
   * @param extentReport
   * @param label
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public static String sumTotalOfAmount(List<WebElement> element, WebDriver driver,
      ExtentTest extentReport, boolean screenshot, String label) throws Exception {
    try {

      double sum = 0;
      for (int i = 0; i < element.size(); i++) {
        String amount = element.get(i).getText();
        if (element.get(i).getText().contains(",")) {
          String output = "";
          for (int a = 0; a < amount.length(); a++) {
            if (amount.charAt(a) == ',') {
              output = amount.substring(0, a) + amount.substring(a + 1, amount.length());
              amount = output;
              break;
            }
          }
        }
        double amtd = Double.parseDouble(amount);
        sum = sum + amtd;
      }
      return Double.toString(sum);
    } catch (Exception e) {
      Log.fail("Sum Total of Amount returned" + label, driver, extentReport, true);
      throw new Exception("Sum total not returned" + e);
    }
  }

  /******
   * 
   * To get the past ,future and current date
   * 
   * @param driver
   * @param label
   * @param extentReport
   * @param screenshot
   * @param element
   * @param input
   * @throws Exception
   * @author Sakshi
   */
  public static String getPastFutureAndCurrentDate(WebElement element, String input,
      WebDriver driver, int noOfDays, ExtentTest extentReport, boolean screenshot, String label)
      throws Exception {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      Calendar cal = Calendar.getInstance();
      String date = "";
      if (input.equals("Current Date")) {
        date = sdf.format(cal.getTime());
      } else if (input.equals("Past Date")) {
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt("-" + noOfDays));
        date = sdf.format(cal.getTime());

      } else if (input.equals("Future Date")) {
        cal.add(Calendar.DAY_OF_MONTH, noOfDays);
        date = sdf.format(cal.getTime());
      }
      return date;
    }

    catch (Exception e) {
      Log.fail("Date not entered " + label, driver, extentReport, true);
      throw new Exception("Date not entered successfully" + e);
    }


  }

  /****
   * 
   * Click on confirm quote includes the handling of the alert
   * 
   * @param element
   * @param label
   * @param driver
   * @param input -> need to pass the alert pop up message
   * @param extentReport
   * @param screenshot
   * @throws Exception
   * @author Akshay.Saini
   */


  public static void getTextOfAlertAndAcceptAlert(WebElement element, String input, String label,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, element);
      Actions action = new Actions(driver);
      action.moveToElement(element).click().build().perform();
      UIInteraction.getTextAndAcceptAlert(driver, input);
      Log.message("Click performed " + label, driver, extentReport, screenshot);

    } catch (NoSuchElementException e) {
      Log.fail("Error in performing click on " + label, driver, extentReport, true);
      throw new Exception("Error in performing click on " + label + e);
    }
  }

  /****
   * 
   * To get the tex of alert and accept alerts
   * 
   * @param driver
   * @param input -> need to pass the alert pop up message
   * @author Akshay.Saini
   */

  public static boolean getTextAndAcceptAlert(WebDriver driver, String input) {
    boolean status = false;

    try {
      Thread.sleep(3000);
      WebDriverWait wait = new WebDriverWait(driver, 5);
      if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
        Alert alert = driver.switchTo().alert();
        if (alert != null) {

          if (alert.getText().equalsIgnoreCase(input)) {
            status = true;
          }
          alert.accept();

          Log.message("Alert Handled");
        }
      }

    } catch (NoAlertPresentException ex) {
      System.out.println("Exception Thrown in acceptAlert method " + ex);

    } catch (Exception e) {
      System.out.println(e);
    }
    return status;
  }

  /****
   * 
   * To Verify the status of checkbox whether checkbox is checked or unchecked
   * 
   * @param driver
   * @param checked -> Pass true if you want to verify checkbox is checked else false
   * @author Akshay.Saini
   */
  public static boolean verifyStatusOfCheckbox(WebElement element, boolean checked,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) {
    boolean status = false;

    if (checked) {
      if (element.getAttribute("checked") != null) {
        status = true;
      }
    }

    else {
      if (element.getAttribute("checked") == null) {
        status = true;
      }

    }


    return status;

  }

  /****
   * Method to get all options in a dropdown
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public static List<WebElement> getAllOptionsInDropdown(WebElement element, String label,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    WaitUtils.waitForElement(driver, element);

    try {
      Select option = new Select(element);
      List<WebElement> options = option.getOptions();

      return options;


    } catch (Exception e) {

      throw new Exception("Error while getting options of dropdown" + label + e);
    }
  }

  public static String validateDisabledButton(WebElement element, String label, WebDriver driver,
      ExtentTest extentReport) {

    Log.message(element.getAttribute("disabled"));

    return (element.getAttribute("disabled"));
  }

  /****
   * 
   * To get attribute value of an element
   * 
   * @param element
   * @param label
   * @param driver
   * @param extentReport
   * @param screenshot
   * @return
   * @throws Exception
   */
  public static String getAttribute(WebElement element, String attribute, String label,
      WebDriver driver, ExtentTest extentReport, boolean screenshot) throws Exception {
    try {

      Log.message("Value read is " + element.getAttribute(attribute) + " from field " + label,
          driver, extentReport, screenshot);
      return (element.getAttribute(attribute));

    } catch (NoSuchElementException e) {
      Log.fail("Error while reading value from field " + label, driver, extentReport, true);
      throw new Exception("Error while reading in value " + label + e);

    }
  }
  
  /****
   * 
   * To mark all transaction matches with reference
   * 
   * @param reference 
   * @param driver
   * @param extentReport
   * @param screenshot
   * author Sandeep.Sharma
   */
  
  public static void markTransctionsBasedOnRef(String reference, WebDriver driver, ExtentTest extentReport)
      throws Exception {
  
  String docRefChkbox= "//table//td[contains(text(),'".concat(reference).concat("')]/preceding-sibling::td//input[@type='checkbox']");
  
  System.out.println(docRefChkbox);

    List<WebElement> chkBoxes = driver.findElements(By.xpath(docRefChkbox));
  
    for(int i=0;i<chkBoxes.size();i++){
      List<WebElement> chkBoxes1 = driver.findElements(By.xpath(docRefChkbox));      
      UIInteraction.clickUsingJS(chkBoxes1.get(i), "Clicking", driver, extentReport, false);
      Thread.sleep(3000);
      
    }
    
  }


}

