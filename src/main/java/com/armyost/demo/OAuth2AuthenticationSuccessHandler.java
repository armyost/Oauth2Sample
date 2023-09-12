package com.armyost.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2AuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {

        // Code here whatever want to do after authetication.

        // If you want to response access token to client. Make it as a Cookie.
        logger.info("!!! Response access token : {}", request.getSession().getAttribute("access_token"));

        /* Authentication userAuthenticationDetail = SecurityContextHolder.getContext().getAuthentication();
        Long userAuthenticationName = Long.valueOf(userAuthenticationDetail.getName());
        logger.info("!!! UserAuthentication Name is : {} !!!", userAuthenticationName);
        */


        
    }
}