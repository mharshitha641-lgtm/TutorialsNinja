package tests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.Basetest;
import pages.HomePage;

public class AddProductToWishlistTest extends Basetest{
	

	    @Test
	    public void addProductToWishlist() throws InterruptedException {

	        HomePage homePage = new HomePage(driver);
	        homePage.searchProduct("Canon EOS 5D");
	        homePage.addProductToWishlist("Canon EOS 5D");

	        Thread.sleep(2000); // wait for success message
	        String message = homePage.getSuccessMessage();
	        Assert.assertTrue(message.contains("Canon EOS 5D"), "Wishlist message not displayed correctly");

	        Reporter.log("AddProductToWishlistTest executed successfully", true);
	    }
	}



