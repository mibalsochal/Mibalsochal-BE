package com.mibalsochal.dopame_be.domain.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String sub;


    public static User createNewUser(String sub){
        return new User(null, sub);
    }

    public static User of(Long id, String sub){
        return new User(id, sub);
    }
}
