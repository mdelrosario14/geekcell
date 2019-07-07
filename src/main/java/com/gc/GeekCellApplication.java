/**
 * GeekCellApplication.java - GeekCellApplication main application.
 * 2019 All rights reserved.
 *
 */
package com.gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Main class of the Geek Cell app.
 * @author Mardolfh Del Rosario
 *
 */
@SpringBootApplication
public class GeekCellApplication {

	/**
	 * Main method of the GeekCellApplication.
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GeekCellApplication.class, args);

	}


	/**
	 * Use this bean to encrypt any string especially passwords.
	 * @return BCryptPasswordEncoder reference.
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
