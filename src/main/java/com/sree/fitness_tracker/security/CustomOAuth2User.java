package com.sree.fitness_tracker.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private String email;
    private String name;
    private String providerId;
    private String provider;
    private Map<String, Object> attributes;

    public CustomOAuth2User(String email, String name, String providerId,
                            String provider, Map<String, Object> attributes) {
        this.email = email;
        this.name = name;
        this.providerId = providerId;
        this.provider = provider;
        this.attributes = attributes;
    }

    public String getEmail() { return email; }
    public String getProviderId() { return providerId; }
    public String getProvider() { return provider; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
