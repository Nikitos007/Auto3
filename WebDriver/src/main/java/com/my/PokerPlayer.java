package com.my;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PokerPlayer {

	private WebDriver driver;
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String city;
	private String address;
	private String phone;

	public boolean equals(Object obj) {		
		if (obj == null) {
			return false;
		}
        if (obj == this) {
        	return true;
        }
        if (obj.getClass() == this.getClass()) {
        	PokerPlayer player = (PokerPlayer) obj;
            if (player.username.equals(this.username) && player.email.equals(this.email) && player.password == this.password && player.firstName.equals(this.firstName)
            		&& player.lastName.equals(this.lastName) && player.city.equals(this.city) && player.address.equals(this.address) && player.phone == this.phone) {
            	return true;
            }
        }
        return false;
  	}

	public int hashCode() {
		 return Objects.hash(username, email, password, firstName, lastName, city, address, phone);
	}

	public String toString() {
		String result;
		result = "username = " + username + "\npassword = " + password + "\nemail = " + email + "\nFirst Name = " + firstName + 
				"\nLast Name = " + lastName + "\ncity = " + city + "\naddress = " + address + "\nphone = " + phone; 
		
		return result;
	}

	PokerPlayer() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		String URL = "http://80.92.229.236:81";
		driver.get(URL + "/auth/login");
		username = generateString();
		email = generateString() + "@mail.ru";
		password = generateString();
		firstName = generateString();
		lastName = generateString();
		city = generateString();
		address = generateString();
		phone = generateNumber();
	}

	public static void main(String[] args) {
		PokerPlayer pokerPlayer = new PokerPlayer(); 
		System.out.println("~~~ Login Test");
		pokerPlayer.loginTest();
		System.out.println("~~~ Create user Test");
		pokerPlayer.createUser();
		System.out.println("~~~ Search and check Test");
		pokerPlayer.searchUser();
		pokerPlayer.checkUser();
		System.out.println("~~~ Update user\n~~~Search and check Test");
		pokerPlayer.updateUser();
		pokerPlayer.searchUser();
		pokerPlayer.checkUser();
		pokerPlayer.driver.quit();
	}

	public void updateUser() {
		email = generateString() + "@mail.ru";
		firstName = generateString();
		lastName = generateString();
		city = generateString();
		address = generateString();
		phone = generateNumber();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).sendKeys(email);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).sendKeys(firstName);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).sendKeys(lastName);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).sendKeys(city);
		driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).clear();
		driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).sendKeys(address);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).sendKeys(phone);
		driver.findElement(By.xpath(".//input[@value = 'Save']")).click();
		assertText(driver.getTitle(), "Players");
	}

	public void checkUser() {
		String getUserName = driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_login']")).getAttribute("value");
		String getEmail = driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).getAttribute("value");
		String getFirstName = driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).getAttribute("value");
		String getLastName = driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).getAttribute("value");
		String getCity = driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).getAttribute("value");
		String getAdress = driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).getAttribute("value");
		String getPhone = driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).getAttribute("value");
		assertText(getUserName, username);
		assertText(getEmail, email);
		assertText(getFirstName, firstName);
		assertText(getLastName, lastName);
		assertText(getCity, city);
		assertText(getAdress, address);
		assertText(getPhone, phone);
	}

	public void searchUser() {
		driver.findElement(By.xpath(".//input[@id='723a925886__login']")).clear();
		driver.findElement(By.xpath(".//input[@id='723a925886__login']")).sendKeys(username);
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
		assertText(driver.getTitle(), "Players - Edit");
	}

	public void createUser() {
		driver.findElement(By.xpath(".//a[text() = 'Insert']")).click();
		assertText(driver.getTitle(), "Players - Insert");
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_login']")).sendKeys(username);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).sendKeys(email);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_password']")).sendKeys(password);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__confirm_password']")).sendKeys(password);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).sendKeys(firstName);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).sendKeys(lastName);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).sendKeys(city);
		driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).sendKeys(address);
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).sendKeys(phone);
		driver.findElement(By.xpath(".//input[@value = 'Save']")).click();
		assertText(driver.getTitle(), "Players");
	}

	public String generateString() {
		String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		int size = rand.nextInt(6) + 6;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(alphabet.toCharArray()[rand.nextInt(alphabet.length())]);
		}
		return sb.toString();
	}

	public String generateNumber() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder("+");
		for (int i = 0; i < 13; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}

	public void loginTest() {
		WebElement usernameInput = driver.findElement(By.id("username")); 
		usernameInput.sendKeys("admin");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.sendKeys("123"); 
		WebElement loginButton = driver.findElement(By.id("logIn")); 
		loginButton.click();
		String actualTitle = driver.getTitle();
		String expectedTitle = "Players";
		assertText(actualTitle, expectedTitle); 
	}

	public void assertText(String actualValue, String expectedValue) {
		if (actualValue.equals(expectedValue)) {
			System.out.println("Passed.");
		} else {
			System.err.println("Failed. Expected text is " + expectedValue + ", but Actual title is " + actualValue);
		}
	}
}
