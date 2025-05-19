package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcompoonts.AbstractComponent;

public class LandPage extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalouge productCatalouge;
	
	public LandPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userMail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	public ProductCatalouge UserAction(String emails, String passWord) {
		userMail.sendKeys(emails);
		password.sendKeys(passWord);
		submit.click();
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		return productCatalouge;
	}
	
	public String getErrorLoginMsg() {
		waitForWebElementToAppear(errormsg);
		return errormsg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
