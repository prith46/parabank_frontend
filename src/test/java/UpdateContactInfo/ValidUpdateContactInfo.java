package UpdateContactInfo;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import ObjectRepository.RegistrationPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidUpdateContactInfo extends BaseTestSuite{
    WebDriver driver;
    RegistrationPageFactory update;
    WebDriverWait wait;
    WebElement updateProfileButton;
    WebElement updatePage;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        update = new RegistrationPageFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.findElement(By.xpath("//a[@href='updateprofile.htm']")).click();
        updateProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Update Profile']")));
    }

    public void testUpdateContactTemplate(String firsName, String lastName, String address, String city, String state, String zipCode, String phoneNumber) throws InterruptedException {

        Thread.sleep(1500);
        update.setFirstName(firsName);
        update.setLastName(lastName);
        update.setAddress(address);
        update.setCity(city);
        update.setState(state);
        update.setZipCode(zipCode);
        update.setPhoneNumber(phoneNumber);
        Thread.sleep(500);
        updateProfileButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='updateProfileResult']//p")));
        Assert.assertEquals(message.getText(), "Your updated address and phone number have been added to the system.");

        updatePage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='updateprofile.htm']")));
        updatePage.click();

        Thread.sleep(2000);
        Assert.assertEquals(update.getFirstName(), firsName);
        Assert.assertEquals(update.getLastName(), lastName);
        Assert.assertEquals(update.getAddress(), address);
        Assert.assertEquals(update.getCity(), city);
        Assert.assertEquals(update.getState(), state);
        Assert.assertEquals(update.getZipCode(), zipCode);
        Assert.assertEquals(update.getPhoneNumber(), phoneNumber);
    }

    @Test
    public void testUpdatingContact() throws InterruptedException {
        testUpdateContactTemplate("Barney", "Stinson","15, The Great Place", "Florida", "California", "64782", "542962332");
    }

    @Test
    public void testUpdateWithoutPhone() throws InterruptedException {
        testUpdateContactTemplate("Robin", "Scherbatsky","34, Maple town", "Ottawa", "Canada", "243533", "");
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
