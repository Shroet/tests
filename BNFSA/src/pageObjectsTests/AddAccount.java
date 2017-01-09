package pageObjectsTests;

import org.apache.commons.lang3.RandomStringUtils;
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

public class AddAccount extends BrowserSettings {
	
	private static ExtentReports extent;
	
	@Test
	public void DeleteCl() throws InterruptedException{
	
	LaunchBrowser();
	
	extent = new ExtentReports("bin/QA report.html", false);
	ExtentTest test = extent.startTest("TC5");
   	
   	LoginPageAdm login = new LoginPageAdm(driver);
	Clients client = new Clients(driver);
	ClientDetails details = new ClientDetails(driver);
	
	try {
	
	login.PreConditions("sys", "sys");
	client.GotoClient();
	details.OpenAccounts();
	details.ClickAddNewAccountButton();
	details.AddnewAccount("FSA" + RandomStringUtils.randomNumeric(3), "777", "2017", "01/01/2017", "01/01/2018");
	
	} catch (NoSuchElementException e) { test.log(LogStatus.ERROR, "Test not executed");	
	};
	
	
	if(driver.getPageSource().contains("Action")){
		test.log(LogStatus.PASS, "Account was added");
		extent.endTest(test);

    	}else{
    		
    		test.log(LogStatus.FAIL, "Account not added");
    		extent.endTest(test);
    	}

	extent.flush();
	driver.quit();

	}
}
