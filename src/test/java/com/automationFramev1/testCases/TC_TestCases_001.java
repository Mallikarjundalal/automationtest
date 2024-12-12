package com.automationFramev1.testCases;

import java.io.IOException;
import java.net.SocketException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationFramev1.pageObjects.Registerpage;

public class TC_TestCases_001 extends Baseclass {

    @Test(priority=1)
    public void testUserRegistration() throws IOException, InterruptedException {
        // Initialize page object
        Registerpage registerPage = new Registerpage(driver);
        try {
            // Attempt connection
         
        // Step 1: Navigate to My Account
        registerPage.myAccount.click();
        logger.info("Clicked on 'My Account'");

        // Step 2: Enter email and password
        wait.until(ExpectedConditions.visibilityOf(registerPage.email));
        registerPage.enterRegisterEmail(usn);
        logger.info("Entered email: " + usn);
        registerPage.enterRegisterPassword(pass);
        logger.info("Entered password");

        // Step 3: Click Register button
        registerPage.clickRegisterButton();
        logger.info("Clicked on 'Register' button");

      //  Step 4: Validate registration error message
       String registrationError = getErrorMessage();
       Assert.assertEquals(registrationError, "Error: An account is already registered with your email address. Please login.");
        logger.info("Validated registration error message");

        // Step 5: Login validation - missing username
        registerPage.loginButton.click();
        logger.info("Clicked on 'Login' button");
        String usernameError = getErrorMessage();
        Assert.assertEquals(usernameError, "Error: Username is required.");
        logger.info("Validated username missing error message");

        // Step 6: Login validation - missing password
        registerPage.usernameField.sendKeys(usn);
        registerPage.loginButton.click();
        logger.info("Clicked on 'Login' button after entering username");
        String passwordError = getErrorMessage();
        Assert.assertEquals(passwordError, "Error: Password is required.");
        logger.info("Validated password missing error message");

        // Step 7: Successful login
        registerPage.loginPassword.sendKeys(pass);
        logger.info("Entered password");
        registerPage.loginButton.click();
        logger.info("Clicked on 'Login' button");

        // Capture screenshot after completing the test
        captureScreen(driver, "TC_TestCases_001");
         // Exit loop if successful
        } catch (SocketException e) {
            Thread.sleep(2000); // Wait before retrying
        }
    }


    // Helper method to fetch error message
    private String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='woocommerce-error']//li")));
        return errorElement.getText();
    }
}

