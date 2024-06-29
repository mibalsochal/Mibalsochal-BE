package com.mibalsochal.dopame_be.global.client.oauth;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mibalsochal.dopame_be.domain.user.domain.Provider;
import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OAuthHandlerBeanConfig {
	private final GoogleOAuthHandler googleOAuthHandler;
	private final KakaoOAuthHandler kakaoOAuthHandler;
	private final NaverOAuthHandler naverOAuthHandler;

	@Bean
	public Map<Provider, OAuthHandler> oAuthHandlerMap() {
		return Map.of(
			Provider.GOOGLE, googleOAuthHandler,
			Provider.NAVER, naverOAuthHandler,
			Provider.KAKAO, kakaoOAuthHandler
		);
	}
}
