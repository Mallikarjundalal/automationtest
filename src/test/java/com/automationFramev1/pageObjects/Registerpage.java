package com.automationFramev1.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registerpage {
    WebDriver driver;

    // Constructor to initialize WebDriver and page elements
    public Registerpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Web Elements

    @FindBy(xpath = "//a[text()='My Account']")
    public WebElement myAccount;

    @FindBy(xpath = "//input[@id='reg_email']")
    public WebElement email;

    @FindBy(xpath = "//input[@id='reg_password']")
    public WebElement registerPassword;

    @FindBy(xpath = "//input[@name='register']")
    public WebElement registerButton;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement loginButton;

    @FindBy(xpath = "//input[@name='username']")
    public WebElement usernameField;
    @FindBy(xpath = "//input[@name='username']")
    public WebElement usernameclear;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement loginPassword;

    @FindBy(xpath = "//a[@href='https://practice.automationtesting.in/my-account/customer-logout/']")
    public WebElement logoutButton;

    // Methods to interact with elements

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterLoginPassword(String password) {
        loginPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void enterRegisterEmail(String mail) {
        email.sendKeys(mail);
    }

    public void enterRegisterPassword(String pwd) {
        registerPassword.sendKeys(pwd);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }
}
