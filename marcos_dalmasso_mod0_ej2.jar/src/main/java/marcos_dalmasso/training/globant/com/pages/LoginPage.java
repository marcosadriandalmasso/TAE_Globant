package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(css=".loginForm")
	private WebElement loginForm;
	
	@FindBy(css=".welcomeText")
	private WebElement welcomeText;
	
	public void fillLoginForm(String email, String psw) {
		loginForm.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
		loginForm.findElement(By.cssSelector("input[type='password']")).sendKeys(psw);
		loginForm.findElement(By.cssSelector("input[name='_eventId_submit']")).click();
	}
	
	public WebElement getLoginForm() {
		return loginForm;
	}
	
	public boolean isLoggedIn(boolean typeOfTest) {
		if(typeOfTest) {
			return !welcomeText.getText().equalsIgnoreCase("Welcome to CheapTickets");
		}else{
			return loginForm.findElement(By.cssSelector("p[class*='error message']")).getText().contains("The e-mail and password you have entered do not match. Please try again.");
		}
	}
	
	public boolean isLoggedOut() {
		return welcomeText.getText().equalsIgnoreCase("Welcome to CheapTickets");
	}
}
