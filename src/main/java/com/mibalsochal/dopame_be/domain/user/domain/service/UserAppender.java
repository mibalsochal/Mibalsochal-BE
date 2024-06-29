package com.mibalsochal.dopame_be.domain.user.domain.service;

import org.springframework.stereotype.Service;

import com.mibalsochal.dopame_be.domain.user.domain.User;
import com.mibalsochal.dopame_be.domain.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;


    public void appendUser(String sub){
        User user = User.createNewUser( sub);
        userRepository.save(user);
    }
}
