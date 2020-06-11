package com.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qa.pages.CookieClickerPage;

public class CookieClicker {

	private static WebDriver driver;

	public static void main(String[] args) {

		ChromeOptions opts = new ChromeOptions();
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();

		driver.get(CookieClickerPage.URL);
		CookieClickerPage page = new CookieClickerPage(driver);

		for (int i = 0; i < 15; i++) {
			page.clickThatCookie();
		}

		driver.findElement(By.cssSelector("#product0")).click();

		while (page.cookies() < 500) {
			page.getUpgrades();
			page.goldenCookie();
			page.closeNotes();
			page.clickThatCookie();
		}

		page.getUpgrades();

		while (true) {

			page.goldenCookie();
			page.getUpgrades();
			page.getProducts();
			page.closeNotes();
			page.clickThatCookie();

		}

	}

}
