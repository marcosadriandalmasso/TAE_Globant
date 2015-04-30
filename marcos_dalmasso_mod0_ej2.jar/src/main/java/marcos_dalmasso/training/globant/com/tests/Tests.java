package marcos_dalmasso.training.globant.com.tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import marcos_dalmasso.training.globant.com.pages.CheckOutPage;
import marcos_dalmasso.training.globant.com.pages.CustomizeTripPage;
import marcos_dalmasso.training.globant.com.pages.FlightsAndHotelsSearchResultPage;
import marcos_dalmasso.training.globant.com.pages.FlightsSearchResult;
import marcos_dalmasso.training.globant.com.pages.HomePage;
import marcos_dalmasso.training.globant.com.pages.LoginPage;
import marcos_dalmasso.training.globant.com.pages.TripsDetailPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author marcos.dalmasso
 * TAE Training
 * Ejercicio: 2
 */

public class Tests {

	WebDriver driver;
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	
	public void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
		}
	}
	
	@BeforeMethod
	public void before() {
		FirefoxProfile profile = new FirefoxProfile();       
		try {
			profile.addExtension(new File("C:\\FF_Profile\\firebug-2.0.9-fx.xpi"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void after() {
		driver.quit();
	}

	/**
	 * Caso: 1
	 */
	@Test
	public void loginTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		driver.findElement(By.cssSelector("a[data-context='signInLink']")).click();
		sleep(3);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.fillLoginForm("testmdalmasso@gmail.com", "testingapp");
		Assert.assertEquals(loginPage.isLoggedIn(true), true, "The test did not pass. The user is not logged in.");
	}

	/**
	 * Caso: 2
	 */
	@Test
	public void badUserLoginTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		driver.findElement(By.cssSelector("a[data-context='signInLink']")).click();
		sleep(3);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.fillLoginForm("mdalmasso@gmail.com", "testingapp");
		Assert.assertEquals(loginPage.isLoggedIn(false), true, "The test did not pass. The user is logged in.");
	}

	/**
	 * Caso: 3
	 */
	@Test
	public void badPasswordLoginTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		driver.findElement(By.cssSelector("a[data-context='signInLink']")).click();
		sleep(3);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.fillLoginForm("testmdalmasso@gmail.com", "testing");
		Assert.assertEquals(loginPage.isLoggedIn(false), true, "The test did not pass. The user is logged in.");
	}

	/**
	 * Caso: 4
	 */
	@Test
	public void logoutTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		driver.findElement(By.cssSelector("a[data-context='signInLink']")).click();
		sleep(3);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.fillLoginForm("testmdalmasso@gmail.com", "testingapp");
		driver.findElement(By.cssSelector("a[data-context='signOutLink']")).click();
		sleep(3);
		Assert.assertEquals(loginPage.isLoggedOut(), true, "The test did not pass. The user is not logged out.");
	}

	/**
	 * Caso: 5
	 */
	@Test
	public void flightOnlySearchTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		driver.findElement(By.xpath("//*[@id='products']/div/fieldset/div[2]/label[1]/div")).click();
		sleep(3);
		driver.findElement(By.xpath("//*[@id='search']/div[2]/div/fieldset/label[2]/div")).click();
		sleep(3);
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).sendKeys("LAS");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).sendKeys("LAX");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).sendKeys(dateFormat.format(new Date().getTime() + (1000 * 60 * 60 * 24)));
		driver.findElement(By.cssSelector("input[value='Search Flights']")).click();
		sleep(5);
		FlightsSearchResult flightSearchResult = PageFactory.initElements(
				driver, FlightsSearchResult.class);
		sleep(3);
		flightSearchResult.verifyResultsPage(driver);
		sleep(3);
		flightSearchResult.selectFirstFlightResultOrderedByMostExpensive(driver);
		sleep(5);

		TripsDetailPage tripDetailPage = PageFactory.initElements(driver,
				TripsDetailPage.class);
		tripDetailPage.verifyTripDetailsPage(driver);
		WebElement tripInfo = tripDetailPage.getTripInformation();
		sleep(10);
		driver.findElement(By.xpath("//*[@id='preMain']/div[3]/div/div[1]/div[1]/div/input")).click();
		sleep(5);
		
		CustomizeTripPage customizeTripPage = PageFactory.initElements(driver,
				CustomizeTripPage.class);
		customizeTripPage.verifyTripInformation(tripInfo);
		driver.findElement(By.cssSelector("input[data-context='continueToNextStep']")).click();
		sleep(5);

		CheckOutPage checkoutPage = PageFactory.initElements(driver,
				CheckOutPage.class);
		checkoutPage.verifyTripInformation(tripInfo);
		checkoutPage.completeAndSendCheckoutForm(driver);
		sleep(5);
	}

	/**
	 * Caso: 6
	 */
	@Test
	public void flightSearchWrongDateTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		sleep(5);
		driver.findElement(By.xpath("//*[@id='products']/div/fieldset/div[2]/label[1]/div")).click();
		sleep(3);
		driver.findElement(By.xpath("//*[@id='search']/div[2]/div/fieldset/label[2]/div")).click();
		sleep(3);
		homePage.fillWrongDateTripFormAndSend(driver);
		sleep(5);
		Assert.assertEquals(homePage.checkForErrors(driver), true);
	}

	/**
	 * Caso: 7
	 */
	@Test
	public void positiveFlightAndBookTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		sleep(5);
		driver.findElement(By.xpath("//*[@id='products']/div/fieldset/div[2]/label[1]/div")).click();
		sleep(3);
		driver.findElement(By.xpath("//*[@id='search']/div[2]/div/fieldset/label[2]/div")).click();
		sleep(3);
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).sendKeys("LAS");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).sendKeys("LAX");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).sendKeys(dateFormat.format(new Date().getTime() + (1000 * 60 * 60 * 24)));
		driver.findElement(By.cssSelector("input[value='Search Flights']")).click();
		sleep(15);
		FlightsSearchResult flightSearchResults = PageFactory.initElements(
				driver, FlightsSearchResult.class);
		flightSearchResults.verifyResultsPage(driver);
	}

	/**
	 * Caso: 8
	 */	
	@Test
	public void logedInFlightOnlySearchTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		sleep(5);
		driver.findElement(By.cssSelector("a[data-context='signInLink']")).click();
		sleep(3);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.fillLoginForm("testmdalmasso@gmail.com", "testingapp");
		sleep(3);
		driver.findElement(By.xpath("//*[@id='products']/div/fieldset/div[2]/label[1]/div")).click();
		sleep(3);
		driver.findElement(By.xpath("//*[@id='search']/div[2]/div/fieldset/label[2]/div")).click();
		sleep(5);
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).clear();
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).sendKeys("LAS");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).clear();
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).sendKeys("LAX");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).clear();
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).sendKeys(dateFormat.format(new Date().getTime() + (1000 * 60 * 60 * 24)));
		driver.findElement(By.cssSelector("input[value='Search Flights']")).click();
		sleep(15);
		
		FlightsSearchResult flightSearchResult = PageFactory.initElements(driver, FlightsSearchResult.class);
		sleep(3);
		flightSearchResult.verifyResultsPage(driver);
		sleep(3);
		flightSearchResult.selectFirstFlightResultOrderedByMostExpensive(driver);
		sleep(5);
		
		TripsDetailPage tripDetailPage = PageFactory.initElements(driver, TripsDetailPage.class);
		tripDetailPage.verifyTripDetailsPage(driver);
		WebElement tripInfo = tripDetailPage.getTripInformation();
		sleep(10);
		driver.findElement(By.xpath("//*[@id='preMain']/div[3]/div/div[1]/div[1]/div/input")).click();
		sleep(5);
		
		CustomizeTripPage customizeTripPage = PageFactory.initElements(driver, CustomizeTripPage.class);
		customizeTripPage.verifyTripInformation(tripInfo);
		driver.findElement(By.cssSelector("input[data-context='continueToNextStep']")).click();
		sleep(5);
		
		CheckOutPage checkoutPage = PageFactory.initElements(driver, CheckOutPage.class);
		checkoutPage.verifyTripInformation(tripInfo);
		checkoutPage.fillTravellerInfoSignedIn(driver,"testmdalmasso@gmail.com");
		sleep(5);
	}

	/**
	 * Caso: 9
	 */	
	@Test
	public void flightAndHotelSearchTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		sleep(5);
		driver.findElement(By.xpath("//*[@id='products']/div/fieldset/div[3]/div/div[2]/label[1]/div")).click();
		sleep(3);
		driver.findElement(By.cssSelector("input[name='aph.leaveSlice.orig.key']")).clear();
		driver.findElement(By.cssSelector("input[name='aph.leaveSlice.orig.key']")).sendKeys("LAS");
		driver.findElement(By.cssSelector("input[name='aph.leaveSlice.dest.key']")).clear();
		driver.findElement(By.cssSelector("input[name='aph.leaveSlice.dest.key']")).sendKeys("LAX");
		driver.findElement(By.cssSelector("input[name='aph.leaveSlice.date']")).clear();
		driver.findElement(By.cssSelector("input[name='aph.leaveSlice.date']")).sendKeys(dateFormat.format(new Date().getTime() + (1000 * 60 * 60 * 24)));
		driver.findElement(By.cssSelector("input[name='aph.returnSlice.date']")).sendKeys(dateFormat.format(new Date().getTime() + (1000 * 60 * 60 * 48)));
		driver.findElement(By.cssSelector("select[name='aph.rooms[0].adlts'] option[value='1']")).click();
		driver.findElement(By.cssSelector("input[name='aph.hname']")).sendKeys("APH - Air Plus Hotel");
		driver.findElement(By.cssSelector("input[data-wt-ti='SearchForm-searchButton']")).click();
		sleep(10);

		FlightsAndHotelsSearchResultPage flightAndHotelSearchResults = PageFactory.initElements(driver, FlightsAndHotelsSearchResultPage.class);
		flightAndHotelSearchResults.verifyResultsPage(driver);
		sleep(5);
		flightAndHotelSearchResults.sortHotelResultByReviewerScore(driver);
		sleep(5);
		flightAndHotelSearchResults.sortHotelResultByFourStarAndUp(driver);
		sleep(5);
		flightAndHotelSearchResults.verifyHotelFilterResults(driver);
		sleep(3);
		flightAndHotelSearchResults.getFirstFlightAndHotelResult(driver);
		sleep(3);
		flightAndHotelSearchResults.verifyTripDetailResults(driver);
	}
}
