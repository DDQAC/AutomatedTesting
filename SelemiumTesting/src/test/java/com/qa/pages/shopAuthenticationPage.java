package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class shopAuthenticationPage {

	public static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

	private WebDriver driver;

	public shopAuthenticationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#email_create")
	WebElement createEmail;

	@FindBy(css = "#SubmitCreate")
	WebElement createButton;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div[1]/form/div/div[1]")
	WebElement createError;

	@FindBy(css = "#email")
	WebElement loginEmail;

	@FindBy(css = "#passwd")
	WebElement loginPassword;

	@FindBy(css = "#SubmitLogin")
	WebElement loginButton;

	public boolean userExists(String email) {
		boolean exists = false;

		createEmail.sendKeys(email);
		createButton.click();

		if (driver.getCurrentUrl().equals(shopCreateAccountPage.URL)) {
			return exists;
		} else {
			try {
				new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(createError));
				if (createError.isDisplayed()) {
					exists = true;
				}
			} catch (Exception e) {
				return exists;
			}
		}
		return exists;
	}

	public void login(String email, String password) {
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		loginButton.click();
	}

}
