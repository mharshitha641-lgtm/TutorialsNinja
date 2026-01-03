package tests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.Basetest;
import pages.HomePage;

public class AddProductToCartTest extends Basetest{
	
	    @Test
	    public void addProductToCart() throws InterruptedException {
	       

	        HomePage homePage = new HomePage(driver);
	        homePage.searchProduct("MacBook");
	        homePage.addProductToCart("MacBook");

	        Thread.sleep(2000); // wait for success message
	        String message = homePage.getSuccessMessage();
	        Assert.assertTrue(message.contains("MacBook"), "Cart message not displayed correctly");
	        Reporter.log("AddProductToCartTest executed successfully", true);
	    
	    }
	}



