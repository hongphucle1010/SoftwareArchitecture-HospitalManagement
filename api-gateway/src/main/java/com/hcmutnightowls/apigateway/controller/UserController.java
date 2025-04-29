package com.hcmutnightowls.apigateway.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/userinfo")
    public Mono<Map<String, Object>> userInfo(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OAuth2User oauth2User) {
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", oauth2User.getName());
        userInfo.put("attributes", oauth2User.getAttributes());
        userInfo.put("authorities", oauth2User.getAuthorities());
        userInfo.put("clientName", authorizedClient.getClientRegistration().getClientName());
        
        return Mono.just(userInfo);
    }
}