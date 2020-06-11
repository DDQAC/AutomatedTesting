package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookieClickerPage {

	public static final String URL = "https://orteil.dashnet.org/cookieclicker/";

	private WebDriver driver;

	public CookieClickerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#bigCookie")
	WebElement bigCookie;

	@FindBy(css = "#products")
	WebElement products;

	@FindBy(css = "#upgrades")
	WebElement upgrades;

	@FindBy(css = "#notes > div.framed.close.sidenote")
	WebElement closeNotes;

	@FindBy(css = "#shimmers")
	WebElement shimmers;

	@FindBy(css = "#cookies")
	WebElement cookieCount;

	public void clickThatCookie() {
		bigCookie.click();
	}

	public int cookies() {
		int cookies = Integer.parseInt(cookieCount.getText().split(" ")[0]);
		return cookies;
	}

	public void getUpgrades() {
		try {
			List<WebElement> availableUpgrades = upgrades.findElements(By.className("enabled"));
			availableUpgrades.get(availableUpgrades.size() - 1).click();
		} catch (Exception e) {
			clickThatCookie();
		}
	}

	public void getProducts() {
		try {
			List<WebElement> availableProducts = products.findElements(By.className("enabled"));
			availableProducts.get(availableProducts.size() - 1).click();
		} catch (Exception e) {
			clickThatCookie();
		}
	}

	public void closeNotes() {
		try {
			closeNotes.click();
		} catch (Exception e) {
			clickThatCookie();
		}
	}

	public void goldenCookie() {
		if (shimmers.isDisplayed()) {
			Actions action = new Actions(driver);
			Dimension cookieSize = shimmers.getSize();
			action.moveToElement(shimmers, cookieSize.width / 2, -(cookieSize.height / 2)).click().build().perform();
		} else {
			clickThatCookie();
		}
	}

}
