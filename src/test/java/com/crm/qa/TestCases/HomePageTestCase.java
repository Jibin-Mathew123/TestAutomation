package com.crm.qa.TestCases;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.Base.BaseClass;
import com.crm.qa.Util.TestUtil;
import com.crm.qa.page.HomePage;
import com.crm.qa.page.LoginPage;

public class HomePageTestCase extends BaseClass {
	

	HomePage home;
	LoginPage login;
	
	public HomePageTestCase()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		intialisation();
		home=new HomePage();
		login= new LoginPage();
		home=login.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority = 0 ,enabled = false)
	public void verifyUserIsLoginSuccesfull()
	{
		String user=home.verifyUserLoginSuccesfull();
		Assert.assertTrue(user.contains(prop.getProperty("username").toString()), "User login is succesfull");
	}
	
	@Test(priority = 1 ,dataProvider = "getTestData")
	public void enterContactDetails(String email,String name,String message)
	{
		home.enterContactDate(email,name,message);
		TestUtil.getScreenshot();
	}
	
	@DataProvider
	public Object[][] getTestData()
	{
		Object[][] data=TestUtil.getData("Contact");
		return data;
	}
	
	@AfterMethod
	public void tearDown()
	{
		
		driver.quit();
	}



}
