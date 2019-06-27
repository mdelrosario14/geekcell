/**
 * CustomLocaleResolver.java - Configuration on locale used in http request.
 * 2019 All rights reserved.
 *
 */
package com.gc.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {

   List<Locale> LOCALES = Arrays.asList(new Locale("en"));

   /**
    * Acquire request' accept-language.  Default is en.
    * @param request HttpServletRequest reference.
    */
   @Override
   public Locale resolveLocale(HttpServletRequest request) {
	  String headerLang = request.getHeader("Accept-Language");
	  return headerLang == null || headerLang.isEmpty()
	        ? Locale.getDefault()
	        : Locale.lookup(Locale.LanguageRange.parse(headerLang), this.LOCALES);
   }

   /**
    * Loads up the messages.properties and its keys.
    * @return ResourceBundleMessageSource reference.
    */
   @Bean
   public ResourceBundleMessageSource messageSource() {
	  ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
	  rs.setBasename("messages");
	  rs.setDefaultEncoding("UTF-8");
	  rs.setUseCodeAsDefaultMessage(true);
	  return rs;
   }
}