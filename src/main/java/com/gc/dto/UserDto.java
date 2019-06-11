package com.gc.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gc.entity.UserEntity;
import com.gc.exception.DtoException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.util.MessagePropertyReader;

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
				throw new DtoException(this.messagePropertyReader.getMessageValue(""));
			}

		} catch (UtilityException e) {
			throw new DtoException("");
		}

		return user;
	}




}
