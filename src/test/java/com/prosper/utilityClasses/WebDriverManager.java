package com.prosper.utilityClasses;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {

	/*
	 * public static final String MODE_KEY="mode"; public static final String
	 * HUB_URL_KEY="hubUrl"; public static final String
	 * DESIRED_PLATFORM_KEY="platform"; public static final String
	 * DESIRED_BROWSER_VERSION="browserVersion"; public static final String
	 * BROSER_KEY="browser";
	 */

	public static WebDriver getWebDriver() {
		Logger logger = Logger.getLogger(WebDriverManager.class);
		try {
			WebDriver driver;
			String browser = PropertyManager.getProperty("browser");
			// String browserVersion= getProperty(DESIRED_BROWSER_VERSION);
			// DesiredCapabilities caps;

			if ("firefox".equalsIgnoreCase(browser)) {
				driver = new FirefoxDriver();

			} else if ("ie".equalsIgnoreCase(browser)) {
				File file = new File(
						PropertyManager
								.getProperty("InternetExplorerServerPath"));
				// get the path of webdriver exe
				System.setProperty("webdriver.ie.driver",
						file.getAbsolutePath());

				DesiredCapabilities capabilities = DesiredCapabilities
						.internetExplorer();
				capabilities.setCapability(" ignoreZoomSetting", true);
				capabilities
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				driver = new InternetExplorerDriver(capabilities);
				driver = new InternetExplorerDriver(capabilities);

			} else if ("chrome".equalsIgnoreCase(browser)) {
				File file = new File(
						PropertyManager.getProperty("ChromeServerPath"));
				System.setProperty("webdriver.chrome.driver",
						file.getAbsolutePath());
				driver = new ChromeDriver();

			} else {
				driver = new HtmlUnitDriver();
			}
			// Based Upong Environment Specifed In config WEbdriver will get the
			// URL to run Automation Suite

			if (Boolean.valueOf(PropertyManager.getProperty("devEnv"))) {
				logger.info("\n\n************************\n Dev Env Selected"
						+ "\n\n************************\n");
				driver.get(PropertyManager.getProperty("devEnvURI"));
			} else if (Boolean.valueOf(PropertyManager.getProperty("stgEnv"))) {
				logger.info("\n\n************************\n Stagging Env Selected"
						+ "\n\n************************\n");
				driver.get(PropertyManager.getProperty("stgEnvURI"));
			} else {
				driver.quit();
				System.out.println("\n\nNO ENVIRONMENT SPECIFIED TO RUN \n\n");
				logger.info("\n\nNO ENVIRONMENT SPECIFIED TO RUN \n\n");
			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		} catch (Exception ex) {
			System.out.println("Inside Get Web Driver: " + ex.getMessage());
		}
		return null;

	}
}
