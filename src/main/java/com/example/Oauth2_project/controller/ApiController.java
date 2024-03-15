package com.example.Oauth2_project.controller;


import com.example.Oauth2_project.config.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

//all requests are authenticated using JWT tokens

    //@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN','ROLE_USER')")
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @GetMapping("/welcome-message")
    //The Authentication object contains information about the current authenticated user
    public ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication) {
        return ResponseEntity.ok("Welcome to the JWT Tutorial: " + authentication.getName() +" " + "with authorities:" + authentication.getAuthorities());
    }


    //@PreAuthorize("hasRole('ROLE_MANAGER')")
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @GetMapping("/manager-message")
    public ResponseEntity<String> getManagerData(Principal principal) {
        return ResponseEntity.ok("Manager: " +" " + principal.getName());

    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin-message")
    @PreAuthorize("hasAuthority('SCOPE_WRITE')")
    public ResponseEntity<String> getAdminData(@RequestParam("message") String message, Principal principal) {
        return ResponseEntity.ok("Admin::" + principal.getName() + " has this message:" + message);


    }

}
