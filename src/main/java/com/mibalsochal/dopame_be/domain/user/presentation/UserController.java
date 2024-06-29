package com.mibalsochal.dopame_be.domain.user.presentation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mibalsochal.dopame_be.domain.user.application.dto.OAuthResponse;
import com.mibalsochal.dopame_be.domain.user.application.service.UserService;
import com.mibalsochal.dopame_be.domain.user.domain.Provider;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/oauth/{provider}")
    public OAuthResponse processingOAuth(
            @Parameter(description = """
                    Header: OAuth-AccessToken
                                        
                    description: OAuth Access Token, Header에 OAuth-AccessToken으로 값을 전달
                            """
            ) @RequestHeader("OAuth-AccessToken") String accessToken,
            @Parameter(description = """
                    PathVariable: Provider
                                        
                    description: 소셜 로그인 플랫폼
                    """) @PathVariable Provider provider
    ) {
        return userService.oAuthExecuting(accessToken, provider);
    }
}
