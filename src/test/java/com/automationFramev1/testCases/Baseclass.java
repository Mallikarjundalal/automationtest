package com.automationFramev1.testCases;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.automationFramev1.utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Baseclass {

    ReadConfig rd = new ReadConfig();
    public String url = rd.applicationurl();
    public String usn = rd.UserName();
    public String pass = rd.Password();

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Logger logger = Logger.getLogger(Baseclass.class);

	@Parameters("browser")
    @BeforeClass
    public void initializeDriver(String browser) {
        // Initialize WebDriver based on the browser
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + rd.chromePath());
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + rd.fireFoxpath());
            driver = new FirefoxDriver();
       
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Configure logging
        PropertyConfigurator.configure("log4j.properties");

        // Maximize browser and set timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // driver.manage().window().maximize();
        

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Launch application URL
        driver.get(url);
        logger.info("Opened application: " + url);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }

    public void captureScreen(WebDriver driver, String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/screenshots/" + testName + ".png");
        FileUtils.copyFile(src, dest);
        logger.info("Screenshot captured for test: " + testName);
    }

    public String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Wait interrupted: " + e.getMessage());
        }
    }
}
