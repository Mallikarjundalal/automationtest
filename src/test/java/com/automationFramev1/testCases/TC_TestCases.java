package com.automationFramev1.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationFramev1.pageObjects.Registerpage;
import com.automationFramev1.utilities.Xlutil;


public class TC_TestCases extends Baseclass {

    @Test(dataProvider = "getdata")
    public void loginData(String user, String pass) throws InterruptedException {
        Registerpage rp = new Registerpage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
            // Step 1: Navigate to My Account
           wait.until(ExpectedConditions.elementToBeClickable(rp.myAccount)).click();
          logger.info("Clicked on 'My Account'");
        rp.usernameclear.clear(); // Clear the username field
        rp.enterUsername(user); // Enter Username
        logger.info("Entered Username: " + user);

        rp.enterLoginPassword(pass); // Enter Password
        logger.info("Entered Password");

        logger.info("Clicked on 'Login' button");
        wait.until(ExpectedConditions.elementToBeClickable(rp.loginButton)).click(); // Click Login Button

        try {
            // Assertion for Login Success
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='mallikarjundalal409']"))); // Check if username is displayed after login
            Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='mallikarjundalal409']")).isDisplayed(), 
                              "Login successful for user: " + user);
            logger.info("Login successful for user: " + user);

            // Perform Logout
            wait.until(ExpectedConditions.elementToBeClickable(rp.logoutButton)).click();
            logger.info("Logged out successfully for user: " + user);
        } catch (Exception e) {
            // Assertion for Login Failure
            Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Error:']")).isDisplayed(), 
                              "Login failed as expected for user: " + user);
            logger.error("Login failed for user: " + user);
        }

               
            
           }
     @DataProvider(name = "getdata")
    public Object[][] getdata() throws IOException {
        // Path to Excel file
        String path = System.getProperty("user.dir") +"/src/test/java/com/automationFramev1/testData/Book1read.xlsx";

        // Read rows and columns from Excel
        int rowCount = Xlutil.getRowCoun(path, "Sheet1");
        int colCount = Xlutil.getcellCoun(path, "Sheet1", 1);

        // Validate row and column counts
        if (rowCount == 0 || colCount == 0) {
            logger.error("Excel file is empty or improperly formatted.");
            throw new IOException("No data found in Excel file.");
        }

        // Initialize data array
        Object[][] loginData = new Object[rowCount][colCount];

        // Populate array with data from Excel
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                loginData[i - 1][j] = Xlutil.getcelldata(path, "Sheet1", i, j);
            }
        }

        logger.info("Data loaded successfully from Excel");
        return loginData;
    }
}
