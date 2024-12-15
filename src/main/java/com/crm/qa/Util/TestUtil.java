package com.crm.qa.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.Base.BaseClass;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class TestUtil extends BaseClass{
	
	 public static long PAGE_LOAD=20;
	public static long IMPLICIT_WAIT=10;
	public static Workbook workBoook;
	public static Sheet sheet;
	
	public static String TEST_DATA_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\TestData\\TestData.xlsx";
	
	
	public static Object[][] getData(String sheetName)
	{ FileInputStream fis=null;
		try {
			 fis= new FileInputStream(TEST_DATA_PATH);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			workBoook=WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet=workBoook.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				if(sheet.getRow(i+1).getCell(j).getCellType().equals(org.apache.poi.ss.usermodel.CellType.NUMERIC))
				{
					sheet.getRow(i+1).getCell(j).getNumericCellValue();
				}
				else if(sheet.getRow(i+1).getCell(j).getCellType().equals(org.apache.poi.ss.usermodel.CellType.STRING))
				{
				
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
		}
		
		return data;
	}
	
	public static void getScreenshot() {
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir");
		try {
			FileUtils.copyFile(src, new File(path+ "/screenshots/" +System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
