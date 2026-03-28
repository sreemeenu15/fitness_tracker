package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.UserRepository;
import com.sree.fitness_tracker.security.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private String provider;


    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);

        String provider = request.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email;
        String name;
        String providerId;

        if (provider.equals("google")) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
            providerId = (String) attributes.get("sub");
        } else if (provider.equals("github")) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("login");
            providerId = attributes.get("id").toString();

            if (email == null) {
                email = name + "@github.com";
            }
        } else {
            throw new RuntimeException("Unsupported provider");
        }

        return new CustomOAuth2User(email, name, providerId, provider, attributes);
    }
}