package com.qa;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import com.qa.pages.JenkinsDashboard;
import com.qa.pages.JenkinsLogin;

@RunWith(Parameterized.class)
public class JenkinsTest2 {

	private String username;
	private String fullName;
	private String password;
	private String email;
	private int rowNum;

	private static ExcelDriver excel = new ExcelDriver("src/test/resources/AssessmentFriday.xlsx");

	private WebDriver driver;

	private static ExtentReports report;

	private ExtentTest test;

	public JenkinsTest2(String username, String fullName, String password, String email, int rowNum) {
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.rowNum = rowNum;
	}

	@Parameters
	public static Collection<Object[]> data() throws IOException {
		XSSFSheet sheet = excel.getSheet();

		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][5];

		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
			ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
			ob[rowNum - 1][2] = sheet.getRow(rowNum).getCell(2).getStringCellValue();
			ob[rowNum - 1][3] = sheet.getRow(rowNum).getCell(4).getStringCellValue();
			ob[rowNum - 1][4] = rowNum;
		}
		return Arrays.asList(ob);
	}

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport-JenkinsTask2.html");
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
	public void addUsersTest() {
		driver.get(JenkinsDashboard.URL);
		this.test = report.createTest("Jenkins Task 2 - Add Users");

		String adminUsername = "DDQAC";
		String adminPassword = "345G*V4ESz-y9Mj";
		JenkinsLogin login = new JenkinsLogin(driver);
		login.login(adminUsername, adminPassword);

		JenkinsDashboard dashboard = new JenkinsDashboard(driver, test);
		dashboard.addUser(username, fullName, password, email);
		List<String> usernames = dashboard.getUsers();
		if (usernames.contains(username)) {
			String msg = String.format("The user %s has been created!", username);
			excel.writeAdded(rowNum, "T");
			test.pass(msg);
		} else {
			excel.writeAdded(rowNum, "F");
			test.fail("The user was not created!");
			fail();
		}
	}

	@After
	public void close() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("src/test/resources/AssessmentFriday.xlsx");

		excel.getWorkbook().write(fileOut);
		fileOut.flush();
		fileOut.close();

		driver.quit();
	}

	@AfterClass
	public static void teardown() throws IOException {
		report.flush();
		excel.getWorkbook().close();
		excel.getFile().close();
	}

}
