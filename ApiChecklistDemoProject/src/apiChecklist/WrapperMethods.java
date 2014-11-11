package apiChecklist;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WrapperMethods {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	Date date = new Date();

	public WebDriver driver;
	public String sheetName;
	public ExcelReporter report;	
		
	public WrapperMethods(WebDriver driver, String sheetName){
		this.driver=driver;
		this.sheetName=sheetName;
		report=new ExcelReporter(sheetName);
	}
		
	//To launch the Application 
	public void funcLaunchApp(String url) {
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.get(url);
			
			report.funcReportEvent("Application is Launched", "PASS");
		} catch (WebDriverException e) {
			report.funcReportEvent("Application is not launched", "FAIL");
		}
		finally	{
			funcSnapShot();
		}
	}
	
	//To Enter a value in Edit Box
	public void funcSetValueInEditBox(String attribute,String attName,String attVal){
		try {
			switch(attribute){
				case "id" : driver.findElement(By.id(attName)).clear();
							driver.findElement(By.id(attName)).sendKeys(attVal);
							report.funcReportEvent("the Value "+attVal+ " is set in the object "+attName, "PASS");
							break;
				
				case "name" : driver.findElement(By.name(attName)).clear();
							  driver.findElement(By.name(attName)).sendKeys(attVal);
							  report.funcReportEvent("the Value "+attVal+ " is set in the object "+attName, "PASS");
							  break;
	
				case "xpath" : driver.findElement(By.xpath(attName)).clear();
							   driver.findElement(By.xpath(attName)).sendKeys(attVal);
							   report.funcReportEvent("the Value "+attVal+ " is set in the object "+attName, "PASS");
							   break;
			}} 
		catch (NoSuchElementException | ElementNotVisibleException e) {
			report.funcReportEvent("the Value "+attVal+ " is not set in the object "+attName, "FAIL");
			
		}
		finally	{
			funcSnapShot();
		}
	}
	
	//To Select a value from a list box by Index
	public void funcSelectValueFromListBoxByIndex(String attribute,String attName,String attVal){
		
		try {
			int attValInt;
			attValInt=Integer.parseInt(attVal);
			switch(attribute){
			case "id" : WebElement listBoxID = driver.findElement(By.id(attName));
						Select listValueID = new Select(listBoxID);
						listValueID.selectByIndex(attValInt);
						report.funcReportEvent("the Value "+attValInt+ " is selected in the object "+attName, "PASS");
						break;
						
			case "name" : WebElement listBoxName = driver.findElement(By.name(attName));
						  Select listValueName = new Select(listBoxName);
						  listValueName.selectByIndex(attValInt);
						  report.funcReportEvent("the Value "+attValInt+ " is selected in the object "+attName, "PASS");
						  break;
						  
			case "xpath" : WebElement listBoxPath = driver.findElement(By.xpath(attName));
						   Select listValuePath = new Select(listBoxPath);
			  			   listValuePath.selectByIndex(attValInt);
			  			   report.funcReportEvent("the Value "+attValInt+ " is selected in the object "+attName, "PASS");
			  			   break;
			}} 
		catch (NoSuchElementException | ElementNotVisibleException e) {
			report.funcReportEvent("the Value "+attVal+ " is not selected in the object "+attName, "FAIL");
		}
		finally	{
			funcSnapShot();
		}
				
	}
	
	//To Select a value from a list box by Index
		public void funcSelectValueFromListBoxByName(String attribute,String attName,String attVal){
			try {
				switch(attribute){
				case "id" : WebElement listBoxID = driver.findElement(By.id(attName));
							Select listValueID = new Select(listBoxID);
							listValueID.selectByVisibleText(attVal);
							report.funcReportEvent("the Value "+attVal+ " is selected in the object "+attName, "PASS");
							break;
							
				case "name" : WebElement listBoxName = driver.findElement(By.name(attName));
							  Select listValueName = new Select(listBoxName);
							  listValueName.selectByVisibleText(attVal);
							  report.funcReportEvent("the Value "+attVal+ " is selected in the object "+attName, "PASS");
							  break;
							  
				case "xpath" : WebElement listBoxPath = driver.findElement(By.xpath(attName));
							   Select listValuePath = new Select(listBoxPath);
				  			   listValuePath.selectByVisibleText(attVal);
				  			   report.funcReportEvent("the Value "+attVal+ " is selected in the object "+attName, "PASS");
				  			   break;
				}} 
			catch (NoSuchElementException | ElementNotVisibleException e) {
				report.funcReportEvent("the Value "+attVal+ " is not selected in the object "+attName, "FAIL");
			}
			finally	{
				funcSnapShot();
			}
					
		}
	
	//Method to click on the object
	public void funcClickObject(String attribute, String attName) {
		try {
			switch(attribute){
			case "id" : driver.findElement(By.id(attName)).click();
						report.funcReportEvent("the object "+attName+ " is clicked successfully", "PASS");
						break;
						
			case "name" :	driver.findElement(By.name(attName)).click();
							report.funcReportEvent("the object "+attName+ " is clicked successfully", "PASS");
							break;
							
			case "link text" : 	driver.findElement(By.linkText(attName)).click();
								report.funcReportEvent("the object "+attName+ " is clicked successfully", "PASS");
							   	break;
							   	
			case "xpath" :	driver.findElement(By.xpath(attName)).click();
							report.funcReportEvent("the object "+attName+ " is clicked successfully", "PASS");
							break;
		}} 
		catch (NoSuchElementException | ElementNotVisibleException e) {
			report.funcReportEvent("the object "+attName+ " is not clicked ", "FAIL");
		}
		finally	{
			funcSnapShot();
		}
	}
	
	//Method to close the application
	public void funcCloseApp(){
		driver.quit();
	}
	
	public void funcSnapShot()	{
		try {
			String url = "D:\\Selenium\\Screenshots\\Snap_"+dateFormat.format(date)+".jpg";
			File sourceName=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceName, new File(url));
			//report.funcWriteSnapLink(url);
		} catch (IOException e) {
			System.out.println("File Exception");
		}
	}	
}
