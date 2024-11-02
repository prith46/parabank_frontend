package Login;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InvalidLogin extends BaseTestSuite{

    WebDriver driver;
    LoginPageFactory login;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        login = new LoginPageFactory(driver);
    }

    @Test
    public void testInvalidLogin(){
        login.setUserName("testrandomusername");
        login.setPassWord("abbvv");
        login.clickLoginButton();

        WebElement loginError = driver.findElement(By.className("error"));
        Assert.assertEquals(loginError.getText(), "An internal error has occurred and has been logged.");

        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @Test
    public void testEmptyLogin(){
        login.clickLoginButton();

        WebElement loginError = driver.findElement(By.className("error"));
        Assert.assertEquals(loginError.getText(), "Please enter a username and password.");
    }
}
