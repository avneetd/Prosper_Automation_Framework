package com.prosper.utilityClasses;

import static com.prosper.utilityClasses.WebdriverWaitTime.WEBDRIVER_WAIT_TIME;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class HelperAPI {
	Logger logger = Logger.getLogger(HelperAPI.class);
	protected WebDriver driver;

	public HelperAPI(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitForPageToLoad();
		verifyPageElements();
	}

	public abstract void waitForPageToLoad();

	public abstract void verifyPageElements();

	/**
	 * To get Web Element
	 * 
	 * @param elmLocator
	 * @return
	 */
	public WebElement getElement(By elmLocator) {
		try {
			return driver.findElement(elmLocator);

		} catch (Exception ex) {
			logger.error("Error while finding an element", ex);
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Verify and click on element
	 * 
	 * @param elmLocator
	 */

	public void verifyAndClick(WebElement element) {
		logger.info("Verify and Click XPath: " + element);
		Assert.assertNotNull(element);
		Assert.assertTrue(element.isEnabled());
		element.click();
	}

	/**
	 * Verify and click on element
	 * 
	 * @param elmLocator
	 */

	public void verifyAttribute(WebElement element, String attrKey,
			String expectedValue) {
		Assert.assertNotNull(element);
		Assert.assertEquals(element.getAttribute(attrKey), expectedValue);
	}

	/**
	 * check if element is present or not . If present returns true otherwise
	 * false
	 * 
	 * @param elmLocaotr
	 * @return
	 */
	public boolean isElementVisible(WebElement element) {
		assertNotNull(element);
		return element.isDisplayed();
	}

	/**
	 * check if element is present or not . If present returns true otherwise
	 * false
	 * 
	 * @param elmLocaotr
	 * @return
	 */
	public void verifyElementVisible(WebElement element) {
		assertNotNull(element);
		assertTrue(element.isDisplayed());
	}

	/**
	 * To Type into text box . takes two parameter
	 * 
	 * @param elmLocator
	 *            :- Locator of element
	 * @param value
	 *            :- Value to be typed
	 */

	public void type(WebElement element, String value) {
		assertNotNull(element);
		element.sendKeys(value);
	}

	/**
	 * To select a value from drop down box
	 * 
	 * @param elmLocator
	 *            :- Element Locator
	 * @param value
	 *            :value to be selected
	 */
	public void selectDropDownByVisibleText(WebElement element, String value) {
		assertNotNull(element);
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	/**
	 * To select a value from drop down box
	 * 
	 * @param elmLocator
	 *            :- Element Locator
	 * @param value
	 *            :value to be selected
	 */
	public void selectDropDownByValue(WebElement element, String value) {
		Assert.assertNotNull(element);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * Wait for a element Present
	 * 
	 * @param elmLocator
	 *            Locator of the element
	 * @param timeInSecs
	 *            time in seconds
	 */
	public void waitForElementPresent(By elmLocator, long timeInSecs) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.presenceOfElementLocated(elmLocator));
	}

	public void WaitForElementToBeClickable(WebElement element, long timeInSecs) {
		assertNotNull(element);
		WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void WaitForElementToBeClickable(WebElement element) {
		assertNotNull(element);
		WebDriverWait wait = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeVisible(WebElement element) {
		assertNotNull(element);
		WebDriverWait wait = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for a element Present
	 * 
	 * @param elmLocator
	 *            Locator of the element
	 * @param timeInSecs
	 *            time in seconds
	 */
	public void waitForElementPresent(By elmLocator) {
		WebDriverWait wait = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME);
		wait.until(ExpectedConditions.presenceOfElementLocated(elmLocator));
	}

	/*
	 * without wait time
	 */
	public void waitForElementClickable(By elmLocator) {
		WebDriverWait wait = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(elmLocator));
	}

	public void verifyElementSelected(WebElement element) {

		Assert.assertNotNull(element);
		Assert.assertTrue(element.isSelected());
	}

	public void verifyElementContainText(WebElement element, String text) {
		Assert.assertNotNull(element);
		Assert.assertTrue(element.getText().contains(text));
	}

	/**
	 * Verify Page Title
	 */
	public void verifyPageTitle(String pageTitle) {
		String pTitle = driver.getTitle();
		Assert.assertTrue(pTitle.toLowerCase().contains(pageTitle.toLowerCase()));
	}

	/**
	 * Get Text from HTML
	 */
	public String getText(WebElement element) {
		Assert.assertNotNull(element);
		return element.getText();
	}
}

