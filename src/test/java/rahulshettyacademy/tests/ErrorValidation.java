package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartCatalouge;
import rahulshettyacademy.pageobjects.ProductCatalouge;
import rahulshettyacademy.testComponents.BaseTest;
import rahulshettyacademy.testComponents.Retry;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void submitOrder() throws IOException {
		
		landingPage.UserAction("Rahul1993@gmail.com", "Biswa@13");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorLoginMsg());
		

	}
	
	@Test
	public void productNameValidation() throws IOException {
		
		landingPage.UserAction("Rahul13@gmail.com", "Biswa@13");
		String productName = "ADIDAS ORIGINAL";
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
		productCatalouge.goToCartButton();
		CartCatalouge cartPage = new CartCatalouge(driver);
		boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL 22");
		Assert.assertFalse(match);
		

	}
	

}
