/**
 * GeekCellSecurityConfig.java - GeekCellApplication security configuration.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	@Autowired
	private GeekCellHttpAuthenticationEntryPoint authEntryPoint;
	@Autowired
	private GeekCellAuthSuccessHandler authSuccessHandler;
	@Autowired
    private GeekCellAuthAuthenticationFailureHandler authFailureHandler;
	@Autowired
    private GeekCellAuthenticationLogoutSuccessHandler authLogoutHandler;


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

    /**
     * Configure() contains security details within the Geek Cell App.
     * @param http HttpSecurity reference.
     * @exception Exception generic exception class.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/register", "/login", "/logout", "/securityQuestions/**").permitAll()
          .antMatchers("/geekcell-user/**").hasAnyRole("ADMIN", "GCSTANDARD")
          .anyRequest().denyAll()
          .and()
          .exceptionHandling()
              .authenticationEntryPoint(this.authEntryPoint)
          .and()
          .formLogin()
              .loginProcessingUrl("/login")
              .loginPage("/").permitAll()
              .successHandler(this.authSuccessHandler)
              .failureHandler(this.authFailureHandler)
          .and()
          .logout()
              .logoutUrl("/logout")
              .deleteCookies("JSESSIONID")
              .invalidateHttpSession(true)
              .logoutSuccessHandler(this.authLogoutHandler)
          .and()
          .csrf().csrfTokenRepository(
               new LazyCsrfTokenRepository(new HttpSessionCsrfTokenRepository()))
              .ignoringAntMatchers("/register", "/login", "/logout", "/securityQuestions/**")
          .and()
              .cors().configurationSource(this.corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
