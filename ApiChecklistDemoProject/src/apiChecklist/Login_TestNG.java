package apiChecklist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Login_TestNG {
  @Test
  public void Login() {
	  try{
			FileInputStream file = new FileInputStream(new File("D:\\Selenium\\Test Data\\api_checklist_testdata.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheet("Login");
			
			int rowcnt = sheet.getLastRowNum();
			
			for (int i = 1; i <= rowcnt; i++) {
				XSSFRow row = sheet.getRow(i);
				System.setProperty("webdriver.chrome.driver","D:\\Selenium\\Configuration\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				WrapperMethods wrapperObj=new WrapperMethods(driver,"Api_Login");
				
				wrapperObj.funcLaunchApp("http://api.checklist.com/");
				
				//Enter User Name
				wrapperObj.funcSetValueInEditBox("name","j_username",row.getCell(0).getStringCellValue());
				
				// Enter Password
				wrapperObj.funcSetValueInEditBox("name","j_password",row.getCell(1).getStringCellValue());
				
				// Click Login
				wrapperObj.funcClickObject("xpath","//*[@id='loginForm']/div[3]/button");
				
				// Verify the hot task is displayed in the home page
				if (driver.findElement(By.xpath("//*[@id='centerSide']/h1")).isDisplayed())
					wrapperObj.report.funcReportEvent("Verify the text HOT TASK is available in the page", "PASS");
				else 
					wrapperObj.report.funcReportEvent("Verify the text HOT TASK is available in the page", "FAIL");
						
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				// Click Logout
				wrapperObj.funcClickObject("xpath","//*[@id='myProfile']/i");
				wrapperObj.funcClickObject("xpath", "//*[@id='profileMenuUl']/li[4]/a");
				
				driver.quit();
			}
		}catch (FileNotFoundException e) {
			System.out.println("The expected file is not Found");
		}catch (IOException e) {
			System.out.println("IO Exception");
		} 
	}
}