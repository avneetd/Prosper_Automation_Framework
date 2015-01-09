package com.prosper.utilityClasses;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.prosper.page.HomePage;
import com.prosper.utilityClasses.WebDriverManager;

public class SetUpWebDriver {
	protected WebDriver driver;
	protected HomePage homePage;
	Logger logger = Logger.getLogger(SetUpWebDriver.class);

	@BeforeMethod(alwaysRun = true)
	public void runBeforeEachMethod(Method method) {
		logger.info("info - Starting new test case " + method.getName());
		driver = WebDriverManager.getWebDriver();
		homePage = new HomePage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenShotOnFailure(ITestResult result) throws IOException {
		logger.info("info - End test test case method " + result.getName());
		if (result.isSuccess() == false) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat(
					"dd_MM_yyyy_hh_mm_ss");
			String destFileName = result.getInstanceName() + "_"
					+ result.getName() + "_"
					+ formater.format(calendar.getTime()) + ".png";
			String destDir = PropertyManager
					.getProperty("screenshot_directory");

			File sourceFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(sourceFile, new File(destDir + "/"
					+ destFileName));
			String screenshotPath = destDir + "/" + destFileName;
			Reporter.setEscapeHtml(false);

			Reporter.log("Failed_Test_Case <a href = " + screenshotPath
					+ "</a>");
			// Reporter.setCurrentTestResult(result);
		}
	}

	@AfterMethod(dependsOnMethods = "takeScreenShotOnFailure", alwaysRun = true)
	public void runAfterEachMethod() {
		driver.quit();
	}
}
