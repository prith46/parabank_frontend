package Registration;

import BaseSuite.BaseTestSuite;
import ObjectRepository.RegistrationPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ValidRegistration extends BaseTestSuite {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){

    }

    public String validRegistrationTemplate(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber, String ssn, String setUserName, String password){
        driver = BaseTestSuite.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        RegistrationPageFactory registration = new RegistrationPageFactory(driver);
        registration.setFirstName(firstName);
        registration.setLastName(lastName);
        registration.setAddress(address);
        registration.setCity(city);
        registration.setState(state);
        registration.setZipCode(zipCode);
        registration.setPhoneNumber(phoneNumber);
        registration.setSsn(ssn);
        registration.setUserName(setUserName);
        registration.setPassword(password);
        registration.setConfirmPassword(password);
        registration.clickRegister();

        String verifyLoginName = "Welcome " + setUserName;
        WebElement userName = driver.findElement(By.tagName("h1"));
        String loginName = userName.getText();
        Assert.assertEquals(loginName, verifyLoginName);

        // get the account number
        driver.findElement(By.xpath("//a[@href='overview.htm']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountTable']//a")));
        String accountNumber = account.getText();

        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
        return accountNumber;
    }

    @Test
    public void testValidRegistration(){

        validRegistrationTemplate("Tedd", "Mosby", "7, Garden place", "New York", "Vinland", "567567", "12345", "54321", "teddmosby", "abc");

    }

    public List<String> getAccountNumbers(){

        List<String> accountNumbers = new ArrayList<>();
        String temp;

        temp = validRegistrationTemplate("Barney", "Stinson", "15, Empire State street", "New York", "Vinland", "2514622", "5235134", "74573", "barneystinson", "abc");
        accountNumbers.add(temp);

        temp = validRegistrationTemplate("Robin", "Schbetsky", "15, Empire State street", "New York", "Vinland", "2514622", "5235134", "74573", "robinschebetsky", "abc");
        accountNumbers.add(temp);

        return accountNumbers;
    }
}
