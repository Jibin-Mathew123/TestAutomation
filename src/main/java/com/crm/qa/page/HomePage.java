package com.crm.qa.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.qa.Base.BaseClass;

public class HomePage extends BaseClass {
	
	public HomePage()
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//a[@id='nameofuser']")
	WebElement userLogin;
	
	@FindBy(xpath = "//input[@id='recipient-email']")
	WebElement email;
	
	@FindBy(xpath = "//input[@id='recipient-name']")
	WebElement name;
	
	@FindBy(xpath = "//textarea[@id='message-text']")
	WebElement message;
	
	
	@FindBy(xpath = "//button[text()='Send message']")
	WebElement send;
	
	@FindBy(xpath = "//a[text()='Contact']")
	WebElement contact;
	
	
	
	
	
	public String verifyUserLoginSuccesfull()
	{
		waitUntilVisible(userLogin);
		return userLogin.getText();
	}
	
	public void enterContactDate(String emailId,String nameV,String messageV)
	
	{
		waitUntilVisible(contact);
		waitUntilClickable(contact);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(contact);
		
		waitUntilVisible(email);
		email.sendKeys(emailId);
		name.sendKeys(nameV);
		message.sendKeys(messageV);
		waitUntilClickable(send);
		click(send);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

}
