package com.armyost.demo;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {
    private static final Logger logger = LoggerFactory.getLogger(KakaoOAuth2UserService.class);

    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // How to get informations of authenticated user.
        logger.info("!!! User attributes are : {} !!!", attributes);
        logger.info("!!! User authorities are : {} !!!", oAuth2User.getAuthorities());
        logger.info("!!! User AccessToken Value is {} !!!", userRequest.getAccessToken().getTokenValue());
        logger.info("!!! User AccessToken will be expired at {} !!!", userRequest.getAccessToken().getExpiresAt());

        // I use session to some data share through a session.
        httpSession.setAttribute("login_info", attributes);
        httpSession.setAttribute("access_token", userRequest.getAccessToken().getTokenValue());

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oAuth2User.getAttributes(), "id");
    }
}