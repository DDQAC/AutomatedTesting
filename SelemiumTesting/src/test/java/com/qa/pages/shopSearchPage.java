package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class shopSearchPage {

	private WebDriver driver;

	private String searchTerm = "chiffon";

	public shopSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@FindBy(css = "#center_column > h1 > span.lighter")
	private WebElement searchTermUsed;

	@FindBy(css = "#center_column > h1 > span.heading-counter")
	private WebElement noOfResults;

	@FindBy(xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/h2")
	private WebElement addToCartMessage;

	@FindBy(css = "#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")
	private WebElement proceedToCheckoutButton;

	public String getSearchTermUsed() {
		return searchTermUsed.getText().replace("\"", "");
	}

	public int getNoOfResults() {
		return Integer.parseInt(noOfResults.getText().split(" ")[0]);
	}

	public void addToCart(int choice) {
		Actions action = new Actions(driver);
		if (choice < getNoOfResults()) {
			WebElement productContainer = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[" + choice + "]/div"));
			action.moveToElement(productContainer);
			WebElement addToCartButton = productContainer.findElement(By.xpath("//div[2]/div[2]/a[1]/span"));
			addToCartButton.click();
		}
	}

	public void proceedToCheckout() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(addToCartMessage));
		if (addToCartMessage.getText().contains("successfully added")) {
			proceedToCheckoutButton.click();
		}
	}

}
