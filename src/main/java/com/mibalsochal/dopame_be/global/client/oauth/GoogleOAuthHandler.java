package com.mibalsochal.dopame_be.global.client.oauth;

import org.springframework.stereotype.Component;

import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthHandler;
import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthProcessingData;
import com.mibalsochal.dopame_be.domain.user.domain.oauth.OAuthTransactionResult;

@Component
public class GoogleOAuthHandler implements OAuthHandler {

	@Override
	public OAuthTransactionResult retrieveOAuthDetail(OAuthProcessingData request) {
		return null;
	}
}
