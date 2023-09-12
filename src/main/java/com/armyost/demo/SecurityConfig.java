package com.armyost.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final KakaoOAuth2UserService kakaoOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(withDefaults())
                .cors(withDefaults()) // if you want to customize cors -> .cors().configurationSource(corsConfigurationSource());
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Let it check validation of client's token first.
                .authorizeHttpRequests(request -> request
                        .antMatchers("/index.html","/images/**","/permitAllConents.html") // White list policy.
                        .permitAll()
                        .antMatchers("/**").hasRole("USER")
                        .anyRequest().authenticated()) 
                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo
                        .userService(kakaoOAuth2UserService) // Let it do actions with user entity. 
                        .and()
                        .successHandler(oAuth2AuthenticationSuccessHandler))) // Let it do actions after authentication like reponsing the authorised token.
                .logout(withDefaults()); // 
        return http.build();
    }
}