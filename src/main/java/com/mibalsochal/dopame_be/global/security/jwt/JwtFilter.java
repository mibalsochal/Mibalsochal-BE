package com.mibalsochal.dopame_be.global.security.jwt;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //url이 통과해도되는지 체크 (http 메서드와 함께 자료구조에 저장하여 검증)
        String url = request.getRequestURI();
        String method = request.getMethod();

        if(!IgnorePathConsts.isIgnorablePath(url, HttpMethod.valueOf(method))){
            //위가 통과되면 토큰을 검출
            String authorization = request.getHeader("Authorization");

            /*//Authorization 헤더 검증
            if (authorization == null || !authorization.startsWith("Bearer ")) {

                System.out.println("token null");
                filterChain.doFilter(request, response); //doFilter를 통해 request와 response를

            }*/

            System.out.println("authorization now");
            //Bearer 부분 제거 후 순수 토큰만 획득
            String token = authorization.split(" ")[1];

            //토큰이 유효한지 검증, 유효성 검증은 extract 메서드에서 처리
            Long userId = jwtUtil.extractUserClaim(token).getUserId();

            Authentication authToken = new JwtAuthentication(userId);

            //세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }



        filterChain.doFilter(request, response);
    }
}
