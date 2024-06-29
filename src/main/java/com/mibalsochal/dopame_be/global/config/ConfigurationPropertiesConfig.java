package com.mibalsochal.dopame_be.global.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.mibalsochal.dopame_be.global.client.gpt.GPTProperties;
import com.mibalsochal.dopame_be.global.client.youtube.YoutubeProperties;
import com.mibalsochal.dopame_be.global.security.jwt.JwtProperties;

@Configuration
@EnableConfigurationProperties({JwtProperties.class, YoutubeProperties.class, GPTProperties.class})
public class ConfigurationPropertiesConfig {
}
