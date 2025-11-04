package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ElementFetch {

	public WebElement getWebElement(String identifierType, String IdentifierValue)
	{
		switch(identifierType)
		{
		case "XPATH":
		return BaseTest.getDriver().findElement(By.xpath(IdentifierValue));
		
		case "CSS":
			return BaseTest.getDriver().findElement(By.cssSelector(IdentifierValue));
			
		case "ID":
			return BaseTest.getDriver().findElement(By.id(IdentifierValue));
			
		case "CLASS":
			return BaseTest.getDriver().findElement(By.className(IdentifierValue));
			
		case "NAME":
			return BaseTest.getDriver().findElement(By.name(IdentifierValue));
			
		case "TAGNAME":
			return BaseTest.getDriver().findElement(By.tagName(IdentifierValue));
			
		default:
			return null;
		}
	}
	
	public List<WebElement> getWebElements(String identifierType, String IdentifierValue)
	{
		switch(identifierType)
		{
		case "XPATH":
		return BaseTest.getDriver().findElements(By.xpath(IdentifierValue));
		
		case "CSS":
			return BaseTest.getDriver().findElements(By.cssSelector(IdentifierValue));
			
		case "ID":
			return BaseTest.getDriver().findElements(By.id(IdentifierValue));
			
		case "CLASS":
			return BaseTest.getDriver().findElements(By.className(IdentifierValue));
			
		case "NAME":
			return BaseTest.getDriver().findElements(By.name(IdentifierValue));
			
		case "TAGNAME":
			return BaseTest.getDriver().findElements(By.tagName(IdentifierValue));
			
		default:
			return null;
		}
	}

	
	
}
