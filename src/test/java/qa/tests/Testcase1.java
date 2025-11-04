package qa.tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePageEvents;
import pages.LoginPageEvents;
import utils.ElementFetch;
public class Testcase1 extends BaseTest {
	ElementFetch ele= new ElementFetch();
	HomePageEvents hmpage= new HomePageEvents();
	LoginPageEvents lginpage =new LoginPageEvents();
	String email = "adminuser"; 
	String password = "12345";
	@Test(dataProvider = "getData")
	public void enteringCredentials(String email, String password)
	{
		getLogger().info("Signin into LoginPage");
		hmpage.signInButton();
		getLogger().info("verify If Login Page Is Loaded");
		lginpage.verifyIfLoginPageIsLoaded();
		getLogger().info("Enter the credentials");
		lginpage.enterEmailId(email);
		lginpage.enterpassword(password);
		lginpage.clickLoginButton();
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"adminuser", "12345"}, {"abcdefgh", "98765"}};
	}

}
