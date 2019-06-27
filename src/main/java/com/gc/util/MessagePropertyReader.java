/**
 * MessagePropertyReader.java - Read a message property file.
 * 2019 All rights reserved.
 *
 */
package com.gc.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * This class focus on getting message properties.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class MessagePropertyReader {

	private static ResourceBundleMessageSource messageSource;

	@Autowired
	MessagePropertyReader(ResourceBundleMessageSource messageSource) {
		MessagePropertyReader.messageSource = messageSource;
	}

	public String toLocale(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode, null, locale);
	}

}
