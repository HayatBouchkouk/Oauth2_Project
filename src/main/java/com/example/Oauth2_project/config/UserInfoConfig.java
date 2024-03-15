package com.example.Oauth2_project.config;

import com.example.Oauth2_project.entity.UserInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;



//this class provide the necessary user details for authentication and authorization within the Spring Security framework.

public class UserInfoConfig implements UserDetails {

    private final UserInfoEntity userInfoEntity;

    public UserInfoConfig(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return Arrays
                .stream(userInfoEntity.getRoles()
                 .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();//it gives the roles list of each user

    }

    @Override
    public String getPassword() {
        return userInfoEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfoEntity.getEmailId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
