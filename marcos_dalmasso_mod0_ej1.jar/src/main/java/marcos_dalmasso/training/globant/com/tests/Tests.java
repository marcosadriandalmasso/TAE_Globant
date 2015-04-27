package marcos_dalmasso.training.globant.com.tests;

import marcos_dalmasso.training.globant.com.pages.ContactUsPage;
import marcos_dalmasso.training.globant.com.pages.HomePage;
import marcos_dalmasso.training.globant.com.pages.PostPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void before() {
		driver = new FirefoxDriver();
	}
	
	@AfterMethod
	public void after() {
		driver.quit();
	}
	
	@Test
	public void validatePageByTitleTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		Assert.assertEquals(driver.getTitle(), "Automation Training | Aprender a automatizar en un solo sitio");
	}
	
	@Test
	public void searchQueryTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		homePage.searchQuery("");
		Assert.assertEquals(homePage.getSearchResult().contains("Nothing Found"), true, "There are search results");
	}
	
	@Test
	public void validatePostsDateTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		PostPage postPage = PageFactory.initElements(driver, PostPage.class);
		postPage.getInFirstPost();
		Assert.assertEquals(postPage.getPostsDate(), homePage.getPostDate(), "The date is incorrect");
	}
	
	@Test
	public void validateContactFormTest() {
		ContactUsPage contactUsPage = PageFactory.initElements(driver, ContactUsPage.class);
		contactUsPage.go(driver);
		contactUsPage.fillInContactForm("Marcos", "tae@globan.com", "Automation Testing", "Testing");
		Assert.assertEquals(contactUsPage.getContactConfirmationMsj(), "Thank you for contacting us.", "Error submitting the form.");
	}
	
	@Test
	public void validateContactFormTest2() {
		ContactUsPage contactUsPage = PageFactory.initElements(driver, ContactUsPage.class);
		contactUsPage.go(driver);
		contactUsPage.fillIncompleteContactForm("Marcos", "Automation Testing", "Testing");
		if(contactUsPage.getContactErrorMsj().equalsIgnoreCase("Please make corrections below and try again.")) {
			contactUsPage.fillIncompleteContactForm2("tae@globant.com");
			Assert.assertEquals(contactUsPage.getContactConfirmationMsj(), "Thank you for contacting us.", "Error submitting the form.");
		}
	}
	
	@Test
	public void postsByCalendarTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		if(homePage.findMonthlyPosts() < 1) {
			homePage.getCalendar().findElement(By.cssSelector("#prev>a")).click();
		}
		homePage.findMonthlyPosts();
		homePage.countPosts();
	}
}