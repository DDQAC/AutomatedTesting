package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopAccountPage {

	private WebDriver driver;

	public shopAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#center_column > div > div:nth-child(1) > ul > li:nth-child(4) > a")
	WebElement personalInfo;

	@FindBy(css = "#firstname")
	WebElement firstName;

	@FindBy(css = "#lastname")
	WebElement lastName;

	public void viewPersonalInfo() {
		personalInfo.click();
	}

	public String retrieveName() {
		String msg = String.format("Signed in as %s %s", firstName.getAttribute("value"),
				lastName.getAttribute("value"));
		return msg;
	}

}
