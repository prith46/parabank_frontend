package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountFactory {

    WebDriver driver;

    public OpenNewAccountFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@id='type']")
    WebElement accountType;

    @FindBy(xpath = "//select[@id='fromAccountId']")
    WebElement fromAccount;

    @FindBy(xpath = "//input[@value='Open New Account']")
    WebElement newAccountButton;

    public void clickAccountType(String userAccountType){
        Select select = new Select(accountType);
        if(userAccountType.equalsIgnoreCase("checking"))
            select.selectByValue("0");
        else
            select.selectByValue("1");
    }

    public void clickFromAccount(String accountNumber){
        Select selectAccount = new Select(fromAccount);
        selectAccount.selectByVisibleText(accountNumber);
    }

    public void clickNewAccountButton(){
        newAccountButton.click();
    }
}
