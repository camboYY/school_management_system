package com.university.course.schoolmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private WebClient client;

    @GetMapping("/users")
    public String[] getUsers (@RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient auth2AuthorizedClient) {
        return new String[]{this.client
                .get()
                .uri("http://127.0.0.1:8090/api/v1/auth/users")
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(auth2AuthorizedClient))
                .retrieve()
                .bodyToMono(String.class)
                .block()};
    }
}
