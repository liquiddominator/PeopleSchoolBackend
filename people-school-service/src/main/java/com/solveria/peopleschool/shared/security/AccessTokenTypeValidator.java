package com.solveria.peopleschool.shared.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AccessTokenTypeValidator implements OAuth2TokenValidator<Jwt> {

    private static final OAuth2Error INVALID_TOKEN_TYPE =
            new OAuth2Error("invalid_token", "JWT token_type must be access", null);

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        String tokenType = token.getClaimAsString("token_type");
        if (!"access".equals(tokenType)) {
            return OAuth2TokenValidatorResult.failure(INVALID_TOKEN_TYPE);
        }
        return OAuth2TokenValidatorResult.success();
    }
}
