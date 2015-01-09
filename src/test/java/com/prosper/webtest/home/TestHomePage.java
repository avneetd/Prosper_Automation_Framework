package com.prosper.webtest.home;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.prosper.page.LoginPage;
import com.prosper.utilityClasses.SetUpWebDriver;

public class TestHomePage extends SetUpWebDriver {

	Logger logger = Logger.getLogger(TestHomePage.class);

	@DataProvider(name = "email")
	public static Object[][] email() {
		return new Object[][] { { "avneet.dhanoa@gmail.com" } };
	}

	@DataProvider(name = "password")
	public static Object[][] password() {
		return new Object[][] { { "1234" } };
	}

	@Test(priority = 3, dataProvider = "email")
	public void homePageDisplayed(String userName) {
		logger.info("Test Case Name - Inside homePageDisplaed Test Case ");
		try {
			LoginPage loginpage = homePage.homepageLoginLink();
			loginpage.loginWithValidUserId(userName);
		} catch (Exception e) {
			System.out.println("Exception in homePageDisplayed: "
					+ e.getMessage());
		}
	}

	@Test(priority = 0, enabled = true)
	public void homepageHomeLinkDisplayed() {
		logger.info("Test Case Name - Inside homepageHomeLinkDisplayed Test Case ");
		try {
			homePage.verifyHomeLinkFooter();
		} catch (Exception e) {
			System.out.println("Exception in homepageHomeLinkDisplayed: "
					+ e.getMessage());
		}
	}

	@Test(priority = 1, enabled = true)
	public void homepageLoanTypesLinkDisplayed() {
		logger.info("Test Case Name - Inside homepageLoanTypesLinkDisplayed Test Case ");
		try {
			homePage.verifyLoanTypesLinkFooter();
		} catch (Exception e) {
			System.out.println("Exception in homepageLoanTypesLinkDisplayed: "
					+ e.getMessage());
		}
	}

	@Test(priority = 2, enabled = true)
	public void homepageQuickInvestLinkDisplayed() {
		logger.info("Test Case Name - Inside homepageQuickInvestLinkDisplayed Test Case ");
		try {
			homePage.verifyQuickInvestLinkFooter();
		} catch (Exception e) {
			System.out
					.println("Exception in homepageQuickInvestLinkDisplayed: "
							+ e.getMessage());
		}
	}
}
