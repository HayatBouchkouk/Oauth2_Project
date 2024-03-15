package com.example.Oauth2_project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;



@ConfigurationProperties(prefix = "rsa")
public record RSAKeyRecord(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
