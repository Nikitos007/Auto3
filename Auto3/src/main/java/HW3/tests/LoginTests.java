package HW3.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HW3.pages.LoginPage;

public class LoginTests {
	
	private WebDriver driver;
    private LoginPage loginPage;

   
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        //wait while driver find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }
    
    /**
	 * Precondition:
	 * 1. Open application LoginPage
	 */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    /**
     * Steps to reproduce:
     * 1. Set username to admin
     * 2. Set password to 123
     * 3. Click Login
     * 4. Verify that title of the page equals to "Players"
     * 5. Verify that URL of the page don't equals to LoginPage.URL
     */
    @Test
    public void positiveLoginTest() {
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getTitle(), "Players", "Wrong title.");
        Assert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, "You are still on login page.");
    }

    /**
     * Steps to reproduce:
     * 1. Set username to admin
     * 2. Set password to 12345
     * 3. Click Login
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that URL of the page don't equals to LoginPage.URL
     * 6. Verify that error message on the page equals to "Invalid username or password"
     */
    @Test
    public void negativeLoginTest() {
        loginPage.setUsernameUsingJS("admin");
        loginPage.setPassword("12345");
        loginPage.logIn();
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong error message.");
    }

    /**
     * Steps to reproduce:
     * 1. Set username to admin123
     * 2. Set password to 123
     * 3. Click Login
     * 4. Verify that title of the page equals to "Login"
     * 5. Verify that URL of the page don't equals to LoginPage.URL
     * 6. Verify that error message on the page equals to "Invalid username or password"
     */
	@Test
	public void negativeTestWrongLogin() {
		loginPage.setUsernameUsingJS("admin123");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong error message.");
	}

	 /**
     * Steps to reproduce:
     * 1. Click Login
     * 2. Verify that title of the page equals to "Login"
     * 3. Verify that URL of the page don't equals to LoginPage.URL
     * 4. Verify that error message on the page equals to "Invalid username or password"
     */
	@Test
	public void negativeTestEmptyFields() {
		loginPage.logIn();
        Assert.assertEquals(loginPage.getErrorMessage(), "Value is required and can't be empty", "Wrong error message.");
        Assert.assertEquals(driver.getCurrentUrl(), LoginPage.URL, "You are NOT on login page.");
		Assert.assertEquals(driver.getTitle(), "Login", "Wrong title after unsuccessful login");
	}

	/**
     * Steps to reproduce:
     * 1. Close driver
     */
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
