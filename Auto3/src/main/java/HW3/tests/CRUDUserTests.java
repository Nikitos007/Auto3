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

public class CRUDUserTests {

	private WebDriver driver;
    private EditPlayerPage crudUserPage;
    private User user;

    /**
	 * Precondition:
	 * 1. Open application LoginPage
	 * 2. Sign in
	 * 3. Verify that title of the page equals to "Players"
	 */
    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        //wait while driver find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.logIn();
        Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful login");
		this.crudUserPage = new EditPlayerPage(driver);
		this.user = new User();
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user
     * 5. Verify that title of the page equals to "Players"
     */
    @Test
    public void positiveInsertTest() {	
    	driver.get("http://80.92.229.236:81/players");
    	crudUserPage.openInsert();
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
    	crudUserPage.createUser(user);
    	Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful insert");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user with empty essential fields
     * 5. Verify that title of the page equals to "Players - Insert"
     */
    @Test
    public void negativeInsertTest() {	
    	driver.get("http://80.92.229.236:81/players");
    	crudUserPage.openInsert();
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
    	User user = new User();
    	user.setUsername("");
    	user.setEmail("");
    	user.setPassword("");
    	user.setPhone("");    	
    	crudUserPage.createUser(user);
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful insert");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Search user by username
     * 3. Verify that title of the page equals to "Players - Edit"
     * 4. Insert new data to the user
     * 5. Update user
     * 6. Verify that title of the page equals to "Players"
     */
    @Test
    public void positiveUpdateTest() {	
    	driver.get("http://80.92.229.236:81/players");
    	PlayersPage playersPage = new PlayersPage(driver);
    	playersPage.searchUserClickEdit(user.getUsername());
    	Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
    	user.newInfoOfUser();
    	crudUserPage.updateUser(user);
    	Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful update");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Search user by username
     * 3. Verify that title of the page equals to "Players - Edit"
     * 4. Read user info
     * 5. Equals fields of user
     */
    @Test
    public void positiveReadTest() {	
    	driver.get("http://80.92.229.236:81/players");
    	PlayersPage playersPage = new PlayersPage(driver);
    	playersPage.searchUserClickEdit(user.getUsername());
    	Assert.assertEquals(driver.getTitle(), "Players - Edit", "Wrong title after unsuccessful insert");
    	User actualUser = crudUserPage.readUser();
    	boolean equelsFlagUser = new User().equelsUsers(actualUser, user);
    	Assert.assertEquals(equelsFlagUser, true, "Wrong users don't equels");
    }
    
    /**
     * Steps to reproduce:
     * 1. Open URL with title "Players"
     * 2. Click to Insert
     * 3. Verify that title of the page equals to "Players - Insert"
     * 4. Create new user
     * 5. Search user by username
     * 6. Delete this user
     * 7. Verify that title of the page equals to "Players"
     */
    @Test
    public void positiveDeleteTest() {	
    	driver.get("http://80.92.229.236:81/players");
    	crudUserPage.openInsert();
    	Assert.assertEquals(driver.getTitle(), "Players - Insert", "Wrong title after unsuccessful login");
    	User user = new User();
    	crudUserPage.createUser(user);
    	PlayersPage playersPage = new PlayersPage(driver);
    	playersPage.searchUser(user.getUsername());
    	crudUserPage.deleteUser(user.getUsername());
    	Assert.assertEquals(driver.getTitle(), "Players", "Wrong title after unsuccessful update");
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
