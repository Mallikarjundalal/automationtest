package com.automationFramev1.testCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.automationFramev1.pageObjects.HomePage;

public class TC_imagearrivals_005 extends Baseclass {
	/*1) Open the browser
	2) Enter the URL “http://practice.automationtesting.in/”
	3) Click on Shop Menu
	4) Now click on Home menu button
	5) Test whether the Home page has Three Arrivals only
	6) The Home page must contains only three Arrivals
	7) Now click the image in the Arrivals
	8) Test whether it is navigating to next page where the user can add that book into his basket.
	9) Image should be clickable and shoul navigate to next page where user can add that book to his basket*/
	@Test
	public void imagearrivals(){
	     try {

	            // Step 3: Click on Shop Menu
	        	HomePage hm=new HomePage(driver);
	            WebElement shopMenu = wait.until(ExpectedConditions.elementToBeClickable(hm.shopButton));
	            shopMenu.click();
	            logger.info("Clicked on Shop Menu");

	            // Step 4: Click on Home menu button
	            WebElement homeMenu = wait.until(ExpectedConditions.elementToBeClickable(hm.homeButton));
	            homeMenu.click();
	            System.out.println("Clicked on Home Menu");

	           

	            // Step 5: Click on an image in the arrivals
	           
	            driver.findElement(By.xpath("//img[@title='Selenium Ruby']")).click();
	           
	            System.out.println("Clicked on the first arrival image");

	            // Step 6: Validate navigation to the next page
	            String expectedUrl = "https://practice.automationtesting.in/product/selenium-ruby/";
	            wait.until(ExpectedConditions.urlToBe(expectedUrl));

	            if (driver.getCurrentUrl().equals(expectedUrl)) {
	                System.out.println("Test Passed: Navigated to the correct product page.");
	            } else {
	                System.out.println("Test Failed: Navigation to the product page failed.");
	            }

	            // Step 7: Add the book to the basket
	            WebElement addToBasketButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to basket']")));
	            addToBasketButton.click();
	            System.out.println("Clicked on 'Add to Basket' button");

	            // Step 8: Validate the book is added to the basket
	            WebElement basketItemCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'has been added to your basket')]")));
	            String itemCount = basketItemCount.getText();
	            System.out.println(itemCount);

	            if (itemCount.equals("VIEW BASKET")) {
	                System.out.println("Test Passed: Book added to the basket successfully.");
	            } else {
	                System.out.println("Test Failed: Book was not added to the basket.");
	            }

	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        } finally {
	            // Step 10: Close the browser
	            driver.quit();
	        }
	    }
	}


