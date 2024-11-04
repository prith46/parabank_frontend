package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountOverviewFactory {

    WebDriver driver;

    public AccountOverviewFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@id='accountTable']/tbody//tr//a")
    List<WebElement> accountNumbers;

    @FindBy(xpath = "//table[@id='accountTable']/tbody//tr//td[2]")
    List<WebElement> accountBalances;

    public List<String> getAccountNumbers(){
        List<String> accountNumbersList = new ArrayList<>();
        for(WebElement accountNumber: accountNumbers)
            accountNumbersList.add(accountNumber.getText());
        return accountNumbersList;
    }

    public List<String> getAccountBalance(){
        List<String> accountBalanceList = new ArrayList<>();
        for(WebElement accountBalance : accountBalances)
            accountBalanceList.add(accountBalance.getText());
        return accountBalanceList;
    }
}
