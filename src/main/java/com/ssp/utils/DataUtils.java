package com.ssp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ssp.support.Log;

/****
 * 
 * Data Utils consists finding Row and Column count,Reading excel headers, Retrieving testdata id
 * and Fetching testdata values
 * 
 * @author Shweta.Saigal
 *
 */
public class DataUtils {
  private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);

  /**
   * FindRowColumnCount method to get total no of row and column count in a excel work sheet
   * 
   * @param sheet name
   * @param rowColumnCount as Hashtable
   * @return Hashtable (returns row count and column count)
   */

  public static Hashtable<String, Integer> findRowColumnCount(HSSFSheet sheet,
      Hashtable<String, Integer> rowColumnCount) {

    HSSFRow row = null;
    int rows;
    rows = sheet.getPhysicalNumberOfRows();
    int cols = 0;
    int tmp = 0;
    int counter = 0;
    String temp = null;

    for (int i = 0; i < 10 || i < rows; i++) {
      row = sheet.getRow(i);
      if (row != null) {
        temp = convertHSSFCellToString(row.getCell(0));
        if (!temp.isEmpty()) {
          counter++;
        }
        tmp = sheet.getRow(i).getPhysicalNumberOfCells();
        if (tmp > cols) {
          cols = tmp;
        }
      }
    }

    rowColumnCount.put("RowCount", counter);
    rowColumnCount.put("ColumnCount", cols);

    return rowColumnCount;
  }

  /**
   * ReadExcelHeaders method read the excel headers column wise sheet
   * 
   * @param sheet name
   * @param excelHeaders (Hashtable)
   * @param rowColumnCount (Hashtable)
   * @return excelHeaders (returns Header column values)
   */
  public static Hashtable<String, Integer> readExcelHeaders(HSSFSheet sheet,
      Hashtable<String, Integer> excelHeaders, Hashtable<String, Integer> rowColumnCount) {

    HSSFRow row = null;
    HSSFCell cell = null;
    for (int r = 0; r < rowColumnCount.get("RowCount"); r++) {
      row = sheet.getRow(r);

      if (row != null) {
        for (int c = 0; c < rowColumnCount.get("ColumnCount"); c++) {
          cell = row.getCell(c);
          if (cell != null) {
            excelHeaders.put(cell.toString(), c);
          }
        }
        break;
      }
    }
    return excelHeaders;
  }

  /**
   * ConvertHSSFCellToString method to convert the HSSFCell value to its equivalent string value
   * 
   * @param cell value
   * @return String cellValue
   */
  public static String convertHSSFCellToString(HSSFCell cell) {
    String cellValue = null;
    if (cell != null) {
      cellValue = cell.toString();
      cellValue = cellValue.trim();
    } else {
      cellValue = "";
    }
    return cellValue;
  }

  /**
   * To overriding the config sheet name to get test data
   * 
   * @param testCaseId from test case
   * @param testClassName test name
   * @return test data values for specified testCaseId
   */
  public static HashMap<String, String> testDatabyID(String testCaseId, String testClassName) {
    String configSheetName = "Config";
    Log.message("testCaseId is ---------> " + testCaseId + "\ntestClassName-----------> "
        + testClassName + "\nconfigSheetName -> " + configSheetName);
    return testDatabyID(testCaseId, testClassName, configSheetName);
  }

  public static void testDatabyID(String testCaseId, String testClassName,
      HashMap<String, String> values, String columnName) throws Exception {
    Log.message("In testDataBy ID for writing test data");
    String configSheetName = "Config";
    Log.message("testCaseId is ---------> " + testCaseId + "\ntestClassName-----------> "
        + testClassName + "\nconfigSheetName -> " + configSheetName);
    writeTestData(testCaseId, testClassName, configSheetName, values, columnName);
  }

  /**
   * Map to the test data sheet based on Config declaration
   * 
   * @param testCaseId - actual test case id
   * @param testClassName - test case class name
   * @param configSheetName - config sheet name
   * @return test data values for specified testCaseId
   */
  public static HashMap<String, String> testDatabyID(String testCaseId, String testClassName,
      String configSheetName) {
    String filePath = "";
    String sheetName = "";
    String fileName = "";
    HSSFRow row = null;
    HSSFCell cell = null;
    HashMap<String, String> data = new HashMap<>();
    Hashtable<String, Integer> excelHeaders = new Hashtable<>();

    try {

      String basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator
          + "main" + File.separator + "resources" + File.separator + "testdata" + File.separator;
      Log.message("data utils base path: " + basePath);
      String configFilePath = basePath + "Config-TD.xls";
      Log.message("Config-------" + configFilePath);
      FileInputStream fs = new FileInputStream(configFilePath);
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      HSSFSheet sheet = wb.getSheet(configSheetName);
      Log.message("Sheet :"+ sheet);

      // Function call to find excel header fields
      Hashtable<String, Integer> excelrRowColumnCount = new Hashtable<>();
      excelrRowColumnCount = findRowColumnCount(sheet, excelrRowColumnCount);
      readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount);

      // Get test data set
      for (int r = 1; r < excelrRowColumnCount.get("RowCount"); r++) {
        row = sheet.getRow(r);
        if (row != null) {
          HSSFCell tempCell = sheet.getRow(r).getCell(0);
          if (tempCell != null) {
            String testClass = convertHSSFCellToString(row.getCell(0));

            if (testClass.equalsIgnoreCase(testClassName)) {
              cell = sheet.getRow(r).getCell(1);
              if (cell != null) {
                fileName = convertHSSFCellToString(row.getCell(1));
              }
              cell = sheet.getRow(r).getCell(2);
              if (cell != null) {
                sheetName = convertHSSFCellToString(row.getCell(2));
              }
              break;
            }
          }
        }
      }
      filePath = basePath + fileName;
      Log.message("\nfile path----------> " + filePath);
      data = getTestData(filePath, fileName, sheetName, testCaseId);
      Log.message("\ndata----------> " + data);
      fs.close();

    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return data;
   
  }

  /**
   * Fetch the test data for a test case based on test case ID
   * 
   * @param filePath test data xl location
   * @param workBook name
   * @param sheetName name
   * @param testCaseId test id
   * @return testData data
   * @throws IOException - java IO exception
   */
  public static HashMap<String, String> getTestData(String filePath, String workBook,
      String sheetName, String testCaseId) throws IOException {
    HSSFRow row = null;
    HSSFCell cell = null;
    Log.message("filePath-------" + filePath + "  " + "workBook------" + workBook + " "
        + "sheetName----" + sheetName + "testCaseId------------" + testCaseId);

    // Establish connection to work sheet
    FileInputStream fs = new FileInputStream(filePath);
    HSSFWorkbook wb = new HSSFWorkbook(fs);
    HSSFSheet sheet = wb.getSheet(sheetName);
    Hashtable<String, Integer> excelrRowColumnCount = new Hashtable<>();
    excelrRowColumnCount = findRowColumnCount(sheet, excelrRowColumnCount);

    // function call to find excel header fields
    Hashtable<String, Integer> excelHeaders = new Hashtable<>();
    readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount);
    
    HashMap<String, String> data = null;
    ArrayList<String> header = new ArrayList<>();
    ArrayList<String> matcher = null;
    HashMap<String, String> matcherList = new HashMap<>();

    // Get all header
    row = sheet.getRow(0);
    if (row != null) {
      for (int c = 0; c < excelrRowColumnCount.get("ColumnCount"); c++) {
        cell = sheet.getRow(0).getCell(c);
        if (cell != null) {
          String temp = convertHSSFCellToString(row.getCell(c));
          header.add(temp);
        }
      }
    }

    // Get test data set
    for (int r = 1; r < excelrRowColumnCount.get("RowCount"); r++) {
      row = sheet.getRow(r);
      if (row != null) {
        HSSFCell tempCell = sheet.getRow(r).getCell(0);
        if (tempCell != null) {
          String tcID = convertHSSFCellToString(row.getCell(0));
          if (tcID.equalsIgnoreCase(testCaseId)) {
            data = new HashMap<>();
            matcher = new ArrayList<>();
            matcher.add(tcID);
            for (int c = 1; c < excelrRowColumnCount.get("ColumnCount"); c++) {
              cell = sheet.getRow(r).getCell(c);
              String temp = cellToString(cell);
              matcher.add(temp);
            }
            // Add all the test data to a Map
            for (int i = 0; i < matcher.size(); i++) {
              data.put(header.get(i), matcher.get(i));
            }
            matcherList.putAll(data);
          }
        }
      }
    }

    fs.close();
    return matcherList;
  }

  /**
   * Fetch the test data form a cell and convert it to string
   * 
   * @param cell cellValue
   */
  public static String cellToString(HSSFCell cell) {
    Object result;

    switch (cell.getCellType()) {

      case 0: // numeric value in Excel
        result = NumberToTextConverter.toText(cell.getNumericCellValue());
        break;
      case 1: // String Value in Excel
        result = cell.getStringCellValue();
        break;
      case 3: // String Value in Excel
        result = cell.toString();
        break;
      default:
        throw new RuntimeException("There is no support for this type of cell");
    }
    return result.toString();
  }

  public static ArrayList<HashMap<String, String>> getListDataValues(String data){
    ArrayList<HashMap<String, String>> list = new ArrayList<>();
    try {
      int itemidx = 0;
      String[] rows = data.split("\n");

      while (itemidx < rows.length) {
        HashMap<String, String> row = new HashMap<>();
        String[] fields = rows[itemidx].split(Pattern.quote("|"));
        int fieldidx = 0;

        while (fieldidx < fields.length) {
          String[] field = fields[fieldidx].split(":");
          row.put(field[0], field[1]);
          fieldidx++;
        }
        list.add(row);
        itemidx++;
      }

    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    } // catch
    return list;
  }

  /*****
   * Write test data to the sheet
   * 
   * @param testCaseId
   * @param testClassName
   * @param configSheetName
   * @param values
   * @param columnName
   * @throws IOException
   */
  public static void writeTestData(String testCaseId, String testClassName, String configSheetName,
      HashMap<String, String> values, String columnName) throws IOException {

    String filePath = "";
    String sheetName = "";
    String fileName = "";
    HSSFRow row = null;
    HSSFCell cell = null;
    int column = 0;

    try {

      Log.message
          ("*********************In Write Data******************************************");
      String basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator
          + "main" + File.separator + "resources" + File.separator + "testdata" + File.separator;
     Log.message("data utils base path: " + basePath);
      String configFilePath = basePath + "Config-TD.xls";
      Log.message("Config-------" + configFilePath);

      // Establish connection to config file to navigate till the test data file
      FileInputStream fs = new FileInputStream(configFilePath);
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      HSSFSheet sheet = wb.getSheet(configSheetName);
      Log.message("Sheet :"+ sheet);
      

      // Function call to find excel header fields
      Hashtable<String, Integer> excelrRowColumnCount = new Hashtable<>();
      excelrRowColumnCount = findRowColumnCount(sheet, excelrRowColumnCount);
      Hashtable<String, Integer> excelHeaders = new Hashtable<>();
      readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount);

      // Get test data set
      for (int r = 1; r < excelrRowColumnCount.get("RowCount"); r++) {
        row = sheet.getRow(r);
        if (row != null) {
          HSSFCell tempCell = sheet.getRow(r).getCell(0);
          if (tempCell != null) {
            String testClass = convertHSSFCellToString(row.getCell(0));

            if (testClass.equalsIgnoreCase(testClassName)) {
              cell = sheet.getRow(r).getCell(1);
              if (cell != null) {
                fileName = convertHSSFCellToString(row.getCell(1));
                Log.message("File name is :" + fileName);
              }
              cell = sheet.getRow(r).getCell(2);
              if (cell != null) {
                sheetName = convertHSSFCellToString(row.getCell(2));
              }
              break;
            }
          }
        }
      }
      filePath = basePath + fileName;
      Log.message("\nfile path----------> " + filePath);

      Log.message("filePath-------" + filePath + "  " + "workBook------" + fileName + " "
          + "sheetName----" + sheetName + "testCaseId------------" + testCaseId);
      fs.close();

      /***fs.close() to close the config file
       * Establishing connection with the test data file in which update needs to be made.
       * Establish connection to work sheet and open it in both read write mode
       */

      FileInputStream inputFile = new FileInputStream(filePath);
      HSSFWorkbook workbook = new HSSFWorkbook(inputFile);
      HSSFSheet sheetTestData = workbook.getSheet(sheetName);
      excelrRowColumnCount = new Hashtable<>();
      excelrRowColumnCount = findRowColumnCount(sheetTestData, excelrRowColumnCount);


      // function call to find excel header fields
      excelHeaders = new Hashtable<>();
      readExcelHeaders(sheetTestData, excelHeaders, excelrRowColumnCount);

      // Get all header
      row = sheetTestData.getRow(0);
      if (row != null) {
        for (int c = 0; c < excelrRowColumnCount.get("ColumnCount"); c++) {
          cell = sheetTestData.getRow(0).getCell(c);
          if (cell != null) {
            String temp = convertHSSFCellToString(row.getCell(c));
           
            if (temp.equalsIgnoreCase(columnName)) {
              column = c;
              for (int r = 1; r < excelrRowColumnCount.get("RowCount"); r++) {
               
                row = sheetTestData.getRow(r);
                if (row != null) {
                  HSSFCell tempCell = sheetTestData.getRow(r).getCell(0);
                  if (tempCell != null) {
                    
                    cell = sheetTestData.getRow(r).getCell(column);
                    
                    cell.setCellValue(values.get(columnName));
                    
                  }
                }
              }
            }
          }
        }
      }
      inputFile.close();
      Log.message("File path of file to be written to :" + filePath);

      FileOutputStream outputFile = new FileOutputStream(filePath);
      workbook.write(outputFile);
      outputFile.close();

    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
}
