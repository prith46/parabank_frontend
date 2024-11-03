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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidRequestLoan extends BaseTestSuite{

    WebDriver driver;
    RequestLoanFactory loan;
    String mainAccountNumber;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        loan = new RequestLoanFactory(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @BeforeMethod
    public void beforeMethod(){

        WebElement getAccountNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountTable']//tr[1]//a")));
        mainAccountNumber = getAccountNumber.getText();

        driver.findElement(By.xpath("//a[@href='requestloan.htm']")).click();
    }

    @Test
    public void testValidLoanRequest() throws InterruptedException {

        loan.setLoadAmount("100");
        loan.setDownPayment("10");
        loan.setFromAccount(mainAccountNumber);
        Thread.sleep(1000);
        loan.clickApplyNow();

        WebElement welcomeText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loanRequestApproved']/p")));
        Assert.assertEquals(welcomeText.getText(), "Congratulations, your loan has been approved.");

        WebElement loanAccountNumber = driver.findElement(By.xpath("//a[@id='newAccountId']"));
        boolean isAccountNumberVisible = loanAccountNumber.isDisplayed();
        Assert.assertTrue(isAccountNumberVisible);
    }
}
