package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/auth/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String value) {
        //create element and clear and sendKeys
    	WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.clear();
        usernameInput.sendKeys(value);
    }

    public void setPassword(String value) {
        //create element and clear and sendKeys
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(value);
    }

    public void logIn() {
        //find logIn button and click
        WebElement logInBtn = driver.findElement(By.id("logIn"));
        logInBtn.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getErrorMessage() {
        WebElement errorElement = driver.findElement(By.xpath(".//ul[@class='errors']/li"));
        return errorElement.getText();
    }

    public void setUsernameUsingJS(String value) {
        String script = "document.getElementById('username').value = '" + value + "'";
        ((JavascriptExecutor) driver).executeScript(script);
    }
    
}
