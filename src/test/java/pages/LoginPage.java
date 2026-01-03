package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.cssSelector("input[type='submit']");
    private By warningMessage = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToLoginPage() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    public String getWarningMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
