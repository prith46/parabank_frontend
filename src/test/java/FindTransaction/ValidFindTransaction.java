package FindTransaction;

import BaseSuite.BaseTestSuite;
import ObjectRepository.FindTransactionsFactory;
import ObjectRepository.LoginPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidFindTransaction {

    WebDriver driver;
    FindTransactionsFactory findTransaction;
    LoginPageFactory login;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        findTransaction = new FindTransactionsFactory(driver);
        login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.findElement(By.xpath("//a[@href='findtrans.htm']")).click();
    }

    @Test
    public void testFindByID(){
        findTransaction.setAccountId("13455");
        findTransaction.setTransactionId("1153");
        findTransaction.clickFindTransactionById();
    }

}
