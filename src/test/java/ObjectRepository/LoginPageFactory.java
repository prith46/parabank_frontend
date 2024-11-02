package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {

    public WebDriver driver;

    public LoginPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='username']")
    WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passWord;

    @FindBy(xpath = "//input[@value='Log In']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@href='lookup.htm']")
    WebElement forgotLogin;

    public void setUserName(String username){
        userName.sendKeys(username);
    }

    public void setPassWord(String password){
        passWord.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickForgotLogin(){
        forgotLogin.click();
    }
}
