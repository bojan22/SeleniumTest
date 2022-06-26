package Test;

import Base.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

import java.util.*;

public class Tests extends BaseTest {

    @BeforeMethod
    public void pageSetup(){
        String homepageURL = excel.getStringData("URLs", 1,1);
        driver.manage().window().maximize();
        driver.navigate().to(homepageURL);
    }

    @Test (priority = 10)
    public void successfulLoginTest(){
        //elements for successful login
        String expectedMassage = "You logged into a secure area!\n" + "×";
        String expectedLoginURL = "https://the-internet.herokuapp.com/secure";
        String displayedPageTitle = "Secure Area";
        String validUsername = excel.getStringData("Login", 1,0);
        String validPassword = excel.getStringData("Login", 1,1);

        //correct login steps
        homepagePage.inputTextInUsernameFiled(validUsername);
        homepagePage.inputTextInPasswordFiled(validPassword);
        homepagePage.clickOnLoginButton();

        //verify correct massage is displayed when user is logged in
        Assert.assertEquals(loginPage.getAlertLogInMassage().getText(), expectedMassage);
        //verify logout button is displayed
        Assert.assertTrue(loginPage.getLogoutButton().isDisplayed());
        //verify correct url is displayed when user is logged in
        wait.until(ExpectedConditions.urlToBe(expectedLoginURL));
        Assert.assertEquals(driver.getCurrentUrl(), expectedLoginURL);
        //verify displayed page title  is correct
        Assert.assertEquals(homepagePage.getDisplayedPageTitle().getText(), displayedPageTitle);


    }

    @Test (priority = 20)
    public void logOutTest(){
        //elements for logout test
        //String expectedBaseURL = "https://the-internet.herokuapp.com/login";
        String expectedMassage = "You logged out of the secure area!\n" + "×";
        String homepageURL = excel.getStringData("URLs", 1,1);
        String validUsername = excel.getStringData("Login", 1,0);
        String validPassword = excel.getStringData("Login", 1,1);


        //test steps
        homepagePage.inputTextInUsernameFiled(validUsername);
        homepagePage.inputTextInPasswordFiled(validPassword);
        homepagePage.clickOnLoginButton();
        loginPage.clickOnLogOUTButton();
        //verify homepage url is displayed when user logged out
        wait.until(ExpectedConditions.urlToBe(homepageURL));
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        //verify correct massage is displayed
        Assert.assertEquals(homepagePage.getAlertMassage().getText(), expectedMassage);
    }

    @Test (priority = 30)
    public void invalidUsernameValidPasswordTest(){
        //test elements
        String invalidUsername = "bojan";
        String expectedMassage = "Your username is invalid!\n" + "×";
        String validPassword = excel.getStringData("Login", 1,1);

        //test steps
        homepagePage.inputTextInUsernameFiled(invalidUsername);
        homepagePage.inputTextInPasswordFiled(validPassword);
        homepagePage.clickOnLoginButton();
        //verify correct massage is displayed
        Assert.assertEquals(homepagePage.getAlertMassage().getText(), expectedMassage);
        //verify that login button is displayed
        visibilityWait(homepagePage.getLoginButton());
        Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
        //verify that logout button is NOT displayed
        boolean check = false;
        try {
            check = driver.findElement(By.cssSelector(".button.secondary.radius")).isDisplayed();
        }catch (Exception e){

        }
        Assert.assertFalse(check);
    }

    @Test (priority = 40)
    public void validUsernameInvalidPasswordTest(){
        //test elements
        String expectedMassage = "Your password is invalid!\n" + "×";
        String validUsername = excel.getStringData("Login", 1,0);
        String invalidPassword = excel.getStringData("Login",1,2);


        //test steps
        homepagePage.inputTextInUsernameFiled(validUsername);
        homepagePage.inputTextInPasswordFiled(invalidPassword);
        homepagePage.clickOnLoginButton();

        //verify correct massage is displayed
        Assert.assertEquals(homepagePage.getAlertMassage().getText(), expectedMassage);

        //verify that login button is displayed
        visibilityWait(homepagePage.getLoginButton());
        Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
    }
    @Test (priority = 50)
    public void emptyUsernameAndPasswordFiledTest(){
        //elements for test
        String expectedMassage = "Your username is invalid!\n" + "×";

        //test steps
        homepagePage.inputTextInUsernameFiled("");
        homepagePage.inputTextInPasswordFiled("");
        homepagePage.clickOnLoginButton();

        //verify correct massage is displayed
        Assert.assertEquals(homepagePage.getAlertMassage().getText(), expectedMassage);

        //verify user is not logged in and logout button is not displayed
        boolean check = false;
        try {
            check = driver.findElement(By.cssSelector(".button.secondary.radius")).isDisplayed();
        }catch (Exception e){

        }
        Assert.assertFalse(check);
    }

    @Test (priority = 60)
    public void validCredentialsToUpperCaseTest(){
        //elements for test
        String expectedMassage = "Your username is invalid!\n" + "×";
        String validUsername = excel.getStringData("Login", 1,0);
        String validPassword = excel.getStringData("Login", 1,1);

        //test steps
        homepagePage.inputTextInUsernameFiled(validUsername.toUpperCase());
        homepagePage.inputTextInPasswordFiled(validPassword.toUpperCase());
        homepagePage.clickOnLoginButton();

        //verify correct massage is displayed
        Assert.assertEquals(homepagePage.getAlertMassage().getText(), expectedMassage);

        //verify login button is displayed and user is not logged in
        visibilityWait(homepagePage.getLoginButton());
        Assert.assertTrue(homepagePage.getLoginButton().isDisplayed());
    }


}
