package apiChecklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReporter {

	private static XSSFWorkbook workBook= null;
	private static XSSFSheet workSheet= null;
	private static XSSFRow row= null;
	
	ExcelReporter(String sheetName){
		workBook=new XSSFWorkbook();
		workSheet=workBook.createSheet(sheetName);
		funcCreateHeader();
	}
	
	public void funcCreateHeader(){
		row=workSheet.createRow(0);
		row.createCell(0).setCellValue("Step No");
		row.createCell(1).setCellValue("Step Description");
		row.createCell(2).setCellValue("Status");
		row.createCell(3).setCellValue("Snapshot");
	}
	
	public void funcReportEvent(String desc, String status){
		int rowNum=workSheet.getLastRowNum()+1;
		row=workSheet.createRow(rowNum);
		row.createCell(0).setCellValue(rowNum);
		row.createCell(1).setCellValue(desc);
		row.createCell(2).setCellValue(status);
	}
	
	/*public void funcWriteSnapLink(String url)
	{
		row = workSheet.createRow(workSheet.getLastRowNum());
		row.createCell(3).setCellValue(url);
	}*/
	
	public void funcFlushWorkBook(){
		try {
			FileOutputStream fos=new FileOutputStream(new File ("D:\\Selenium\\Results\\Test_Report.xlsx"));
			workBook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		}catch (IOException e) {
			System.out.println("IO Exception");
		}			
	}
}
