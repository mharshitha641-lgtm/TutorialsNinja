package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage {


    WebDriver driver;

    // Search field and button
    @FindBy(name = "search")
    WebElement searchField;

    @FindBy(css = "button.btn.btn-default.btn-lg")
    WebElement searchButton;

    // Product titles in search results
    @FindBy(css = "div.caption h4 a")
    List<WebElement> productTitles;

    // Success messages
    @FindBy(css = ".alert-success")
    WebElement successMessage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void searchProduct(String productName) {
        searchField.clear();
        searchField.sendKeys(productName);
        searchButton.click();
    }

    public boolean isProductFound(String productName) {
        return productTitles.stream()
                .anyMatch(e -> e.getText().equalsIgnoreCase(productName));
    }

    public void addProductToCart(String productName) {
        for (WebElement product : productTitles) {
            if (product.getText().equalsIgnoreCase(productName)) {
                // Click product to open product detail page
                product.click();

                // Now click "Add to Cart" button on product detail page
                WebElement addToCartBtn = driver.findElement(By.id("button-cart"));
                addToCartBtn.click();
                break;
            }
        }
    }


    public void addProductToWishlist(String productName) {
        for (WebElement product : productTitles) {
            if (product.getText().equalsIgnoreCase(productName)) {
                // Click product to go to product detail page
                product.click();

                // Now click "Add to Wish List" button on product page
                WebElement wishlistBtn = driver.findElement(
                        By.cssSelector("button[data-original-title='Add to Wish List']")
                );
                wishlistBtn.click();
                break;
            }
        }
    }


    public String getSuccessMessage() {
        try {
            return successMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}



