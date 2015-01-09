package com.prosper.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.prosper.utilityClasses.HelperAPI;

public class LoginPage extends HelperAPI {

	Logger logger = Logger.getLogger(LoginPage.class);

	@FindBy(xpath = ".//*[@id='M_ctl00_MainContent_MainContent_c7_tb_email']")
	WebElement inputEmail;

	@FindBy(xpath = ".//*[@id='M_ctl00_MainContent_MainContent_c7_btn_continue']")
	WebElement continueBTNEmail;

	@FindBy(xpath = ".//*[@id='M_ctl00_MainContent_MainContent_c7_tb_password']")
	WebElement inputPwd;

	@FindBy(xpath = ".//*[@id='M_ctl00_MainContent_MainContent_c7_btn_signin']")
	WebElement continuePWDBTN;
	
	@FindBy(xpath = ".//*[@id='recaptcha_challenge_image']")
	WebElement pwdVerificationImage;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForPageToLoad() {
		waitForElementToBeVisible(continueBTNEmail);
	}

	@Override
	public void verifyPageElements() {
		// TODO Auto-generated method stub
	}

	// Login with Valid Username and Password
	public void loginWithValidUserId(String email) {
		type(inputEmail, email);
		verifyAndClick(continueBTNEmail);
		System.out.println("Image Text" + driver.findElement(By.xpath(".//*[@id='recaptcha_challenge_image']")).getText());
		logger.info("Image Text" + driver.findElement(By.xpath(".//*[@id='recaptcha_challenge_image']")).getText());
		type(inputPwd, "Prosper1234");
		verifyAndClick(continuePWDBTN);
		logger.info("Dashboard Page Title: " + driver.getTitle());
		verifyPageTitle("Sign");
	}
}

