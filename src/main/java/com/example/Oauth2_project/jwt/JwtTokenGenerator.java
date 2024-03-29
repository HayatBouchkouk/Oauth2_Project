package com.example.Oauth2_project.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final JwtEncoder jwtEncoder;


    public String generateAccessToken(Authentication authentication) {


        String roles = getRolesOfUser(authentication);

        String permissions = getPermissionsFromRoles(roles);


        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("hayat")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                .subject(authentication.getName())//the email
                .claim("scope", permissions)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }

        private static String getRolesOfUser(Authentication authentication) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
        }

        private String getPermissionsFromRoles(String roles) {
            Set<String> permissions = new HashSet<>();

            if (roles.contains("ROLE_ADMIN")) {
                permissions.addAll(List.of("READ", "WRITE", "DELETE"));
            }
            if (roles.contains("ROLE_MANAGER")) {
                permissions.add("READ");
            }
            if (roles.contains("ROLE_USER")) {
                permissions.add("READ");
            }

            return String.join(" ", permissions);
        }
    }

