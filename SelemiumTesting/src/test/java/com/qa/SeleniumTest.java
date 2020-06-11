package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	WebDriver driver;

	@Before
	public void init() {
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		driver.manage().window().maximize();
		driver.get("https://www.bing.co.uk");
		WebElement searchBar = driver.findElement(By.id("sb_form_q"));
		searchBar.sendKeys("Turtles");
		searchBar.sendKeys(Keys.ENTER);
	}

	@After
	public void close() {
		driver.quit();
	}

}
