/**
 * GeekCellSecurityConfig.java - GeekCellApplication security configuration.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class handles spring security configuration, from access to csrf and cors config.
 * @author Mardolfh Del Rosario
 *
 */
@Configuration
public class GeekCellSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(GeekCellSecurityConfig.class);

	@Autowired
    private GeekCellAuthenticationProvider authProvider;

    /**
     * Provide the GeekCell's authentication, checks login and pwd in DB.
     * @param auth AuthenticationManagerBuilder reference.
     * @exception Exception general exception of the framework.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(this.authProvider);
    }
}
