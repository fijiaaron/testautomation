package theinternet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class SeleniumLoginTest extends SeleniumBaseTest
{	
	public enum LoginStatus {
		SUCCESS, INVALID_PASSWORD, INVALID_USER
	}
	
	@DataProvider
	public Object[][] users()
	{
		return new Object[][] {
			{ "tomsmith", "SuperSecretPassword!", LoginStatus.SUCCESS },
			{ "tomsmith", "SuperWrongPassword!}", LoginStatus.INVALID_PASSWORD }
		};
	}
	
	@Test(dataProvider="users")
	public void loginAsUser(String username, String password, LoginStatus loginStatus)
	{
		/* Arrange */
		LoginPage loginPage = new LoginPage(driver);
		String expectedMessage = loginPage.successfulLoginMessage;
		
		if (loginStatus == LoginStatus.INVALID_PASSWORD)
		{
			expectedMessage = loginPage.invalidPasswordMessage;
		}
		
		/* Act */
		String message = loginPage.login(username, password);
		
		/* Assert */
		System.out.println("message: " + message);
		assertThat(message).contains(expectedMessage);
	}
	
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
