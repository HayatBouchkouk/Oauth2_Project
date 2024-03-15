package com.example.Oauth2_project.controller;


import com.example.Oauth2_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //If the provided credentials are valid, Spring Security creates an Authentication object  authentication representing the authenticated user.
    //This Authentication object contains information about the authenticated user, such as username, authorities, and authentication status.
    //This authentication process typically involves checking the username and password against a user database or identity provider.
   // Authentication Object: If the provided credentials are valid, Spring Security creates an Authentication object,
    // representing the authenticated user, and passes it to the authenticateUser method as a parameter.
    //we provide the authenticatedUser details to the method authenticatedUser in order to generate the token
    @PostMapping("/sign-in")
    //This method is responsible for generating JWT tokens after successful authentication
    public ResponseEntity<?> authenticatedUser(Authentication authentication){

        return ResponseEntity.ok(authService.getJwtTokensAfterAuthentication(authentication));
    }
}
