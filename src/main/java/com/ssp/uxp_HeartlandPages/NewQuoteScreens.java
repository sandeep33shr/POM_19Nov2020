package com.ssp.uxp_HeartlandPages;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
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
import com.ssp.utils.heartland.Interactions;

public class NewQuoteScreens extends LoadableComponent<NewQuoteScreens> {
  private final WebDriver driver;
  private ExtentTest extentReport;
  private boolean isPageLoaded;



  @FindBy(css = "iframe[id='dqjIframe']")
  WebElement frameDQJ;

  @FindBy(css = "iframe[id='QMIframe']")
  WebElement frameQMI;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_0_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_TRADING_NAME")
  WebElement fldTradingName;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_0_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_TELEPHONE_NUM")
  WebElement fldTelephoneNumber;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_0_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_CONTACT_NAME")
  WebElement fldContanctName;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_0_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_COMPANY_STATUS")
  WebElement dropdownCompanyStatus;

  @FindBy(css = "#C1__C1__QUE_BA23D49FD83824B31924")
  WebElement fldPostCode;

  @FindBy(css = "#C1__C1__BUT_BA23D49FD83824B32484")
  WebElement btnFindAddress;

  @FindBy(css = "#C1__C1__QUE_BA23D49FD83824B31964")
  WebElement dropdownSelectAddress;

  @FindAll({@FindBy(
      xpath = "//*[@name='C1__HIUAGH[1].DATA_SET[1].RISK_OBJECTS[1].COMPONENT_HIUAGH_POLICY_BINDER[1].COMPONENT_GENERAL_DETAILS[1].CHANNEL_ISLES']")})
  List<WebElement> radioButtonChannelIsles;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_0_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_CHANNEL_ISLES_1")
  WebElement radioButtonChannelIslesNo;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_1_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_rISK_DETAILS_COVER_TYPE")
  WebElement dropdownCoverType;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_1_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_AT_THIS_PREMISES")
  WebElement fldCurrentPremisesYears;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_1_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_GENERAL_DETAILS_ELSEWHERE")
  WebElement fldElsewhereYears;

  @FindBy(css = "#C1__C1__QUE_BA23D49FD83824B31968")
  WebElement fldAddressLine1;

  @FindBy(css = "#C1__C1__QUE_BA23D49FD83824B31972")
  WebElement fldAddressLine2;

  @FindBy(css = "#C1__C1__QUE_BA23D49FD83824B31976")
  WebElement fldAddressLine3;

  @FindBy(css = "#C1__C1__QUE_4ABA2D83CE3F6B8189114")
  WebElement fldCounty;

  @FindBy(
      css = "#C1__p4_Screen_SSd_GENDETS_1_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_GOOD_STATE_OF_rEPAIR > div > div > div:nth-child(1) > label")
  WebElement radiobtnGoodStateYes;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_2_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_rEPAIR_DETAILS")
  WebElement fldRepairDetails;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_1_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_AGE_OF_BUILDING")
  WebElement fldYearOfConstruction;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_2_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_ARE_ALL_THE_WALLS_0")
  WebElement radiobtnAreTheWallsYes;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_2_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_ARE_ALL_rOOFS_0")
  WebElement radiobtnAreTheRoofYes;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_4_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUBSIDENCE_ASSESSMENT_STATE_OF_SITE")
  WebElement dropdownStateOfSite;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_4_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUBSIDENCE_ASSESSMENT_FOUNDATIONS")
  WebElement dropdownFoundation;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_7_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_LETTING_BEDROOMS")
  WebElement fldNoOfBedroom;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_7_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_TURNOVER")
  WebElement fldTurnAround;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_TOTAL_WINE")
  WebElement fldTotalWine;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_TOTAL_COMPUTER")
  WebElement fldTotalComputer;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_FROZEN_FOOD")
  WebElement fldFrozenFood;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_9_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_INCCIT")
  WebElement fldIncreaseSI_1;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_9_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_INCCIS")
  WebElement fldIncreaseSI_2;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_MAKE_MODEL_SAFE")
  WebElement fldMakeAndModel;
  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_9_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_PUBLIC_LIABILITY")
  WebElement dropdownPublicLiability;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_10_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_HH_BUILDINGS")
  WebElement fldBuildingImprovement;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_10_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_HH_CONTENTS")
  WebElement fldContents;

  @FindBy(xpath = "//button[@title='Next']")
  WebElement btnNext;

  @FindBy(xpath = "//button[@title='Quote']")
  WebElement btnQuote;

  @FindBy(css = "#C1__HEAD_20D13DB375CF531F582463")
  WebElement QuoteResultTitle;

  @FindBy(css = "#C1__QUE_1E812951410511F6398675")
  WebElement txtAnnualPremium;

  @FindBy(css = "#C1__QUE_CE78B7D13414C653636886")
  WebElement txtInsurerPremium;

  @FindBy(css = "#C1__QUE_CE78B7D13414C653636888")
  WebElement txtTotalAddOns;

  @FindBy(css = "#C1__QUE_CE78B7D13414C653636890")
  WebElement txtBrokerFees;

  @FindBy(css = "#C1__QUE_06F3B4EAEA82929E449207")
  WebElement txtNetIncome;

  @FindBy(css = "#C1__QUE_7AAAFB7E43E78591310677")
  WebElement txtDueFromSubAgent;

  @FindBy(css = "#C1__QUE_06F3B4EAEA82929E439331")
  WebElement txtThirdPartyComm;

  @FindBy(css = "#C1__BUT_CE78B7D13414C653641573")
  WebElement btnSave;

  @FindBy(css = "#C1__BUT_CE78B7D13414C653641578")
  public WebElement btnManualQuote;

  // Manual Quote Section
  @FindBy(css = "#C1__C1__QUE_9B559B0CD1263D787991245")
  public WebElement fldNetPremium;

  @FindBy(css = "#C1__C1__BUT_A986AC9ABD649F1C62816")
  WebElement btnCalculate;

  @FindBy(css = "#C1__C1__QUE_1607BC33A327DCF3191387")
  public WebElement fldPolicyNo;

  @FindBy(css = "#C1__C1__BUT_9B559B0CD1263D786984458")
  public WebElement btnSaveManualQuote;

  // Add-ons section
  @FindBy(css = "#accordian_Add_ons")
  WebElement headerAddOnSection;

  @FindBy(
      xpath = "(//li[@aria-hidden='false']//div[@class='panel panel-default panel-yellow nobgcolor']//button[@title='Add'])[1]")
  WebElement buttonAddAddOn1;

  @FindBy(
      xpath = "(//li[@aria-hidden='false']//div[@class='panel panel-default panel-yellow nobgcolor']//button[@title='Add'])[2]")
  WebElement buttonAddAddon2;
  
  @FindBy(
      css = "#C1__BUT_21896C493B7E4484403075_R5")
  WebElement buttonAddKeyCare;

  // Broker Fee section
  @FindBy(css = "#accordian_fee_section")
  WebElement headerBrokerFeeSection;

  @FindBy(xpath = "//span[@style='display: inline; visibility: visible; opacity: 1;']")
  WebElement txtErrorMessage;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_0_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_TURNOVER")
  WebElement fldTurnoverAmount;

  @FindBy(css = "#C1__C1__QUE_378FFC841CDE2278269218")
  WebElement dropdownCountry;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_BUILDINGS")
  WebElement fldBuildings;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_CONTENTS_AMOUNT")
  WebElement dropdownContentsAmount;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_INCCIT")
  WebElement fldIncreaseSIforCash;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_8_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_SUMS_INSURED_GH_INCCIS")
  WebElement fldIncreaseSIonTransit;

  @FindBy(
      css = "#C1__Screen_SSd_GENDETS_5_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_SEASONAL_BASIS_1")
  WebElement radiobtnSeasonalBasisNo;

  @FindBy(
      xpath = "//span[@class='GlobalFont Color1'][contains(text(),'Manual')]/parent::div/parent::td/preceding-sibling::td//div//a[@title='Insurer Name Icon']")
  public WebElement linkDemoManualPolicy;

  @FindBy(css = "#C1__BUT_CE78B7D13414C653641578")
  WebElement btnOverride;

  @FindBy(css = "#C1__C1__QUE_275617B0F903FD8B139505")
  WebElement dropdownRating;

  @FindBy(css = "#C1__C1__QUE_9B559B0CD1263D788093144")
  WebElement fldNetPremiumInOverride;

  @FindBy(css = "#C1__C1__QUE_9B559B0CD1263D786975206")
  WebElement dropdownOverrideReason;

  @FindBy(css = "#C1__C1__QUE_A29270A78A527B77287321")
  public WebElement fldOverrideText;

  @FindBy(css = "#C1__QUE_09464E2384418BE11314022")
  WebElement fldOverrideFeeAmount;

  // @FindBy(css = "#C1__BUT_09464E2384418BE11314040")

  @FindBy(xpath = "(//button[@id='C1__BUT_7F96F421A619713B565039_R1'])[2]")
  WebElement btnOverrideAddOn;

  @FindBy(xpath = "//input[@id='C1__QUE_79E66D173F553B84450403']")
  WebElement fldRevisedPremiumAddOn;

  @FindAll({@FindBy(xpath = "//*[@summary='Quote Panel']/tbody/tr")})
  List<WebElement> ListOfQuotes;

  @FindBy(xpath = "//input[@id='C1__QUE_7F6D7F11132547CF77630']")
  WebElement fldSACommAddOn;

  @FindBy(css = "#C1__QUE_79E66D173F553B84450408")
  WebElement fldCommissionAddOn;

  @FindBy(css = "#C1__BUT_924F3239EDD2010F780489")
  WebElement btnSaveAddOn;

  @FindBy(css = "#C1__BUT_9F9CD8071D613D6A347440")
  WebElement btnEditSACommission;

  @FindBy(xpath = "//div[@id='C1__p1_HEAD_CF2076640ECEE99D281270_R1']/div")
  WebElement txtAddOnCommission;

