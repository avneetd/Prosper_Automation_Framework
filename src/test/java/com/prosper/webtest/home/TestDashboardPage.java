package com.prosper.webtest.home;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.prosper.page.PasswordPage;
import com.prosper.utilityClasses.SetUpWebDriver;

public class TestDashboardPage extends SetUpWebDriver {

	Logger logger = Logger.getLogger(TestHomePage.class);

	@Test(enabled=false)
	public void passwordPageDisplayed() {
		logger.info("Test Case Name - Inside passwordPageDisplaed Test Case ");
		try {
			PasswordPage pwdPage = new PasswordPage(driver);
			pwdPage.validUserPassword("Prosper1234");
		} catch (Exception e) {
			System.out.println("Exception in passwordPageDisplayed: "
					+ e.getMessage());
		}
	}
}

