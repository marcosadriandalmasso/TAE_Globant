package marcos_dalmasso.training.globant.com.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	Date date;
	Calendar calendar = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	
	@FindBy(css="a[data-context='signInLink']")
	private WebElement signInLink;
	
	public void go(WebDriver driver) {
		driver.get("http://cheaptickets.com");
	}
	
	public void fillWrongDateTripFormAndSend(WebDriver driver) {
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).sendKeys("LAS");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).sendKeys("LAX");
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 350); // adds 350 days to date
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).sendKeys(dateFormat.format(calendar.getTime()));
		driver.findElement(By.cssSelector("input[value='Search Flights']")).click();
	}
	
	public boolean checkForErrors(WebDriver driver) {
		return driver.findElement(By.xpath("//p[starts-with(@class, 'error message')]")).getText().contains("Most airlines only support reservations up to 330 days in advance");
	}
}
