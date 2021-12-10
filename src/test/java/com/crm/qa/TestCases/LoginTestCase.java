package com.crm.qa.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.Base.BaseClass;
import com.crm.qa.page.HomePage;
import com.crm.qa.page.LoginPage;

public class LoginTestCase extends BaseClass {
	LoginPage login ;
	HomePage home;
	
	public LoginTestCase()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		intialisation();
		login=new LoginPage();
		
	}
	
	@Test(priority = 0)
	public void loginToApplication()
	{
		home=login.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
