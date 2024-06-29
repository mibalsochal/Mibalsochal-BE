package com.mibalsochal.dopame_be.global.security.jwt;

import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrivateClaims {

    private final TokenType tokenType;
    private final UserClaims userClaims;

    public Map<String, Object> convertClaimsMap(){
        return Map.of(
                ClaimsConsts.TOKEN_TYPE, this.tokenType,
                ClaimsConsts.USER_CLAIMS, this.userClaims
        );
    }

    @Getter
    public static class UserClaims{
        private final Long userId;

        public UserClaims(Long userId){
            this.userId = userId;
        }

        public PrivateClaims convertToPrivateClaims(TokenType tokenType){
            return new PrivateClaims(tokenType, this);
        }
    }
}
