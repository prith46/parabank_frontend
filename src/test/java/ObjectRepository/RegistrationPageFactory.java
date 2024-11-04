package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class RegistrationPageFactory {
    public WebDriver driver;

    public RegistrationPageFactory(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "customer.firstName")
    WebElement firstName;

    @FindBy(id = "customer.lastName")
    WebElement lastName;

    @FindBy(id = "customer.address.street")
    WebElement address;

    @FindBy(id = "customer.address.city")
    WebElement city;

    @FindBy(id = "customer.address.state")
    WebElement state;

    @FindBy(id = "customer.address.zipCode")
    WebElement zipCode;

    @FindBy(id = "customer.phoneNumber")
    WebElement phoneNumber;

    @FindBy(id = "customer.ssn")
    WebElement ssn;

    @FindBy(id = "customer.username")
    WebElement userName;

    @FindBy(id = "customer.password")
    WebElement password;

    @FindBy(id = "repeatedPassword")
    WebElement confirmPassword;

    @FindBy(xpath = "//input[@value='Register']")
    WebElement registerButton;

    public void setFirstName(String userFirstName){
        firstName.clear();
        firstName.sendKeys(userFirstName);
    }

    public void setLastName(String userLastName){
        lastName.clear();
        lastName.sendKeys(userLastName);
    }

    public void setAddress(String userAddress){
        address.clear();
        address.sendKeys(userAddress);
    }

    public void setCity(String userCity){
        city.clear();
        city.sendKeys(userCity);
    }

    public void setState(String userState){
        state.clear();
        state.sendKeys(userState);
    }

    public void setZipCode(String userZipCode){
        zipCode.clear();
        zipCode.sendKeys(userZipCode);
    }

    public void setPhoneNumber(String userPhoneNumber){
        phoneNumber.clear();
        phoneNumber.sendKeys(userPhoneNumber);
    }

    public void setSsn(String userSsn){
        ssn.sendKeys(userSsn);
    }

    public void setUserName(String userName1){
        userName.sendKeys(userName1);
    }

    public void setPassword(String password1){
        password.sendKeys(password1);
    }

    public void setConfirmPassword(String confirmPassword1){
        confirmPassword.sendKeys(confirmPassword1);
    }

    public void clickRegister(){
        registerButton.click();
    }

    public String getFirstName(){
        return firstName.getAttribute("value");
    }

    public String getLastName(){
        return lastName.getAttribute("value");
    }

    public String getAddress(){
        return address.getAttribute("value");
    }

    public String getCity(){
        return city.getAttribute("value");
    }

    public String getState(){
        return state.getAttribute("value");
    }

    public String getZipCode(){
        return zipCode.getAttribute("value");
    }

    public String getPhoneNumber(){
        return phoneNumber.getAttribute("value");
    }

}
