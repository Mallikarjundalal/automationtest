package com.automationFramev1.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.automationFramev1.testCases.Baseclass;



public class HomePage extends Baseclass {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy (xpath="//a[text()='Shop']")
	public WebElement shopButton;
	
	@FindBy (xpath="//a[text()='Home']")
	public WebElement homeButton;
	
	@FindBy (xpath="//div[contains(@class,'woocommerce')]//ul//li//a[@data-product_id='160']")
	public WebElement product1Button;
	
	@FindBy (xpath="//div[contains(@class,'woocommerce')]//ul//li//a[@data-product_id='163']")
	public WebElement product2Button;
  
	@FindBy (xpath="//div[contains(@class,'woocommerce')]//ul//li//a[@data-product_id='165']")
	public WebElement product3Button;
	
	@FindBy (xpath="//div[@class='sidemenu sidemenu-off']//a[@class='wpmenucart-contents']//i[@class='wpmenucart-icon-shopping-cart-0']")
	public WebElement shopcartButton;
	
	@FindBy (xpath="//div[@class='wc-proceed-to-checkout']//a")
	public WebElement proceedcheckoutButton;
	
	
	private static final String productName = "//tr[td[@class='product-name']/a[contains(text(), '##FIELD_VALUE##')]]//input[@type='number']";
	
	public void clickclearsendkeys(String element, String value) throws InterruptedException {
		driver.navigate().refresh();
		String productNameLocator = productName.replaceAll("##FIELD_VALUE##", element);
		for (int i = 0; i < 3; i++) { 
	        try {
		WebElement productNameElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productNameLocator)));
		productNameElements.clear();
		productNameElements.sendKeys(value +Keys.ENTER);
		break;
	        }
	        catch (org.openqa.selenium.StaleElementReferenceException e) {
	            System.out.println("StaleElementReferenceException occurred. Retrying... " + (i + 1));
	          
	        }
		}
	
	}
	
	
	}

	
	


