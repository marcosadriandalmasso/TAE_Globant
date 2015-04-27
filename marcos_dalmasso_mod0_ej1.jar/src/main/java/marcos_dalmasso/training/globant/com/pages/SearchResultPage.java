package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage {

	@FindBy(css="article[id^='post-']")
	private WebElement postsResult;
	
	
}
