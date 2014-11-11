package apiChecklist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Register_TestNG {
  @Test
  public void Register() {
	  try{
			FileInputStream file = new FileInputStream(new File("D:\\Selenium\\Test Data\\api_checklist_testdata.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheet("Register");
			
			System.setProperty("webdriver.chrome.driver","D:\\Selenium\\Configuration\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			WrapperMethods wrapperObj=new WrapperMethods(driver,"Api_Register");
			
			int rowcnt = sheet.getLastRowNum();
			
			for (int i = 1; i <= rowcnt; i++) {
				XSSFRow row = sheet.getRow(i);
								
				wrapperObj.funcLaunchApp("http://api.checklist.com/");
				
				//Click Sign Up Link
				wrapperObj.funcClickObject("xpath","/html/body/div[2]/div[2]/div/h3/a");
							
				//Enter Email id
				wrapperObj.funcSetValueInEditBox("id","email",row.getCell(0).getStringCellValue());
				
				// Enter Password
				wrapperObj.funcSetValueInEditBox("id","password",row.getCell(1).getStringCellValue());
				
				// Enter Name
				wrapperObj.funcSetValueInEditBox("id","name",row.getCell(2).getStringCellValue());
				
				// Select Country from drop down by id
				wrapperObj.funcSelectValueFromListBoxByIndex("id","country",row.getCell(3).getStringCellValue());
				
				// Click Register
				wrapperObj.funcClickObject("xpath","//*[@id='regForm']/div[5]/button");
				
				wrapperObj.report.funcFlushWorkBook();
			}
			driver.quit();
		}catch (FileNotFoundException e) {
			System.out.println("The expected file is not Found");
		}catch (IOException e) {
			System.out.println("IO Exception");
		} 
  }
}
