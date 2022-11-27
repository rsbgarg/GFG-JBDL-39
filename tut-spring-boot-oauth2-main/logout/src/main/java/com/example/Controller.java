package com.example;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/user")
    public Map<String,Object> user(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> result = new HashMap<>();
        result.put("name", oAuth2User.getAttribute("name"));
        result.put("company", oAuth2User.getAttribute("company"));
        return result;

    }


    @GetMapping("/userdetails")
    public Map<String,Object> userDetails(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> result = new HashMap<>();
        result.put("details", oAuth2User.getAttributes());
        return result;

    }
}
