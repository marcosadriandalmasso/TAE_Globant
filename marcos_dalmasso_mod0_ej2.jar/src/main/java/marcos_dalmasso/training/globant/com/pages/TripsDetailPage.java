package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TripsDetailPage {

	@FindBy(xpath = "//div[@class='productInfo']")
	private WebElement tripInfo;
	
	public void verifyTripDetailsPage(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector(".carCrossSellMod.stat-mod-box"))); // Add's car offer
		Assert.assertNotNull(driver.findElement(By.id("preMain"))); // Booking short desc.
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[data-context='loyaltyRewardsRail']"))); // CheapCash Advertisement
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='airItinerary']"))); // Flight details
		Assert.assertNotNull(driver.findElement(By.cssSelector("table[class='costSummary']"))); // Trip cost
	}
	
	public String getTripInformation(WebDriver driver) {
		return driver.findElement(By.cssSelector(".contentText>strong")).getText() + "\n" +
				driver.findElement(By.cssSelector("inlineTerm")).getText() + "\n" +
				driver.findElement(By.cssSelector(".flightOriginDestination")).getText();
	}
	
	public WebElement getTripInformation() {
		return tripInfo;
	}
}
