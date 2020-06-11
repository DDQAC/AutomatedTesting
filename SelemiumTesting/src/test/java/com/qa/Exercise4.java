package com.qa;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.pages.shopAccountPage;
import com.qa.pages.shopAuthenticationPage;
import com.qa.pages.shopCreateAccountPage;

@RunWith(Parameterized.class)
public class Exercise4 {

	private String email = "XImNotARobotHonestX@Gmail.com";
	private String firstName;
	private String lastName;
	private String password = "d9fJ!mDSqDgQsKW";
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	private int rowNum;

	private static ExcelDriver excel = new ExcelDriver("src/test/resources/User Details.xlsx");

	private WebDriver driver;

	private static ExtentReports report;

	private ExtentTest createTest;
	private ExtentTest loginTest;

	public Exercise4(String email, String firstName, String lastName, String password, String address, String city,
			String state, String zipCode, String phone, int rowNum) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phone = phone;
		this.rowNum = rowNum;
	}

	@Parameters
	public static Collection<Object[]> data() throws IOException {
		XSSFSheet sheet = excel.getSheet();

		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][10];

		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
			ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
			ob[rowNum - 1][2] = sheet.getRow(rowNum).getCell(2).getStringCellValue();
			ob[rowNum - 1][3] = sheet.getRow(rowNum).getCell(3).getStringCellValue();
			ob[rowNum - 1][4] = sheet.getRow(rowNum).getCell(4).getStringCellValue();
			ob[rowNum - 1][5] = sheet.getRow(rowNum).getCell(5).getStringCellValue();
			ob[rowNum - 1][6] = sheet.getRow(rowNum).getCell(6).getStringCellValue();
			ob[rowNum - 1][7] = String.valueOf((int) sheet.getRow(rowNum).getCell(7).getNumericCellValue());
			ob[rowNum - 1][8] = String.valueOf((int) sheet.getRow(rowNum).getCell(8).getNumericCellValue());
			ob[rowNum - 1][9] = rowNum;
		}
		return Arrays.asList(ob);
	}

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport-Exercise4.html");
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(htmlReport);
	}

	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
	}

	@Test
	public void test() {
		driver.get(shopAuthenticationPage.URL);

		shopAuthenticationPage auth = new shopAuthenticationPage(driver);
		shopCreateAccountPage create = new shopCreateAccountPage(driver);
		shopAccountPage account = new shopAccountPage(driver);

		if (auth.userExists(email)) {
			auth.login(email, password);
			this.loginTest = report.createTest("Login Test");
			excel.writeAuthentication(rowNum, "Log In");
			if (driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=my-account")) {
				account.viewPersonalInfo();
				loginTest.pass(account.retrieveName());
				excel.writeResult(rowNum, "PASS");
			} else {
				loginTest.fail("Oops, login was unsuccessful");
				excel.writeResult(rowNum, "FAIL");
				fail();
			}
		} else {
			this.createTest = report.createTest("Create New User Test");
			excel.writeAuthentication(rowNum, "Create User");
			if (driver.getCurrentUrl().equals(shopCreateAccountPage.URL)) {
				create.createAccount(firstName, lastName, password, address, city, state, zipCode, phone);
				if (driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=my-account")) {
					account.viewPersonalInfo();
					createTest.pass(account.retrieveName());
					excel.writeResult(rowNum, "PASS");
				} else {
					createTest.fail("User was not created successfully");
					excel.writeResult(rowNum, "FAIL");
					fail();
				}
			}

		}
	}

	@After
	public void close() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("src/test/resources/User Details.xlsx");

		excel.getWorkbook().write(fileOut);
		fileOut.flush();
		fileOut.close();

		driver.get("http://automationpractice.com/index.php?mylogout=");
		driver.quit();
	}

	@AfterClass
	public static void teardown() throws IOException {
		report.flush();
		excel.getWorkbook().close();
		excel.getFile().close();
	}

}
