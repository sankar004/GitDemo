package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvide {
	DataFormatter formatter = new DataFormatter();
	@Test(dataProvider = "driveTest")
	public void testCaseData(String greeting, String comm , String id) {
		
		System.out.println(greeting + comm + id);
	}
	
	@DataProvider(name="driveTest")
	public Object[][] getData() throws IOException {
	
		//Object[][] data = {{"hello", "test",1},{"bye", "message",143},{"solo", "call",420}};
		
		FileInputStream fls = new FileInputStream("excelDriven.xlsx");					
		XSSFWorkbook wb= new XSSFWorkbook(fls);
		XSSFSheet ws = wb.getSheetAt(0);
		int rowCount = ws.getPhysicalNumberOfRows();
		XSSFRow row = ws.getRow(0);
		int columnCount = row.getLastCellNum();
		Object[][] data = new Object[rowCount-1][columnCount];
		for(int i = 0 ; i<rowCount-1; i++) {
			row = ws.getRow(i + 1);
			for(int j=0; j<columnCount; j++) {
				//System.out.println(row.getCell(j));
				XSSFCell cell = row.getCell(j);
				data[i][j]= formatter.formatCellValue(cell);
			}
		}
		return data;
	}
	
}