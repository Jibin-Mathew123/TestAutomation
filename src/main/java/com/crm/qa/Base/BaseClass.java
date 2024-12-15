package com.crm.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.Util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import java.time.Duration;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	public BaseClass() {
		prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config"
							+ "\\config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	public static void intialisation()
	{
		String browser=prop.getProperty("browser");
		if(browser.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
		else if(browser.equals("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			//System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}
		Assert.assertNotNull(driver, "driver null");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD));
		//driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait= new WebDriverWait(driver,Duration.ofSeconds(TestUtil.PAGE_LOAD));
		js = (JavascriptExecutor) driver;
		
		driver.get(prop.getProperty("url"));
		
		
	}
	
	public static void waitUntilClickable(WebElement ele)
	{
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static void waitUntilVisible(WebElement ele)
	{
		wait.until(ExpectedConditions.visibilityOf(ele));
	}  
	
	public static void highligher(WebElement ele)
	{
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 3px solid red');", ele);
		
		
	}
	
	public static void click(WebElement ele)
	{
		waitUntilClickable(ele);
		highligher(ele);
		ele.click();
	}

}
