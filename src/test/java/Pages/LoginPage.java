package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    WebElement logoutButton;
    WebElement alertMassage;



    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    //----getters and setters

    public WebElement getLogoutButton() {
        return driver.findElement(By.cssSelector(".button.secondary.radius"));
    }
    public WebElement getAlertLogInMassage(){
        return driver.findElement(By.id("flash"));
    }


    //-------methods
    public void clickOnLogOUTButton(){
        this.getLogoutButton().click();
    }
}
