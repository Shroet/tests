package pagesAdmin;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageAdm {
	
	WebDriver driver;

	By username = By.id("signin_username");
	By password = By.id("signin_password");
	By loginButton = By.xpath("//input[@value='Sign In']");
	By forgotpass = By.xpath("/html/body/div/div[2]/div/div[1]/div/form/div[4]/input");
	By contactus = By.xpath("/html/body/div/div[2]/div/div[2]/div/a/b");
	
	AddClient waitmethod = new AddClient(driver);
	
	public LoginPageAdm(WebDriver driver){
		
		this.driver = driver;
	}
	
public void PreConditions(){
	
	GetLoginPage();
	LoginAdminPortal();
	waitmethod.Waitsec();
	}	

public void LoginAdminPortal(){
		
		driver.findElement(username).sendKeys("sys");
		driver.findElement(password).sendKeys("sys");
		driver.findElement(loginButton).click();
		
//		driver.findElement(username).sendKeys("bgworker");
//		driver.findElement(password).sendKeys("Hidden*Activity*32167#");
		
		
	}
	
	
	public void TypeUsername(){
		
		driver.findElement(username).sendKeys("sys");
	}
		
public void TypePassword(){
	
	    driver.findElement(password).sendKeys("sys");
	}
	
public void ClickLoginButton(){
	
    driver.findElement(loginButton).click();
}

public void ClickForgotPassButton(){
	
    driver.findElement(forgotpass).click();	
}

public void ClickContactusButton(){
	
    driver.findElement(contactus).click();	
}

public String email_name = RandomStringUtils.randomAlphabetic(5) + "@" + "yopmail.com";


public void TypeUsernameEmail(){
	
	driver.findElement(username).sendKeys(email_name);
}

public void TypePasswordEmail(){
	
    driver.findElement(password).sendKeys(email_name);
}




public void GetLoginPage(){
	
	driver.get("https://admin-qa.wtwbenefitsmarketplacefsa.com/Auth/SignIn?ReturnUrl=%2F");
}

}

