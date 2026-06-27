package com.solveria.iamservice.api.rest;

import com.solveria.iamservice.api.exception.dto.ApiErrorResponse;
import com.solveria.iamservice.application.dto.CreateRoleRequest;
import com.solveria.iamservice.application.dto.RoleResponse;
import com.solveria.iamservice.application.service.RoleManagementService;
import com.solveria.iamservice.application.service.TenantAuthorizationService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Role management operations")
@SecurityRequirement(name = "bearer-jwt")
public class RoleController {

    private final RoleManagementService roleManagementService;
    private final TenantAuthorizationService tenantAuthorizationService;

    public RoleController(
            RoleManagementService roleManagementService,
            TenantAuthorizationService tenantAuthorizationService) {
        this.roleManagementService = roleManagementService;
        this.tenantAuthorizationService = tenantAuthorizationService;
    }

    @GetMapping
    @Operation(
            operationId = "listRoles",
            summary = "List roles",
            description =
                    "Lists roles visible to the authenticated actor. Global administrators may omit X-Tenant-Id to list all roles; tenant-scoped actors are limited to their tenant.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Roles returned",
                        content =
                                @Content(
                                        array =
                                                @ArraySchema(
                                                        schema =
                                                                @Schema(
                                                                        implementation =
                                                                                RoleResponse
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
    public ResponseEntity<List<RoleResponse>> getRoles(
            @Parameter(description = "Optional tenant UUID used to scope the query")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForRead(tenantId, authentication);
        if (scopedTenantId == null) {
            return ResponseEntity.ok(roleManagementService.findAll());
        }
        return ResponseEntity.ok(roleManagementService.findAllByTenant(scopedTenantId));
    }

    @PostMapping
    @Operation(
            operationId = "createRole",
            summary = "Create a role",
            description =
                    "Creates a tenant role with the supplied name, description, hierarchy level, and permission assignments.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Role created",
                        content = @Content(schema = @Schema(implementation = RoleResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Validation error, duplicate role, or invalid tenant scope",
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
                        description = "Insufficient permissions or hierarchy violation",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<RoleResponse> createRole(
            @Parameter(description = "Optional tenant UUID used to scope role creation")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication,
            @Valid @RequestBody CreateRoleRequest request) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForWrite(
                        tenantId, request.tenantId(), authentication);
        RoleResponse roleResponse =
                roleManagementService.create(scopedTenantId, request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleResponse);
    }
}
