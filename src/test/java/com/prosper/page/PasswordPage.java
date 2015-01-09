package com.prosper.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.prosper.utilityClasses.HelperAPI;

public class PasswordPage extends HelperAPI {

	@FindBy(xpath = ".//*[@id='M_ctl00_MainContent_MainContent_c7_tb_password']")
	WebElement inputPWD;

	@FindBy(xpath = ".//*[@id='M_ctl00_MainContent_MainContent_c7_btn_signin']")
	WebElement signInBTN;

	public PasswordPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForPageToLoad() {
		waitForElementToBeVisible(signInBTN);
	}

	@Override
	public void verifyPageElements() {
		// TODO Auto-generated method stub
	}

	// Return back to Homepage in Same Session and continue from where it was
	// Left on Homepage
	public LoginPage validUserPassword(String pwd) {
		type(inputPWD, pwd);
		verifyAndClick(signInBTN);
		return new LoginPage(driver);
	}
}

