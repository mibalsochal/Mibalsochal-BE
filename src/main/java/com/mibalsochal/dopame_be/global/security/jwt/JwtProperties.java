package com.mibalsochal.dopame_be.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ConfigurationProperties(prefix = "jwt")
@RequiredArgsConstructor
@Getter
public class JwtProperties {

    private final String secret;
    private final Long accessTokenExpiredTime;
}
