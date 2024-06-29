package com.mibalsochal.dopame_be.global.storage.user.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findBySub(String sub);
}
