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

public class ValidFindTransaction {

    WebDriver driver;
    FindTransactionsFactory findTransaction;
    LoginPageFactory login;
    WebDriverWait wait;
    String transactionID;
    String transactionDate;
    String transactionAmount;

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
        getTransactionDetails();
    }

    public void getTransactionDetails(){
        driver.findElement(By.xpath("//a[@href='overview.htm']")).click();
        // Find the first account
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountTable']//tr[1]//a"))).click();

        // Go inside the first transaction
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='transactionTable']//tr[1]//a"))).click();

        // Get transaction details
        transactionID = driver.findElement(By.xpath("//div[@id='rightPanel']//tr[1]//td[2]")).getText();
        transactionDate = driver.findElement(By.xpath("//div[@id='rightPanel']//tr[2]//td[2]")).getText();
        transactionAmount = driver.findElement(By.xpath("//div[@id='rightPanel']//tr[5]//td[2]")).getText();
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='findtrans.htm']")).click();
        Thread.sleep(1000);
    }

    @Test
    public void testFindByID(){
        findTransaction.setTransactionId(transactionID);
        findTransaction.clickFindTransactionById();

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error']")));
        Assert.assertEquals(error.getText(), "An internal error has occurred and has been logged.");
    }

    @Test
    public void testFindByDate(){
        findTransaction.setTransactionDate(transactionDate);
        findTransaction.clickFindTransactionByDate();

        WebElement transDate = driver.findElement(By.xpath("//tbody[@id='transactionBody']//tr//td[1]"));
        Assert.assertEquals(transDate.getText(), transactionDate);

        boolean isPresent;
        WebElement transaction = driver.findElement(By.xpath("//tbody[@id='transactionBody']//tr//td[2]/a"));
        isPresent = transaction.isDisplayed();
        Assert.assertTrue(isPresent);
    }

    @Test
    public void testFindByDataRange(){
        String[] dateParts = transactionDate.split("-");
        String month = dateParts[0];
        String year = dateParts[2];

        String fromDate = month + "-01-" + year;
        String toDate = month + "-31-" + year;

        System.out.println(fromDate + "    " + toDate);

        findTransaction.setFromDate(fromDate);
        findTransaction.setToDate(toDate);
        findTransaction.clickFindTransactionByDateRange();

        boolean isPresent;
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='transactionBody']/tr/td[1]")));
        isPresent = result.isDisplayed();
        Assert.assertTrue(isPresent);
    }

    @Test
    public void testFindByAmount(){
        transactionAmount = transactionAmount.replaceAll("[$]","").split("\\.")[0];
        findTransaction.setAmount(transactionAmount);
        findTransaction.clickFindTransactionByAmount();

        boolean isPresent;
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='transactionBody']/tr/td[1]")));
        isPresent = result.isDisplayed();
        Assert.assertTrue(isPresent);
    }

    @AfterClass
    public void afterClass(){
        // Logout
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

}
