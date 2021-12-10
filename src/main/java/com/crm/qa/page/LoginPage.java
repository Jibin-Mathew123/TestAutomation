package com.crm.qa.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.Base.BaseClass;
import com.crm.qa.Util.TestUtil;

import io.netty.util.Timeout;

public class LoginPage extends BaseClass{
	
	@FindBy(xpath = "//input[@id='loginusername']")
	WebElement usrename;
	
	@FindBy(xpath = "//input[@id='loginpassword']")
	WebElement password;
	
	@FindBy(xpath = "//a[text()='Log in']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//button[text()='Log in']")
	WebElement login;
	
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
			}
	
	public HomePage login(String user,String pass)
	{
		
		waitUntilClickable(loginBtn);
		click(loginBtn);
		waitUntilVisible(usrename);
		usrename.sendKeys(user);
		password.sendKeys(pass);
		waitUntilClickable(login);
		click(login);
		return new HomePage();
	}

}
