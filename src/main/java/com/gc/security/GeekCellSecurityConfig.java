/**
 * GeekCellSecurityConfig.java - GeekCellApplication security configuration.
 * 2019 All rights reserved.
 *
 */
package com.gc.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gc.service.impl.GeekCellJwtUserDetailsService;

/**
 * This class handles spring security configuration, access to csrf and cors config (web) JWT (mobile).
 * @author Mardolfh Del Rosario
 *
 */
@Configuration
@EnableWebSecurity
public class GeekCellSecurityConfig {

	/**
	 * This inner class handles first web ant matchers.
	 * @author Mardolfh Del Rosario
	 *
	 */
	@Configuration
	@Order(1)
	public class GeekCellAuthConfig extends WebSecurityConfigurerAdapter {

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


		@Value(value = "${cors-origin}")
		private String corsSetting;
		@Value(value = "${cors-timeout}")
		private String corsTimeout;



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
	          .antMatchers("/web/**", "/mobile/**")
	          .permitAll()
	          .antMatchers("/web/geekcell-user/**").hasAnyRole("ADMIN", "GCSTANDARD")
	          .and()
	          .exceptionHandling()
	              .authenticationEntryPoint(this.authEntryPoint)
	          .and()
	          .formLogin()
	              .loginProcessingUrl("/web/login")
	              .loginPage("/web/").permitAll()
	              .successHandler(this.authSuccessHandler)
	              .failureHandler(this.authFailureHandler)
	          .and()
	          .logout()
	              .logoutUrl("/web/logout")
	              .deleteCookies("JSESSIONID")
	              .invalidateHttpSession(true)
	              .logoutSuccessHandler(this.authLogoutHandler)
	          .and()
	          .csrf().csrfTokenRepository(
	               new LazyCsrfTokenRepository(new HttpSessionCsrfTokenRepository()))
	              .ignoringAntMatchers("/**/register", "/**/login", "/**/logout", "/**/securityQuestions/**", "/**/frontPage", "/**/mobile/**")
	          .and()
	              .cors().configurationSource(this.corsConfigurationSource());
	     }

	    /**
	     * CORS configuration for web.
	     * @return CorsConfigurationSource	reference config.
	     */
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList(this.corsSetting));
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
	        configuration.addAllowedHeader("*");
	        configuration.setAllowCredentials(true);
	        configuration.setMaxAge(Long.valueOf(this.corsTimeout));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	}


	/**
	 * This inner class handles the mobile ant matchers.
	 * @author Mardolfh Del Rosario
	 *
	 */
	@Configuration
	@Order(2)
	public class GeekCellSecurityJWTConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private GeekCellJWTAuthEntryPoint jwtAuthenticationEntryPoint;
		@Autowired
		private GeekCellJwtUserDetailsService jwtUserDetailsService;
		@Autowired
		private GeekCellJwtRequestFilter jwtRequestFilter;


		/**
		 * Make sure that passwords are encoded .
		 * @param AuthenticationManagerBuilder auth reference.
		 * @throws Exception 		if an error occurred.
		 */
		@Override
		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(this.jwtUserDetailsService).passwordEncoder(this.passwordEncoder());
		}

		/**
		 * Returns bean instance of PasswordEncoder.
		 * @return BCryptPasswordEncoder reference.
		 */
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		/**
		 * This method handles authentication management.
		 * @return AuthenticationManager reference.
		 * @throws Exception 		if an error occurred.
		 */
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		/**
		 * Configure stateless session.
		 * @param httpSecurity		HttpSecurity reference.
		 * @throws Exception 		if an error occurred.
		 */
		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/mobile/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
					.exceptionHandling()
					.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			httpSecurity.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}
	}
}
