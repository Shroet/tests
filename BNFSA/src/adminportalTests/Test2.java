package adminportalTests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test2 {
	
	WebDriver driver;
	
	@Test 
	
	public void test2 () {
		
		 	
		 System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
		 driver = new ChromeDriver();
		 
		 driver.get("http://www.google.com");
		
		}
	
	

}