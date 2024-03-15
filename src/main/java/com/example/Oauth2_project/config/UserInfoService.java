package com.example.Oauth2_project.config;

import com.example.Oauth2_project.repo.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//this class is for used to retrieve user-related data, using loadUserByUsername(), and returns UserDetails.
@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepo userInfoRepo;
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        //return the user details if the username which is the email is found
        return userInfoRepo
                .findByEmailId(emailId)
                // Map UserInfoEntity to UserInfoConfig if user is found, otherwise throw UsernameNotFoundException
                //When you call the constructor with new UserInfoConfig(userInfoEntity), you're essentially telling Java to create a new UserInfoConfig object and initialize it with the provided userInfoEntity.
                .map((userInfoEntity) -> new UserInfoConfig(userInfoEntity))
                .orElseThrow(()-> new UsernameNotFoundException("UserEmail: "+emailId+" does not exist"));
    }






}


