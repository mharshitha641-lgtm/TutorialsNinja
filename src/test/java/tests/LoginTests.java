package tests;

import base.Basetest;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends Basetest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupTest() {
        loginPage = new LoginPage(driver);
        // Clear cookies and go to login page fresh
        driver.manage().deleteAllCookies();
        loginPage.goToLoginPage();
    }

    @Test
    public void validLoginTest() {

        loginPage.login("harshitham1732001@gmail.com", "Test@1234");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("My Account"));

        Assert.assertTrue(driver.getTitle().contains("My Account"),
                "User should be redirected to My Account page");

        Reporter.log("Valid login test executed successfully", true);
    }


    @Test
    public void invalidLoginTest() {
        loginPage.login("wrong@example.com", "wrongpass");

        String warning = loginPage.getWarningMessage();
        Assert.assertTrue(warning.contains("Warning: No match for E-Mail Address and/or Password."),
                "Error message should be displayed for invalid credentials");

        Reporter.log("Invalid login test executed successfully", true);
    }

    @Test
    public void loginWithoutPasswordTest() {
        loginPage.login("harshitham1732001@gmail.com", "");

        String warning = loginPage.getWarningMessage();
        Assert.assertTrue(warning.contains("Warning:"), "Warning should be displayed for empty password");

        Reporter.log("Login without password test executed successfully", true);
    }
}



