/**
 * UserDto.java - Data transfer object of Entity to Model for User.
 * 2019 All rights reserved.
 *
 */
package com.gc.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gc.entity.UserEntity;
import com.gc.exception.DtoException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
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


	@Override
	public User transferDataToModel(Object o) throws DtoException {
		User user = null;
		try {
			if (o instanceof UserEntity && o != null) {
				UserEntity userEntity = (UserEntity) o;
				user = new User();
				user.setEmail(userEntity.getEmail());
				user.setFirstName(userEntity.getFirstName());
				user.setLastName(userEntity.getLastName());

				//Copy Set<RoleEntity> to List<String>
				List<String> rolesStrList = userEntity.getRoleEntities()
						.stream().map(role -> role.getRoleName()).collect(Collectors.toList());
				user.setRoles(rolesStrList);

			} else {
				throw new DtoException(this.messagePropertyReader.getMessageValue(
						MessageConstants.GC_DTO_TRANSFER_FAILED));
			}

		} catch (UtilityException e) {
			throw new DtoException(EntityModelDto.DTO_ERROR);
		}

		return user;
	}

}
