package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateContactInfoFactory {

    WebDriver driver;

    public UpdateContactInfoFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@value='Update Profile']")
    WebElement updateProfileButton;

    @FindBy(xpath = "//a[@href='updateprofile.htm']")
    WebElement updateProfilePage;

    public void clickUpdateProfile(){
        updateProfileButton.click();
    }

    public void clickUpdatePage(){
        updateProfilePage.click();
    }
}
