package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.Basetest;
import pages.HomePage;


public class SearchValidProductTest extends Basetest{

	    @Test
	    public void searchValidProduct() {

	        HomePage homePage = new HomePage(driver);
	        homePage.searchProduct("iPhone");

	        Assert.assertTrue(homePage.isProductFound("iPhone"), "Product not found!");
	        Reporter.log("SearchvalidProductTest executed successfully", true);
	        }
	}



