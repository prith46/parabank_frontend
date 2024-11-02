package Login;

import BaseSuite.BaseTestSuite;
import ObjectRepository.ForgotPasswordFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgotPassword {

    WebDriver driver;
    ForgotPasswordFactory forgotPassword;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        forgotPassword = new ForgotPasswordFactory(driver);
        driver.get("https://parabank.parasoft.com/parabank/lookup.htm");
    }

    @Test
    public void testForgotLogin(){
        forgotPassword.setFirstName("Tedd");
        forgotPassword.setLastName("Mosby");
        forgotPassword.setAddress("7, Garden place");
        forgotPassword.setState("Vinland");
        forgotPassword.setCity("New York");
        forgotPassword.setZipCode("567567");
        forgotPassword.setSsn("54321");
        forgotPassword.clickFindInfo();

        WebElement loginDetails = driver.findElement(By.xpath("//div[@id='rightPanel']//p[2]"));

        Assert.assertEquals(loginDetails.getText(), "Username: tedfmosbyist\n" +
                "Password: abc");

        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
