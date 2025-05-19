package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcompoonts.AbstractComponent;

public class CheckOutCatalouge extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//input[@class='input txt'])[1]")
	WebElement cvv;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".ta-item:last-of-type")
	WebElement clickCountry;
	
	@FindBy(css=".actions a")
	WebElement placingOrder;
	
	By visibilityCVV = By.xpath("(//input[@class='input txt'])[1]");
	By visibilityOfAllCountries = By.cssSelector(".ta-results");
	
	public void sendingCVV() {
		waitForElementToAppear(visibilityCVV);
		cvv.sendKeys("1312");
	}
	
	public ConfirmationCatalouge actionsToConfirm(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, countryName).build().perform();
		waitForElementToAppear(visibilityOfAllCountries);
		clickCountry.click();
		placingOrder.click();
		ConfirmationCatalouge confirmationPage = new ConfirmationCatalouge(driver);
		return confirmationPage;
	}
	
	
}
