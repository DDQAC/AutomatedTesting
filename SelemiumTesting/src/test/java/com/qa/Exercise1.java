package com.qa;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise1 {

	static WebDriver driver;

	@BeforeClass
	public static void init() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public String[] addUser() {
		driver.get("http://thedemosite.co.uk/addauser.php");
		final String username = "user";
		final String password = "pass";
//		WebElement username = driver
//				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/blockquote/blockquote[2]/blockquote"));
//		WebElement password = driver
//				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/blockquote/blockquote[2]/blockquote"));
		WebElement unBox = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(1) > td:nth-child(2) > p > input"));
		WebElement pBox = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(2) > td:nth-child(2) > p > input[type=password]"));
		WebElement save = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(3) > td:nth-child(2) > p > input[type=button]"));
		unBox.sendKeys(username);
		pBox.sendKeys(password);
		String[] loginDetails = new String[] { username, password };
		save.click();
		return loginDetails;
	}

	public void login(String[] loginDetails) {
		driver.get("http://thedemosite.co.uk/login.php");
		String username = loginDetails[0];
		String password = loginDetails[1];
		WebElement unBox = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > table > tbody > tr:nth-child(1) > td:nth-child(2) > p > input"));
		WebElement pBox = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > p > input[type=password]"));
		WebElement testLogin = driver.findElement(By.cssSelector(
				"body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > table > tbody > tr:nth-child(3) > td:nth-child(2) > p > input[type=button]"));
		unBox.sendKeys(username);
		pBox.sendKeys(password);
		testLogin.click();
	}

	@Test
	public void addUserAndLoginTest() {
		login(addUser());
		WebElement loginMessage = driver
				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("**Successful Login**", loginMessage.getText());
	}

	@AfterClass
	public static void close() {
		driver.quit();
	}

}
