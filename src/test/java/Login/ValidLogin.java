package Login;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidLogin extends BaseTestSuite {

    WebDriver driver;
    LoginPageFactory login;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        login = new LoginPageFactory(driver);
    }

    @Test
    public void testValidLogin(){
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();

        WebElement userName = driver.findElement(By.className("smallText"));
        Assert.assertEquals(userName.getText(), "Welcome Tedd Mosby");
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
