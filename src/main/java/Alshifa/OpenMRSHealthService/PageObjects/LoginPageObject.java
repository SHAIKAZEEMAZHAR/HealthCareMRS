package Alshifa.OpenMRSHealthService.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {
	
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='username']")
	WebElement usernameField;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordField;
	
	@FindBy(xpath = "//li[@id='Inpatient Ward']")
	WebElement locationField;
	
	@FindBy(xpath = "//input[@id='loginButton']")
	WebElement loginButton;
	
	@FindBy(xpath = "//i[@class='icon-signout small']")
	WebElement logoutButton;
	
	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}
	
	public void enterpassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void selectLocation() {
		locationField.click();
	}
	
	public void clcikOnLoginButton() {
		loginButton.click();
	}
	
	public void login(String username, String password) {
		enterUsername(username);
		enterpassword(password);
		selectLocation();
		clcikOnLoginButton();
	}
	
	public boolean isLogoutButtonVisible() {
		try {
			return logoutButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
}
