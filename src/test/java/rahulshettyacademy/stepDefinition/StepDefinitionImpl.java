package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartCatalouge;
import rahulshettyacademy.pageobjects.CheckOutCatalouge;
import rahulshettyacademy.pageobjects.ConfirmationCatalouge;
import rahulshettyacademy.pageobjects.LandPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;
import rahulshettyacademy.testComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	
	public LandPage landingPage;
	public ProductCatalouge productCatalouge;
	public CartCatalouge cartPage;
	public CheckOutCatalouge checkOutPage;
	public ConfirmationCatalouge confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_creds(String emails, String passWord) {
		productCatalouge = landingPage.UserAction(emails, passWord);
	}
	
	@When("^I add product (.+) to Cart$")
	public void add_Product_to_Cart(String productName) {
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_And_Submit_Order(String productName) {
		productCatalouge.goToCartButton();
		cartPage = new CartCatalouge(driver);
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.checkOut();
		checkOutPage = new CheckOutCatalouge(driver);
		checkOutPage.sendingCVV();
		checkOutPage.actionsToConfirm("India");
	}
	
	@Then("{string} message is displayed on ConfiramationPage")
	public void message_Displayed_Confirmation(String string){
		confirmationPage = new ConfirmationCatalouge(driver);
		String confirmMessage = confirmationPage.gettingConfirmationText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void some_Message_Displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorLoginMsg());
		driver.close();
	}

}
