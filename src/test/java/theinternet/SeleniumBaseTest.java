package theinternet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class SeleniumBaseTest
{
	protected WebDriver driver;
	
	@BeforeClass
	public void configureDrivers()
	{
		System.out.println("set properties to find drivers");
		
		
		String platform = System.getProperty("os.name");
		System.out.println(platform);
		
		if (platform.contains("Windows"))
		{
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
			System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");	
		}
		else if (platform.contains("Mac"))
		{
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
		}
			
	}
	
	
	@BeforeMethod
	public void setup()
	{
		// open browser
		driver = new ChromeDriver();
	}
	
	@AfterMethod
	public void cleanup()
	{
		driver.quit();
	}
}
