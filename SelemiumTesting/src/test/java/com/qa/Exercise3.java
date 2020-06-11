package com.qa;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.pages.FTSEPage;

public class Exercise3 {

	private WebDriver driver;

	private static ExtentReports report;

	private ExtentTest test;

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport-Exercise3.html");
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
	public void riserTest() {
		driver.get(FTSEPage.URL);
		this.test = report.createTest("FTSE100RisersTest");

		FTSEPage page = new FTSEPage(driver);
		page.clickRisers();
		if (page.retrieveTopResult().equalsIgnoreCase("AVEVA Group plc")) {
			test.pass("The correct stock was retrieved");
		} else {
			test.fail("The correct stock was not retrieved");
			fail();
		}
	}

	@Test
	public void fallerTest() {
		driver.get(FTSEPage.URL);
		this.test = report.createTest("FTSE100FallersTest");

		FTSEPage page = new FTSEPage(driver);
		page.clickFallers();
		if (page.retrieveTopResult().equalsIgnoreCase("Rolls Royce Holdings plc")) {
			test.pass("The correct stock was retrieved");
		} else {
			test.fail("The correct stock was not retrieved");
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
