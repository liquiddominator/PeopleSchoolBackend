package com.solveria.peopleschool.shared.security;

import java.util.UUID;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class RequiredIamClaimsValidator implements OAuth2TokenValidator<Jwt> {

    private static final OAuth2Error MISSING_OR_INVALID_CLAIMS =
            new OAuth2Error("invalid_token", "JWT is missing required IAM claims", null);

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (isBlank(token.getId())
                || isBlank(token.getSubject())
                || isBlank(token.getClaimAsString("tenant_id"))) {
            return OAuth2TokenValidatorResult.failure(MISSING_OR_INVALID_CLAIMS);
        }

        try {
            Long.parseLong(token.getSubject());
            UUID.fromString(token.getClaimAsString("tenant_id"));
        } catch (IllegalArgumentException ex) {
            return OAuth2TokenValidatorResult.failure(MISSING_OR_INVALID_CLAIMS);
        }

        return OAuth2TokenValidatorResult.success();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
