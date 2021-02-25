package theinternet;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

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
		assertThat(message).contains(loginPage.successfulLoginMessage);
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
		assertThat(message).contains(loginPage.invalidPasswordMessage);
	}
}
