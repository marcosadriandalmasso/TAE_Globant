package marcos_dalmasso.training.globant.com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FlightsSearchResult {

	public void verifyResultsPage(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector(".airResultsMod.resultSetAir.incremental"))); // Air results
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[data-context='airLowestPrice']"))); // Cheapest price
		Assert.assertNotNull(driver.findElement(By.cssSelector("span[data-context='originalTotal']"))); // Matching results
		Assert.assertNotNull(driver.findElement(By.cssSelector("table[class='control matrix block']"))); // Flight matrix
		Assert.assertNotNull(driver.findElement(By.cssSelector("table[class='tripSummary oneWay']"))); // Trip summary
	}
	
	public void selectFirstFlightResultOrderedByMostExpensive(WebDriver driver) {
		List<WebElement> list = driver.findElements(By.cssSelector("div[data-context='airResultsCard']"));
		list.get(list.size()-1).findElement(By.cssSelector("a[data-context='selectButton']")).click();
	}
}
