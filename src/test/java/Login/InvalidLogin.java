package Login;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
        Assert.assertEquals(loginError.getText(), "The username and password could not be verified.");

        // In some testcases, even after providing invalid login credentials the application is logging in. The testcase will fail but if it is going inside, the following testcases will fail. So I'm using this logout code to make sure it doesn't affect the other testcases.
        List<WebElement> logoutButton = driver.findElements(By.xpath("//a[text()='Log Out']"));
        if (!logoutButton.isEmpty()) {
            logoutButton.get(0).click();
        } else {
            System.out.println("Logout button is not displayed.");
        }

    }

    @Test
    public void testEmptyLogin(){
        login.clickLoginButton();

        WebElement loginError = driver.findElement(By.className("error"));
        Assert.assertEquals(loginError.getText(), "Please enter a username and password.");
    }
}
