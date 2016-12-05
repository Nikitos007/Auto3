package HW3.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HW3.Entity.User;
import HW3.pages.EditPlayerPage;
import HW3.pages.LoginPage;
import HW3.pages.PlayersPage;

public class SearchTest {

	private WebDriver driver;
    private PlayersPage playersPage;
    private LoginPage loginPage;
    private EditPlayerPage crudUserPage;
	private User user;

	/**
	 * Precondition:
	 * 1. Open application LoginPage
	 * 2. Sign in
	 * 3. Verify that title of the page equals to "Players"
	 * 4. Create new user
	 */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        //wait while driver find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(loginPage.getTitle(), "Players", "Wrong title.");
        this.playersPage = new PlayersPage(driver);
		this.user = new User();
		crudUserPage = new EditPlayerPage(driver);
		crudUserPage.openInsert();
    	crudUserPage.createUser(user);
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set username and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByUsernameTest() {
    	driver.get("http://80.92.229.236:81/players");
    	playersPage.searchUserClickEdit(user.getUsername());
    	Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by username");
	}
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set email and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByEmailTest() {
    	driver.get("http://80.92.229.236:81/players");
    	playersPage.searchUserByEmail(user.getEmail());
    	Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by email");
	}
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set firstName and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByFirstNameTest() {
    	driver.get("http://80.92.229.236:81/players");
    	playersPage.searchUserByFirstName(user.getFirstName(), user.getUsername());
    	Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by first name");
	}
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Set lastname and click search
     * 3. Verify that title of the page equals to "Players - Edit"
     */
    @Test
    public void positiveSearchByLastNameTest() {
    	driver.get("http://80.92.229.236:81/players");
    	playersPage.searchUserByLastName(user.getLastName(), user.getUsername());
    	Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful search by last name");
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
