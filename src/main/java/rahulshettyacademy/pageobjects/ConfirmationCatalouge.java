package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcompoonts.AbstractComponent;

public class ConfirmationCatalouge extends AbstractComponent {
	
	WebDriver driver;
	
	public ConfirmationCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationText;
	
	public String gettingConfirmationText() {
		return confirmationText.getText();
	}
	
	
	//String matchingText = driver.findElement(By.cssSelector(".hero-primary")).getText();
}
