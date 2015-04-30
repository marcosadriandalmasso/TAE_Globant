package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CustomizeTripPage {

	@FindBy(xpath = "//div[@class='productInfo']")
	private WebElement tripInfo;
	
	public void verifyTripInformation(WebElement tripInformation) {
		Assert.assertEquals(tripInfo, tripInformation);
	}
}
