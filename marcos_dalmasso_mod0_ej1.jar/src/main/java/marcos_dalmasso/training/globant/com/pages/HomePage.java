package marcos_dalmasso.training.globant.com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id="content")
	private WebElement postContents;
	
	@FindBy(css="#site-title>span>a")
	private WebElement homePageTitle;
	
	@FindBy(id="s")
	private WebElement searchInput;
	
	@FindBy(css=".entry-title")
	private WebElement nonSearchResult;
	
	@FindBy(css=".entry-date")
	private WebElement postsDate;
	
	@FindBy(id="wp-calendar")
	private WebElement calendar;
	
	public void go(WebDriver driver) {
		driver.get("http://10.28.148.127/wordpress/");
	}
	
	public String getTitle() {
		return homePageTitle.getText();
	}
	
	public String getSearchResult() {
		return nonSearchResult.getText();
	}
	
	public void searchQuery(String query) {
		searchInput.sendKeys(query);
		searchInput.submit();
	}
	
	public String getPostDate() {
		return postsDate.getText();
	}
	
	public WebElement getCalendar() {
		return calendar;
	}
	
	public int findMonthlyPosts() {
		List<WebElement> postList = calendar.findElements(By.tagName("a"));
		postList.get(0).click();
		return postList.size();
	}
	
	public void countPosts() {
		List<WebElement> postsList = postContents.findElements(By.tagName("article"));
		System.out.println("Posts: "+postsList.size());
		int i = 0;
		for(WebElement articles : postsList) {
			System.out.println("Post #"+ ++i +": "+articles.findElement(By.cssSelector("article a:first-child")).getText());
		}
	}
}