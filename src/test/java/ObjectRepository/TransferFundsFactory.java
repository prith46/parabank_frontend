package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsFactory {

    WebDriver driver;

    public TransferFundsFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(partialLinkText = "Transfer")
    WebElement transferFundPage;

    @FindBy(xpath = "//input[@id='amount']")
    WebElement amount;

    @FindBy(xpath = "//select[@id='fromAccountId']")
    WebElement fromAccount;

    @FindBy(xpath = "//select[@id='toAccountId']")
    WebElement toAccount;

    @FindBy(xpath = "//input[@value='Transfer']")
    WebElement transferButton;

    public void setAmount(String userAmount){
        amount.sendKeys(userAmount);
    }

    public void setFromAccount(String userFromAccount){
        Select select = new Select(fromAccount);
        select.selectByValue(userFromAccount);
    }

    public void setToAccount(String userToAccount){
        Select select = new Select(toAccount);
        select.selectByValue(userToAccount);
    }

    public void clickTransferButton(){
        transferButton.click();
    }

    public void clickTransferFundPage(){
        transferFundPage.click();
    }
}
