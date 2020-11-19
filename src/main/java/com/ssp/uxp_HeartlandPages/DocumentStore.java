package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.UIInteraction;
import com.ssp.utils.WaitUtils;

public class DocumentStore extends LoadableComponent<DocumentStore> {

  static HashMap<String, String> dyamicHashMap = new HashMap<>();
  @FindBy(xpath = "//h1[contains(text(), 'Document Store')]")
  WebElement headerDocumentStore;

  @FindBy(css = "[title='Policy']")
  WebElement linkPolicyFolder;

  @FindBy(xpath = "//span[contains(text(),'Policy')]")
  WebElement headerPolicyFolder;

  @FindBy(xpath = "//*[@id='table-compact']//td//span[contains(text(),'.pdf')]")
  WebElement linkPolicyDoc;

  @FindBy(xpath = "//*[@id='table-compact']//tr[1]//td//button")
  WebElement btnAction;

  @FindBy(xpath = "//div[contains(text(),'View details')]/parent::a")
  WebElement linkViewDetails;

  @FindBy(xpath = "//span[contains(text(),'Renewal')]")
  WebElement tagRenewals;

  @FindBy(xpath = "//span[contains(text(),'NewBusiness')]")
  WebElement tagNewBusiness;

  @FindBy(xpath = "//span[contains(text(),'MTA')]")
  WebElement tagMTA;
  
  public final WebDriver driver;
  public final ExtentTest extentReport;
  private boolean isPageLoaded;


  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }

    new ElementLayer(driver);
  }


  @Override
  protected void isLoaded() throws Error {
    

  }

  public DocumentStore(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);

  }


  /***
   * Verify is doc store is loaded
   * 
   * @return
   * @throws Exception
   */

  public boolean verifyDocStorePage() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, headerDocumentStore))
      throw new Exception("Document Store is not loaded");
    return true;

  }

  /****
   * Verify the navigation to Policy folder.
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToPolicy(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkPolicyFolder, "Policy Folder", driver, extentReport, true);
  }

  /*****
   * Verify Policy folder loaded.
   * @return
   * @throws Exception
   */
  public boolean verifyPolicyPage() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, headerPolicyFolder))
      throw new Exception("Policy folder is not loaded");
    return true;

  }

/****
 *  
 *  select current policy folder. 
 * @param quotesData
 * @param driver
 * @param extentReport
 * @throws Exception
 */
  public void selectCurrentPolicyFolder(HashMap<String, String> quotesData, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    String policyFolder = "//*[@id='table-compact']//td/span[contains(text(),'"
        .concat(quotesData.get("policyNo")).concat("')]/div");
    Log.message(policyFolder);
    WebElement policyFolderLink = driver.findElement(By.xpath(policyFolder));
    UIInteraction.click(policyFolderLink, "Select policy folder", driver, extentReport, true);
  }

  /****
   * 
   * method to navigate back to the portal
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateBackToPortal(WebDriver driver) throws Exception {

      UIInteraction.closeCurrentTab(driver);
  }

  /****
   * To verify if document is generated.
   * @return
   * @throws Exception
   */
  public boolean verifyDocumentGenerated() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, linkPolicyDoc))
      throw new Exception("Current policy has no documents generated");
    return true;

  }

  /****
   * Method to navigate to the document details. 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void navigateToDocumentDetails(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.click(btnAction, "Actions Button", driver, extentReport, true);
    UIInteraction.click(linkViewDetails, "View Details", driver, extentReport, true);

  }
  /****
   * Method to open a document and close it. 
   * @return
   * @throws Exception
   */
  public boolean openDocument() throws Exception {

    UIInteraction.click(linkPolicyDoc, "Open Document", driver, extentReport, true);
    UIInteraction.switchToDocument(driver, extentReport);
    UIInteraction.switchToTab(driver);

    return true;

  }
  /****
   * Verify if new business tag is present on the document. 
   * @return
   * @throws Exception
   */
  public boolean verifyNewBusinessDocument() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, tagNewBusiness))
      throw new Exception("New Business tag not present.");
    return true;

  }
  /****
   * Verify if renewal tag is present on the document.
   * @return
   * @throws Exception
   */
  public boolean verifyRenewalDocument() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, tagRenewals))
      throw new Exception("Renewal tag not present.");
    return true;
  }
  /****
   * Verify if MTA tag is present on the document. 
   * @return
   * @throws Exception
   */
  public boolean verifyMTADocument() throws Exception {
    WaitUtils.waitForSpinner(driver);

    if (!WaitUtils.waitForElement(driver, tagMTA))
      throw new Exception("MTA tag not present.");
    return true;
  }

  /*****
   * To capture the number of existing files
   * 
    * @param driver
    * @param element
    * @param extentReport
    * @param label
    * @param screenshot
    * @throws Exception
    * @author Sakshi
   */
  public static String captureNoOfExistingFile(List<WebElement> element,WebDriver driver,
      ExtentTest extentReport, boolean screenshot,String label) throws Exception {
      try {
        
      int NoOfExistingFile= element.size();
      dyamicHashMap.put("NoOfExistingFile",Integer.toString(NoOfExistingFile));
      return Integer.toString(NoOfExistingFile);
    } catch (Exception e) {
      Log.fail("Number of existing file not captured properly " + label, driver, extentReport, true);
      throw new Exception("Number of existing file not captured properly" + e);
    }
     
    }
  
  /****
   *  
   *  Verify current policy folder. 
   * @param quotesData
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
    public boolean verifyCurrentPolicyFolder(String policyNumber, WebDriver driver
        ) throws Exception {

      boolean status = false;
      
      String policyFolder = "//*[@id='table-compact']//td/span[contains(text(),'"
          .concat(policyNumber).concat("')]/div");
      Log.message(policyFolder);
      WebElement policyFolderLink = driver.findElement(By.xpath(policyFolder));
      
      if(policyFolderLink.isDisplayed()) {
        status = true;
      }
      
      return status;
    }

}
