package com.mibalsochal.dopame_be.global.security.jwt;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpMethod;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IgnorePathConsts {

    private static final Map<String, Set<HttpMethod>> ignorePathMap=
            Map.of(
                    "/login", Set.of(HttpMethod.GET),
                    "/swagger-ui/**", Set.of(HttpMethod.GET, HttpMethod.OPTIONS)
            );

    public static Boolean isIgnorablePath(String uri, HttpMethod httpMethod){
        if(ignorePathMap.containsKey(uri)){
            Set<HttpMethod> methods = ignorePathMap.get(uri);
            return methods.stream()
                    .anyMatch(method -> method.equals(httpMethod));
        }
        return false;
    }
}
