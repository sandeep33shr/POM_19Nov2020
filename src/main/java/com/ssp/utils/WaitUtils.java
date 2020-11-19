package com.ssp.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.support.WebDriverFactory;

public class WaitUtils {
  static final ExpectedCondition<Boolean> documentLoad;
  static final ExpectedCondition<Boolean> framesLoad;
  static final ExpectedCondition<Boolean> imagesLoad;
  static final ExpectedCondition<Boolean> spinnerLoad;
  static final ExpectedCondition<Boolean> overLayLoad;
  static final int MAXPAGELOADWAIT = 180;
  static final int MAXELEMENTWAIT = 15;

  static String cssSpinner = ".ssp-circles-spinner";
  static String overLay = "#overlay";
  private static By allSpinners = By.cssSelector(cssSpinner);
  private static By overLayBy = By.cssSelector(overLay);

  static {
    documentLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean docReadyState = false;
        try {
          docReadyState = (Boolean) js.executeScript(
              "return (function() { if (document.readyState != 'complete') {  return false; } if (window.jQuery != null && window.jQuery != undefined && window.jQuery.active) { return false;} if (window.jQuery != null && window.jQuery != undefined && window.jQuery.ajax != null && window.jQuery.ajax != undefined && window.jQuery.ajax.active) {return false;}  if (window.angular != null && angular.element(document).injector() != null && angular.element(document).injector().get('$http').pendingRequests.length) return false; return true;})();");
        } catch (WebDriverException e) {
          docReadyState = true;
        }
        return docReadyState;
      }
    };

    imagesLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        boolean docReadyState = true;
        try {
          JavascriptExecutor js;
          List<WebElement> images = driver.findElements(By.cssSelector("img[src]"));
          for (WebElement image : images) {
            try {
              js = (JavascriptExecutor) driver;
              docReadyState = (Boolean) js.executeScript(
                  "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0")
                  && image.isDisplayed();
              if (!docReadyState) {
                break;
              }
            } catch (StaleElementReferenceException e) {
              driver.findElements(By.cssSelector("img[src]"));
            } catch (WebDriverException e) {
              docReadyState = true;
            }
          }
        } catch (WebDriverException e) {
          docReadyState = true;
        }
        return docReadyState;
      }
    };

    framesLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        boolean docReadyState = true;
        try {
          JavascriptExecutor js;
          List<WebElement> frames = driver.findElements(By.cssSelector("iframe[style*='hidden']"));
          for (WebElement frame : frames) {
            try {
              driver.switchTo().defaultContent();
              driver.switchTo().frame(frame);
              js = (JavascriptExecutor) driver;
              docReadyState =
                  (Boolean) js.executeScript("return (document.readyState==\"complete\")");
              driver.switchTo().defaultContent();
              if (!docReadyState) {
                break;
              }
            } catch (WebDriverException e) {
              docReadyState = true;
            }
          }
        } catch (WebDriverException e) {
          Log.message("Exception: " + e);
        } finally {
          driver.switchTo().defaultContent();
        }
        return docReadyState;
      }
    };

    spinnerLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        List<WebElement> spinners = driver.findElements(allSpinners);
        for (WebElement spinner : spinners) {
          try {
            if (spinner.isDisplayed()) {
              return false;
            }
          } catch (NoSuchElementException ex) {
            ex.printStackTrace();
          }
        }
        // To wait click events to trigger
        try {
          Thread.sleep(250);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        spinners = driver.findElements(allSpinners);
        for (WebElement spinner : spinners) {
          try {
            if (spinner.isDisplayed()) {
              return false;
            }
          } catch (NoSuchElementException ex) {
            ex.printStackTrace();
          }
        }
        return true;
      }
    };

    overLayLoad = new ExpectedCondition<Boolean>() {
      public final Boolean apply(final WebDriver driver) {
        List<WebElement> overlays = driver.findElements(overLayBy);
        for (WebElement overlay : overlays) {
          try {
            if (overlay.isDisplayed()) {
              return false;
            }
          } catch (NoSuchElementException ex) {
            ex.printStackTrace();
          }
        }
        // To wait click events to trigger
        try {
          Thread.sleep(250);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        overlays = driver.findElements(overLayBy);
        for (WebElement overlay : overlays) {
          try {
            if (overlay.isDisplayed()) {
              return false;
            }
          } catch (NoSuchElementException ex) {
            ex.printStackTrace();
          }
        }
        return true;
      }
    };

  }

  /**
   * To wait till spinner load
   * 
   * @param driver -
   */

  public static void waitForSpinner(final WebDriver driver) {
    long startTime = StopWatch.startTime();
    try {
      Thread.sleep(3000);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page document is not loading")).until(documentLoad);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page document is not loading")).until(imagesLoad);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Realize spinners/page not loading")).until(spinnerLoad);
    } catch (Exception ex) {
      Log.event("Catched spinner load exception");
    }
    Log.event("Spinner Load Wait: (Sync)", StopWatch.elapsedTime(startTime));

  }

  public static void waitForOverlay(final WebDriver driver) {
    long startTime = StopWatch.startTime();
    try {
      Thread.sleep(2000);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page document is not loading")).until(documentLoad);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page document is not loading")).until(imagesLoad);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Realize spinners/page not loading")).until(spinnerLoad);
    } catch (Exception ex) {
      Log.event("Catched Overlay exception");
    }
    Log.event("Overlay Wait: (Sync)", StopWatch.elapsedTime(startTime));

  }

  /**
   * To wait for the Element is present until its visibility is true
   * 
   * @param driver : Webdriver
   * @param seconds : no of millisecsonds to wait
   * @param elementToCheck : locator to be given as Webelement
   * @param msg : message to print in the log
   */
  public static void waitForElementPresent(WebDriver driver, int seconds, WebElement elementTocheck,
      String msg) {
    (new WebDriverWait(driver, seconds)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage(msg)).until(ExpectedConditions.visibilityOf(elementTocheck));

  }

  /**
   * To wait for the Element is present until its visibility is true
   * 
   * @param driver : Webdriver
   * @param elementToCheck : locator to be given as Webelement
   * @param msg : message to print in the log
   */
  public static void waitForElementPresent(WebDriver driver, WebElement elementTocheck,
      String msg) {
    (new WebDriverWait(driver, 180)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage(msg)).until(ExpectedConditions.visibilityOf(elementTocheck));
    Log.event(msg);

  }

  public static void waitForElementPresent(WebDriver driver, By elementTocheck, String msg) {
    (new WebDriverWait(driver, 180)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage(msg)).until(ExpectedConditions.visibilityOfElementLocated(elementTocheck));
  }

  public static void waitForStalenessOFElementPresent(WebDriver driver, WebElement elementTocheck,
      String msg) {
    (new WebDriverWait(driver, 180)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage(msg)).until(ExpectedConditions.stalenessOf(elementTocheck));
  }

  /**
   * Finds the element by the given locatorType and locatorValue and waits for the element to be
   * visible.
   * 
   * @param driver : Webdriver
   * @param locatorType : how the element to be looked up (CSS or Xpath)
   * @param locatorValue : the address of the element
   * @param timeOutInSec: the maximum wait time in seconds
   * @param msg : timeout error message
   */
  public static void waitForElementPresent(WebDriver driver, String locatorType,
      String locatorValue, int timeOutInSec, String msg) {
    if (locatorType.equalsIgnoreCase("css")) {
      (new WebDriverWait(driver, timeOutInSec)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage(msg))
              .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
    } else if (locatorType.equalsIgnoreCase("xpath")) {
      (new WebDriverWait(driver, timeOutInSec)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage(msg))
              .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
    }
  }

  /**
   * Finds the element by the given locatorType and locatorValue and waits for the element to be
   * visible.
   * 
   * @param driver : Webdriver
   * @param locatorType : how the element to be looked up (CSS or Xpath)
   * @param locatorValue : the address of the element
   * @param timeOutInSec: the maximum wait time in seconds
   * @param msg : timeout error message
   */
  public static void waitForElementAbsent(WebDriver driver, String locatorType, String locatorValue,
      int timeOutInSec, String msg) {
    if (locatorType.equalsIgnoreCase("css")) {
      (new WebDriverWait(driver, timeOutInSec)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage(msg))
              .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locatorValue)));
    } else if (locatorType.equalsIgnoreCase("xpath")) {
      (new WebDriverWait(driver, timeOutInSec)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage(msg))
              .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locatorValue)));
    }
  }

  /**
   * To wait for the Element is present until its invisibility is true
   * 
   * @param driver : Webdriver
   * @param seconds : no of millisecsonds to wait
   * @param locator : locator to be given as Webelement
   * @param msg : message to print in the log
   */
  public static void waitForinvisiblityofElement(WebDriver driver, int seconds, String locator) {
    (new WebDriverWait(driver, seconds)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Unable to locate the element"))
            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));

  }

  /**
   * To wait for the Element is present until its invisibility is true
   * 
   * @param driver : Webdriver
   * @param seconds : no of millisecsonds to wait
   * @param locator : locator to be given as List element
   * @param msg : message to print in the log
   */
  public static void waitForinvisiblityofListElement(WebDriver driver, String locator, String msg) {
    List<WebElement> element = driver.findElements(By.cssSelector(locator));
    (new WebDriverWait(driver, 180)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage(msg)).until((ExpectedConditions.invisibilityOfAllElements(element)));

  }

  /**
   * To wait for the Element is visible and clickable
   * 
   * @param driver : Webdriver
   * @param elementTocheck : locator to be given as Webelement
   * @param msg : message to print in the log
   */
  public static void waitForelementToBeClickable(WebDriver driver, WebElement elementTocheck,
      String msg) {
    (new WebDriverWait(driver, 180)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage(msg)).until(ExpectedConditions.elementToBeClickable(elementTocheck));
    Log.event(msg);
  }

  /**
   * WaitForPageLoad waits for the page load with default page load wait time
   * 
   * @param driver : Webdriver
   */
  public static void waitForPageLoad(final WebDriver driver) {
    waitForPageLoad(driver, WebDriverFactory.maxPageLoadWait);
  }

  /**
   * WaitForPageLoad waits for the page load with custom page load wait time
   * 
   * @param driver : Webdriver
   * @param maxWait : Max wait duration
   */
  public static void waitForPageLoad(final WebDriver driver, int maxWait) {
    long startTime = StopWatch.startTime();
    FluentWait<WebDriver> wait = new WebDriverWait(driver, maxWait)
        .ignoring(StaleElementReferenceException.class).withMessage("Page Load Timed Out");
    try {

      wait.until(WebDriverFactory.documentLoad);

      String title = driver.getTitle().toLowerCase();
      String url = driver.getCurrentUrl().toLowerCase();
      Log.event("Page URL:: " + url);

      if ("the page cannot be found".equalsIgnoreCase(title) || title.contains("is not available")
          || url.contains("/error/") || url.toLowerCase().contains("/errorpage/")) {
        Assert.fail("Site is down. [Title: " + title + ", URL:" + url + "]");
      }
    } catch (TimeoutException e) {
      driver.navigate().refresh();
      wait.until(WebDriverFactory.documentLoad);

    }
    Log.event("Page Load Wait: (Sync)", StopWatch.elapsedTime(startTime));

  } // waitForPageLoad

  /**
   * To wait for the specific element on the page
   * 
   * @param driver : Webdriver
   * @param element : Webelement to wait for
   * @return boolean - return true if element is present else return false
   */
  public static boolean waitForElement(WebDriver driver, WebElement element) {
    return waitForElement(driver, element, MAXELEMENTWAIT);
  }

  /**
   * To wait for the specific element on the page
   * 
   * @param driver : Webdriver
   * @param element : Webelement to wait for
   * @param maxWait : Max wait duration
   * @return boolean - return true if element is present else return false
   */
  public static boolean waitForElement(WebDriver driver, WebElement element, int maxWait) {
    boolean statusOfElementToBeReturned = false;
    long startTime = StopWatch.startTime();
    WebDriverWait wait = new WebDriverWait(driver, maxWait);
    try {
      WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
        statusOfElementToBeReturned = true;
        Log.event("Element is displayed:: " + element.toString());
      }
    } catch (Exception e) {
      statusOfElementToBeReturned = false;
      Log.event("Unable to find a element after " + StopWatch.elapsedTime(startTime) + " sec ==> "
          + element.toString());
    }
    return statusOfElementToBeReturned;
  }

  public static boolean waitForButtonClick(WebDriver driver, WebElement element, int maxWait) {
    boolean statusOfElementToBeReturned = false;
    long startTime = StopWatch.startTime();
    WebDriverWait wait = new WebDriverWait(driver, maxWait);
    try {

      if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
        statusOfElementToBeReturned = true;
        Log.event("Button is clickable:: " + element.toString());
      }
    } catch (Exception e) {
      statusOfElementToBeReturned = false;
      Log.event("Button not clickable after " + StopWatch.elapsedTime(startTime) + " sec ==> "
          + element.toString());
    }
    return statusOfElementToBeReturned;
  }

  /**
   * To wait for the specific list elements on the page
   * 
   * @param driver -
   * @param elements - List elements to wait for to appear
   * @param maxWait - how long to wait for
   * @return boolean - return true if element is present else return false
   */
  public static boolean waitForListElement(WebDriver driver, List<WebElement> elements,
      int maxWait) {
    boolean statusOfElementToBeReturned = false;
    long startTime = StopWatch.startTime();
    WebDriverWait wait = new WebDriverWait(driver, maxWait);
    try {
      WebElement waitElement =
          (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
        statusOfElementToBeReturned = true;
        Log.event("List Element is displayed:: " + elements.toString());
      }
    } catch (Exception ex) {
      statusOfElementToBeReturned = false;
      Log.event("Unable to find list element after " + StopWatch.elapsedTime(startTime)
          + " sec ==> " + elements.toString());
    }
    return statusOfElementToBeReturned;
  }

  /**
   * Wait until element disappears in the page
   * 
   * @param driver - driver instance
   * @param element - webelement to wait to have disaapear
   * @return true if element is not appearing in the page
   */
  public static boolean waitUntilElementDisappear(WebDriver driver, final WebElement element) {
    final boolean isNotDisplayed;

    WebDriverWait wait = new WebDriverWait(driver, WebDriverFactory.maxPageLoadWait);
    isNotDisplayed = wait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver webDriver) {
        boolean isPresent = false;
        try {
          if (element.isDisplayed()) {

            Log.event("Element " + element.toString() + ", is still visible in page");
          }
        } catch (Exception ex) {
          isPresent = true;
          Log.event("Element " + element.toString() + ", is not displayed in page ");
          return isPresent;
        }
        return isPresent;
      }
    });
    return isNotDisplayed;
  }

  /**
   * To wait for the specific element which is in disabled state on the page
   * 
   * @param driver - current driver object
   * @param element - disabled web element
   * @param maxWait - duration of wait in seconds
   * @return boolean - return true if disabled element is present else return false
   */
  public static boolean waitForDisabledElement(WebDriver driver, WebElement element, int maxWait) {
    boolean statusOfElementToBeReturned = false;
    long startTime = StopWatch.startTime();
    WebDriverWait wait = new WebDriverWait(driver, maxWait);
    try {
      WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
      if (!waitElement.isEnabled()) {
        statusOfElementToBeReturned = true;
        Log.event("Element is displayed and disabled:: " + element.toString());
      }
    } catch (Exception ex) {
      statusOfElementToBeReturned = false;
      Log.event("Unable to find disabled element after " + StopWatch.elapsedTime(startTime)
          + " sec ==> " + element.toString());
    }
    return statusOfElementToBeReturned;
  }

  /****
   * To wait till iframe loads
   * 
   * @param driver
   */
  public static void waitForIframe(final WebDriver driver) {
    long startTime = StopWatch.startTime();
    try {
      Thread.sleep(2000);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page document is not loading")).until(documentLoad);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page document is not loading")).until(imagesLoad);
      (new WebDriverWait(driver, 60)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Iframe not loading")).until(framesLoad);
    } catch (Exception ex) {
      Log.event("Catched iframe load exception");
    }
    Log.event("Iframe load Wait: (Sync)", StopWatch.elapsedTime(startTime));

  }

  public static boolean waitForRadioButtonCheckbox(WebDriver driver, WebElement element,
      String message) {
    boolean statusOfElementToBeReturned = false;
    long startTime = StopWatch.startTime();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    try {
      
      if (element.isEnabled()) {
        statusOfElementToBeReturned = true;
        Log.event(message + element.toString());
      }
    } catch (Exception e) {
      statusOfElementToBeReturned = false;
      Log.event("Unable to find a element after " + StopWatch.elapsedTime(startTime) + " sec ==> "
          + element.toString());
    }
    return statusOfElementToBeReturned;
  }
}
