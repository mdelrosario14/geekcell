/**
 * MessagePropertyReader.java - Read a message property file.
 * 2019 All rights reserved.
 *
 */
package com.gc.util;

import org.springframework.stereotype.Component;

import com.gc.exception.UtilityException;

/**
 * This class is a subclass of PropertyReader that focus more on http response.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class MessagePropertyReader extends PropertyReader {

	public MessagePropertyReader() {
		this("message.properties");
	}

	public MessagePropertyReader(String propFileName) {
		super(propFileName);
	}

    /**
     * Get the actual message string in message properties.
     *
     * @param key value to get
     * @return value message
     * @throws UtilityException any type of error.
     */
    public String getMessageValue(String key) throws UtilityException {
    	return super.getProperties().getProperty(key);
    }

}