  @FindBy(xpath = "//input[@id='C1__QUE_AA090F540C4B2794212383_R1']")
  WebElement txtAddOnSACommission;

  @FindBy(css = "#C1__BUT_9F9CD8071D613D6A364381")
  WebElement btnCancelSACommission;

  @FindBy(xpath = "//div[@id='C1__p4_HEAD_648917DEA19232B978320']/div")
  WebElement msgSACommission;

  @FindBy(css = "#C1__C1__QUE_5789C47AC0DDB2E5426066")
  WebElement taxAmountCss;

  @FindBy(css = "#C1__C1__QUE_9B559B0CD1263D787954071")
  WebElement commissionPercentageCss;

  @FindBy(xpath = "//a[contains(text(),'Demo Manual')]")
  WebElement demoManualPolicyXpath;

  @FindBy(css = "#C1__C1__Override_close")
  WebElement btnCancelManualQuote;

  @FindBy(css = "#C1__C1__FMT_23138FE1A0AA4BA42390417 > div > div.modal-header.modal-title")
  WebElement headerOverridePopUp;

  @FindBy(css = "#C1__C1__HEAD_F7EFDFDD779C91CD2950636")
  WebElement headerOriginalQuoteSection;

  @FindBy(css = "#C1__C1__HEAD_F7EFDFDD779C91CD2950644")
  WebElement headerRevisedQuoteSection;

  @FindBy(
      xpath = "//span[@class='GlobalFont Color1'][contains(text(),'test1')]/parent::div/parent::td/preceding-sibling::td//div//a[@title='Insurer Name Icon']")
  WebElement linkTest1Policy;

  @FindBy(css = "#accordian_panel_excess")
  WebElement headerExcessSection;

  @FindBy(css = "#C1__BUT_D825B447D4D5FCEB45256")
  WebElement addExcessbutton;

  @FindBy(css = "#C1__QUE_3760A175A4B77B7878718")
  WebElement excessSelectDropDown;

  @FindBy(css = "#C1__QUE_6DBA05E33D386335397010")
  WebElement textBoxDescriptionExcess;

  @FindBy(css = "#C1__QUE_3760A175A4B77B7878723")
  WebElement textBoxAmountExcess;

  @FindBy(css = "#C1__BUT_3760A175A4B77B7878730")
  WebElement buttonSaveAndClose;

  @FindBy(css = "#C1__QUE_42619103D62A82AE738311_0")
  WebElement checkBoxPersistAtRenewal;

  @FindBy(css = "#C1__QUE_42619103D62A82AE738306_0")
  WebElement checkBoxPersistAtMTA;

  @FindBy(css = "#C1__BUT_1BD0C3AFDEEF34B7199074_R1")
  WebElement view1stExcess;

  @FindBy(css = "#C1__BUT_1BD0C3AFDEEF34B7199074_R2")
  WebElement view2ndExcess;

  @FindBy(css = "#C1__BUT_1BD0C3AFDEEF34B7199074_R3")
  WebElement view3rdExcess;

  @FindBy(css = "#C1__BUT_1BD0C3AFDEEF34B7199074_R4")
  WebElement view4thExcess;

  @FindBy(css = "#C1__BUT_3760A175A4B77B7878730")
  WebElement editExcessButton;

  @FindBy(css = "#C1__QUE_AA47CB9629A325C8135182_0_R1")
  WebElement checkBoxMTA1stExcess;

  @FindBy(css = "#C1__QUE_42619103D62A82AE341334_0_R1")
  WebElement checkBoxRenewal1stExcess;

  @FindBy(css = "#C1__QUE_AA47CB9629A325C8135182_0_R2")
  WebElement checkBoxMTA2ndExcess;

  @FindBy(css = "#C1__QUE_42619103D62A82AE341334_0_R2")
  WebElement checkBoxRenewal2ndExcess;

  @FindBy(css = "#C1__QUE_CE78B7D13414C6531904878_R1")
  WebElement amount1stExcess;

  @FindBy(css = "#C1__QUE_CE78B7D13414C6531904878_R2")
  WebElement amount2ndExcess;

  @FindBy(css = "#C1__QUE_CE78B7D13414C653636785")
  WebElement textBoxNotes;

  @FindBy(css = "#C1__QUE_2F7C6BECAF974F0B416894_R1")
  WebElement nameOf1stExcess;

  @FindBy(css = "#C1__QUE_2F7C6BECAF974F0B416894_R2")
  WebElement nameOf2ndExcess;

  @FindBy(css = "#C1__QUE_2F7C6BECAF974F0B416894_R3")
  WebElement nameOf3rdExcess;

  @FindBy(css = "#C1__QUE_2F7C6BECAF974F0B416894_R4")
  WebElement nameOf4thExcess;

  @FindBy(
      xpath = "//div[@class='bx-viewport']//li[2]//div[@id='C1__p4_BUT_897C7CF2B16389A99415671_R1']")
  WebElement btnRemoveAddOn;

  @FindBy(xpath = "(//span[@id='C1__QUE_F7430DF425D53224467593_R1'])[2]")
  WebElement addedAddOnValue;

  @FindBy(css = "#C1__BUT_924F3239EDD2010F780489")
  WebElement btnAddOnSave;

  @FindBy(css = "#C1__QUE_79E66D173F553B84450403")
  WebElement txtFieldRevisedPremium;

  @FindBy(css = "#C1__HEAD_21896C493B7E4484399753")
  WebElement addOnHeadingAdded;

  @FindBy(xpath = "(//span[@id='C1__QUE_F7430DF425D53224467687_R3'])[2]")
  WebElement removedAddedAddOnValue;

  @FindBy(
      xpath = "//li[@aria-hidden='false']//div[@class=' panel panel-default panel-yellow nobgcolor ']//button[@title='Remove'][1]")
  WebElement btnremovefirstaddon;

  @FindBy(css = "#C1__TBL_09464E2384418BE1774275")
  WebElement brokerFeesTable;

  @FindBy(css = "#C1__QUE_09464E2384418BE1774280_0_R3")
  WebElement policyFee;

  @FindBy(css = "#C1__BUT_09464E2384418BE1786236_R3")
  WebElement policyFeeOverrideBtn;

  @FindBy(css = "#C1__BUT_09464E2384418BE11314035")
  WebElement overrideCancelBtn;

  @FindBy(css = "#C1__BUT_09464E2384418BE11314040")
  WebElement overrideSaveBtn;

  @FindBy(css = "#C1__QUE_09464E2384418BE11314022")
  WebElement overrideFeesAmount;

  @FindBy(css = "#C1__QUE_09464E2384418BE11314026")
  WebElement overrideFeesTax;

  @FindBy(css = "#C1__QUE_09464E2384418BE11314030")
  WebElement overrideTotal;

  @FindBy(css = "#C1__BUT_09464E2384418BE11314040")
  WebElement btnSaveFeeOverride;

  @FindBy(css = "#accordian_panel_endorsement")
  WebElement headerEndorsementSection;

  @FindBy(xpath = "//div[@id='C1__panel_endorsement']//button[@title='Add']")
  WebElement btnAddManualEndorsement;

  @FindBy(css = "#C1__QUE_7359FF9E38701916843523")
  WebElement dropdownEndorsement;

  @FindBy(css = "#C1__QUE_7359FF9E38701916843556")
  WebElement textBoxTextEndorsement;

  @FindBy(css = "#C1__QUE_33B6E15EBB7071AE133400")
  WebElement textBoxHeaderEndorsement;

  @FindBy(css = "#C1__QUE_33B6E15EBB7071AE113401")
  WebElement textBoxCodeEndorsement;

  @FindBy(css = "#C1__QUE_E99694DA5B561AC0243079_0")
  WebElement checkboxPersistAtRenewalEndorsement;

  @FindBy(xpath = "//button[@title='SAVE & CLOSE']")
  WebElement btnSaveAndCloseEndorsement;

  @FindBy(xpath = "//*[@title='DELETE']")
  WebElement btnDeleteOnPopUpEndorsement;

  @FindBy(css = "#C1__BUT_AA456212D96EE0C7605535")
  WebElement btnBackToDataCapture;

  @FindBy(css = "#C1__BUT_C8FE0DED896F7E74881521")
  WebElement btnOverwriteQuote;

  @FindBy(css = "#C1__BUT_C8FE0DED896F7E74877122")
  WebElement btnNewQuote;

  @FindBy(
      xpath = "//*[@id='C1__Screen_SSd_GENDETS_2_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_THATCHED_0']")
  WebElement radiobtnRoofThatchedYes;

  @FindBy(
      xpath = "//*[@id='C1__Screen_SSd_GENDETS_2_T__DATA_SET_rISK_OBJECTS_Component_HIUAGH_POLICY_BINDER_Component_BUILDING_CONSTRUCTION_FLAT_rOOF_DETAILS']")
  WebElement textboxThatchedDetails;

  @FindBy(xpath = "//*[@class='panel-title  ']")
  WebElement headerPanel;
  
  @FindBy(xpath = "//div[contains(text(),'Manual Quote Entry / Override')]")
  public WebElement overlayScreenHeader;
  
  @FindBy(css = "#C1__C1__QUE_A986AC9ABD649F1C88508")
  public WebElement grossPremium;
  
  @FindBy(css = "#C1__C1__QUE_1607BC33A327DCF3191387_ERRORMESSAGE")
  public WebElement errMsgPolicyFld;
  
  @FindBy(
      xpath = "//*[@id='C1__TBL_CE78B7D13414C653635890']//tr[contains(@class,'table-row-active')]//td[6]")
  WebElement txtQuoteStatus;

  @FindBy(xpath = "//*[@id=\"accordian_panel_endorsement\"]")
  WebElement headerEndorsemntSection;

  @FindBy(css = "#C1__QUE_CE78B7D13414C6531904975_R1")
  WebElement endorsement1Code;

  @FindBy(xpath = "//label[@for='C1__QUE_CE78B7D13414C653635961_1']")
  WebElement declinedQuote;

