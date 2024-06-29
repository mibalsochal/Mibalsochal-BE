package com.mibalsochal.dopame_be.global.storage.user.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sub;

    public static UserEntity of(Long id, String sub){
        return new UserEntity(id, sub);
    }

}
