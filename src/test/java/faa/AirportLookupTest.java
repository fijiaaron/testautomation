package faa;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import theinternet.SeleniumBaseTest;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.concurrent.TimeUnit;

public class AirportLookupTest extends SeleniumBaseTest
{

	@Test
	public void airportLookup() throws InterruptedException
	{
		driver.get("https://www.faa.gov/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("airportCode")).sendKeys("GPI");
		driver.findElement(By.id("checkAirport")).click();
		
		
		By checkAirport = By.id("checkAirport");
		WebElement checkAirportButton = driver.findElement(checkAirport);
		checkAirportButton.click();
		
		String airportName = driver.findElement(By.cssSelector(".airportInfo b")).getText();	
		System.out.println("airportName: " + airportName);
	}
}
