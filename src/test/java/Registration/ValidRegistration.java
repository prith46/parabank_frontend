package Registration;

import BaseSuite.BaseTestSuite;
import ObjectRepository.RegistrationPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidRegistration extends BaseTestSuite {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    @Test
    public void testValidRegistration(){
        RegistrationPageFactory registration = new RegistrationPageFactory(driver);
        registration.setFirstName("Tedd");
        registration.setLastName("Mosby");
        registration.setAddress("7, Garden place");
        registration.setCity("New York");
        registration.setState("Vinland");
        registration.setZipCode("567567");
        registration.setPhoneNumber("12345");
        registration.setSsn("54321");
        registration.setUserName("tedfmosbyist");
        registration.setPassword("abc");
        registration.setConfirmPassword("abc");
        registration.clickRegister();

        WebElement userName = driver.findElement(By.tagName("h1"));
        String loginName = userName.getText();
        Assert.assertEquals(loginName, "Welcome tedfmosbyist");

        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