  @FindBy(xpath = "//*[@id='C1__TBL_CE78B7D13414C653635890']")
  WebElement quotesTable;

  @FindBy(
      xpath = "//li[@aria-hidden='false']//div[@class=' panel panel-default panel-yellow nobgcolor ']//i/parent::div[contains(text(),'Added')]")
  WebElement txtAddedOnAddOn;

  @FindBy(css = "#C1__QUE_D06B95BB3296F9AA126092_R1")
  WebElement txtBrokerFee1stRowQotePanel;

  @FindBy(
      xpath = "//li[@aria-hidden='false']//div[@class=' panel panel-default panel-yellow nobgcolor ']//div//span[@class='addon-val'][1]")
  WebElement txtFirstAddOnAddedValue;

  @FindBy(
      xpath = "//li[@aria-hidden='false']//div[@class=' panel panel-default panel-yellow ']//div//span[@class='addon-val']")
  WebElement txtSecondAddOnAddedValue;
  
  @FindBy(xpath = "//button[@title='Save and Exit']")
  WebElement btnSaveAndExit;


  /*****
   * Constructor of class
   * 
   * @param driver
   * @param report
   */
  public NewQuoteScreens(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentReport = report;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void load() {
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !driver.getTitle().contains("SSP - Pure Insurance")) {
      Log.fail("Unable to load the new quote screens", driver, extentReport);
    }
    new ElementLayer(driver);

  }

  @Override
  protected void isLoaded() throws Error {
    isPageLoaded = true;

  }

  /****
   * Method to switch to iframes
   * 
   * @param driver
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void switchToForm(WebDriver driver) throws Exception {
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(1000);
    driver.switchTo().frame(frameDQJ);
    driver.switchTo().frame(frameQMI);

  }

  /*****
   * Methods to switch out of iframes
   * 
   * @param driver
   * @author Shweta.Saigal
   */
  public void switchOutOfForm(WebDriver driver) {
    driver.switchTo().defaultContent();
    driver.switchTo().defaultContent();

  }

