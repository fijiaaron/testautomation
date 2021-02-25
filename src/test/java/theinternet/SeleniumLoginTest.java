package theinternet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumLoginTest extends SeleniumBaseTest
{	
	public enum LoginStatus {
		SUCCESS, INVALID_PASSWORD, INVALID_USER
	}
	
	@DataProvider
	public Object[][] users() throws IOException
	{
		InputStream input = new FileInputStream("src/test/resources/users.properties");
		System.out.println(input);
		Properties users = new Properties();
		users.load(input);
		
		String validUsername = users.getProperty("valid.username");
		String validPassword = users.getProperty("valid.password");
		String invalidUsername = users.getProperty("invalid.username");
		String invalidPassword = users.getProperty("invalid.password");
		
		return new Object[][] {
			{ validUsername, validPassword, LoginStatus.SUCCESS },
			{ validUsername, invalidPassword, LoginStatus.INVALID_PASSWORD },
			{ invalidUsername, invalidPassword, LoginStatus.INVALID_USER }
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
		if (loginStatus == LoginStatus.INVALID_USER)
		{
			expectedMessage = loginPage.invalidUserMessage;
			
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
