package com.prosper.utilityClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyManager {
	static final Logger logger = Logger.getLogger(PropertyManager.class);

	private static Properties props = null;

	private PropertyManager() {

	}

	public static String getProperty(String key) {
		try {
			String value = System.getProperty(key);
			if (props == null) {
				props = new Properties();
				try {
					// FileInputStream fis = new FileInputStream(
					// "C:\\sqa_class\\RARcProject\\src\\rideauction.properties");
					InputStream fis = ClassLoader.getSystemClassLoader()
							.getResourceAsStream("automation.properties");

					props.load(fis);
				} catch (FileNotFoundException fne) {
					fne.printStackTrace();

				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}

			if (value == null) {
				value = props.getProperty(key);

			}
			logger.info("key: " + key + " value: " + value);
			return value;
			// return props.getProperty(key);
		} catch (Exception e) {
			System.out.println("Inside Property Manager Class: "
					+ e.getMessage());
		}
		return null;
	}
}
