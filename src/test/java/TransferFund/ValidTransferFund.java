package TransferFund;

import BaseSuite.BaseTestSuite;
import ObjectRepository.AccountOverviewFactory;
import ObjectRepository.LoginPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class ValidTransferFund {
    WebDriver driver;
    AccountOverviewFactory account;

    @BeforeClass
    public void beforeClass(){
        driver = BaseTestSuite.getDriver();
        account = new AccountOverviewFactory(driver);
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPageFactory login = new LoginPageFactory(driver);
        login.setUserName("teddmosby");
        login.setPassWord("abc");
        login.clickLoginButton();
    }


}
