package HW3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlayersPage {

	private WebDriver driver;
	
	public PlayersPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void searchUserClickEdit(String username) {
		driver.findElement(By.xpath(".//input[@id='723a925886__login']")).clear();
		driver.findElement(By.xpath(".//input[@id='723a925886__login']")).sendKeys(username);
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
	}
	
	public void searchUser(String username) {
		driver.findElement(By.xpath(".//input[@id='723a925886__login']")).clear();
		driver.findElement(By.xpath(".//input[@id='723a925886__login']")).sendKeys(username);
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
	}
	
	public void searchUserByEmail(String email) {
		driver.findElement(By.xpath(".//*[@id='723a925886__email']")).clear();
		driver.findElement(By.xpath(".//*[@id='723a925886__email']")).sendKeys(email);
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + email + "'] ] //img[@title='Edit']")).click();
	}
	
	public void searchUserByFirstName(String firstName, String username) {
		driver.findElement(By.xpath(".//*[@id='723a925886__firstname']")).clear();
		driver.findElement(By.xpath(".//*[@id='723a925886__firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
	}
	
	public void searchUserByLastName(String lastName, String username) {
		driver.findElement(By.xpath(".//*[@id='723a925886__lastname']")).clear();
		driver.findElement(By.xpath(".//*[@id='723a925886__lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath(".//input[@value = 'Search']")).click();
		driver.findElement(By.xpath(".//tr [.//a[text()= '" + username + "'] ] //img[@title='Edit']")).click();
	}
	
}
