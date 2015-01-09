package com.prosper.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.prosper.utilityClasses.HelperAPI;

public class HomePage extends HelperAPI {

	Logger logger = Logger.getLogger(HomePage.class);

	@FindBy(xpath = ".//*[@id='M_c5_c4_hlSignIn']")
	WebElement homePgaeLogoImg;

	// Bottom links
	@FindBy(xpath = ".//*[@id='M_divFooter']/div/div/div[1]/ul[1]/li[2]/a")
	WebElement Footer_Home_Link;

	@FindBy(xpath = ".//*[@id='M_divFooter']/div/div/div[1]/ul[2]/li[2]/a")
	WebElement Footer_Quick_Invest_Link;

	@FindBy(xpath = ".//*[@id='M_divFooter']/div/div/div[1]/ul[3]/li[2]")
	WebElement Footer_Loan_Types;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void verifyPageElements() {
		// TODO Auto-generated method stub
	}

	@Override
	public void waitForPageToLoad() {
		waitForElementToBeVisible(homePgaeLogoImg);
	}

	public LoginPage homepageLoginLink() {
		try {
			logger.info("Inside Click on Login Link: " + "homePgaeLogoImg"
					+ "\t" + homePgaeLogoImg);
			verifyAndClick(homePgaeLogoImg);
			return new LoginPage(driver);
		} catch (Exception e) {
			System.out.println("Exception in Homepage Log In Link: "
					+ e.getMessage());
			logger.debug("Exception in Homepage Log In Link: " + e.getMessage());
			return null;
		}

	}

	public void verifyHomeLinkFooter() {
		try {
			logger.debug("Verify Home Link in Footer: " + "xpath" + " - "
					+ homePgaeLogoImg);
			verifyElementVisible(Footer_Home_Link);
		} catch (Exception e) {
			System.out.println("Exception in Verify Home Link in Footer: "
					+ e.getMessage());
			logger.debug("Exception in Verify Home Link in Footer: "
					+ e.getMessage());
		}
	}

	public void verifyLoanTypesLinkFooter() {
		try {
			logger.debug("Verify Loan Types Link in Footer: " + "xpath" + " - "
					+ homePgaeLogoImg);
			verifyElementVisible(Footer_Loan_Types);
		} catch (Exception e) {
			System.out
					.println("Exception in Verify Loan Types Link in Footer: "
							+ e.getMessage());
			logger.debug("Exception in Verify Loan Types Link in Footer: "
					+ e.getMessage());
		}
	}

	public void verifyQuickInvestLinkFooter() {
		try {
			logger.debug("Verify Quick Invest Link in Footer: " + "xpath"
					+ " - " + homePgaeLogoImg);
			verifyElementVisible(Footer_Quick_Invest_Link);
		} catch (Exception e) {
			System.out
					.println("Exception in Verify Quick Invest Link in Footer: "
							+ e.getMessage());
			logger.debug("Exception in Verify Quick Invest Link in Footer: "
					+ e.getMessage());
		}
	}
}

