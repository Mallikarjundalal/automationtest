package com.automationFramev1.testCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.automationFramev1.pageObjects.HomePage;

import java.util.List;

public class TC_Homepagethreearrivals_004 extends Baseclass {
	/*
	 Homepage with three arrivals only 
	1) Open the browser
	2) Enter the URL “http://practice.automationtesting.in/”
	3) Click on Shop Menu
	4) Now click on Home menu button
	5) Test whether the Home page has Three Arrivals only
	6) The Home page must contains only three Arrivals*/
	
	
	@Test
   public void Homepagethreearrivals() {
	        try {

	            // Step 3: Click on Shop Menu
	        	HomePage hm=new HomePage(driver);
	            WebElement shopMenu = wait.until(ExpectedConditions.elementToBeClickable(hm.shopButton));
	            shopMenu.click();
	            logger.info("Clicked on Shop Menu");

	            // Step 4: Now click on Home menu button
	            WebElement homeMenu = wait.until(ExpectedConditions.elementToBeClickable(hm.homeButton));
	            homeMenu.click();
	            logger.info("Clicked on Home Menu");

	            // Step 5: Test whether the Home page has Three Arrivals only
	            List<WebElement> arrivals = driver.findElements(By.xpath("//a[text()='Add to basket']"));
	            int arrivalsCount = arrivals.size();
	            logger.info("Number of Arrivals on the Home page: " + arrivalsCount);

	            // Step 6: Validate the arrivals count
	            if (arrivalsCount == 3) {
	                System.out.println("Test Passed: Home page contains exactly three arrivals.");
	            } else {
	                System.out.println("Test Failed: Home page contains " + arrivalsCount + " arrivals instead of three.");
	            }
	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	}



