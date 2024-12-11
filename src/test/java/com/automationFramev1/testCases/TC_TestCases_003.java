package com.automationFramev1.testCases;




import org.testng.annotations.Test;

import com.automationFramev1.pageObjects.HomePage;

public class TC_TestCases_003 extends Baseclass {
	

	@Test
	public void homePageTest() throws InterruptedException {
		
 
	HomePage hm=new HomePage(driver);
	hm.shopButton.click();
	logger.info("click shopbutton");
	hm.homeButton.click();
	logger.info("click home button");
	hm.product1Button.click();
	logger.info("click product1 addtocart");
	hm.product2Button.click();
	logger.info("click product2 addtocart");
	hm.product3Button.click();
	logger.info("click product3 addtocart");
	Thread.sleep(2000);
	hm.shopcartButton.click();
	logger.info("click shopcarticon addtocart");
	Thread.sleep(2000);

	hm.clickclearsendkeys("Selenium Ruby", "4");
	logger.info("seleniumRuby added to cart");
	
	hm.clickclearsendkeys("Thinking in HTML", "2");
	logger.info("Thinking in HTML added to cart");
	
	hm.clickclearsendkeys("Mastering JavaScript", "3");
	logger.info("Mastering JavaScript added to cart");
	Thread.sleep(5000);
	hm.proceedcheckoutButton.click();
	
}
}
