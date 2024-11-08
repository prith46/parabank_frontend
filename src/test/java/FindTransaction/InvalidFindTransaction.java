package FindTransaction;

import BaseSuite.BaseTestSuite;
import ObjectRepository.FindTransactionsFactory;
import ObjectRepository.LoginPageFactory;
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

public class InvalidFindTransaction {

    WebDriver driver;
    FindTransactionsFactory findTransaction;
    LoginPageFactory login;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        findTransaction = new FindTransactionsFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='findtrans.htm']")).click();
        Thread.sleep(1000);
    }
    
    @Test
    public void testEmptyFields(){
        findTransaction.clickFindTransactionById();
        findTransaction.clickFindTransactionByDate();
        findTransaction.clickFindTransactionByAmount();
        findTransaction.clickFindTransactionByDateRange();

        WebElement errorMessage = driver.findElement(By.id("transactionIdError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid transaction ID");

        errorMessage = driver.findElement(By.id("transactionDateError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid date format");

        errorMessage = driver.findElement(By.id("dateRangeError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid date format");

        errorMessage = driver.findElement(By.id("amountError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid amount");
    }

    @Test
    public void testInvalidTransactionId(){
        findTransaction.setTransactionId("253422");
        findTransaction.clickFindTransactionById();

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error']")));
        Assert.assertEquals(error.getText(), "An internal error has occurred and has been logged.");
    }

    @Test
    public void testInvalidDateFormat(){
        findTransaction.setTransactionDate("11072024");
        findTransaction.clickFindTransactionByDate();

        WebElement errorMessage = driver.findElement(By.id("transactionDateError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid date format");
    }

    @Test
    public void testRandomDate(){
        findTransaction.setTransactionDate("01-01-1999");
        findTransaction.clickFindTransactionByDate();

        Boolean error = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tbody[@id='transactionBody']//tr//td[1]")));
        Assert.assertTrue(error);
    }

    @Test
    public void testInvalidDateFormatInRange(){
        findTransaction.setFromDate("11072024");
        findTransaction.setToDate("11082025");
        findTransaction.clickFindTransactionByDateRange();

        WebElement errorMessage = driver.findElement(By.id("dateRangeError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid date format");
    }

    @Test
    public void testWrongDateRange(){
        findTransaction.setFromDate("11-07-2024");
        findTransaction.setToDate("11-08-2023");
        findTransaction.clickFindTransactionByDateRange();

        Boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tbody[@id='transactionBody']/tr/td[1]")));
        Assert.assertTrue(result);
    }

    @Test
    public void testAmountWrongFormat(){
        findTransaction.setAmount("$10.00");
        findTransaction.clickFindTransactionByAmount();

        WebElement errorMessage = driver.findElement(By.id("amountError"));
        Assert.assertEquals(errorMessage.getText(), "Invalid amount");
    }

    @Test
    public void testRandomAmount(){
        findTransaction.setAmount("8236487236");
        findTransaction.clickFindTransactionByAmount();

        boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tbody[@id='transactionBody']/tr/td[1]")));
        Assert.assertTrue(result);
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
