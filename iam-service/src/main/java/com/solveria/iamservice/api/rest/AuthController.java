package com.solveria.iamservice.api.rest;

import com.solveria.iamservice.api.exception.dto.ApiErrorResponse;
import com.solveria.iamservice.application.dto.AuthMeResponse;
import com.solveria.iamservice.application.dto.AuthTokenResponse;
import com.solveria.iamservice.application.dto.ChangePasswordRequest;
import com.solveria.iamservice.application.dto.LoginRequest;
import com.solveria.iamservice.application.dto.LogoutRequest;
import com.solveria.iamservice.application.dto.RefreshTokenRequest;
import com.solveria.iamservice.application.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Authentication and token operations")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(
            operationId = "login",
            summary = "Authenticate a user",
            description =
                    "Validates user credentials for the tenant provided in X-Tenant-Id and returns an access token and refresh token pair.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Authentication succeeded",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = AuthTokenResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Missing or invalid tenant header or request body",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Invalid credentials or inactive user",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "429",
                        description = "Too many login attempts",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<AuthTokenResponse> login(
            @Parameter(
                            description = "Tenant UUID where the user authenticates",
                            example = "00000000-0000-0000-0000-000000000000")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(tenantId, request));
    }

    @PostMapping("/refresh")
    @Operation(
            operationId = "refreshToken",
            summary = "Refresh an authentication session",
            description =
                    "Validates a refresh token, revokes it, and returns a rotated access token and refresh token pair.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Token pair refreshed",
                        content =
                                @Content(
                                        schema =
                                                @Schema(implementation = AuthTokenResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid request body",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Refresh token is invalid, expired, or revoked",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "429",
                        description = "Too many refresh attempts",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<AuthTokenResponse> refresh(
            @Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

    @GetMapping("/me")
    @Operation(
            operationId = "getCurrentUser",
            summary = "Get the authenticated user",
            description =
                    "Returns profile, tenant, role, and scope information for the current access token.",
            security = {@SecurityRequirement(name = "bearer-jwt")})
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Current user returned",
                        content =
                                @Content(schema = @Schema(implementation = AuthMeResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Missing, invalid, expired, or inactive access token",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<AuthMeResponse> me(Authentication authentication) {
        return ResponseEntity.ok(authService.me(authentication));
    }

    @PostMapping("/change-password")
    @Operation(
            operationId = "changePassword",
            summary = "Change the authenticated user's password",
            description =
                    "Changes the password for the current user after validating the current password.",
            security = {@SecurityRequirement(name = "bearer-jwt")})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Password changed"),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid request body or password reuse",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Invalid access token or current password",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<Void> changePassword(
            Authentication authentication, @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(authentication, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    @Operation(
            operationId = "logout",
            summary = "Log out the authenticated session",
            description =
                    "Revokes the provided refresh token and revokes the current access token until it naturally expires.",
            security = {@SecurityRequirement(name = "bearer-jwt")})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Session logged out"),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid request body",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Missing, invalid, or expired token",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<Void> logout(
            Authentication authentication,
            @Parameter(hidden = true) @RequestHeader(value = "Authorization", required = false)
                    String authorizationHeader,
            @Valid @RequestBody LogoutRequest request) {
        authService.logout(
                new RefreshTokenRequest(request.refreshToken()),
                authentication,
                authorizationHeader);
        return ResponseEntity.noContent().build();
    }
}
