package theinternet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	WebDriver driver;
	String url = "https://the-internet.herokuapp.com/login";
	
	@FindBy(id="username")
	WebElement usernameField;

	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(className="fa-sign-in")
	WebElement loginButton;
	
	@FindBy(id="flash")
	WebElement message;

	// constructor function
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.open();
	}
	
	public LoginPage open()
	{
		driver.get(url);
		return this;
	}
	
	public String login(String username, String password)
	{	
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		
		String text =  message.getText();
		
		return text;
	}
}
