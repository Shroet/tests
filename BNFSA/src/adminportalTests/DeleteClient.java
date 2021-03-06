package adminportalTests;

import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pagesAdmin.Clients;
import pagesAdmin.LoginPageAdm;

public class DeleteClient extends BrowserSettings {

	@Test
	public void DeleteCl() throws InterruptedException {

		extent = new ExtentReports("src/QA report.html", false);
		ExtentTest test = extent.startTest("TC2");
		TakeScreenshot screen = new TakeScreenshot(driver);

		LoginPageAdm login = new LoginPageAdm(driver);
		Clients client = new Clients(driver);

		try {

			login.PreConditions();

			if (driver.getPageSource().contains("AAA")) {

				client.ClickDeleteСlient();
				client.ClickDeleteСlientOk();

			} else {

				screen.ScreenShot("TC2");
			}

			if (driver.getPageSource().contains("AAA")) {
				client.ClickDeleteСlient();

			} else {
				test.log(LogStatus.PASS, "Client AAA not exist");
				screen.ScreenShot("TC2");
				extent.endTest(test);

			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Test not executed");
		}

		catch (ElementNotVisibleException e) {
			test.log(LogStatus.ERROR, "Test not executed");
			screen.ScreenShot("TC2");
			extent.endTest(test);
		}

	}
}
