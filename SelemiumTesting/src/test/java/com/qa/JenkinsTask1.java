package com.qa;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.pages.JenkinsDashboard;
import com.qa.pages.JenkinsLogin;

public class JenkinsTask1 {

	private WebDriver driver;

	private static ExtentReports report;

	private ExtentTest test;

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport-JenkinsTask1.html");
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(htmlReport);
	}

	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
//		opts.setHeadless(true);
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
	}

	@Test
	public void createProjectTest() {
		driver.get(JenkinsDashboard.URL);
		this.test = report.createTest("Jenkins Task 1 - Create New Project");

		String username = "DDQAC";
		String password = "345G*V4ESz-y9Mj";
		JenkinsLogin login = new JenkinsLogin(driver);
		login.login(username, password);

		JenkinsDashboard dashboard = new JenkinsDashboard(driver, test);
		dashboard.newProject("AutomatedItem");
		String newProject = driver
				.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[3]/a")).getText();
		if (newProject.equals("AutomatedItem")) {
			test.pass("Project created successfully!");
		} else {
			test.fail("Project was not created");
			fail();
		}
	}

	@After
	public void close() {
		driver.quit();
	}

	@AfterClass
	public static void teardown() {
		report.flush();
	}

}
