package adminportalTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pagesAdmin.AddClient;
import pagesAdmin.ClientDetails;
import pagesAdmin.Clients;
import pagesAdmin.LoginPageAdm;
import pagesAdmin.MemberPage;

public class MemberEdit extends BrowserSettings {


	String elementval;
	String updatename;

	@Test
	public void MemberEditInfo() throws InterruptedException {

		extent = new ExtentReports("src/QA report.html", false);
		ExtentTest test = extent.startTest("TC13");

		updatename = elementval;
		LoginPageAdm login = new LoginPageAdm(driver);
		Clients client = new Clients(driver);
		AddClient addclient = new AddClient(driver);
		ClientDetails details = new ClientDetails(driver);
		MemberPage mempage = new MemberPage(driver);
		AddClient waitmethod = new AddClient(driver);
		TakeScreenshot screen = new TakeScreenshot(driver);

		try {

			login.PreConditions();
			client.GotoClient();
			details.ClickMemberButton();
			mempage.WaitVisible();
			mempage.EditBtnClick();
			mempage.AddEMail();

			mempage.SaveBtnClick();
//			mempage.WaitVisibleEditBtn();
//			mempage.EditBtn2Click();
			Thread.sleep(2000);
			
//			WebElement element = driver.findElement(By.cssSelector(".table.table-striped.table-bordered>tbody>tr>td"));
			String element = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div[2]/div/div[2]/div/div[1]/div/div[1]/table/tbody/tr[10]/td[2]")).getText();
			
//			String elementval = element.getAttribute("value");

			// Get the excel file.
			FileInputStream file = new FileInputStream(new File("src/resourses/emailsrandom.xls"));

			// Get workbook for XLS file.
			HSSFWorkbook yourworkbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook.

			// If there have >1 sheet in your workbook, you can change it here
			// IF you want to edit other sheets.
			HSSFSheet sheet1 = yourworkbook.getSheetAt(0);

			// Get the row of your desired cell.
			// Let's say that your desired cell is at row 2.
			Row row = sheet1.getRow(0);
			// Get the column of your desired cell in your selected row.
			// Let's say that your desired cell is at column 2.
			Cell column = row.getCell(0);
			// If the cell is String type.If double or else you can change it.
			elementval = column.getStringCellValue();
			// New content for desired cell.
			//elementval = element.getAttribute("value");
			elementval = element;
			// Print out the updated content.
			System.out.println(elementval);
			// Set the new content to your desired cell(column).
			column.setCellValue(elementval);
			// Close the excel file.
			file.close();
			// Where you want to save the updated sheet.
			FileOutputStream out = new FileOutputStream(new File("src/resourses/emailsrandom.xls"));
			yourworkbook.write(out);
			out.close();

			Thread.sleep(2000);

			if (driver.getPageSource().contains("Edit")) {
				test.log(LogStatus.PASS, "Data was verify");
				screen.ScreenShot("TC13");
				extent.endTest(test);
			} else {
				test.log(LogStatus.FAIL, "Data was not verify");
				extent.endTest(test);
				screen.ScreenShot("TC13");
			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Test not executed");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			extent.endTest(test);
		} catch (IOException e) {
			e.printStackTrace();
			extent.endTest(test);
		}
		;

	}

}
