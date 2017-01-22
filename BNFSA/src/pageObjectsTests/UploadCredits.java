package pageObjectsTests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pagesAdmin.AddClient;
import pagesAdmin.ClientDetails;
import pagesAdmin.Clients;
import pagesAdmin.LoginPageAdm;

public class UploadCredits extends BrowserSettings {
	
	private static ExtentReports extent;
	
	@Test
	public void UploadEmployees() throws InterruptedException, AWTException{
	
	LaunchBrowser();
	
	extent = new ExtentReports("bin/QA report.html", false);
	ExtentTest test = extent.startTest("TC7");
	
   	LoginPageAdm login = new LoginPageAdm(driver);
	Clients client = new Clients(driver);
	ClientDetails details = new ClientDetails(driver);
	AddClient waitmethod = new AddClient(driver);
	TakeScreenshot screen = new TakeScreenshot(driver);
	
	 try {
	login.PreConditions("sys", "sys");
	client.GotoClient();
	details.OpenAccounts();
	details.GotoAccounts();
	details.AccountFundingClick();
	details.ChooseCreditsClick();
	
    StringSelection ss = new StringSelection("C:\\GIT\\BNFSA\\bin\\bulks\\MemberCredits.en-GB.xls");  
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
    
    details.CreditSubmitClick();
	details.SeccessfulCredClick();
	details.CreditsCancelClick();
	details.GotoAccounts();
	details.TransactionClick();
	
	 if(driver.getPageSource().contains("2500")){
			test.log(LogStatus.PASS, "Credit added was added");
			screen.ScreenShot("TC7");
			extent.endTest(test);
			
	    	}else{
	    		
	    		test.log(LogStatus.FAIL, "Credit not added");
	    		screen.ScreenShot("TC7");
	    		extent.endTest(test);
	    		
	    	}
	 
	 } catch (NoSuchElementException e) { test.log(LogStatus.ERROR, "Test not executed");}
	   catch (ElementNotVisibleException e) {test.log(LogStatus.ERROR, "Test not executed");}
	   catch (NullPointerException e) {test.log(LogStatus.ERROR, "Test not executed");}
		
		
		screen.ScreenShot("TC7");
		extent.endTest(test);	
	 extent.flush();
	 driver.quit();
}
}
