package com.flipkart.generic;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
	
	public static String getData(String filepath, String sheetname, int row, int cell) {
		
		String value="";
		
		try {
				
			FileInputStream fis = new FileInputStream(filepath);
			Workbook wb=WorkbookFactory.create(fis);
			
			value=wb.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return value;
		
	}
	
	public static int getRowCount(String filepath, String sheetname) {
		
		int rc=-1;
		
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(filepath));
			
			rc=wb.getSheet(sheetname).getLastRowNum();
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rc;
		
	}

	public static int getCellCount(String filepath, String sheetname, int row) {
		
		try {
			
			Workbook wb=WorkbookFactory.create(new FileInputStream(filepath));
			int cc = wb.getSheet(sheetname).getRow(row).getLastCellNum();
			return cc;
		} catch (Exception e) {
			return 0;
		}
		
	}
}
