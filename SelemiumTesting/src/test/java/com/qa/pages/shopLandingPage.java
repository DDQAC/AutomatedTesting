package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopLandingPage {

	private WebDriver driver;

	public shopLandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#search_query_top")
	private WebElement searchBar;

	public void search(String searchTerm) {
		searchBar.sendKeys(searchTerm);
		searchBar.sendKeys(Keys.ENTER);
	}

}
