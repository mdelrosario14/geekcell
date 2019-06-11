/**
 * GeekCellApplication.java - GeekCellApplication main application.
 * 2019 All rights reserved.
 *
 */
package com.gc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.gc.service.UserAccessService;

/**
 * Main class of the Geek Cell app.
 * @author Mardolfh Del Rosario
 *
 */
@SpringBootApplication
public class GeekCellApplication implements CommandLineRunner {


	/**
	 * Main method.
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GeekCellApplication.class, args);

	}

	@Autowired
	private UserAccessService userAccessService;

    @Transactional()
    @Override
    public void run(String... args) throws Exception {

    	this.userAccessService.testOnly();

    }

}
