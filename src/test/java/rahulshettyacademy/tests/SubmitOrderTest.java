package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartCatalouge;
import rahulshettyacademy.pageobjects.CheckOutCatalouge;
import rahulshettyacademy.pageobjects.ConfirmationCatalouge;
import rahulshettyacademy.pageobjects.OrderCatalouge;
import rahulshettyacademy.pageobjects.ProductCatalouge;
import rahulshettyacademy.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider= "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		ProductCatalouge productCatalouge = landingPage.UserAction(input.get("userMail"), input.get("password"));
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("productName"));
		productCatalouge.goToCartButton();
		CartCatalouge cartPage = new CartCatalouge(driver);
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		cartPage.checkOut();
		CheckOutCatalouge checkOutPage = new CheckOutCatalouge(driver);
		checkOutPage.sendingCVV();
		checkOutPage.actionsToConfirm("India");
		ConfirmationCatalouge confirmationPage = new ConfirmationCatalouge(driver);
		String confirmMessage = confirmationPage.gettingConfirmationText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws IOException {
		
		ProductCatalouge productCatalouge = landingPage.UserAction("Rahul13@gmail.com", "Biswa@13");
		OrderCatalouge orderPage = productCatalouge.goToOrderButton();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	
	
	//@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"Rahul13@gmail.com", "Biswa@13", "ADIDAS ORIGINAL"}, {"Rahul1313@gmail.com", "Rahul@13", "ZARA COAT 3"}};
//	}
	
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("userMail", "Rahul13@gmail.com");
//		map.put("password", "Biswa@13");
//		map.put("productName", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("userMail", "Rahul13@gmail.com");
//		map1.put("password", "Biswa@13");
//		map1.put("productName", "ZARA COAT 3");
//		
//		return new Object[][] {{map}, {map1}};
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrderData.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

	

}
