package marcos_dalmasso.training.globant.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage {

	@FindBy(id="cntctfrm_contact_form")
	private WebElement contactForm;
	
	@FindBy(id="cntctfrm_thanks")
	private WebElement contactConfirmMessage;
	
	@FindBy(css="#cntctfrm_contact_form div:first-child")
	private WebElement contactErrorMessage;
	
	public void go(WebDriver driver) {
		driver.get("http://10.28.148.127/wordpress/sample-page/");
	}
	
	public void fillInContactForm(String name, String mail, String subject, String message) {
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_name")).sendKeys(name);
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_email")).sendKeys(mail);
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_subject")).sendKeys(subject);
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_message")).sendKeys(message);
		contactForm.findElement(By.cssSelector("input[type='submit']")).click();
	}
	
	public void fillIncompleteContactForm(String name, String subject, String message) {
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_name")).sendKeys(name);
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_subject")).sendKeys(subject);
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_message")).sendKeys(message);
		contactForm.findElement(By.cssSelector("input[type='submit']")).click();
	}
	
	public void fillIncompleteContactForm2(String mail) {
		contactForm.findElement(By.cssSelector("#cntctfrm_contact_email")).sendKeys(mail);
		contactForm.findElement(By.cssSelector("input[type='submit']")).click();
	}
	
	public String getContactConfirmationMsj() {
		return contactConfirmMessage.getText();
	}
	
	public String getContactErrorMsj() {
		return contactForm.findElement(By.cssSelector("#cntctfrm_contact_form div:first-child")).getText();
	}
}
