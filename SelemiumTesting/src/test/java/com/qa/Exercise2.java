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
import com.qa.pages.shopCartPage;
import com.qa.pages.shopLandingPage;
import com.qa.pages.shopSearchPage;

public class Exercise2 {

	private WebDriver driver;

	private static ExtentReports report;

	private ExtentTest test;

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport-Exercise2.html");
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
	public void searchTest() {
		driver.get("http://automationpractice.com/index.php");
		this.test = report.createTest("searchTest");

		shopLandingPage landing = new shopLandingPage(driver);
		shopSearchPage search = new shopSearchPage(driver);

		String searchTerm = search.getSearchTerm();
		landing.search(searchTerm);

		if (search.getSearchTerm().equalsIgnoreCase(searchTerm)) {
			test.pass("Correct search term found!");
			if (search.getNoOfResults() > 0) {
				test.pass("Search returned " + search.getNoOfResults() + " results!");
			} else {
				test.fail("No results were returned!");
				fail();
			}
		} else {
			test.fail("Correct search term was not found! " + search.getSearchTerm() + " does not equal dress");
			fail();
		}
	}

	@Test
	public void buyTest() {
		driver.get(
				"http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=chiffon&submit_search=");
		this.test = report.createTest("buyTest");

		String email = "XImNotARobotHonestX@Gmail.com";
		String password = "d9fJ!mDSqDgQsKW";

		shopSearchPage search = new shopSearchPage(driver);
		shopCartPage cart = new shopCartPage(driver, email, password);

		search.addToCart(1);
		search.proceedToCheckout();
		cart.reviewSummary();
		cart.login();
		cart.reviewAddress();
		cart.reviewShipping();
		cart.reviewPayment();
		cart.reviewAndConfirmOrder();

		if (cart.checkOrder().equalsIgnoreCase("Your order on My Store is complete.")) {
			test.pass("Order confirmed!");
		} else {
			test.fail("Order not confirmed!");
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
