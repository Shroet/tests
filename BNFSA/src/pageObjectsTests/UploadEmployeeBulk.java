package pageObjectsTests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import pages.AddClient;
import pages.ClientDetails;
import pages.Clients;
import pages.LoginPageAdm;

public class UploadEmployeeBulk extends BrowserSettings {
	
	private static ExtentReports extent;
	
	@Test
	public void UploadEmployees() throws InterruptedException, AWTException{
		
		LaunchBrowser();
		
		extent = new ExtentReports("bin/QA report.html", false);
		ExtentTest test = extent.startTest("TC6");
		
	   	LoginPageAdm login = new LoginPageAdm(driver);
		Clients client = new Clients(driver);
		ClientDetails details = new ClientDetails(driver);
		AddClient waitmethod = new AddClient(driver);
		
		try {
		login.PreConditions("sys", "sys");
		client.GotoClient();
				
		details.ClickImportEmployeeButton();
		details.ClickChooseFile();
		
	    StringSelection ss = new StringSelection("C:\\GIT\\BNFSA\\bin\\Employees.en-GB.xls");  
	    waitmethod.Waitsec();
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	   
	    Robot robot = new Robot();
	    
	    waitmethod.Waitsec();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    waitmethod.Waitsec();
	    robot.keyPress(KeyEvent.VK_V);
	    waitmethod.Waitsec();
	    robot.keyRelease(KeyEvent.VK_V);
	    waitmethod.Waitsec();
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    waitmethod.Waitsec();
	    robot.keyPress(KeyEvent.VK_ENTER);
	    waitmethod.Waitsec();
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    waitmethod.Waitsec();
	    
	    details.ClickSubmitEmpl();
	    waitmethod.Waitsec();
	    details.ClickOkafterUpload();
	    waitmethod.Waitsec();
	    details.ClickMemberButton();
	    waitmethod.Waitsec();

	    if(driver.getPageSource().contains("John")){
			test.log(LogStatus.PASS, "Member added was added");
			extent.endTest(test);
			
	    	}else{
	    		
	    		test.log(LogStatus.FAIL, "Member not added");
	    		extent.endTest(test);
	    		
	    	}
	    
		} catch (NoSuchElementException e) { test.log(LogStatus.ERROR, "Test not executed");	
		};

	    extent.flush();
		driver.quit();
	}
}
