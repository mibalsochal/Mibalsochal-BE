package com.mibalsochal.dopame_be.global.storage.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mibalsochal.dopame_be.domain.user.domain.User;
import com.mibalsochal.dopame_be.domain.user.domain.UserRepository;
import com.mibalsochal.dopame_be.global.storage.user.jpa.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	private final UserJpaRepository userJpaRepository;
	private final UserMapper userMapper;

	@Override
	public User save(User user) {
		return userMapper.toDomain(userJpaRepository.save(userMapper.toEntity(user)));
	}

	@Override
	public Optional<User> findBySub(String sub) {
		return userJpaRepository.findBySub(sub)
			.map(userMapper::toDomain);
	}
}
