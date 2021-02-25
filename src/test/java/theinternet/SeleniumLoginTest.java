package theinternet;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class SeleniumLoginTest extends SeleniumBaseTest
{	
	@Test
	public void testLoginSuccess()
	{
		System.out.println("This should login successfully");
		
		/* Arrange */
		String username = "tomsmith";
		String password = "SuperSecretPassword!";
		
		/* Act */
		LoginPage loginPage = new LoginPage(driver);
		String message = loginPage.login(username, password);
		
		/* Assert */
		System.out.println("message: " + message);
		assertTrue(message.contains("You logged into a secure area!"));
	}
	
	@Test
	public void testLoginFailure()
	{
		System.out.println("This should fail to login");
	
		/* Arrange */
		String username = "tomsmith";
		String password = "SuperDuperWrongPassword!!!";
		
		/* Act */
		LoginPage loginPage = new LoginPage(driver);
		String message = loginPage.login(username, password);
		
		/* Assert */
		System.out.println("message: " + message);
		assertTrue(message.contains("Your password is invalid!"));
	}
}
