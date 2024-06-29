package com.mibalsochal.dopame_be.domain.user.domain.service;

import org.springframework.stereotype.Service;

import com.mibalsochal.dopame_be.domain.user.domain.User;
import com.mibalsochal.dopame_be.domain.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFactory {
    private final UserRepository userRepository;


    public User createUser(final String sub){
        return userRepository.findBySub(sub).orElseGet(() -> {
            User user = User.createNewUser(sub);
            return userRepository.save(user);
        });
    }
}
