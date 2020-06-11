package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopCartPage {

	private WebDriver driver;

	private String email;
	private String password;

	public shopCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public shopCartPage(WebDriver driver, String email, String password) {
		this.driver = driver;
		this.email = email;
		this.password = password;
		PageFactory.initElements(driver, this);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@FindBy(css = "#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")
	WebElement proceedToSignInButton;

	@FindBy(css = "#email")
	WebElement enterEmail;

	@FindBy(css = "#passwd")
	WebElement enterPassword;

	@FindBy(css = "#SubmitLogin")
	WebElement submitLoginButton;

	@FindBy(css = "#center_column > form > p > button")
	WebElement ProceedToShippingButton;

	@FindBy(css = "#cgv")
	WebElement TsAndCsCheckBox;

	@FindBy(css = "#form > p > button")
	WebElement proceedToPaymentButton;

	@FindBy(css = "#HOOK_PAYMENT > div:nth-child(1) > div > p > a")
	WebElement payByWireTransfer;

	@FindBy(css = "#cart_navigation > button")
	WebElement confirmOrder;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/p/strong")
	WebElement orderConfirmation;

	public void reviewSummary() {
		proceedToSignInButton.click();
	}

	public void login() {
		enterEmail.sendKeys(email);
		enterPassword.sendKeys(password);
		submitLoginButton.click();
	}

	public void reviewAddress() {
		ProceedToShippingButton.click();
	}

	public void reviewShipping() {
		if (!TsAndCsCheckBox.isSelected()) {
			TsAndCsCheckBox.click();
		}
		proceedToPaymentButton.click();
	}

	public void reviewPayment() {
		payByWireTransfer.click();
	}

	public void reviewAndConfirmOrder() {
		confirmOrder.click();
	}

	public String checkOrder() {
		return orderConfirmation.getText();
	}

}
