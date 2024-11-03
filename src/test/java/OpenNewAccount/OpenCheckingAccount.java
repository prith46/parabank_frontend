package OpenNewAccount;

import BaseSuite.BaseTestSuite;
import ObjectRepository.LoginPageFactory;
import ObjectRepository.OpenNewAccountFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpenCheckingAccount extends BaseTestSuite{

    private static final Logger log = LoggerFactory.getLogger(OpenCheckingAccount.class);
    WebDriver driver;
    OpenNewAccountFactory openAccount;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        openAccount = new OpenNewAccountFactory(driver);
    }

    @Test
    public void testOpenCheckingAccount() throws InterruptedException {

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("tedfmosbyist");
        login.setPassWord("abc");
        login.clickLoginButton();

        driver.findElement(By.xpath("//a[@href='openaccount.htm']")).click();

        openAccount.clickAccountType("checking");
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
}
