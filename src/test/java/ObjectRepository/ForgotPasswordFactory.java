package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordFactory {

    WebDriver driver;

    public ForgotPasswordFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "address.street")
    WebElement address;

    @FindBy(id = "address.city")
    WebElement city;

    @FindBy(id = "address.state")
    WebElement state;

    @FindBy(id = "address.zipCode")
    WebElement zipCode;

    @FindBy(id = "ssn")
    WebElement ssn;

    @FindBy(xpath = "//input[@value='Find My Login Info']")
    WebElement findInfoButton;

    public void setFirstName(String firstName1){
        firstName.sendKeys(firstName1);
    }

    public void setLastName(String lastName1){
        lastName.sendKeys(lastName1);
    }

    public void setAddress(String address1){
        address.sendKeys(address1);
    }

    public void setCity(String addressCity){
        city.sendKeys(addressCity);
    }

    public void setState(String addressState){
        state.sendKeys(addressState);
    }

    public void setZipCode(String addressZipCode){
        zipCode.sendKeys(addressZipCode);
    }

    public void setSsn(String ssn1){
        ssn.sendKeys(ssn1);
    }

    public void clickFindInfo(){
        findInfoButton.click();
    }
}
