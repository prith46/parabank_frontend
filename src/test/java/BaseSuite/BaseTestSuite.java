package BaseSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

public class BaseTestSuite {

    protected static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver == null) {
            driver = new ChromeDriver();
            driver.get("https://parabank.parasoft.com/parabank/admin.htm");
            driver.manage().window().maximize();
            driver.findElement(By.xpath("//button[@value='CLEAN']")).click();
        }
        return driver;
    }

    @AfterSuite
    public void afterSuite(){
//        driver.quit();
    }

}
