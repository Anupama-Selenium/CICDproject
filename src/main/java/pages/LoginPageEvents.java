package pages;

import org.testng.Assert;

import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {

	ElementFetch ele= new ElementFetch();

	public void verifyIfLoginPageIsLoaded()
	{
		Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.loginButton).size()>0, "Element Not Found");	
	}

	public void enterEmailId(String email)
	{
		ele.getWebElement("XPATH", LoginPageElements.emailIdField).sendKeys(email);;
	}
	
	public void enterpassword(String password)
	{
		ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys(password);
	}
	public void clickLoginButton()
	{
		ele.getWebElement("XPATH", LoginPageElements.loginButton).click();
	}
}
