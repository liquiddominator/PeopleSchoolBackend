package com.solveria.peopleschool.shared.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class RevokedAccessTokenValidator implements OAuth2TokenValidator<Jwt> {

    private static final Logger log = LoggerFactory.getLogger(RevokedAccessTokenValidator.class);
    private static final OAuth2Error REVOKED_TOKEN =
            new OAuth2Error("invalid_token", "Token has been revoked", null);
    private static final OAuth2Error REVOCATION_UNAVAILABLE =
            new OAuth2Error("server_error", "Token revocation status is unavailable", null);

    private final StringRedisTemplate redisTemplate;
    private final boolean enabled;
    private final String redisKeyPrefix;
    private final boolean failOpenOnRedisError;

    public RevokedAccessTokenValidator(
            ObjectProvider<StringRedisTemplate> redisTemplateProvider,
            JwtSecurityProperties properties) {
        this.redisTemplate = redisTemplateProvider.getIfAvailable();
        this.enabled = Boolean.TRUE.equals(properties.revocation().enabled());
        this.redisKeyPrefix = properties.revocation().redisKeyPrefix();
        this.failOpenOnRedisError =
                Boolean.TRUE.equals(properties.revocation().failOpenOnRedisError());
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (!enabled) {
            return OAuth2TokenValidatorResult.success();
        }

        String tokenId = token.getId();
        if (tokenId == null || tokenId.isBlank()) {
            return OAuth2TokenValidatorResult.success();
        }

        if (redisTemplate == null) {
            log.warn("event=PEOPLE_SCHOOL_TOKEN_REVOCATION_REDIS_UNAVAILABLE tokenId={}", tokenId);
            return failOpenOnRedisError
                    ? OAuth2TokenValidatorResult.success()
                    : OAuth2TokenValidatorResult.failure(REVOCATION_UNAVAILABLE);
        }

        try {
            if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKeyPrefix + tokenId))) {
                return OAuth2TokenValidatorResult.failure(REVOKED_TOKEN);
            }
        } catch (Exception ex) {
            log.warn("event=PEOPLE_SCHOOL_TOKEN_REVOCATION_LOOKUP_FAILED tokenId={}", tokenId, ex);
            return failOpenOnRedisError
                    ? OAuth2TokenValidatorResult.success()
                    : OAuth2TokenValidatorResult.failure(REVOCATION_UNAVAILABLE);
        }

        return OAuth2TokenValidatorResult.success();
    }
}
