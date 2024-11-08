package BillPay;

import BaseSuite.BaseTestSuite;
import ObjectRepository.BillPayFactory;
import ObjectRepository.LoginPageFactory;
import Registration.ValidRegistration;
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
import java.util.List;

public class ValidBillPay {

    WebDriver driver;
    BillPayFactory bill;
    WebDriverWait wait;
    ValidRegistration register;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        bill = new BillPayFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        register = new ValidRegistration();
    }

    public void login(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("barneystinson");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @Test
    public void testValidBillPay(){
        List<String> accountNumbers;
        accountNumbers = register.getAccountNumbers();
        System.out.println(accountNumbers);

        login();
        // Navigate to Billpay page
        driver.findElement(By.xpath("//a[@href='billpay.htm']")).click();
        bill.setPayeeName("Robin");
        bill.setAddress("15, Empire State street");
        bill.setCity("New York");
        bill.setState("Vinland");
        bill.setZipCode("2514622");
        bill.setPhoneNumber("5235134");
        bill.setAccount(accountNumbers.get(1));
        bill.setVerifyAccount(accountNumbers.get(1));
        bill.setFromAccount(accountNumbers.get(0));
        bill.setAmount("10");
        bill.clickSendPayment();

        WebElement temp = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[@id='payeeName']"))));
        Assert.assertEquals(temp.getText(), "Robin");

        temp = driver.findElement(By.xpath("//span[@id='amount']"));
        Assert.assertEquals(temp.getText(), "$10.00");

        temp = driver.findElement(By.xpath("//span[@id='fromAccountId']"));
        Assert.assertEquals(temp.getText(), accountNumbers.get(0));
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
