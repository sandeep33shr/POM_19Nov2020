package com.ssp.support;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Reporter;
import org.testng.SkipException;


/**
 * WebdriverFactory class used to get a web driver instance, depends on the user requirement as
 * driverHost, driverPort and browserName we adding the desiredCapabilities and other static action
 * initialized here and some methods used to retrieve the Hub and node information. It also consists
 * page wait load for images/frames/document
 */

public class WebDriverFactory {
  private static EnvironmentPropertiesReader configProperty =
      EnvironmentPropertiesReader.getInstance();


  static String browserName;

  static ChromeOptions chromeOpt = new ChromeOptions();
  static FirefoxOptions firefoxOpt = new FirefoxOptions();
  static EdgeOptions edgeOpt = new EdgeOptions();

  public static final ExpectedCondition<Boolean> documentLoad;
  public static final ExpectedCondition<Boolean> framesLoad;
  public static final ExpectedCondition<Boolean> imagesLoad;
  public static int maxPageLoadWait = 180;

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
          // List <WebElement> images =
          // driver.findElements(By.cssSelector("img[src]"));
          List<WebElement> images = driver.findElements(By.cssSelector("img[src*='images']"));
          for (int i = 0; i < images.size(); i++) {
            try {
              js = (JavascriptExecutor) driver;
              docReadyState = docReadyState && (Boolean) js.executeScript(
                  "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                  images.get(i));
              if (!docReadyState) {
                break;
              }
            } catch (StaleElementReferenceException e) {
              // images =
              // driver.findElements(By.cssSelector("img[src]"));
              images = driver.findElements(By.cssSelector("img[src*='images']"));
              i--;
              continue;
            } catch (WebDriverException e) {

              // setting the true value if any exception arise
              // Ex:: inside frame or switching to new windows or
              // switching to new frames
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
              docReadyState = (Boolean) js.executeScript("return (document.readyState==\"complete\")");
              driver.switchTo().defaultContent();
              if (!docReadyState) {
                break;
              }
            } catch (WebDriverException e) {
              docReadyState = true;
            }
          }
        } catch (WebDriverException e) {
          Log.message("Exception "+ e);
        } finally {
          driver.switchTo().defaultContent();
        }
        return docReadyState;
      }
    };

    Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
   

    maxPageLoadWait = configProperty.getProperty("maxPageLoadWait") != null
        ? Integer.valueOf(configProperty.getProperty("maxPageLoadWait")) : maxPageLoadWait;

    chromeOpt.addArguments("--ignore-certificate-errors");
    chromeOpt.addArguments("--disable-bundled-ppapi-flash");
    chromeOpt.addArguments("--disable-extensions");
    chromeOpt.addArguments("--disable-web-security");
    chromeOpt.addArguments("--always-authorize-plugins");
    chromeOpt.addArguments("--allow-running-insecure-content");
    chromeOpt.addArguments("--test-type");
    chromeOpt.addArguments("--enable-npapi");
 //   chromeOpt.addArguments("--incognito");
    
   firefoxOpt.setLegacy(true);

  }

   

  public static void quite(WebDriver driver) {
    driver.quit();
  }

  public static void close(WebDriver driver) {
    driver.close();
  }


  /**
   * Method to get instance of web driver using default parameters
   * 
   * @return driver
   */
  public static WebDriver get() {
    browserName = System.getProperty("browserName") != null ? System.getProperty("browserName")
        : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
            .getParameter("browserName").toLowerCase();
    System.out.println("browserName : " + browserName);
    return get(browserName);
  }

  /**
   * Web driver to get the web driver with browser name and platform and setting the desired
   * capabilities for browsers
   * 
   * @param browserWithPlatform : Browser With Platform
   * @return driver
   */
  public static WebDriver get(String browser) {

    WebDriver driver = null;
    // long startTime = StopWatch.startTime();
    System.out.println(browser);

   
    try {
      if ("chrome".equalsIgnoreCase(browser)) {
        System.out.println("starting Browser " + browser);
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/executables/chromedriver.exe");
        driver = new ChromeDriver(chromeOpt);
      }
      else if ("edge".equalsIgnoreCase(browser)) {
        System.out.println("starting Browser " + browser);
        System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/src/main/resources/executables/msedgedriver.exe");
        
        
        driver = new EdgeDriver();
        
       }
      else if("firefox".equalsIgnoreCase(browser)){
        System.out.println("starting Browser " + browser);
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/resources/executables/geckodriver.exe");
        
        driver =  new FirefoxDriver();
       
       }
     
      driver.manage().window().maximize();     
      driver.manage().timeouts().pageLoadTimeout(maxPageLoadWait, TimeUnit.SECONDS);
    }
    catch (Exception e) {
        e.printStackTrace();
        throw new SkipException("Browser not launched.");
    }
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
   
    return driver;

  }
 
}


