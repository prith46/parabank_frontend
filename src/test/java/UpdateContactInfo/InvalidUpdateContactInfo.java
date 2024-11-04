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
import org.testng.annotations.Test;

import java.time.Duration;

public class InvalidUpdateContactInfo {
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

        updatePage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='updateprofile.htm']")));
        updatePage.click();

        updateProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Update Profile']")));
    }

    @Test
    public void testInvalidUpdateContactInfo() throws InterruptedException {
        Thread.sleep(1000);
        update.setFirstName("Lily");
        update.setLastName("");
        update.setAddress("25, Kindergardern school");
        update.setCity("Texas");
        update.setState("");
        update.setZipCode("551353");
        update.setPhoneNumber("");
        updateProfileButton.click();

        WebElement lastNameError = driver.findElement(By.id("lastName-error"));
        Assert.assertEquals(lastNameError.getText(), "Last name is required.");

        WebElement stateError = driver.findElement(By.id("state-error"));
        Assert.assertEquals(stateError.getText(), "State is required.");
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
