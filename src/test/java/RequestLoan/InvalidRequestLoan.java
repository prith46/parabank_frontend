package RequestLoan;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import ObjectRepository.RequestLoanFactory;
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

public class InvalidRequestLoan {

    WebDriver driver;
    RequestLoanFactory loan;
    String mainAccountNumber;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        loan = new RequestLoanFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();

        WebElement getAccountNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountTable']//tr[1]//a")));
        mainAccountNumber = getAccountNumber.getText();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.findElement(By.xpath("//a[@href='requestloan.htm']")).click();
    }

    @Test
    public void testHugeLoanAmount() throws InterruptedException {

        loan.setLoadAmount("100000");
        loan.setDownPayment("100");
        loan.setFromAccount(mainAccountNumber);
        Thread.sleep(1000);
        loan.clickApplyNow();

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='loanStatus']")));
        Assert.assertEquals(status.getText(), "Denied");

        WebElement errorMessage = driver.findElement(By.xpath("//p[@class='error']"));
        Assert.assertEquals(errorMessage.getText(), "We cannot grant a loan in that amount with your available funds.");
    }

    @Test
    public void testHugeDownPayment() throws InterruptedException {

        loan.setLoadAmount("100");
        loan.setDownPayment("100000");
        loan.setFromAccount(mainAccountNumber);
        Thread.sleep(1000);
        loan.clickApplyNow();

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='loanStatus']")));
        Assert.assertEquals(status.getText(), "Denied");

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error']")));
        Assert.assertEquals(errorMessage.getText(), "You do not have sufficient funds for the given down payment.");
    }

    @Test
    public void testHugeLoanAndDownPayment() throws InterruptedException {

        loan.setLoadAmount("100000");
        loan.setDownPayment("1000");
        loan.setFromAccount(mainAccountNumber);
        Thread.sleep(1000);
        loan.clickApplyNow();

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='loanStatus']")));
        Assert.assertEquals(status.getText(), "Denied");

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error']")));
        Assert.assertEquals(errorMessage.getText(), "You do not have sufficient funds for the given down payment.");
    }

    @Test
    public void testEmptyLoanAmount() throws InterruptedException {
        loan.setDownPayment("10");
        loan.setFromAccount(mainAccountNumber);
        Thread.sleep(1000);
        loan.clickApplyNow();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='requestLoanError']//p")));
        Assert.assertEquals(errorMessage.getText(), "An internal error has occurred and has been logged.");
    }

    @Test
    public void testEmptyDownPaymentAmount() throws InterruptedException {
        loan.setLoadAmount("1000");
        loan.setFromAccount(mainAccountNumber);
        Thread.sleep(1000);
        loan.clickApplyNow();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='requestLoanError']//p")));
        Assert.assertEquals(errorMessage.getText(), "An internal error has occurred and has been logged.");
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
