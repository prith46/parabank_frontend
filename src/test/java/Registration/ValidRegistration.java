package Registration;

import ObjectRepository.RegistrationPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidRegistration {

    public WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        driver.manage().window().maximize();
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
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
