/**
 * UserDto.java - Data transfer object of Entity to Model for User.
 * 2019 All rights reserved.
 *
 */
package com.gc.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gc.entity.RoleEntity;
import com.gc.entity.UserEntity;
import com.gc.exception.DtoException;
import com.gc.model.User;
import com.gc.repository.RoleAccessRepository;
import com.gc.util.MessageConstants;
import com.gc.util.MessagePropertyReader;

/**
 * UserDto transfer UserEntity to User object reference.
 * @author Mardolfh Del Rosario
 *
 */
@Component
public class UserDto extends EntityModelDto {

	@Autowired
	private MessagePropertyReader messagePropertyReader;

	@Autowired
	private RoleAccessRepository roleAccessRepository;


	@Override
	public User transferEntityToModel(Object o) throws DtoException {
		User user = null;
		if (null != o && o instanceof UserEntity) {
			UserEntity userEntity = (UserEntity) o;
			user = new User();
			user.setUserId(userEntity.getUserId().toString());
			user.setEmail(userEntity.getEmail());
			user.setFirstName(userEntity.getFirstName());
			user.setLastName(userEntity.getLastName());
			user.setPwd("******");

			//Copy Set<RoleEntity> to List<String>
			List<String> rolesStrList = userEntity.getRoleEntities()
					.stream().map(role -> role.getRoleName()).collect(Collectors.toList());
			user.setRoles(rolesStrList);

		} else {
			throw new DtoException(this.messagePropertyReader.toLocale(
					MessageConstants.GC_DTO_TRANSFER_FAILED));
		}

		return user;
	}


	@Override
	public UserEntity transferModelToEntity(Object o) throws DtoException {
		UserEntity userEntity = null;
		if (null != o && o instanceof User) {
			User user = (User) o;
			userEntity = new UserEntity();
			LocalDateTime today = LocalDateTime.now();
			userEntity.setActiveSwitch("Y");
			userEntity.setCreateDateTime(today);
			userEntity.setLastUpdate(today);
			userEntity.setEmail(user.getEmail());
			userEntity.setFirstName(user.getFirstName());
			userEntity.setLastName(user.getLastName());
			userEntity.setPwd(user.getPwd());

			Set<String> roleSet = user.getRoles().stream().map(role -> role.trim()).collect(Collectors.toSet());
			Set<RoleEntity> roleSetEntity = roleSet.stream()
					.map(role -> this.roleAccessRepository.findByRoleName(role).get(0)).collect(Collectors.toSet());

			userEntity.setRoleEntities(roleSetEntity);
		} else {
			throw new DtoException(this.messagePropertyReader.toLocale(
					MessageConstants.GC_DTO_TRANSFER_FAILED));
		}

		return userEntity;
	}

}