  /*****
   * Method to enter details in the risk screen pages.
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void riskScreens(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    WaitUtils.waitForElementPresent(driver, fldTradingName, "General Details Tab loaded");

    enterGeneralDetails(testdata, driver, extentReport);

    WaitUtils.waitForelementToBeClickable(driver, btnNext, "Next button is clickable");
    UIInteraction.clickUsingJS(btnNext, "Next button General Details ", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    enterRiskAddress(testdata, driver, extentReport);
    enterCoverDetails(testdata, driver, extentReport);

    WaitUtils.waitForelementToBeClickable(driver, btnNext, "Next button is clickable");
    UIInteraction.click(btnNext, "Next button Policy Cover", driver, extentReport, true);

    WaitUtils.waitForSpinner(driver);

    enterBuildingConstructionDetails(testdata, driver, extentReport);

    WaitUtils.waitForelementToBeClickable(driver, btnNext, "Next button is clickable");
    UIInteraction.click(btnNext, "Next button Building Construction Details", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    enterPremiseEquipmentDetails(driver, extentReport);

    WaitUtils.waitForelementToBeClickable(driver, btnNext, "Next button is clickable");
    UIInteraction.click(btnNext, "Next button Premises Equipment", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    enterNoOfBedrooms(testdata, driver, extentReport);

    WaitUtils.waitForelementToBeClickable(driver, btnNext, "Next button is clickable");
    UIInteraction.clickUsingJS(btnNext, "Next button Additional Info", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    enterSumInsured(testdata, driver, extentReport);

    WaitUtils.waitForelementToBeClickable(driver, btnNext, "Next button is clickable");
    UIInteraction.clickUsingJS(btnNext, "Next button Guest House Sum Insured", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    // Claims
    WaitUtils.waitForelementToBeClickable(driver, btnQuote, "Quote button is clickable");
    UIInteraction.click(btnQuote, "Quote button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }



  /******
   * Method to enter details on MTA risk screens
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   * 
   */
  public void riskScreensMTA(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    // General Details page

    UIInteraction.clickUsingJS(btnNext, "Next button General Details ", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Cover Details

    enterCoverDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Policy Cover", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Building Construction Details
    UIInteraction.clickUsingJS(btnNext, "Next button Building Construction Details", driver,
        extentReport, true);
    WaitUtils.waitForSpinner(driver);


    // Premise Equipment
    enterPremiseEquipmentDetails(driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Premises Equipment", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);


    // Guest House Additional Info
    enterNoOfBedrooms(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Additional Info", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Sum Insured
    enterSumInsured(testdata, driver, extentReport);

    UIInteraction.clickUsingJS(btnNext, "Next button Guest House Sum Insured", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    // Claims
    UIInteraction.clickUsingJS(btnQuote, "Quote button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  
  /****
   * Verify quotes page is loaded
   * 
   * @return
   */
  public boolean verifyQuotesPage() {
    WaitUtils.waitForSpinner(driver);

    new WebDriverWait(driver, 60)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Quotes not loaded").until(ExpectedConditions.visibilityOf(QuoteResultTitle));
    return true;
  }

  /****
   * 
   * read quote data
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */

  public void readQuote(HashMap<String, String> dynamicHashMap, String[] labels, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    WaitUtils.waitForSpinner(driver);

    dynamicHashMap.put(labels[0],
        UIInteraction.getText(txtAnnualPremium, "Annual Premium", driver, extentReport, true));
    dynamicHashMap.put(labels[1],
        UIInteraction.getText(txtInsurerPremium, "Insurer Premium", driver, extentReport, false));
    dynamicHashMap.put(labels[2],
        UIInteraction.getText(txtTotalAddOns, "Total Add Ons", driver, extentReport, false));
    dynamicHashMap.put(labels[3],
        UIInteraction.getText(txtBrokerFees, "Broker Fees", driver, extentReport, false));
    dynamicHashMap.put(labels[4],
        UIInteraction.getText(txtNetIncome, "Net Income", driver, extentReport, false));

    if (WaitUtils.waitForElement(driver, txtDueFromSubAgent)) {
      dynamicHashMap.put(labels[4], UIInteraction.getText(txtDueFromSubAgent,
          "Amount due from Sub Agent", driver, extentReport, false));
      dynamicHashMap.put(labels[5], UIInteraction.getText(txtThirdPartyComm, "Third Party Comm",
          driver, extentReport, false));
    }

  }

  /***
   * click on save quote
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void saveQuote(WebDriver driver, ExtentTest extentReport) throws Exception {

    WaitUtils.waitForelementToBeClickable(driver, btnSave, "Save button on QPS is now clickable");
    UIInteraction.clickUsingJS(btnSave, "Save Button", driver, extentReport, true);


  }

  /***
   * Method to Save quote
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void saveQuoteByJS(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(btnSave, "Save Button", driver, extentReport, true);
  }

  /***
   * Method to add an add on
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void addAddOn1(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(headerAddOnSection, "Add On Section", driver, extentReport, false);
    Thread.sleep(2000);
    UIInteraction.click(buttonAddAddOn1, "Add Add-On 1", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(4000);
  }


  /****
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void clickNextButton(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(btnNext, "Next button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  /*****
   * Method to create manual quote
   * 
   * @param testData
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void createManualQuote(HashMap<String, String> testData, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkDemoManualPolicy, "Select Manaul Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

    UIInteraction.click(btnManualQuote, "Manual Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

    Thread.sleep(1000);
    UIInteraction.click(fldNetPremium, "To focus", driver, extentReport, false);
    fldNetPremium.clear();
    UIInteraction.sendKeys(fldNetPremium, "Net Premium", testData.get("Net Premium"), driver,
        extentReport, false);
    fldNetPremium.sendKeys(Keys.TAB);

    WaitUtils.waitForelementToBeClickable(driver, btnCalculate,
        "Calculate button is now clickable");
    Thread.sleep(2000);
    UIInteraction.clickUsingJS(btnCalculate, "Calculate", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Interactions.enteringRandomPolicyGenratedNumber(fldPolicyNo, driver, extentReport, true);
    UIInteraction.clickUsingJS(btnSaveManualQuote, "Save Manual Quote", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);
  }



  /******
   * Method to update the manual quote
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void updateManaulQuote(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    Thread.sleep(1000);
    UIInteraction.click(fldNetPremium, "To focus", driver, extentReport, false);
    fldNetPremium.clear();
    UIInteraction.sendKeys(fldNetPremium, "Net Premium", testdata.get("Net Premium Updated"),
        driver, extentReport, false);
    fldNetPremium.sendKeys(Keys.TAB);

    WaitUtils.waitForelementToBeClickable(driver, btnCalculate,
        "Calculate button is now clickable");

    UIInteraction.clickUsingJS(btnCalculate, "Calculate", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.clickUsingJS(btnSaveManualQuote, "Save Manual Quote", driver, extentReport,
        false);

    Thread.sleep(4000);

  }

  /*****
   * Method to perform click on Manual quote button.
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void clickManaulQuote(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnManualQuote, "Manual Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  /*****
   * Method to create override Premium quote
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void createOverridePremium(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnOverride, "Override Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectDropdownByIndex(dropdownRating, "Ratings", "1", driver, extentReport, true);

    Thread.sleep(4000);

    UIInteraction.sendKeysViaActionClass(fldNetPremiumInOverride, "Net Premium", "500.00", driver,
        extentReport, true);
    fldNetPremiumInOverride.sendKeys(Keys.TAB);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectDropdownByIndex(dropdownOverrideReason, "Override reason", "1", driver,
        extentReport, true);
    Thread.sleep(2000);

    UIInteraction.sendKeys(fldOverrideText, "Override text", "Override policy test scenarion",
        driver, extentReport, true);
    UIInteraction.clickUsingJS(btnSaveManualQuote, "Save Override Quote", driver, extentReport,
        false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);


  }

  /*****
   * To expand the Broker Fee Section
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void expandBrokerFeeSection(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(headerBrokerFeeSection, "Broker Fee Section", driver, extentReport, false);
  }

  /*****
   * Method to select fees based on the name of the fee. The fee name will be read from the data
   * sheet given under the column Fees Selected. Multiple fee name can be given. Fee value will also
   * be stored in hash map and returned.
   * 
   * @param driver
   * @param extentReport
   * @param testData
   * @param dynamicHashMap
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */

  public HashMap<String, String> selectFees(WebDriver driver, ExtentTest extentReport,
      HashMap<String, String> testData, String columnName, HashMap<String, String> dynamicHashMap)
      throws Exception {

    String[] feeNames = testData.get(columnName).split(",");

    for (int i = 0; i < feeNames.length; i++) {

      String checkBox = "//table//td//span[contains(text(),'".concat(feeNames[i])
          .concat("')]/parent::div/parent::td/following-sibling::td//input");

      String feeValue = "//table//td//span[contains(text(),'".concat(feeNames[i])
          .concat("')]/parent::div/parent::td/following-sibling::td[3]//span");

      WebElement selectCheckbox = driver.findElement(By.xpath(checkBox));
      UIInteraction.clickRadioButton(selectCheckbox, "Select fee" + feeNames[i], driver,
          extentReport, true);

      WaitUtils.waitForElementPresent(driver, By.xpath(feeValue), "Fee is now visible");

      WebElement selectedFeeValue = driver.findElement(By.xpath(feeValue));

      dynamicHashMap.put(feeNames[i], UIInteraction.getText(selectedFeeValue, "Selected Fee Value",
          driver, extentReport, true));

    }
    return dynamicHashMap;

  }

  /*****
   * Select fee and override it.Fee will be selected from the data sheet from column Fees Selected
   * 
   * @param driver
   * @param extentReport
   * @param testData
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void selectFeeOverrideButton(WebDriver driver, ExtentTest extentReport,
      HashMap<String, String> testData) throws Exception {
    String[] feeNames = testData.get("Fees Override").split(",");

    for (int i = 0; i < feeNames.length; i++) {

      String checkBox = "//table//td//span[contains(text(),'".concat(feeNames[i])
          .concat("')]/parent::div/parent::td/following-sibling::td//input");

      WebElement selectCheckbox = driver.findElement(By.xpath(checkBox));

      UIInteraction.clickRadioButton(selectCheckbox, "Select fee " + feeNames[i], driver,
          extentReport, true);

      String button = "//table//td//span[contains(text(),'".concat(feeNames[i])
          .concat("')]/parent::div/parent::td/following-sibling::td//button");

      WaitUtils.waitForElementPresent(driver, By.xpath(button), "Override button is now visible");
      WebElement overrideButton = driver.findElement(By.xpath(button));
      UIInteraction.click(overrideButton, "Override Button", driver, extentReport, true);

      WaitUtils.waitForElement(driver, fldOverrideFeeAmount);
      UIInteraction.sendKeysViaActionClass(fldOverrideFeeAmount, "Override fee",
          testData.get("Fees Override Amount"), driver, extentReport, true);
      fldOverrideFeeAmount.sendKeys(Keys.TAB);
      WaitUtils.waitForelementToBeClickable(driver, btnSaveFeeOverride,
          "Save Override Fee button now enabled.");
      UIInteraction.click(btnSaveFeeOverride, "Save override fee", driver, extentReport, true);
    }

  }

  /****
   * 
   * Verify sorting of quotes
   * 
   * @param screenshot
   * @param extentReport
   * @param input1, input2, input3
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean verifyDefaultSortingOfQuotes(WebDriver driver, ExtentTest extentReport,
      String input1, String input2, String input3) throws Exception {

    boolean status = false;

    for (int i = 1; i <= ListOfQuotes.size(); i++) {
      status = false;
      String str = "//*[@summary='Quote Panel']/tbody/tr[" + i + "]/td[7]/div/span";

      String quoteText = driver.findElement(By.xpath(str)).getText();


      if (i == 1 && quoteText.trim().equalsIgnoreCase(input1)) {
        status = true;
      }

      if (i == 2 && quoteText.trim().equalsIgnoreCase(input2)) {
        status = true;
      }

      if (i == 3 && quoteText.trim().equalsIgnoreCase(input3)) {
        status = true;
      }


    }
    return status;

  }

  /****
   * 
   * update addon premium on quote result
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */

  public void overrideAddOnAndUpdateRevisedPremium(WebDriver driver, ExtentTest extentReport,
      String RevisedPremium) throws Exception {

    UIInteraction.clickUsingJS(btnOverrideAddOn, "Override AddOn Button", driver, extentReport,
        false);
    Thread.sleep(2000);
    UIInteraction.sendKeysViaActionClass(fldRevisedPremiumAddOn, "Revised Premium AddOn",
        RevisedPremium, driver, extentReport, false);
    Thread.sleep(2000);
    fldRevisedPremiumAddOn.sendKeys(Keys.TAB);
    // UIInteraction.click(txtCommissionEditBrokerScreen, "CommissionText", driver, extentReport,
    // true);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * 
   * update revised addon commission and SA commission on Edit Broker screen
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void updateCommAndSACommOnEditBrokerAddOnScreen(WebDriver driver, ExtentTest extentReport,
      String Commission, String SACommission) throws Exception {

    fldCommissionAddOn.clear();
    UIInteraction.sendKeysViaActionClass(fldCommissionAddOn, "AddOn Commission", Commission, driver,
        extentReport, false);
    Thread.sleep(2000);
    fldSACommAddOn.clear();
    Thread.sleep(4000);
    UIInteraction.sendKeysViaActionClass(fldSACommAddOn, "SA Commission AddOn", SACommission,
        driver, extentReport, false);
    Thread.sleep(2000);
    UIInteraction.sendKeys(fldSACommAddOn, "SA Commission AddOn", SACommission, driver,
        extentReport, false);
    Thread.sleep(2000);
    fldSACommAddOn.sendKeys(Keys.TAB);
    UIInteraction.clickUsingJS(btnSaveAddOn, "Save Button AddOn", driver, extentReport, false);

    WaitUtils.waitForSpinner(driver);

  }
  
  /****
   * 
   * update revised addon commission and SA commission on Edit Broker screen
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public void enterRemoveAddonDetailsOnEditBrokerAddOnScreen(WebDriver driver, ExtentTest extentReport,String revisedPremium,
      String Commission, String SACommission,boolean SA) throws Exception {
    
    Actions action = new Actions(driver);
    action.moveToElement(txtFieldRevisedPremium).doubleClick().build().perform();
    WaitUtils.waitForElementPresent(driver, txtFieldRevisedPremium, "Element displayed");
    txtFieldRevisedPremium.sendKeys(revisedPremium);
    txtFieldRevisedPremium.sendKeys(Keys.TAB);
    Thread.sleep(2000);
    
    fldCommissionAddOn.clear();
    UIInteraction.sendKeys(fldCommissionAddOn, "AddOn Commission", Commission, driver, extentReport, false);  
    fldCommissionAddOn.sendKeys(Keys.TAB);
    Thread.sleep(2000);
       
    if(SA==true){
      fldSACommAddOn.sendKeys(Keys.TAB);
      fldSACommAddOn.clear();
      Thread.sleep(4000);
      UIInteraction.sendKeysViaActionClass(fldSACommAddOn, "SA Commission AddOn", SACommission,
          driver, extentReport, false);
      Thread.sleep(2000);
      }
    UIInteraction.clickUsingJS(btnSaveAddOn, "Save Button AddOn", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * 
   * store AddOn message when SA commission is greater then commission
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public String verifySAMessageOnAddON(WebDriver driver, ExtentTest extentReport) throws Exception {

    String CommissionMessage =
        UIInteraction.getText(msgSACommission, "Commission Message", driver, extentReport, true);
    return CommissionMessage;
  }

  /****
   * 
   * Clicking on Edit SA Button
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void clickOnEditSAbutton(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(btnEditSACommission, "Save Button AddOn", driver, extentReport,
        false);
    Thread.sleep(2000);

  }

  /****
   * 
   * store AddOn commission value on Edit SA screen
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public String verifyAddOnCommission(WebDriver driver, ExtentTest extentReport) throws Exception {

    String AddONCommission =
        UIInteraction.getText(txtAddOnCommission, "AddON Commission", driver, extentReport, true);
    Thread.sleep(2000);
    return AddONCommission;
  }

  /****
   * 
   * store AddOn SA commission value on Edit SA screen
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public String verifyAddOnSACommission(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    String AddONCommission = UIInteraction.getValue(txtAddOnSACommission, "AddOn SA Commission",
        driver, extentReport, true);
    Thread.sleep(2000);
    return AddONCommission;
  }

  /****
   * 
   * Click on cancel button on Edit SA screen
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   */
  public void clickOnCancelButtonOnEditSAScreen(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnCancelSACommission, "Cancel Button SA Commission", driver,
        extentReport, false);
    Thread.sleep(2000);
    btnEditSACommission.sendKeys(Keys.TAB);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * 
   * To verify if auto rated or manual quotes selected by default
   * 
   * @param driver
   * @param element
   * @param extentReport
   * @throws Exception
   * @author Sakshi
   */
  public boolean checkLinkIsActive(WebElement element, WebDriver driver, ExtentTest extentReport)
      throws Exception {
    try {
      boolean status = true;
      String backgroundcolor = element.getCssValue("background-color");
      if (backgroundcolor.equals("rgba(0, 119, 128, 1)")) {
        status = true;
        System.out.println("status is " + status);
      } else {
        status = false;
      }
      return status;
    } catch (Exception e) {
      throw new Exception("Links is not active" + e);
    }
  }

  /****
   * 
   * To check link is Active
   * 
   * @param driver
   * @param element
   * @param extentReport
   * @param input
   * @param screenshot
   * @throws Exception
   * @author Sakshi
   */
  public String validateGrossAmountBasedOnTaxRate(WebElement element, String input,
      WebDriver driver, String taxrate, ExtentTest extentReport) throws Exception {

    String GrossPremium = "";
    try {

      GrossPremium = element.getAttribute("value");
      if (GrossPremium == null) {
        GrossPremium = element.getText();
      }
      String taxRate = taxrate;
      String val = "";
      if (taxRate.contains("IPT"))

      {
        val = taxRate.substring(12, 17);
      } else if (taxRate.contains("VAT")) {
        val = taxRate.substring(12, 17);
      } else if (taxRate.contains("tax")) {
        val = taxRate.substring(12, 17);
      } else {
        val = taxRate;
      }
      System.out.println(taxRate.substring(12, 17));
      Double substring = Double.parseDouble(val);
      Double input1 = (Double.parseDouble(input) * substring) / 100;
      Double inp = input1 + (Double.parseDouble(input));
      System.out.println("input" + inp);
      String ValueAfterTax = inp.toString();
      String ExpectedValue = ValueAfterTax + "0";
      return ExpectedValue;
    } catch (Exception e) {
      throw new Exception("value not returned" + e);
    }
  }


  public void expandAddOnSection(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(headerAddOnSection, "Add On Section", driver, extentReport, false);
    Thread.sleep(2000);
  }

  public boolean verifyAddedAddOnQuoteScreen() throws Exception {
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, addOnHeadingAdded))
      throw new Exception("Added Add on displayed in expand section");
    Thread.sleep(3000);
    return true;

    
  }
   
    public String verifyAddedAddOnWithZeroValue(WebDriver driver, ExtentTest extentReports) throws Exception {
      String addOnValue=null;
      UIInteraction.pressF12("", driver, extentReports);
      addOnValue=UIInteraction.getText(addedAddOnValue, "adddon premium", driver, extentReport, true);
      UIInteraction.pressF12("", driver, extentReports);
      return addOnValue; 
    }
    
    public String verifyRemovedAddOnWithZeroValue(WebDriver driver) throws Exception {
      String removeAddOnValue=null;  
      removeAddOnValue=UIInteraction.getText(removedAddedAddOnValue, "adddon premium", driver, extentReport, true);
      return removeAddOnValue; 
  
    }   
    
 
    public void selectPolicyFees(WebDriver driver) throws Exception {
      UIInteraction.click(policyFee, "Policy fees", driver, extentReport, false);
    }
    

    
    /***
     * to verify if override cancel button displayed
     * @return
     * @throws Exception
     */
    public boolean verifyOverrideCancelbtn() throws Exception {
      WaitUtils.waitForSpinner(driver);
      if (!WaitUtils.waitForElement(driver, overrideCancelBtn))
        throw new Exception("Override cancel btn not displayed");
      return true;
    }


 
  public String verifyRemovedAddOnWithZeroValue(WebDriver driver, ExtentTest extentReports)
      throws Exception {
    String removeAddOnValue = null;
    removeAddOnValue =
        UIInteraction.getText(removedAddedAddOnValue, "adddon premium", driver, extentReport, true);
    return removeAddOnValue;

  }

  public boolean removeAddOn(WebDriver driver) throws Exception {
   // WaitUtils.waitForElementPresent(driver, btnRemoveAddOn, "Element displayed");
    Thread.sleep(3000);
    UIInteraction.click(btnRemoveAddOn, "AddOn remove", driver, extentReport, false);
    Thread.sleep(2000);
    return true;
  }

  public void saveAddOn(WebDriver driver) throws Exception {
    UIInteraction.click(btnAddOnSave, "AddOn save", driver, extentReport, false);
    Thread.sleep(2000);
  }

  public void overrideAddOnPremium(WebDriver driver) throws Exception {
    Actions action = new Actions(driver);
    action.moveToElement(txtFieldRevisedPremium).doubleClick().build().perform();
    WaitUtils.waitForElementPresent(driver, txtFieldRevisedPremium, "Element displayed");
    txtFieldRevisedPremium.sendKeys("-100");
    txtFieldRevisedPremium.sendKeys(Keys.TAB);
    Thread.sleep(2000);
  }

  public boolean addFirstRemovedAddOn(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnremovefirstaddon, "Removed addon added", driver, extentReport, false);
    Thread.sleep(4000);
    return true;
  }

  public void expandFeesSection(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(headerBrokerFeeSection, "Fees Section", driver, extentReport, false);
    Thread.sleep(2000);
  }

  public boolean assertFeesTableNotContains(WebDriver driver, String input, ExtentTest extentReport)
      throws Exception {
    boolean status =
        Interactions.validateTableValuesNotContains(brokerFeesTable, input, driver, extentReport);
    return status;
  }


  public void clickPolicyFeeOverrdieBtn(WebDriver driver) throws Exception {
    UIInteraction.click(policyFeeOverrideBtn, "Policy fees override btn", driver, extentReport,
        false);
  }



  /***
   * to verify if override save button displayed
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyOverrideSavebtn() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, overrideSaveBtn))
      throw new Exception("Override save btn not displayed");
    return true;
  }

  /***
   * to verify if override Fees Tax text field status
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyOverrideFeesTaxFldStatus() throws Exception {
    boolean status = UIInteraction.validateTextFieldSataus(overrideFeesTax, driver, "disabled",
        extentReport, true);
    return true;
  }

  /***
   * to verify if override Total text field status
   * 
   * @return
   * @throws Exception
   */
  public boolean verifyOverrideTotalFldStatus() throws Exception {
    boolean status = UIInteraction.validateTextFieldSataus(overrideTotal, driver, "disabled",
        extentReport, true);
    return true;
  }



  /****
   * 
   * Click on Demo Manual policy
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnManualPolicy(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(demoManualPolicyXpath, "Select Manaul Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * 
   * Verify fields on Overlay screen
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyfTaxAmountHyphen(WebDriver driver, ExtentTest extentedReport)
      throws Exception {

    try {
      boolean status = false;
      if (taxAmountCss.getAttribute("class").contains("hyphen")) {
        status = true;

      }
      return status;

    } catch (NoSuchElementException e) {
      Log.fail("taxAmount is not found", extentedReport);
      throw new Exception("No Element Found " + e);
    }

  }

  /****
   * 
   * Verify fields on Overlay screen
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyCommissionPercentage(WebDriver driver, ExtentTest extentedReport,
      String input) throws Exception {

    try {
      boolean status = false;

      if (commissionPercentageCss.getText().trim().contains(input)) {
        status = true;
      }

      return status;
    } catch (NoSuchElementException e) {
      Log.fail("Commission percentage is not found", extentedReport);
      throw new Exception("No Element Found " + e);
    }

  }

  /****
   * 
   * Click on Auto Rated policy
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnAutoRatedPolicy(String label, WebDriver driver, ExtentTest extentReport,
      boolean screenshot) throws Exception {

    for (int i = 1; i <= ListOfQuotes.size(); i++) {

      String str = "//*[@summary='Quote Panel']/tbody/tr[" + i + "]/td[7]/div/span";

      WebElement element = driver.findElement(By.xpath(str));
      String quoteText = element.getText();

      if (quoteText.trim().equalsIgnoreCase("Insurer")) {

        element.click();
        Thread.sleep(5000);
        Log.message("Click performed " + label, driver, extentReport, screenshot);
        break;

      }
    }
  }

  /****
   * 
   * Click on Cancel button on Overlay screen
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnCancelButtonOnOverlayScreen(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnCancelManualQuote, "Cancel Manual Quote", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(5000);
  }

  /****
   * 
   * Click on override button
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnOverrideButton(WebDriver driver, ExtentTest extentReport) throws Exception {


    UIInteraction.clickUsingJS(btnOverride, "Override Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * 
   * Verify header text on Overlay screen
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyHeaderTextOfOverridePopUp(WebDriver driver, ExtentTest extentReport,
      String input) {

    boolean status = false;

    if (headerOverridePopUp.getText().trim().contains(input)) {
      status = true;
    }

    return status;
  }

  /****
   * 
   * Verify header text of Original quote section on Overlay screen
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyHeaderOfOriginalQuoteSection(WebDriver driver, ExtentTest extentReport,
      String input) {

    boolean status = false;

    if (headerOriginalQuoteSection.getText().trim().contains(input)) {
      status = true;
    }

    return status;

  }

  /****
   * 
   * Verify header text of Revised quote section on Overlay screen
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyHeaderOfRevisedQuoteSection(WebDriver driver, ExtentTest extentReport,
      String input) {

    boolean status = false;

    if (headerRevisedQuoteSection.getText().trim().contains(input)) {
      status = true;
    }

    return status;

  }

  /****
   * 
   * Click on Test1 Policy
   * 
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnTestPolicy(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(linkTest1Policy, "Select Test1 Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * 
   * Click on Add excess button
   * 
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnAddExcess(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(addExcessbutton, "Click on Add button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);

  }


  /****
   * 
   * Click on Expand excess section
   * 
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnExpandExcess(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(headerExcessSection, "Click on expand Excess section", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * 
   * Click on Add manual excess button
   * 
   * @param description -> Description of excess
   * @param amount -> Amount of excess
   * @param persistAtRenewal -> yes if you need to click on Persist At Renewal check box
   * @param persistAtMta -> yes if you need to click on Persist At MTA check box
   * @param index -> select Excess from dropdown
   * @throws Exception
   * @author Akshay.Saini
   */
  public void addAnManualExcess(WebDriver driver, ExtentTest extentReport, String description,
      String amount, boolean persistAtRenewal, boolean persistAtMta, String index)
      throws Exception {

    UIInteraction.selectDropdownByIndex(excessSelectDropDown, "Excess Name", index, driver,
        extentReport, true);
    Thread.sleep(2000);

    UIInteraction.sendKeys(textBoxDescriptionExcess, "Excess description", description, driver,
        extentReport, false);
    Thread.sleep(2000);

    UIInteraction.sendKeysViaActionClass(textBoxAmountExcess, "Excess amount", amount, driver,
        extentReport, false);
    Thread.sleep(2000);

    if (persistAtRenewal) {
      UIInteraction.clickRadioButton(checkBoxPersistAtRenewal, "click On Persist At Renewal",
          driver, extentReport, false);
      Thread.sleep(2000);
    }

    if (!persistAtMta) {
      UIInteraction.clickRadioButton(checkBoxPersistAtMTA, "click On Persist At MTA checkbox",
          driver, extentReport, false);
      Thread.sleep(2000);
    }

    UIInteraction.click(buttonSaveAndClose, "Click on save and close button", driver, extentReport,
        false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);


  }


  /****
   * 
   * Click on View excess button
   * 
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnView1stExcess(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(view1stExcess, "Click on 1st view button", driver, extentReport, false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);

  }


  /****
   * 
   * Click on View excess button
   * 
   * @throws Exception
   * @author Akshay.Saini
   */

  public void clickOnView2ndExcess(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(view2ndExcess, "Click on 2nd view button", driver, extentReport, false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);

  }


  /****
   * 
   * Click on View excess button
   * 
   * @throws Exception
   * @author Akshay.Saini
   */

  public void clickOnView3rdExcess(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(view3rdExcess, "Click on 3rd view button", driver, extentReport, false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);

  }


  /****
   * 
   * Click on View excess button
   * 
   * @throws Exception
   * @author Akshay.Saini
   */

  public void clickOnView4thExcess(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(view4thExcess, "Click on 4th view button", driver, extentReport, false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);

  }


  /****
   * 
   * Click on Edit excess button
   * 
   * @throws Exception
   * @author Akshay.Saini
   */

  public void clickOnEditExcessButton(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(editExcessButton, "Click on Edit excess button", driver, extentReport,
        false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);

  }


  /****
   * 
   * To edit the excess
   * 
   * @param persistAtMta -> Yes if need to check Presist At MTA checkbox
   * @param persistAtRenewal -> Yes if need to check Presist At Renewal checkbox
   * @param amount -> Excess amount
   * @throws Exception
   * @author Akshay.Saini
   */

  public void editExcess(WebDriver driver, ExtentTest extentReport, String amount,
      boolean persistAtMta, boolean persistAtRenewal) throws Exception {

    UIInteraction.sendKeysViaActionClass(textBoxAmountExcess, "Excess amount", amount, driver,
        extentReport, false);
    Thread.sleep(2000);

    if (persistAtMta) {
      UIInteraction.clickRadioButton(checkBoxPersistAtMTA, "click On Persist At Renewal", driver,
          extentReport, false);

      Thread.sleep(2000);

      if (persistAtRenewal) {
        UIInteraction.clickRadioButton(checkBoxPersistAtRenewal, "click On Persist At Renewal",
            driver, extentReport, false);
        Thread.sleep(2000);
      }

    }
    UIInteraction.click(buttonSaveAndClose, "Click on save and close button", driver, extentReport,
        false);
    Thread.sleep(2000);
    WaitUtils.waitForSpinner(driver);
  }


  /****
   * 
   * Verify Status of check box
   * 
   * @param input
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean verifyStatusOfCheckBoxMTA1stExcess(WebDriver driver, ExtentTest extentReport) {

    return UIInteraction.verifyStatusOfCheckbox(checkBoxMTA1stExcess, true, driver, extentReport,
        true);


  }


  /****
   * 
   * Verify Status of check box
   * 
   * @param input
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean verifyStatusOfCheckBoxRenewal1stExcess(WebDriver driver, ExtentTest extentReport) {

    return UIInteraction.verifyStatusOfCheckbox(checkBoxRenewal1stExcess, true, driver,
        extentReport, true);
  }


  /****
   * 
   * Verify Status of check box
   * 
   * @param input
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean verifyStatusOfCheckBoxMTA2ndExcess(WebDriver driver, ExtentTest extentReport) {
    return UIInteraction.verifyStatusOfCheckbox(checkBoxMTA2ndExcess, true, driver, extentReport,
        true);
  }


  /****
   * 
   * Verify Status of check box
   * 
   * @param input
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean verifyStatusOfCheckBoxRenewal2ndExcess(WebDriver driver, ExtentTest extentReport) {
    return UIInteraction.verifyStatusOfCheckbox(checkBoxRenewal2ndExcess, false, driver,
        extentReport, true);
  }


  /****
   * 
   * Verify amount of 1st excess
   * 
   * @param input
   * @throws Exception
   * @author Akshay.Saini
   */

  public boolean verifyAmountOf1stExcess(WebDriver driver, ExtentTest extentReport, String input) {

    boolean status = false;

    if (amount1stExcess.getText().trim().contains(input)) {
      status = true;
    }

    return status;

  }


  /****
   * 
   * Verify amount of 2nd excess
   * 
   * @param input
   * @throws Exception
   * @author Akshay.Saini
   */
  public boolean verifyAmountOf2ndExcess(WebDriver driver, ExtentTest extentReport, String input) {

    boolean status = false;

    if (amount2ndExcess.getText().trim().contains(input)) {
      status = true;
    }

    return status;

  }


  /****
   * 
   * Verify Notes text
   * 
   * @param testdata
   * @param label
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   * 
   */
  public boolean verifyNotesText(WebDriver driver, ExtentTest extentReport, String input) {
    boolean status = false;
    if (textBoxNotes.getText().trim().contains(input)) {
      status = true;
    }
    return status;

  }

  /****
   * 
   * read Excess data
   * 
   * @param dynamicHashmap
   * @param labels
   * @param screenshot
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */

  public void readExcessData(HashMap<String, String> dynamicHashmap, String[] labels,
      WebDriver driver, ExtentTest extentReport) throws Exception {
    WaitUtils.waitForSpinner(driver);

    dynamicHashmap.put(labels[0],
        UIInteraction.getText(nameOf1stExcess, "1st Excess", driver, extentReport, true));
    dynamicHashmap.put(labels[1],
        UIInteraction.getText(nameOf2ndExcess, "2nd Excess", driver, extentReport, true));
    dynamicHashmap.put(labels[2],
        UIInteraction.getText(nameOf3rdExcess, "3rd Excess", driver, extentReport, true));
    dynamicHashmap.put(labels[3],
        UIInteraction.getText(nameOf4thExcess, "4th Excess", driver, extentReport, true));


  }


  /****
   * 
   * Click on Endorsement Header
   * 
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnEndorsementHeader(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(headerEndorsementSection, "Endorsement header", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);


  }

  /****
   * 
   * Click on View button for Auto rated Endorsement
   * 
   * @param input -> Pass the number as "1" to view the 1st Endorsement and "2" for 2nd Endorsement
   *        and so on.
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnViewEndorsementAuto(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {

    String path = "//table[@summary='Endorsements']//tr[" + input + "]/td//button[@title='View']";


    WebElement element = driver.findElement(By.xpath(path));

    UIInteraction.click(element, "Click on View Endorsement", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);

  }

  /****
   * 
   * Click on Delete button for Auto rated Endorsement
   * 
   * @param input -> Pass the number as "1" to Delete the 1st Endorsement and "2" for 2nd
   *        Endorsement and so on.
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnDeleteEndorsementAuto(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {

    String path =
        "//table[@summary='Endorsements']//tr[" + input + "]/td//button[@title=' Delete']";

    WebElement element = driver.findElement(By.xpath(path));

    UIInteraction.click(element, "Click on Delete Endorsement", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);

    UIInteraction.click(btnDeleteOnPopUpEndorsement, "Click on Delete Endorsement", driver,
        extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);
  }

  /****
   * 
   * Add an Manual Endorsement
   * 
   * @param index -> Pass the index to select option for dropdown
   * @param textInput -> Input need to fill into text areas
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void addAnManualEndorsement(WebDriver driver, ExtentTest extentReport, String index,
      String textInput, boolean PersistAtRenewal) throws Exception {

    UIInteraction.click(btnAddManualEndorsement, "Add Manual Endorsement button", driver,
        extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);


    UIInteraction.selectDropdownByIndex(dropdownEndorsement, "Endorsement", index, driver,
        extentReport, true);
    Thread.sleep(2000);

    if (index.equals("1")) {

      UIInteraction.sendKeys(textBoxCodeEndorsement, "TextBox Code", textInput, driver,
          extentReport, false);
      Thread.sleep(2000);

      UIInteraction.sendKeys(textBoxTextEndorsement, "TextBox Text", textInput, driver,
          extentReport, false);
      Thread.sleep(2000);

      UIInteraction.sendKeys(textBoxHeaderEndorsement, "TextBox Header", textInput, driver,
          extentReport, false);
      Thread.sleep(2000);

    }

    if (PersistAtRenewal) {
      UIInteraction.clickRadioButton(checkboxPersistAtRenewalEndorsement,
          "click On Persist At Renewal checkbox", driver, extentReport, false);
      Thread.sleep(2000);
    }

    UIInteraction.click(btnSaveAndCloseEndorsement, "Save and Close button", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);

  }

  /****
   * 
   * Click on View button for Manual Endorsement
   * 
   * @param input -> Pass the number as "1" to view the 1st Endorsement and "2" for 2nd Endorsement
   *        and so on.
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnViewEndorsementManual(WebDriver driver, ExtentTest extentReport, String input)
      throws Exception {

    String path = "(//table[@summary='Manual'])[2]//tr[" + input + "]/td//button[@title='View']";

    WebElement element = driver.findElement(By.xpath(path));

    UIInteraction.click(element, "Click on View Endorsement", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);

  }

  /****
   * 
   * Click on Delete button for Manual Endorsement
   * 
   * @param input -> Pass the number as "1" to Delete the 1st Endorsement and "2" for 2nd
   *        Endorsement and so on.
   * @param extentReport
   * @throws Exception
   * @author Akshay.Saini
   */
  public void clickOnDeleteEndorsementManual(WebDriver driver, ExtentTest extentReport,
      String input) throws Exception {

    String path = "(//table[@summary='Manual'])[2]//tr[" + input + "]/td//button[@title=' Delete']";

    WebElement element = driver.findElement(By.xpath(path));

    UIInteraction.click(element, "Click on Delete Endorsement", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);

    UIInteraction.click(btnDeleteOnPopUpEndorsement, "Click on Delete Endorsement", driver,
        extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(3000);

  }

  /*****
   * Method to click on back to data capture and then on overwrite button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void backToDataCaptureAndOverwriteQuote(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnBackToDataCapture, "Back to Data Capture Button", driver,
        extentReport, true);
    UIInteraction.clickUsingJS(btnOverwriteQuote, "Overwrite Quote Button", driver, extentReport,
        true);
  }

  /*******
   * method to click on back to data capture and then on new quote button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void backToDataCaptureAndNewQuote(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    UIInteraction.clickUsingJS(btnBackToDataCapture, "Back to Data Capture Button", driver,
        extentReport, true);
    UIInteraction.clickUsingJS(btnNewQuote, "New Quote Button", driver, extentReport, true);
  }

  /******
   * Method to create a referred quote.
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void riskScreensReferred(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    Thread.sleep(4000);

    // General Details page
    enterGeneralDetails(testdata, driver, extentReport);

    UIInteraction.clickUsingJS(btnNext, "Next button General Details ", driver, extentReport, true);

    // Cover Details
    Thread.sleep(1000);
    enterRiskAddress(testdata, driver, extentReport);
    enterCoverDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Policy Cover", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);

    // Building Construction Details
    enterBuildingConstructionDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Building Construction Details", driver,
        extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);

    enterPremiseEquipmentDetails(driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Premises Equipment", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);
    // Next button

    // Guest House Additional Info

    enterNoOfBedroomsReferred(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Additional Info", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Sum Insured
    enterSumInsured(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Guest House Sum Insured", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);
    // Claims
    UIInteraction.click(btnQuote, "Quote button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /*****
   * Method to create declined quote
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void riskScreensDeclined(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    // General Details page
    enterGeneralDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button General Details ", driver, extentReport, true);

    // Cover Details
    Thread.sleep(1000);
    enterRiskAddress(testdata, driver, extentReport);
    enterCoverDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Policy Cover", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);

    // Building Construction Details
    enterBuildingConstructionDeclined(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Building Construction Details", driver,
        extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);

    // Premise Equipment
    enterPremiseEquipmentDetails(driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Premises Equipment", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Additional Info
    enterNoOfBedrooms(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Additional Info", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Sum Insured

    enterSumInsured(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Guest House Sum Insured", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);

    // Claims
    UIInteraction.clickUsingJS(btnQuote, "Quote button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /*****
   * Method to enter risk screens after MTA to create referred quote
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void riskScreensMTReferred(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    // General Details page
    UIInteraction.clickUsingJS(btnNext, "Next button General Details ", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Cover Details
    enterCoverDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Policy Cover", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Building Construction Details
    UIInteraction.clickUsingJS(btnNext, "Next button Building Construction Details", driver,
        extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Premise Equipment
   Thread.sleep(1000);
    UIInteraction.clickUsingJS(btnNext, "Next button Premises Equipment", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);


    // Guest House Additional Info
    enterNoOfBedroomsReferred(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Additional Info", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Sum Insured
    UIInteraction.clickUsingJS(btnNext, "Next button Guest House Sum Insured", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    // Claims
    UIInteraction.click(btnQuote, "Quote button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /*****
   * Method to enter risk screen details after MTA to get declined quote
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void riskScreensMTADeclined(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    // General Details page
    UIInteraction.sendKeys(fldTurnoverAmount, "Turnover Amount", testdata.get("Annual Turnover"),
        driver, extentReport, false);
    UIInteraction.clickUsingJS(btnNext, "Next button General Details ", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Cover Details
    enterCoverDetails(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Policy Cover", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Building Construction Details
    enterBuildingConstructionDeclined(testdata, driver, extentReport);
    UIInteraction.clickUsingJS(btnNext, "Next button Building Construction Details", driver,
        extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Premise Equipment
    Thread.sleep(1000);
    UIInteraction.clickUsingJS(btnNext, "Next button Premises Equipment", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Additional Info
    UIInteraction.clickUsingJS(btnNext, "Next button Additional Info", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

    // Guest House Sum Insured
    UIInteraction.clickUsingJS(btnNext, "Next button Guest House Sum Insured", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);

    // Claims
    UIInteraction.click(btnQuote, "Quote button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);

  }

  /****
   * Method to click on Quote button
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void clickQuoteButton(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickUsingJS(btnQuote, "Next button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  
  /***
   * to verify if overlay screen is loaded
   * @return
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public boolean verifyOverlayScreen() throws Exception {
    if (!WaitUtils.waitForElement(driver, overlayScreenHeader))
      throw new Exception("Home Page is not loaded");
    return true;
  }
  
  /******
   * Method to update the manual quote
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public void updateNetPremiumAmountAndCalculate(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    Thread.sleep(1000);
    UIInteraction.sendKeysViaActionClass(fldNetPremium, "Net Premium Updated",
        testdata.get("Net Premium Updated"), driver, extentReport, false);
    fldNetPremium.sendKeys(Keys.TAB);
    Thread.sleep(5000);
    UIInteraction.click(btnCalculate, "Calculate", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(5000);
    
  }
  
  /*****
   * Method to perform click on Manual quote button.
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void saveManaulQuote(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.click(btnSaveManualQuote, "Save Manual Quote", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
  }

  /******
   * Method to update the manual quote
   * 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Bhavesh.KumarSingh
   */
  public void updateManaulQuoteForCancellationAndCalculate(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    Thread.sleep(1000);
    UIInteraction.sendKeysViaActionClass(fldNetPremium, "Net Premium Updated",
        testdata.get("Cancellation net Premium Updated"), driver, extentReport, false);
    fldNetPremium.sendKeys(Keys.TAB);
    UIInteraction.click(btnCalculate, "Calculate", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.clickUsingJS(btnSaveManualQuote, "Save Manual Quote", driver, extentReport,
        false);
    
  }
  
  /****
  * Method to Generate Random number and return
  *
  * @param driver
  * @param extentReport
  * @throws Exception
  * @author Akshay.Saini
  */
  public String generateRandomNumber(WebDriver driver, ExtentTest extentReport) throws Exception {
  return Interactions.enteringRandomPolicyGenratedNumber(driver, extentReport);
  }




  /****
   * Verify quotes page is loaded
   * 
   * @return
   * @throws Exception
   */
  public String getRenewalResultsTitle() throws Exception {
    WaitUtils.waitForSpinner(driver);

    return UIInteraction.getText(QuoteResultTitle, "Renewal Result Title", driver, extentReport,
        true);
  }

  /***
   * Method to get Quote Status on QPS
   * 
   * @param driver
   * @return
   * @throws Exception
   * @author Durga.Pradhan
   */
  public String getQuoteStatus(WebDriver driver) throws Exception {
    String quoteStatus =
        UIInteraction.getText(txtQuoteStatus, "Quote Status", driver, extentReport, true);
    return quoteStatus;
  }

  /***
   * Method to Override with Updating Reason and Text only
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Durga.Pradhan
   */
  public void overrideWithReasonAndTextOnly(WebDriver driver, ExtentTest extentReport)
      throws Exception {
    UIInteraction.click(btnOverride, "Override Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(4000);
    UIInteraction.selectDropdownByIndex(dropdownOverrideReason, "Override reason", "1", driver,
        extentReport, true);
    Thread.sleep(2000);
    UIInteraction.sendKeys(fldOverrideText, "Override text", "Override policy test scenario",
        driver, extentReport, true);
    UIInteraction.clickUsingJS(btnSaveManualQuote, "Save Override Quote", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);
  }

  /***
   * Method to expand Endorsement section
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Durga.Pradhan
   */
  public void expandEndorsementSection(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(headerEndorsemntSection, "Endorsement Section", driver, extentReport, true);
    Thread.sleep(2000);
  }

  /***
   * Method to get Code text for first Endorsement displayed in list
   * 
   * @param driver
   * @return
   * @throws Exception
   * @author Durga.Pradhan
   */
  public String getEndorsement1Code(WebDriver driver) throws Exception {
    String endorsementCode =
        UIInteraction.getText(endorsement1Code, "endorsement Text", driver, extentReport, true);
    return endorsementCode;
  }


  /**
   * To override a Referred Quote to Quoted without changing the rates
   * 
   * @author Kirti.Kapoor
   * @param driver
   * @param extentReport
   * @throws Exception
   */
  public void overrideQuote(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnOverride, "Override Button", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectDropdownByIndex(dropdownRating, "Ratings", "1", driver, extentReport, true);
    UIInteraction.selectDropdownByIndex(dropdownOverrideReason, "Override reason", "1", driver,
        extentReport, true);
    Thread.sleep(2000);
    UIInteraction.sendKeys(fldOverrideText, "Override text", "Override policy test scenarion",
        driver, extentReport, true);
    UIInteraction.selectDropdownByIndex(dropdownOverrideReason, "Override reason", "1", driver,
        extentReport, true);
    UIInteraction.clickUsingJS(btnSaveManualQuote, "Save Override Quote", driver, extentReport,
        false);
    WaitUtils.waitForSpinner(driver);
  }

  /****
   * To verify declined quote
   * 
   * @param driver
   * @param extentReport
   * @param input
   * @return
   * @author Kirti.Kapoor
   */
  public boolean verifyDeclinedQuote(WebDriver driver, ExtentTest extentReport, String input) {

    boolean status = false;

    if (declinedQuote.getText().trim().contains(input)) {

      status = true;
    }
    return status;

  }

  /*****
   * Method to return the no of policies returned on QPS
   * 
   * @param driver
   * @param extentReport
   * @return
   * @author Shweta.Saigal
   */
  public int noOfPoliciesInQuotesTable(WebDriver driver, ExtentTest extentReport) {

    String quotesTablePath = "//*[@id='C1__TBL_CE78B7D13414C653635890']//tbody";

    WebElement quotesTable = driver.findElement(By.xpath(quotesTablePath));

    List<WebElement> rows = quotesTable.findElements(By.tagName("tr"));
    Log.message("No of policies returned " + rows.size());

    return (rows.size());
  }

  /***
   * Method to add add on 2
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void addAddOn2(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(buttonAddAddon2, "Add on 2", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(4000);
  }
  
  /***
   * Method to add KeyCare Add-on
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Sandeep.Sharma
   */
  public void addKeyCareAddOn(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(headerAddOnSection, "Add On Section", driver, extentReport, false);
    Thread.sleep(2000);
    UIInteraction.click(buttonAddKeyCare, "Key Care Add-On", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(4000);
    
  }

  /*****
   * Method to remove add on
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void removeAddOn1(WebDriver driver, ExtentTest extentReport) throws Exception {

    UIInteraction.clickUsingJS(btnremovefirstaddon, "Remove first add-on", driver, extentReport,
        true);
    WaitUtils.waitForSpinner(driver);
    Thread.sleep(2000);
  }

  /****
   * Method to click on Save Add-On
   * 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void clickSaveAddOn(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnSaveAddOn, "Save Add-On", driver, extentReport, true);
    WaitUtils.waitForSpinner(driver);
  }

  /***
   * Method to get add on value
   * 
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   */
  public String getAddOnValue(String addOnName, WebDriver driver, ExtentTest extentReport)
      throws Exception {


    String addOnXpath = null;
    addOnXpath = "//div[text() = '".concat(addOnName)
        .concat("']/parent::div/parent::div/following-sibling::div//span");

    WebElement addOnValue = driver.findElement(By.xpath(addOnXpath));
    return UIInteraction.getAttribute(addOnValue, "innerHTML", "Add On Value", driver, extentReport,
        true);
  }

  /****
   * To verify if an add on is added or not
   * 
   * @param addOnName
   * @param driver
   * @param extentReport
   * @return
   * @throws Exception
   * @author Shweta.Saigal
   */
  public boolean verifyAddOnAdded(String addOnName, WebDriver driver, ExtentTest extentReport)
      throws Exception {

    String addOnXpath = null;

    addOnXpath = "//div[text() = '".concat(addOnName)
        .concat("']/parent::div/parent::div/parent::div/parent::div/following-sibling::div//i");

    WebDriverWait wait = new WebDriverWait(driver, 5);

    wait.until(ExpectedConditions
        .refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath(addOnXpath))));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addOnXpath)));
    Log.message("Element is present");
    WebElement addedTick = driver.findElement(By.xpath(addOnXpath));

    if (addedTick != null)
      return true;
    else
      return false;

  }

  /****
   * Method to enter general details
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterGeneralDetails(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    // General Details page
    UIInteraction.sendKeys(fldTradingName, "Trading Name", testdata.get("Trading Name"), driver,
        extentReport, false);
    UIInteraction.sendKeys(fldContanctName, "Contact Name", testdata.get("Contact Name"), driver,
        extentReport, false);
    UIInteraction.selectDropdownByVisibleText(dropdownCompanyStatus, "Company Status",
        testdata.get("Company Status").toString(), driver, extentReport, false);
    Thread.sleep(2000);
    UIInteraction.sendKeys(fldTurnoverAmount, "Turnover Amount",
        testdata.get("Annual Turnover").toString(), driver, extentReport, false);
  }

  /*****
   * Method to enter risk address on cover details page
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterRiskAddress(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    // Risk Address on Cover Details

    WaitUtils.waitForElementPresent(driver, dropdownCountry, "Cover details tab loaded");
    UIInteraction.selectDropdownByIndex(dropdownCountry, "Country", testdata.get("Country"), driver,
        extentReport, true);
    WaitUtils.waitForelementToBeClickable(driver, fldPostCode, "Post code field clickable");
    UIInteraction.sendKeys(fldPostCode, "Postcode", testdata.get("Postcode"), driver, extentReport,
        false);
    fldPostCode.sendKeys(Keys.TAB);
    WaitUtils.waitForelementToBeClickable(driver, btnFindAddress, "Find address button clickable");
    UIInteraction.click(btnFindAddress, "Find Address button", driver, extentReport, false);
    WaitUtils.waitForSpinner(driver);
    UIInteraction.selectDropdownByIndex(dropdownSelectAddress, "Select Address",
        testdata.get("Select Address"), driver, extentReport, false);



  }

  /*****
   * Method to enter cover details 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterCoverDetails(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {

    // Cover Details
    UIInteraction.clickRadioButton(radiobtnGoodStateYes, "Good State of Construction", driver,
        extentReport, false);
    UIInteraction.sendKeys(fldYearOfConstruction, "Construction year",
        testdata.get("Construction Year"), driver, extentReport, false);

    UIInteraction.sendKeys(fldCurrentPremisesYears, "Years at current premises",
        testdata.get("Current Premises").toString(), driver, extentReport, false);

    UIInteraction.sendKeys(fldElsewhereYears, "Total experience",
        testdata.get("Elsewhere").toString(), driver, extentReport, false);
  }

  /*****
   * Method to enter Building construction details 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterBuildingConstructionDetails(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    // Building Construction Details
    WaitUtils.waitForRadioButtonCheckbox(driver, radiobtnAreTheWallsYes,
        "Building contruction details tab loaded");
    UIInteraction.clickRadioButton(radiobtnAreTheWallsYes, "Are the walls radio button", driver,
        extentReport, false);
    UIInteraction.clickRadioButton(radiobtnAreTheRoofYes, "Are the roofs radio button", driver,
        extentReport, false);
    Thread.sleep(1000);
  }

  /****
   * Method to enter premise equipment details 
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterPremiseEquipmentDetails(WebDriver driver, ExtentTest extentReport)
      throws Exception {

    // Premise Equipment

    WaitUtils.waitForRadioButtonCheckbox(driver, radiobtnSeasonalBasisNo,
        "Premises Equipment tab is loaded");
    UIInteraction.clickRadioButton(radiobtnSeasonalBasisNo, "Seasonal basis radio button", driver,
        extentReport, false);
    Thread.sleep(1000);

  }

  /*****
   * Method to enter no of bedrooms
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterNoOfBedrooms(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    // Guest House Additional Info

    WaitUtils.waitForElementPresent(driver, fldNoOfBedroom, "Additional Info tab is loaded");
    UIInteraction.sendKeys(fldNoOfBedroom, "No of bedrooms",
        testdata.get("No Of Bedrooms").toString(), driver, extentReport, false);
  }

  /*****
   * Method to enter sum insured details 
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterSumInsured(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    // Guest House Sum Insured

    WaitUtils.waitForElementPresent(driver, dropdownContentsAmount, "Sum Insured tab is loaded");
    UIInteraction.sendKeys(fldBuildings, "Building", testdata.get("Buildings").toString(), driver,
        extentReport, true);
    UIInteraction.selectDropdownByIndex(dropdownContentsAmount, "Contents",
        testdata.get("Contents"), driver, extentReport, false);
    UIInteraction.sendKeys(fldTotalWine, "Total Wine", testdata.get("Total Wine").toString(),
        driver, extentReport, false);
    UIInteraction.sendKeys(fldTotalComputer, "Total Computer",
        testdata.get("Total Computer").toString(), driver, extentReport, false);
    UIInteraction.sendKeys(fldFrozenFood, "Total Frozen", testdata.get("Total Frozen").toString(),
        driver, extentReport, false);

    UIInteraction.sendKeys(fldIncreaseSIforCash, "Increase SI for Cash on Premise",
        testdata.get("IncreaseSI_1").toString(), driver, extentReport, true);
    UIInteraction.sendKeys(fldIncreaseSIonTransit, "Increase SI for Cash on transit",
        testdata.get("IncreaseSI_2").toString(), driver, extentReport, true);
    UIInteraction.sendKeys(fldMakeAndModel, "Make and Model", testdata.get("Make & Model"), driver,
        extentReport, true);
  }

  /*****
   * Method to enter no of bedrooms referred.
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterNoOfBedroomsReferred(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentReport) throws Exception {
    UIInteraction.sendKeys(fldNoOfBedroom, "No of bedrooms Referred",
        testdata.get("No of bedrooms Referred").toString(), driver, extentReport, false);
    Thread.sleep(2000);
  }
  
  /*******
   * Method to enter building construction declined
   * @param testdata
   * @param driver
   * @param extentReport
   * @throws Exception
   * @author Shweta.Saigal
   */
  public void enterBuildingConstructionDeclined(HashMap<String, String> testdata, WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.clickRadioButton(radiobtnAreTheWallsYes, "Are the walls radio button", driver,
        extentReport, false);
    Thread.sleep(2000);
    UIInteraction.clickRadioButton(radiobtnAreTheRoofYes, "Are the roofs radio button", driver,
        extentReport, false);
    UIInteraction.clickRadioButton(radiobtnRoofThatchedYes, "Is the roof Thatched radio button",
        driver, extentReport, false);
    UIInteraction.click(textboxThatchedDetails, "Roof Thatched details", driver, extentReport,
        false);
    UIInteraction.sendKeys(textboxThatchedDetails, "Roof Thatched details",
        testdata.get("Roof Thatched").toString(), driver, extentReport, false);
    Thread.sleep(2000);
  }
  
  public void clickButttonSaveAndExit(WebDriver driver, ExtentTest extentReport) throws Exception {
    UIInteraction.click(btnSaveAndExit, "Save button", driver, extentReport, true);
  }



/*****
 * Get Broker Fee for selected quote
  * @param driver
 * @param extentReport
 * @throws Exception
 * @author Sandeep.Sharma
 */
public String getBrokerFeeFromSelectedQuotePanel(WebDriver driver,
    ExtentTest extentReport) throws Exception {
 String Bfee= UIInteraction.getText(txtBrokerFee1stRowQotePanel, "Broker Fee", driver, extentReport, false);
  Thread.sleep(2000);
  return Bfee;
}

}