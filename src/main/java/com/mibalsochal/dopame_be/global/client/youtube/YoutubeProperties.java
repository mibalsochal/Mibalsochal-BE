package com.mibalsochal.dopame_be.global.client.youtube;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@ConfigurationProperties(prefix = "youtube")
@RequiredArgsConstructor
public class YoutubeProperties {
    private final String apiKey;
}
