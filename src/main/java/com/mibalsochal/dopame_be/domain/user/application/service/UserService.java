package com.mibalsochal.dopame_be.domain.user.application.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mibalsochal.dopame_be.domain.user.application.dto.OAuthResponse;
import com.mibalsochal.dopame_be.domain.user.domain.Provider;
import com.mibalsochal.dopame_be.domain.user.domain.User;
import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthHandler;
import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthProcessingData;
import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthTransactionResult;
import com.mibalsochal.dopame_be.domain.user.domain.service.UserFactory;
import com.mibalsochal.dopame_be.global.security.jwt.JwtUtil;
import com.mibalsochal.dopame_be.global.security.jwt.PrivateClaims;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final Map<Provider, OAuthHandler> oAuthHandlerMap;
    private final UserFactory userFactory;
    private final JwtUtil jwtUtil;

    public OAuthResponse oAuthExecuting(String accessToken, Provider provider){
        final OAuthHandler oAuthHandler = oAuthHandlerMap.get(provider);
        OAuthTransactionResult oAuthTransactionResult = oAuthHandler.retrieveOAuthDetail(new OAuthProcessingData(accessToken));
        User user = userFactory.createUser(oAuthTransactionResult.sub());
        return new OAuthResponse(jwtUtil.createAccessToken(new PrivateClaims.UserClaims(user.getId())));
    }



    public void deleteUser(){

    }
}
