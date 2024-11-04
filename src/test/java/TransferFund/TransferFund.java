package TransferFund;

import BaseSuite.BaseTestSuite;
import ObjectRepository.AccountOverviewFactory;
import ObjectRepository.LoginPageFactory;
import ObjectRepository.TransferFundsFactory;
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
import java.util.List;

public class TransferFund {
    WebDriver driver;
    AccountOverviewFactory account;
    TransferFundsFactory transfer;
    String fromAccount;
    String toAccount;
    String fromAccountBalance;
    String toAccountBalance;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        account = new AccountOverviewFactory(driver);
        transfer = new TransferFundsFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='overview.htm']")).click();
        Thread.sleep(1500);
        List<String> accountNumbers = account.getAccountNumbers();
        fromAccount = accountNumbers.get(0);
        toAccount = accountNumbers.get(1);

        List<String> accountBalance = account.getAccountBalance();
        fromAccountBalance = accountBalance.get(0);
        toAccountBalance = accountBalance.get(1);
    }

    @Test
    public void testValidTransferFund() throws InterruptedException {
        transfer.clickTransferFundPage();
        Thread.sleep(1000);
        transfer.setAmount("10");
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.clickTransferButton();

        WebElement resultH1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='showResult']/h1[@class='title']")));
        Assert.assertEquals(resultH1.getText(), "Transfer Complete!");

        WebElement resultAmount = driver.findElement(By.xpath("//span[@id='amountResult']"));
        Assert.assertEquals(resultAmount.getText(), "$10.00");

        WebElement fromResult = driver.findElement(By.xpath("//span[@id='fromAccountIdResult']"));
        Assert.assertEquals(fromResult.getText(), fromAccount);

        WebElement toResult = driver.findElement(By.xpath("//span[@id='toAccountIdResult']"));
        Assert.assertEquals(toResult.getText(), toAccount);

        //a[@href='activity.htm?id=13455']//parent::td//following-sibling::td[1]
        // Navigating to Accounts Overview
        driver.findElement(By.xpath("//a[@href='overview.htm']")).click();
        Thread.sleep(1000);

        String cleanedBalance = fromAccountBalance.replaceAll("[$,]", "");
        double balance = Double.parseDouble(cleanedBalance);
        balance = balance - 10;
        String updatedBalance = "$" + String.format("%.2f", balance);

        String getBalanceTemplate = "//a[@href='activity.htm?id="+fromAccount+"']//parent::td//following-sibling::td[1]";
        WebElement fromBalance = driver.findElement(By.xpath(getBalanceTemplate));
        Assert.assertEquals(fromBalance.getText(), updatedBalance);

        cleanedBalance = toAccountBalance.replaceAll("[$,]", "");
        balance = Double.parseDouble(cleanedBalance);
        balance = balance + 10;
        updatedBalance = "$" + String.format("%.2f", balance);

        getBalanceTemplate = "//a[@href='activity.htm?id="+toAccount+"']//parent::td//following-sibling::td[1]";
        WebElement toBalance = driver.findElement(By.xpath(getBalanceTemplate));
        Assert.assertEquals(toBalance.getText(), updatedBalance);
    }

    @Test
    public void testInvalidTransferFund() throws InterruptedException {
        transfer.clickTransferFundPage();
        Thread.sleep(1000);
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.clickTransferButton();

        WebElement transferError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='showError']//p[@class='error']")));
        Assert.assertEquals(transferError.getText(), "An internal error has occurred and has been logged.");
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

}
