package com.mibalsochal.dopame_be.global.client.gpt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@ConfigurationProperties(prefix = "gpt")
@RequiredArgsConstructor
public class GPTProperties {
    private final String apiKey;
}
