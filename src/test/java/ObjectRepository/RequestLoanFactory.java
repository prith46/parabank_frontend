package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RequestLoanFactory {

    WebDriver driver;

    public RequestLoanFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "amount")
    WebElement loadAmount;

    @FindBy(id = "downPayment")
    WebElement downPayment;

    @FindBy(id = "fromAccountId")
    WebElement fromAccount;

    @FindBy(xpath = "//input[@value='Apply Now']")
    WebElement applyNow;

    public void setLoadAmount(String userLoanAmount){
        loadAmount.sendKeys(userLoanAmount);
    }

    public void setDownPayment(String userDownPayment){
        downPayment.sendKeys(userDownPayment);
    }

    public void setFromAccount(String userFromAccount){
        Select select = new Select(fromAccount);
        select.selectByValue(userFromAccount);
    }

    public void clickApplyNow(){
        applyNow.click();
    }
}
