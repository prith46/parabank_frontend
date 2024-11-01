package Registration;

import BaseSuite.BaseTestSuite;
import ObjectRepository.RegistrationPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidRegistration extends BaseTestSuite {

    RegistrationPageFactory registration;
    WebDriver driver = BaseTestSuite.getDriver();

    @BeforeClass
    public void beforeClass() {
        registration = new RegistrationPageFactory(driver);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    @Test
    public void testInValidRegistration(){

        registration.setFirstName("Tedd");
        registration.setLastName("Mosby");
        registration.setAddress("7, Garden place");
        registration.setState("Vinland");
        registration.setZipCode("567567");
        registration.setPhoneNumber("12345");
        registration.setSsn("54321");
        registration.setUserName("tedfmosbyist");
        registration.setConfirmPassword("abc");
        registration.clickRegister();

        WebElement cityError = driver.findElement(By.id("customer.address.city.errors"));
        Assert.assertEquals(cityError.getText(), "City is required.");

        WebElement passwordError = driver.findElement(By.id("customer.password.errors"));
        Assert.assertEquals(passwordError.getText(), "Password is required.");
    }

    @Test
    public void testDuplicateValues(){

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

        WebElement userNameError = driver.findElement(By.id("customer.username.errors"));
        Assert.assertEquals(userNameError.getText(), "This username already exists.");
    }

    @Test
    public void testSpecialCharacter(){

        registration.setFirstName("Tedd%@");
        registration.setLastName("Mosb^%y");
        registration.setAddress("7, Garden!%@ place");
        registration.setCity("New &^#York");
        registration.setState("Vinland!#$");
        registration.setZipCode("567567$");
        registration.setPhoneNumber("^12345");
        registration.setSsn("54321!");
        registration.setUserName("t^^edfmosbyist");
        registration.setPassword("abc*&");
        registration.setConfirmPassword("abc*&");
        registration.clickRegister();

        WebElement userName = driver.findElement(By.tagName("h1"));
        String loginName = userName.getText();
        Assert.assertEquals(loginName, "Welcome t^^edfmosbyist");
    }

}
