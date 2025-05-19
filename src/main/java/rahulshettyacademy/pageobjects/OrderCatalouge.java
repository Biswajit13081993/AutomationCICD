package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcompoonts.AbstractComponent;

public class OrderCatalouge extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userMail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = orderedProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
