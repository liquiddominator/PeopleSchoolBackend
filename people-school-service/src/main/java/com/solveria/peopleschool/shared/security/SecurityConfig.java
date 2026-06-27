package com.solveria.peopleschool.shared.security;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class SecurityConfig {

    private final JwtSecurityProperties jwtSecurityProperties;
    private final ObjectProvider<StringRedisTemplate> redisTemplateProvider;

    public SecurityConfig(
            JwtSecurityProperties jwtSecurityProperties,
            ObjectProvider<StringRedisTemplate> redisTemplateProvider) {
        this.jwtSecurityProperties = jwtSecurityProperties;
        this.redisTemplateProvider = redisTemplateProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers(
                                                "/actuator/health/**",
                                                "/actuator/info/**",
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**",
                                                "/swagger-ui.html",
                                                "/error")
                                        .permitAll()
                                        .requestMatchers("/actuator/prometheus")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL", "SCOPE_operations.read")
                                        .requestMatchers(
                                                HttpMethod.GET,
                                                "/api/v1/persons/*/sensitive-profile/**",
                                                "/api/v1/persons/*/sensitive-coverages/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL",
                                                "SCOPE_people-school.sensitive.read")
                                        .requestMatchers(
                                                HttpMethod.POST,
                                                "/api/v1/persons/*/sensitive-profile/**",
                                                "/api/v1/persons/*/sensitive-coverages/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL",
                                                "SCOPE_people-school.sensitive.write")
                                        .requestMatchers(
                                                HttpMethod.PUT,
                                                "/api/v1/persons/*/sensitive-profile/**",
                                                "/api/v1/persons/*/sensitive-coverages/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL",
                                                "SCOPE_people-school.sensitive.write")
                                        .requestMatchers(HttpMethod.GET, "/api/v1/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL", "SCOPE_people-school.read")
                                        .requestMatchers(HttpMethod.POST, "/api/v1/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL", "SCOPE_people-school.write")
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL", "SCOPE_people-school.write")
                                        .requestMatchers(HttpMethod.PATCH, "/api/v1/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL", "SCOPE_people-school.write")
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**")
                                        .hasAnyAuthority(
                                                "ROLE_ADMIN_GLOBAL", "SCOPE_people-school.write")
                                        .anyRequest()
                                        .denyAll())
                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(
                                        jwt ->
                                                jwt.decoder(jwtDecoder())
                                                        .jwtAuthenticationConverter(
                                                                jwtConverter())));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey =
                new SecretKeySpec(
                        jwtSecurityProperties.secret().getBytes(StandardCharsets.UTF_8),
                        "HmacSHA256");
        NimbusJwtDecoder jwtDecoder =
                NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

        OAuth2TokenValidator<Jwt> validator =
                new DelegatingOAuth2TokenValidator<>(
                        JwtValidators.createDefaultWithIssuer(jwtSecurityProperties.issuer()),
                        new AccessTokenTypeValidator(),
                        new RequiredIamClaimsValidator(),
                        new RevokedAccessTokenValidator(
                                redisTemplateProvider, jwtSecurityProperties));
        jwtDecoder.setJwtValidator(validator);
        return jwtDecoder;
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtConverter() {
        JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();

        JwtGrantedAuthoritiesConverter rolesConverter = new JwtGrantedAuthoritiesConverter();
        rolesConverter.setAuthorityPrefix("ROLE_");
        rolesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                jwt -> {
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.addAll(scopesConverter.convert(jwt));
                    authorities.addAll(rolesConverter.convert(jwt));
                    return authorities;
                });
        converter.setPrincipalClaimName("username");

        return jwt -> {
            AbstractAuthenticationToken token = converter.convert(jwt);
            if (token == null) {
                return new JwtAuthenticationToken(
                        jwt, scopesConverter.convert(jwt), jwt.getSubject());
            }
            return token;
        };
    }
}
