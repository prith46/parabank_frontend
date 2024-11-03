package OpenNewAccount;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import ObjectRepository.OpenNewAccountFactory;
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

public class OpenAccount extends BaseTestSuite{

    WebDriver driver;
    OpenNewAccountFactory openAccount;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        openAccount = new OpenNewAccountFactory(driver);
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    public void testOpenAccount(String accountType) throws InterruptedException {

        driver.findElement(By.xpath("//a[@href='openaccount.htm']")).click();

        openAccount.clickAccountType(accountType);
        Thread.sleep(1000);
        openAccount.clickNewAccountButton();

        WebElement tempElement = driver.findElement(By.id("newAccountId"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.visibilityOf(tempElement));

        String accountNumber = tempElement.getText();
        String accountXpath = "//a[@href='activity.htm?id="+accountNumber+"']";

        // Go to Accounts Overview Page
        driver.findElement(By.xpath("//a[@href='overview.htm']")).click();
        String accountBalanceXpath = accountXpath+ "//parent::td//following-sibling::td";
        WebElement accountId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(accountXpath)));
        WebElement accountBalance = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(accountBalanceXpath)));

        Assert.assertEquals(accountId.getText(), accountNumber);
        Assert.assertEquals(accountBalance.getText(), "$100.00");
    }

    @Test
    public void testOpenCheckingAccount() throws InterruptedException {
        testOpenAccount("checking");
    }

    @Test
    public void testOpenSavingsAccount() throws InterruptedException {
        testOpenAccount("savings");
    }

    @AfterClass
    public void afterClass(){
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }
}
