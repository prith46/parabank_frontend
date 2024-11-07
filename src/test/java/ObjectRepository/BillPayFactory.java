package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BillPayFactory {

    WebDriver driver;

    public BillPayFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='payee.name']")
    WebElement payeeName;

    @FindBy(xpath = "//input[@name='payee.address.street']")
    WebElement address;

    @FindBy(xpath = "//input[@name='payee.address.city']")
    WebElement city;

    @FindBy(xpath = "//input[@name='payee.address.state']")
    WebElement state;

    @FindBy(xpath = "//input[@name='payee.address.zipCode']")
    WebElement zipCode;

    @FindBy(xpath = "//input[@name='payee.phoneNumber']")
    WebElement phoneNumber;

    @FindBy(xpath = "//input[@name='payee.accountNumber']")
    WebElement account;

    @FindBy(xpath = "//input[@name='verifyAccount']")
    WebElement verifyAccount;

    @FindBy(xpath = "//input[@name='amount']")
    WebElement amount;

    @FindBy(xpath = "//select[@name='fromAccountId']")
    WebElement fromAccount;

    @FindBy(xpath = "//input[@value='Send Payment']")
    WebElement sendPayment;

    public void setPayeeName(String name){
        payeeName.sendKeys(name);
    }

    public void setAddress(String payeeAddress){
        address.sendKeys(payeeAddress);
    }

    public void setCity(String payeeCity){
        city.sendKeys(payeeCity);
    }

    public void setState(String payeeState){
        state.sendKeys(payeeState);
    }

    public void setZipCode(String payeeZipCode){
        zipCode.sendKeys(payeeZipCode);
    }

    public void setPhoneNumber(String payeePhoneNumber){
        phoneNumber.sendKeys(payeePhoneNumber);
    }

    public void setAccount(String payeeAccount){
        account.sendKeys(payeeAccount);
    }

    public void setVerifyAccount(String payeeVerifyAccount){
        verifyAccount.sendKeys(payeeVerifyAccount);
    }

    public void setAmount(String payeeAmount){
        amount.sendKeys(payeeAmount);
    }

    public void setFromAccount(String accountNumber){
        Select select = new Select(fromAccount);
        select.selectByValue(accountNumber);
    }

    public void clickSendPayment(){
        sendPayment.click();
    }

}
