package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FTSEPage {

	public static final String URL = "https://www.hl.co.uk/shares/stock-market-summary/ftse-100";

	private WebDriver driver;

	public FTSEPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "/html/body/main/div/div/div[3]/div[4]/div[1]/div[2]/table/tbody/tr[1]")
	private WebElement topResult;

	@FindBy(xpath = "/html/body/div[4]/div/a[2]")
	private WebElement cookieBar;

	@FindBy(css = "#view-constituents > ul > li:nth-child(2) > a")
	private WebElement risers;

	@FindBy(css = "#view-constituents > ul > li:nth-child(3) > a")
	private WebElement fallers;

	public void clickRisers() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(risers));
		cookieBar.click();
		risers.click();
	}

	public void clickFallers() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(fallers));
		cookieBar.click();
		fallers.click();
	}

	public String retrieveTopResult() {
		String name = topResult.findElement(By.xpath("//td[2]/a")).getText();
		return name;
	}

}
