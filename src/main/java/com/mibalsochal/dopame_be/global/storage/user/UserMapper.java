package com.mibalsochal.dopame_be.global.storage.user;

import org.springframework.stereotype.Component;

import com.mibalsochal.dopame_be.domain.user.domain.User;
import com.mibalsochal.dopame_be.global.storage.user.jpa.UserEntity;

@Component
public class UserMapper {

	public UserEntity toEntity(User user) {
		return UserEntity.of(user.getId(), user.getSub());
	}

	public User toDomain(UserEntity userEntity) {
		return User.of(userEntity.getId(), userEntity.getSub());
	}
}
