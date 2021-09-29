package com.iNetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Test;

public class XLUtils {

	public static String xlFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\iNetBanking\\testData\\iNetBankingTestData.xls";
	public static String xlSheetName = "LoginData";
	public static String xlResultPath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\iNetBanking\\testReport\\testResult.xls";
	public static String xlResultSheetName = "TestResult";
	public static int xlRow, xlCol;
	public static String xlTD[][];
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static HSSFWorkbook wb;
	public static HSSFSheet ws;
	public static HSSFRow row;
	public static HSSFCell cell;
	//Method to get RowCount
	public static int getRowCount(String xlfilePath, String xlSheetName) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new HSSFWorkbook();
		ws = wb.getSheet(xlSheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	//Method to get ColumnCount
	public static int getColCount(String xlfilePath, String xlSheetName,int rownum) throws IOException {
		fi = new FileInputStream(xlfilePath);
		wb = new HSSFWorkbook();
		ws = wb.getSheet(xlSheetName);
		int colCount =ws.getRow(rownum).getLastCellNum();
		wb.close();
		fi.close();
		return colCount;
	}
	
	@Test(priority=1)
	public void ReadXlFile() throws IOException {
		xlTD = xlRead(xlFilePath,xlSheetName);
		System.out.println("Read Successful");
	}
	
	@Test(priority=2)
	public void WriteXlFile() throws IOException {
	xlWrite(xlResultPath, xlResultSheetName, xlTD);	
	System.out.println("Write Successful");
	}
	
	public static String[][] xlRead(String xlfilePath, String xlSheetName) throws IOException {
		String[][] xlData;
		File file = new File(xlfilePath);
		FileInputStream myStream = new FileInputStream(file);
		HSSFWorkbook myWb = new HSSFWorkbook(myStream);
		HSSFSheet mySheet = myWb.getSheet(xlSheetName);
		xlRow = mySheet.getLastRowNum();  //6
		xlCol = mySheet.getRow(0).getLastCellNum(); //2
		System.out.println("Total number of Rows in the " + xlSheetName + " is " + (xlRow +1));
		System.out.println("Total number of Columns in the " + xlSheetName + " is " + xlCol);
		xlData = new String[xlRow][xlCol];
		for (int i = 1; i <= xlRow; i++) {
			HSSFRow row = mySheet.getRow(i);
			for (int j = 0; j < xlCol; j++) {
				HSSFCell cell = row.getCell(j);
				String value = "-";
				if (cell != null) {
					value = cellToString(cell);
				}
				xlData[i-1][j] = value;
			}
		}
		file = null;
		return xlData;
	}

	public void xlWrite(String xlfilePath, String xlSheetName, String[][] xlData) throws IOException {
		File file = new File(xlfilePath);
		HSSFWorkbook myWb = new HSSFWorkbook();
		HSSFSheet mySheet = myWb.createSheet(xlSheetName);
		int xl_row = xlData.length;
		int xl_col = xlData[0].length;
		for (int i=0;i<xl_row;i++) {
			HSSFRow myRow= mySheet.createRow(i);
			for (int j=0;j<xl_col;j++) {
				HSSFCell myCell = myRow.createCell(j);
				myCell.setCellValue(xlData[i][j]);
			}
			FileOutputStream fOut = new FileOutputStream(file);
			myWb.write(fOut);
			fOut.flush();
			fOut.close();
		}
		
	} 
	public static String cellToString(HSSFCell cell) {
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			throw new RuntimeException("We can't evaluate firmula in Java");
		case HSSFCell.CELL_TYPE_ERROR:
			throw new RuntimeException("This celll has error");
		case HSSFCell.CELL_TYPE_BLANK:
			result = "%";
			break;
		default:
			throw new RuntimeException("We don't support this cell type " + type);
		}
		return result.toString();
	}

}
