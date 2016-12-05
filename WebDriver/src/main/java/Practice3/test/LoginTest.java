package Practice3.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

	private WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("http://80.92.229.236:81/auth/login");
		System.out.println("Before Test");
	}
	
	@Test
	public void positiveLoginTest() {
		
	}
	
}
