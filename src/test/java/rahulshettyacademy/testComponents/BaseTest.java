package rahulshettyacademy.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandPage landingPage;
	
	public WebDriver driverInitialize() throws IOException {
		
		//we need to create first a global data and save it as ".properties" type in the main.
		
		//setting up property class within tests.
		Properties prop = new Properties();
		
		//setting up File Input Stream class from where we place the path where we created Global Data properties.
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		
		if(browserName.contains("chrome")) {
			
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		

		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440, 900)); //helps to run full screen
		
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			//safari
		driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
		
	}
	
		public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {
		
		//reading json file to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//string to hash map to list
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		}
	
	@BeforeMethod(alwaysRun=true)
	public LandPage launchApplication() throws IOException {
		driver = driverInitialize();
		landingPage = new LandPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void exitBrowser() {
		driver.close();
	}

}
