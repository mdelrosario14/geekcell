/**
 * PropertyReader.java - Read all kinds of Property file.
 * 2019 All rights reserved.
 *
 */
package com.gc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.gc.exception.UtilityException;

/**
 * This class is a parent class that intends to load the property file.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class PropertyReader {
	private String propFileName;
    private Properties properties;

    public PropertyReader(String propFileName) {
    	this.propFileName = propFileName;
    }

    /**
     * Get all properties.
     *
     * @return Properties reference.
     * @throws Exception Unable to load file.
     */
    protected final Properties getProperties() throws UtilityException {
		try(InputStream input = getClass().getClassLoader().getResourceAsStream(this.propFileName)) {
			this.properties = new Properties();
    		this.properties.load(input);
		} catch (IOException e) {
			throw new UtilityException("Unable to load file.");
		}
    	return this.properties;
    }
}
