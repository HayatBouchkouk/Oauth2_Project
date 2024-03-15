package com.example.Oauth2_project.service;


import com.example.Oauth2_project.dtos.AuthResponseDto;
import com.example.Oauth2_project.dtos.TokenType;
import com.example.Oauth2_project.jwt.JwtTokenGenerator;
import com.example.Oauth2_project.repo.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//The AuthService is responsible for creating JWT tokens based on the provided authentication details.
@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserInfoRepo userInfoRepo;
        private final JwtTokenGenerator jwtTokenGenerator;
        public AuthResponseDto getJwtTokensAfterAuthentication(Authentication authentication) {
            try
            {
                var userInfoEntity = userInfoRepo.findByEmailId(authentication.getName())
                        .orElseThrow(()->{
                            //log.error("[AuthService:userSignInAuth] User :{} not found",authentication.getName());
                            return new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND ");});


                String accessToken = jwtTokenGenerator.generateAccessToken(authentication);

                //log.info("[AuthService:userSignInAuth] Access token for user:{}, has been generated",userInfoEntity.getUserName());
                return  AuthResponseDto.builder()
                        .accessToken(accessToken)
                        .accessTokenExpiry(15 * 60)
                        .userName(userInfoEntity.getUserName())
                        .tokenType(TokenType.Bearer)
                        .build();


            }catch (Exception e){
                //log.error("[AuthService:userSignInAuth]Exception while authenticating the user due to :"+e.getMessage());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please Try Again");
            }
        }
}
