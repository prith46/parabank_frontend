package BillPay;

import BaseSuite.BaseTestSuite;
import ObjectRepository.BillPayFactory;
import ObjectRepository.LoginPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InvalidBillPay extends BaseTestSuite {

    WebDriver driver;
    BillPayFactory bill;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        bill = new BillPayFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @BeforeMethod
    public void beforeMethod(){
        // Navigate to Bill pay page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='billpay.htm']"))).click();
    }

    @Test
    public void testInvalidBillPay() throws InterruptedException {
        // Send payment without Account, Phone number and Name
        bill.setAddress("23, Mexico city");
        bill.setCity("Melbourne");
        bill.setState("Mexico");
        bill.setZipCode("523532");
        bill.setVerifyAccount("12555");
        bill.setAmount("100");
        Thread.sleep(1000);
        bill.clickSendPayment();

        WebElement payeeNameError = driver.findElement(By.xpath("//span[@id='validationModel-name']"));
        Assert.assertEquals(payeeNameError.getText(), "Payee name is required.");

        WebElement payeeAccountError = driver.findElement(By.xpath("//span[@id='validationModel-account-empty']"));
        Assert.assertEquals(payeeAccountError.getText(), "Account number is required.");

        WebElement payeePhoneError = driver.findElement(By.xpath("//span[@id='validationModel-phoneNumber']"));
        Assert.assertEquals(payeePhoneError.getText(), "Phone number is required.");
    }
}
