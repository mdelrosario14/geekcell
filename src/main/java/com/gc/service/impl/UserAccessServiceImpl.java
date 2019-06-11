package com.gc.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gc.dto.UserDto;
import com.gc.entity.RoleEntity;
import com.gc.entity.UserEntity;
import com.gc.exception.DtoException;
import com.gc.exception.ServiceException;
import com.gc.exception.UtilityException;
import com.gc.model.User;
import com.gc.repository.RoleAccessRepository;
import com.gc.repository.UserAccessRepository;
import com.gc.service.UserAccessService;
import com.gc.util.MessagePropertyReader;

@Service
public class UserAccessServiceImpl implements UserAccessService {
	@Autowired
    private DataSource dataSource;

    @Autowired
    private UserAccessRepository userAccessRepository;
    @Autowired
    private RoleAccessRepository roleAccessRepository;
    @Autowired
    private MessagePropertyReader messagePropertyReader;
    @Autowired
    private UserDto userDto;

    @Override
	public void testOnly() {
		System.out.println("DATASOURCE = " + this.dataSource);

		UserEntity gc = new UserEntity(Long.parseLong("1"), "Mardolfh", "Del Rosario", "mdelrosario14@gmail.com",
        		"abc123", LocalDateTime.now(), LocalDateTime.now(), "Y", new HashSet<RoleEntity>());

        RoleEntity gr = this.roleAccessRepository.findByRoleName("ADMIN").get(0);
        System.out.println("role=" + gr.getRoleId());

        gc.getRoleEntities().add(gr);

        this.userAccessRepository.save(gc);

        System.out.println("\n1.findAll()...");
        for (UserEntity geekCellUser : this.userAccessRepository.findAll()) {
            System.out.println(geekCellUser);
        }

        System.out.println("\n2.findByEmail(String email)...");
        for (UserEntity geekCellUser : this.userAccessRepository.findByEmail("mdelrosario14@gmail.com")) {
            System.out.println(geekCellUser);
        }

        System.out.println("\n3.findByCreateDateTime(Date date)...");
        for (UserEntity geekCellUser : this.userAccessRepository.findByCreateDateTime(LocalDateTime.now())) {
            System.out.println(geekCellUser);
        }

        // For Stream, need @Transactional
        //System.out.println("\n4.findByEmailReturnStream(@Param(\"email\") String email)...");
        //try (Stream<UserEntity> stream = this.userAccessRepository.findByEmailReturnStream("@gmail.com")) {
       //     stream.forEach(x -> System.out.println(x));
       // }

        System.out.println("Done!");

	}

	@Override
	public User loginAccount(String email, String pwd) throws ServiceException, UtilityException, DtoException {
		User userLogin = null;
		if (null != email && !email.isBlank() && null != pwd && !pwd.isEmpty()) {
			UserEntity userEntity = this.userAccessRepository.findByLogin(email, pwd).get(0);
			if (null == userEntity) {
				throw new ServiceException(this.messagePropertyReader.getMessageValue(""));
			} else {
				userLogin = this.userDto.transferDataToModel(userEntity);
			}
		}

		return userLogin;
	}
}
