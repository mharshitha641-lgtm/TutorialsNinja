package tests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.Basetest;
import pages.HomePage;


public class SearchInvalidProductTest extends Basetest{


	    @Test
	    public void searchInvalidProduct() {

	        HomePage homePage = new HomePage(driver);
	        homePage.searchProduct("XYZ123");

	        Assert.assertFalse(homePage.isProductFound("XYZ123"), "Product should not be found!");
	        Reporter.log("SearchInvalidProductTest executed successfully", true);
	        
	    }
	}

	

