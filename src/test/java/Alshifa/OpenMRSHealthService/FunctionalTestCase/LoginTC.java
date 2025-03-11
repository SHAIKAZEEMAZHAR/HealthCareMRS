package Alshifa.OpenMRSHealthService.FunctionalTestCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Alshifa.OpenMRSHealthService.BaseClass.BaseTestClass;
import Alshifa.OpenMRSHealthService.PageObjects.LoginPageObject;
import Alshifa.OpenMRSHealthService.Utility.ConfigFile;

public class LoginTC extends BaseTestClass {
	
	private String username = ConfigFile.getProperty("app.username");
	private String password = ConfigFile.getProperty("app.password");
	
	@BeforeClass
	public void initBrowser() {
		super.setUpBrowser(browserType.CHROME);
	}
	
	@Test
	public void loginFunctionality() {
		ExtentTest test = getExtentTest();
		LoginPageObject loginObjs = new LoginPageObject(driver);
		try {
			test.log(Status.INFO, "Attempting to login with username: " + username);
			loginObjs.login(username, password);
			test.log(Status.INFO, "Login attempt completed.");
			boolean isLogoutButtonVisible = loginObjs.isLogoutButtonVisible();
			test.log(Status.PASS, "Login successful, logout button is visible.");
			Assert.assertTrue(isLogoutButtonVisible, "LogoutButton is not visible");
		} catch (Exception e) {
			 test.log(Status.FAIL, "Login failed: " + e.getMessage());
			System.out.println("Issue : " + e.getMessage());
		}
		
	}
	
	@AfterClass
	public void closeBrowser() {
		tearDown();
	}
}
