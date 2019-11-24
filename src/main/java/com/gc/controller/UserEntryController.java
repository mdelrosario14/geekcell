/**
 * UserEntryController.java - User login register controller.
 * 2019 All rights reserved.
 *
 */
package com.gc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gc.exception.MapperException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.service.UserAccessService;

/**
 * This controller handles all user login, register etc.
 * @author Mardolfh Del Rosario
 *
 */
@RestController
public class UserEntryController {

	@Autowired
	private UserAccessService userAccessService;

	/**
	 * This API is just used when loading initial page in UI.
	 *
	 * @return String date time format.
	 */
    @GetMapping("frontPage")
	public String frontPage() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	/**
     * Register new user.
     *
     * @param user             User reference from UI.
     * @return                 ResponseEntity - bad request or success.
     */
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            user = this.userAccessService.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (ServiceException | UtilityException | MapperException e) {
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
