package pageObjectsTests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPageAdm;
import testcases.TestClass;

public class Test1Login {
	
@Test
public void Login2AdminPortal(){
	
	System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
    WebDriver driver = new ChromeDriver();	
    
    ExtentReports logger = ExtentReports.get(Test1Login.class);
 	logger.init ("bin/QA report.html", true); 
	logger.startTest("TC1");
	
	LoginPageAdm login = new LoginPageAdm(driver);
	
    driver.manage().window().maximize();
    login.GetLoginPage();
    
    
    
    login.TypeUsername();
    login.TypePassword();
    login.ClickLoginButton();
    logger.log(LogStatus.INFO, "User logged");
    
    if(driver.getPageSource().contains("Clients")){
    	logger.log(LogStatus.PASS, "Data was verify");
    	logger.endTest();
    	}else{
    		logger.log(LogStatus.FAIL, "Admin was sign in");
    		logger.endTest();
    	}
    
    driver.quit();
		
}

}