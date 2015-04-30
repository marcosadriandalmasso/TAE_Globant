package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckOutPage {
	
	@FindBy(xpath = "//div[@class='productInfo']")
	private WebElement tripInfo;

	public void completeAndSendCheckoutForm(WebDriver driver) {
		driver.findElement(By.cssSelector("option[value='MR']")).click();
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].name.firstName')]")).sendKeys("Marcos");
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].name.middleName')]")).sendKeys("A");
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].name.lastName')]")).sendKeys("Dalmasso");
		driver.findElement(By.cssSelector("option[value='SR']")).click();
		driver.findElement(By.cssSelector("option[value='AR']")).click();
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].phoneNumber.phoneNumber')]")).sendKeys("3415034627");
		driver.findElement(By.cssSelector("option[value='M']")).click();
		driver.findElement(By.cssSelector("option[value='6']")).click();
		driver.findElement(By.cssSelector("option[value='28']")).click();
		driver.findElement(By.cssSelector("option[value='1985']")).click();
		driver.findElement(By.xpath("//input[contains(@name, 'email.emailAddress')]")).sendKeys("testmdalmasso@gmail.com");
		driver.findElement(By.xpath("//input[@class='needsclick' and @value='-1']")).click();
		driver.findElement(By.cssSelector("input[name='_eventId_submit']")).click();
	}
	
	public void fillTravellerInfoSignedIn(WebDriver driver, String email) {
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
		driver.findElement(By.xpath(".//*[@id='main']/div[8]/div/div[2]/div[3]/div/label/div")).click();
		driver.findElement(By.cssSelector(".submit.button-primary.button-small>input")).click();
	}
	
	public void completeAndSendCheckoutFormSignedIn(WebDriver driver, String email, String password, String tel) {
		driver.findElement(By.cssSelector("h2[id='signInModule']")).click();
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[name='_eventId_authenticate']")).click();
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.cssSelector("option[value='W']")).click(); // Select Windows side
		driver.findElement(By.cssSelector("option[value='AR']")).click(); // Select Argentina country
		driver.findElement(By.cssSelector("input[type='tel']")).sendKeys(tel); // Write telephone
		driver.findElement(By.cssSelector("option[value='M']")).click(); // Select gender: Male
		driver.findElement(By.cssSelector("option[value='6']")).click(); // Select date of birth: day -> 28
		driver.findElement(By.cssSelector("option[value='28']")).click(); // Select date of birth: month -> June
		driver.findElement(By.cssSelector("option[value='1985']")).click(); // Select date of birth: year -> 1985
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email); // Write contact email
		driver.findElement(By.xpath("//input[@class='needsclick' and @value='-1']")).click(); // Select "No, I choose not to protect my purchase" radio button 
		driver.findElement(By.cssSelector("input[name='_eventId_submit']")).click(); // Click "Continue" button
	}
	
	public void verifyTripInformation(WebElement tripInformation) {
		Assert.assertEquals(tripInfo, tripInformation);
	}
}
