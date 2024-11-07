package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FindTransactionsFactory {

    WebDriver driver;

    public FindTransactionsFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@id='accountId']")
    WebElement accountId;

    @FindBy(id = "transactionId")
    WebElement transactionId;

    @FindBy(id = "findById")
    WebElement findTransactionById;

    @FindBy(xpath = "//input[@id='transactionDate']")
    WebElement transactionDate;

    @FindBy(id = "findByDate")
    WebElement findTransctionByDate;

    @FindBy(id = "fromDate")
    WebElement fromDate;

    @FindBy(id = "toDate")
    WebElement toDate;

    @FindBy(id = "findByDateRange")
    WebElement findTransactionByDateRange;

    @FindBy(xpath = "//input[@id='amount']")
    WebElement amount;

    @FindBy(id = "findByAmount")
    WebElement findTransactionByAmount;

    public void setAccountId(String accountNumber){
        Select select = new Select(accountId);
        select.selectByValue(accountNumber);
    }

    public void setTransactionId(String userTransactionID){
        transactionId.sendKeys(userTransactionID);
    }

    public void clickFindTransactionById(){
        findTransactionById.click();
    }

    public void setTransactionDate(String userTransactionDate){
        transactionDate.sendKeys(userTransactionDate);
    }

    public void clickFindTransactionByDate(){
        findTransctionByDate.click();
    }

    public void setFromDate(String userFromDate){
        fromDate.sendKeys(userFromDate);
    }

    public void setToDate(String userToDate){
        toDate.sendKeys(userToDate);
    }

    public void clickFindTransactionByDateRange(){
        findTransactionByDateRange.click();
    }

    public void setAmount(String userAmount){
        amount.sendKeys(userAmount);
    }

    public void clickFindTransactionByAmount(){
        findTransactionByAmount.click();
    }
}
