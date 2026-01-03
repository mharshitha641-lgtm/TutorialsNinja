package pages;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Basetest;


public class SearchInvalidProductTest extends Basetest{


	    @Test
	    public void searchInvalidProduct() {

	        HomePage homePage = new HomePage(driver);
	        homePage.searchProduct("XYZ123");

	        Assert.assertFalse(homePage.isProductFound("XYZ123"), "Product should not be found!");

	        
	    }
	}

	

