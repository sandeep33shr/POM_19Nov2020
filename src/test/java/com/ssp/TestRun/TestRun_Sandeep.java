package com.ssp.TestRun;

import org.testng.annotations.Test;
import com.ssp.Heartland.Test.TC_Banking_filtering_Marking;
import com.ssp.Heartland.Test.TC_IncomeClearing_4644;
import com.ssp.support.BaseTest;

public class TestRun_Sandeep  extends BaseTest{
  
/*  @Test(groups="bank")
  public void test() throws Exception {
	  
    new TC_Banking_filtering_Marking().checkBranchColumnAsPerSelectedFilter(driver, testData, extentReport);
     
 }*/
  
/*  @Test(groups="bank")
  public void test2() throws Exception {
  
    new TC_Banking_filtering_Marking().checkMarkUnmarkAll(driver, testData, extentReport);   
 }*/
  
/*  @Test(groups="bank")
  public void test3() throws Exception {
      
   
    new TC_Banking_filtering_Marking().assertColumnHeaderCheckbox(driver, testData, extentReport);
 }*/
  
/*  @Test(groups="bank")
  public void test4() throws Exception {
      
    new TC_Banking_filtering_Marking().checkFiltersAvailabilityDynamically(driver, testData, extentReport);
   
   
  }*/
  
/*  @Test(groups="bank")
  public void test5() throws Exception {
      
    new TC_Banking_filtering_Marking().checkMediaTypeColumnAsPerSelectedFilter(driver, testData, extentReport);
     
 }*/
  
/*  @Test(groups="bank")
  public void test6() throws Exception {
      
    new TC_Banking_filtering_Marking().checkDefaultStateOfButtons(driver, extentReport);
     
 }*/
/*  @Test(groups="bank")
  public void test7() throws Exception {
      
   
    new TC_Banking_filtering_Marking().checkFiltersAvailabilityDynamicallyOnClickingFiltersButton(driver, testData, extentReport);
   
 }*/
  
/*  @Test(groups="bank")
  public void test8() throws Exception {
      
    new TC_Banking_filtering_Marking().checkMarkUnmarkAllBankDynamicStateLogic(driver, extentReport);
     
 }*/
 /* @Test(groups="bank")
  public void test9() throws Exception {
      
    new TC_Banking_filtering_Marking().checkAvailabilityOfPaymentDetails(driver, extentReport);
     
 }*/
/*  @Test(groups="bank")
  public void test10() throws Exception {
      
    new TC_Banking_filtering_Marking().checkAvailabilityOfCollectionDetails(driver, extentReport);
     
 }*/
/*  @Test(groups="bank")
  public void test11() throws Exception {
      
    new TC_Banking_filtering_Marking().checkMarkedStatusFilter(driver, extentReport);
     
 }*/
  
    @Test(groups="IncomeClering")
  public void test11() throws Exception {
      
    new TC_IncomeClearing_4644().NBWithPayNowFullCollection(driver, testData, extentReport);
     
 }
}
