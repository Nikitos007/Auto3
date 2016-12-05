package HW3.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import HW3.Entity.User;

public class EditPlayerPage {

	private WebDriver driver;
	
	public EditPlayerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openInsert() {
		driver.findElement(By.xpath(".//a[text() = 'Insert']")).click();
	}

	public void createUser(User user) {
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_login']")).sendKeys(user.getUsername());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).sendKeys(user.getEmail());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_password']")).sendKeys(user.getPassword());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__confirm_password']")).sendKeys(user.getPassword());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).sendKeys(user.getFirstName());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).sendKeys(user.getLastName());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).sendKeys(user.getCity());
		driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).sendKeys(user.getAddress());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).sendKeys(user.getPhone());
		driver.findElement(By.xpath(".//input[@value = 'Save']")).click();
	}
	
	public void updateUser(User user) {
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).sendKeys(user.getEmail());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).sendKeys(user.getFirstName());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).sendKeys(user.getLastName());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).sendKeys(user.getCity());
		driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).clear();
		driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).sendKeys(user.getAddress());
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).clear();
		driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).sendKeys(user.getPhone());
		driver.findElement(By.xpath(".//input[@value = 'Save']")).click();
	}

	public User readUser() {
		User user = new User();
		user.setUsername(driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_login']")).getAttribute("value"));
		user.setEmail(driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_email']")).getAttribute("value"));
		user.setFirstName(driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_fname']")).getAttribute("value"));
		user.setLastName(driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_lname']")).getAttribute("value"));
		user.setCity(driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_city']")).getAttribute("value"));
		user.setAddress(driver.findElement(By.xpath(".//textarea[@id='ff14642ac1c__us_address']")).getAttribute("value"));
		user.setPhone(driver.findElement(By.xpath(".//input[@id='ff14642ac1c__us_phone']")).getAttribute("value"));
		return user;
	}

	public void deleteUser(String username) {
		driver.findElement(By.xpath(".//tr [.//td/a[text() = '" + username + "']] /td/a/img[@title = 'Delete']")).click();
		try{
			   //Wait 10 seconds till alert is present
			   WebDriverWait wait = new WebDriverWait(driver, 10);
			   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			   //Accepting alert.
			   alert.accept();
			   System.out.println("Accepted the alert successfully.");
			}catch(Throwable e){
			   System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
			}
	}

}
