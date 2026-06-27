package com.solveria.peopleschool.shared.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "people-school.security.jwt")
public record JwtSecurityProperties(String secret, String issuer, Revocation revocation) {

    public JwtSecurityProperties {
        issuer = issuer == null || issuer.isBlank() ? "iam-service" : issuer;
        revocation =
                revocation == null
                        ? new Revocation(true, "iam:blacklist:access:", false)
                        : revocation;
    }

    public record Revocation(Boolean enabled, String redisKeyPrefix, Boolean failOpenOnRedisError) {
        public Revocation {
            enabled = enabled == null || enabled;
            redisKeyPrefix =
                    redisKeyPrefix == null || redisKeyPrefix.isBlank()
                            ? "iam:blacklist:access:"
                            : redisKeyPrefix;
            failOpenOnRedisError = failOpenOnRedisError != null && failOpenOnRedisError;
        }
    }
}
