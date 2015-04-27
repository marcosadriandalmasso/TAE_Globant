package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage {

	@FindBy(css=".entry-date")
	private WebElement postsDate;
	
	public String getPostsDate() {
		return postsDate.getText();
	}
	
	public void getInFirstPost() {
		postsDate.click();
	}
}
