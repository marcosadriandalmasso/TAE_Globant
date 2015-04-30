package marcos_dalmasso.training.globant.com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FlightsAndHotelsSearchResultPage {
	
	public void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
		}
	}
	public void sortHotelResultByReviewerScore(WebDriver driver) {
		driver.findElement(By.cssSelector(".links.dropDown div[data-context='sortLinksDropDown']")).click();
		sleep(1);
		driver.findElement(By.xpath("//a[.='Reviewer Score']")).click();
	}
	
	public void sortHotelResultByFourStarAndUp(WebDriver driver) {
		driver.findElement(By.cssSelector("div[class='rating']>ul>li:nth-child(4)>a")).click();
	}
	
	public void verifyResultsPage(WebDriver driver) {
		sleep(5);
		Assert.assertNotNull(driver.findElement(By.cssSelector(".pkgResultsMod>div"))); // Matching results
		Assert.assertNotNull(driver.findElement(By.cssSelector(".actionExpand.titleBar.hideFromNonJS.trigger"))); // Sort by dropdown menu
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[data-context='pkgResultsChangeSearch']"))); // Trip summary
		Assert.assertNotNull(driver.findElement(By.cssSelector(".location"))); // Most booked areas
		Assert.assertNotNull(driver.findElement(By.cssSelector(".rating>ul"))); // Star rating
	}
	
	public void verifyHotelFilterResults(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='content noneBlock']>ul>li:nth-child(6)[class='selected']"))); // Check if "Sort by reviewer score" is selected
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='rating']>ul>li:nth-child(4)[class='selected']"))); // Check if "4-star hotels and up" is selected
	}
	
	public void verifyTripDetailResults(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[id='preMain']>h1"))); // Check if the Header Title is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector(".airItinerary"))); // Check if "Air Itinerary" is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector(".hotelName"))); // Check if the Hotel Name is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector(".costSummaryContainer.block"))); // Check if "Trip Cost" table is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector(".productSummaryContainer.block"))); // Check if "Trip Information" table is shown
	}
	
	public void getFirstFlightAndHotelResult(WebDriver driver) {
		List<WebElement> hotelList = driver.findElements(By.cssSelector("div[data-context='pkgResults']>div"));
		hotelList.get(0).findElement(By.cssSelector("//a[.='Select']")).click();
		
		sleep(5);
		List<WebElement> flightList = driver.findElements(By.cssSelector(".pkgResultsMod.resultSetAir.incremental>div"));
		flightList.get(0).findElement(By.xpath("//a[.='Select']")).click();
	}
}
