package com.solveria.iamservice.api.rest;

import com.solveria.iamservice.api.exception.dto.ApiErrorResponse;
import com.solveria.iamservice.application.dto.CreateUserRequest;
import com.solveria.iamservice.application.dto.UpdateUserRequest;
import com.solveria.iamservice.application.dto.UserResponse;
import com.solveria.iamservice.application.service.TenantAuthorizationService;
import com.solveria.iamservice.application.service.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User management operations")
@SecurityRequirement(name = "bearer-jwt")
public class UserController {

    private final UserManagementService userManagementService;
    private final TenantAuthorizationService tenantAuthorizationService;

    public UserController(
            UserManagementService userManagementService,
            TenantAuthorizationService tenantAuthorizationService) {
        this.userManagementService = userManagementService;
        this.tenantAuthorizationService = tenantAuthorizationService;
    }

    @GetMapping
    @Operation(
            operationId = "listUsers",
            summary = "List users",
            description =
                    "Lists users visible to the authenticated actor. Global administrators may omit X-Tenant-Id to list all visible users; tenant-scoped actors are limited to their tenant.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Users returned",
                        content =
                                @Content(
                                        array =
                                                @ArraySchema(
                                                        schema =
                                                                @Schema(
                                                                        implementation =
                                                                                UserResponse
                                                                                        .class)))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Missing or invalid access token",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "403",
                        description = "Insufficient permissions",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<List<UserResponse>> getUsers(
            @Parameter(description = "Optional tenant UUID used to scope the query")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForRead(tenantId, authentication);
        if (scopedTenantId == null) {
            return ResponseEntity.ok(userManagementService.findAll(authentication));
        }
        return ResponseEntity.ok(
                userManagementService.findAllByTenant(scopedTenantId, authentication));
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "getUserById",
            summary = "Get a user",
            description =
                    "Returns a user by ID when the authenticated actor has visibility over the requested tenant and user.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User returned",
                        content = @Content(schema = @Schema(implementation = UserResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Missing or invalid access token",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "403",
                        description = "Insufficient permissions",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "User not found",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<UserResponse> getUserById(
            @Parameter(description = "Optional tenant UUID used to scope the lookup")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication,
            @Parameter(description = "User identifier", example = "1") @PathVariable Long id) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForRead(tenantId, authentication);
        if (scopedTenantId == null) {
            return ResponseEntity.ok(userManagementService.findById(id, authentication));
        }
        return ResponseEntity.ok(
                userManagementService.findById(scopedTenantId, id, authentication));
    }

    @PostMapping
    @Operation(
            operationId = "createUser",
            summary = "Create a user",
            description =
                    "Creates a user, credential, role assignments, and user scopes in the resolved tenant.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "User created",
                        content = @Content(schema = @Schema(implementation = UserResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Validation error, duplicate user, or invalid tenant scope",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Missing or invalid access token",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "403",
                        description = "Insufficient permissions or role hierarchy violation",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<UserResponse> createUser(
            @Parameter(description = "Optional tenant UUID used to scope user creation")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication,
            @Valid @RequestBody CreateUserRequest request) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForWrite(
                        tenantId, request.tenantId(), authentication);
        UserResponse response =
                userManagementService.create(scopedTenantId, request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(
            operationId = "updateUser",
            summary = "Update a user",
            description =
                    "Updates user profile data, role assignments, active status, and scopes for a user visible to the authenticated actor.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User updated",
                        content = @Content(schema = @Schema(implementation = UserResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Validation error or invalid tenant scope",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "Missing or invalid access token",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "403",
                        description = "Insufficient permissions or role hierarchy violation",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(
                        responseCode = "404",
                        description = "User not found",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "Optional tenant UUID used to scope the update")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication,
            @Parameter(description = "User identifier", example = "1") @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForRead(tenantId, authentication);
        if (scopedTenantId == null) {
            return ResponseEntity.ok(userManagementService.update(id, request, authentication));
        }
        return ResponseEntity.ok(
                userManagementService.update(scopedTenantId, id, request, authentication));
    }
}
