/**
 * UserAccessServiceImpl.java - Implementation of UserAccessService.
 * 2019 All rights reserved.
 *
 */
package com.gc.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gc.dto.UserMapper;
import com.gc.entity.LogEntity;
import com.gc.entity.UserEntity;
import com.gc.exception.MapperException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.repository.LoginAccessRepository;
import com.gc.repository.TransactionRepository;
import com.gc.repository.UserAccessRepository;
import com.gc.service.UserAccessService;
import com.gc.util.MessageConstants;
import com.gc.util.MessagePropertyReader;

/**
 * This service contains all implementation about user access.
 * @author Mardolfh Del Rosario
 *
 */
@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Autowired
    private UserAccessRepository userAccessRepository;
    @Autowired
    private LoginAccessRepository loginAccessRepository;
    @Autowired
    private MessagePropertyReader messagePropertyReader;
    @Autowired
    private UserMapper userDto;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private TransactionRepository transactionRepository;


	@Override
	public User loginAccount(String email, String pwd) throws ServiceException, UtilityException, MapperException {
		User userLogin = null;
		if (null != email && !email.isBlank() && null != pwd && !pwd.isEmpty()) {
			List<UserEntity> userEntities = this.userAccessRepository.findByEmail(email);
			if (null != userEntities && !userEntities.isEmpty()) {
				UserEntity userEntity = this.userAccessRepository.findByEmail(email).get(0);
				if (null == userEntity) {
					throw new ServiceException(this.messagePropertyReader.toLocale(
							MessageConstants.GC_LOGIN_USER_NOT_FOUND));
				} else {
					if (this.bCryptPasswordEncoder.matches(pwd, userEntity.getPwd())) {
						userLogin = this.userDto.transferEntityToModel(userEntity);
						LocalDate ld = LocalDate.of(2019, Month.AUGUST, 1);
						LocalTime lt = LocalTime.of(0, 0, 0);
						LocalDateTime ldt1 = LocalDateTime.of(ld, lt);
						LocalDateTime ldt2 = ldt1.plusDays(30);
						//List<TransactionEntity> ret = this.transactionRepository.findByRange(ldt1);
						//List<IncomeGroup> el = this.transactionRepository.findByIncome(ldt1, Currency.USD);
						//List<IncomeGroup> el = this.transactionRepository.findByIncome();
						//List<TransactionEntity> ret = this.transactionRepository.findByTrxDateTime(LocalDateTime.of(ld, lt));
						System.out.println("test");
					} else {
						throw new ServiceException(this.messagePropertyReader.toLocale(
								MessageConstants.GC_LOGIN_USER_INVALID));
					}
				}
			} else {
				throw new ServiceException(this.messagePropertyReader.toLocale(
						MessageConstants.GC_LOGIN_USER_NOT_FOUND));
			}

		}

		return userLogin;
	}


	@Override
	public User createUser(User user) throws ServiceException, UtilityException, MapperException {
		if (null != user) {
			if (this.isUserExists(user.getEmail())) {
				throw new ServiceException(this.messagePropertyReader.toLocale(
						MessageConstants.GC_REGISTER_USER_EXISTS));
			}

			if (!this.isPasswordCompliant(user.getPwd())) {
				throw new ServiceException(this.messagePropertyReader.toLocale(
						MessageConstants.GC_REGISTER_PWD_NON_COMPLIANT));
			}

			//set email to lower case
			user.setEmail(user.getEmail().toLowerCase().trim());

			//Encrypt password
			String rawPwd = user.getPwd();
			user.setPwd(this.bCryptPasswordEncoder.encode(rawPwd));

			UserEntity userEntity = this.userDto.transferModelToEntity(user);
			//Set next change password date
			userEntity.setChangePwdDate(LocalDateTime.now().plusDays(60));

			this.userAccessRepository.save(userEntity);

			user.setPwd("********");
		} else {
			throw new ServiceException(this.messagePropertyReader.toLocale(
					MessageConstants.GC_REGISTER_FAILED));
		}

		return user;
	}


	@Override
	public void insertLoginTrail(String ipAddress, String userEmail) {
		if (null != ipAddress && !ipAddress.isBlank()) {
			LogEntity logEntity = new LogEntity();
			logEntity.setCreateDateTime(LocalDateTime.now());
			logEntity.setIpAddress(ipAddress);
			logEntity.setUserEmail(userEmail);

			this.loginAccessRepository.save(logEntity);
		}

	}

	/**
	 * This method checks if user email exists in the database.
	 *
	 * @param userEmail email address to verify.
	 * @return true/false exists or not.
	 */
	private boolean isUserExists(String userEmail) {
		boolean isUserExist = false;

		List<UserEntity> userEntities = this.userAccessRepository.findByEmail(userEmail.toLowerCase());
		if (null != userEntities && !userEntities.isEmpty()) {
			isUserExist = true;
		}

		return isUserExist;
	}


	/**
	 * This checks whether a provided password is compliant.
	 *
	 * @param password to be tested.
	 * @return true/false if compliant.
	 */
	private boolean isPasswordCompliant(String password) throws ServiceException {
		boolean isPwdCompliant = false;
		if (password.isBlank() || password.length() < 8) {
			throw new ServiceException(this.messagePropertyReader.toLocale(
					MessageConstants.GC_REGISTER_PWD_LEN));
		}

		int let, num, spclChar;
		let = num = spclChar = 0;
		for (char pwd : password.toCharArray()) {
			String charPwdStr = String.valueOf(pwd);
			if (charPwdStr.matches("[A-Za-z]+")) {
				let++;
			} else if (charPwdStr.matches("[0-9]+")) {
				num++;
			} else {
				spclChar++;
			}
		}

		if (let > 0 && num > 0 && spclChar > 0) {
			isPwdCompliant = true;
		}

		return isPwdCompliant;
	}


	@Override
	public User getUserDetailsByEmail(String email) throws ServiceException {
		List<UserEntity> users = this.userAccessRepository.findByEmail(email);
		User user = null;
		try {
			user = this.userDto.transferEntityToModel(users.get(0));
		} catch (MapperException e) {
			throw new ServiceException(e.getMessage());
		}

		return user;
	}
}
