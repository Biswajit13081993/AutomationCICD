package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcompoonts.AbstractComponent;

public class CartCatalouge extends AbstractComponent {
	
	WebDriver driver;
	
	public CartCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userMail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void checkOut() {
		checkOutButton.click();
	}
	
	
}
