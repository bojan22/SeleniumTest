package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class HomepagePage {
    WebDriver driver;
    WebDriverWait wait;
    WebElement usernameFiled;
    WebElement passwordFiled;
    WebElement loginButton;
    WebElement alertLogoutMassage;
    WebElement displayedPageTitle;

    public HomepagePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    //getters and setters

    public WebElement getUsernameFiled() {
        return driver.findElement(By.id("username"));
    }
    public WebElement getPasswordFiled() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.className("radius"));
    }
    public WebElement getAlertMassage() {
        return driver.findElement(By.id("flash"));
    }

    public WebElement getDisplayedPageTitle() {
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/h2"));
    }
    //---------------methods

    public void inputTextInUsernameFiled(String username){
        this.getUsernameFiled().clear();
        this.getUsernameFiled().sendKeys(username);
    }
    public void inputTextInPasswordFiled(String password){
        this.getPasswordFiled().clear();
        this.getPasswordFiled().sendKeys(password);
    }
    public void clickOnLoginButton(){
        this.getLoginButton().click();
    }



}
