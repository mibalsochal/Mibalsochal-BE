package com.mibalsochal.dopame_be.domain.user.domain.oauth;

public interface OAuthHandler {
    OAuthTransactionResult retrieveOAuthDetail(OAuthProcessingData request);
}
