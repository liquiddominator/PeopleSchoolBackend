package com.solveria.iamservice.api.rest;

import com.solveria.iamservice.api.exception.dto.ApiErrorResponse;
import com.solveria.iamservice.application.dto.PermissionResponse;
import com.solveria.iamservice.application.service.PermissionQueryService;
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
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permissions")
@Tag(name = "Permissions", description = "Permission management operations")
@SecurityRequirement(name = "bearer-jwt")
public class PermissionController {

    private final PermissionQueryService permissionQueryService;
    private final TenantAuthorizationService tenantAuthorizationService;

    public PermissionController(
            PermissionQueryService permissionQueryService,
            TenantAuthorizationService tenantAuthorizationService) {
        this.permissionQueryService = permissionQueryService;
        this.tenantAuthorizationService = tenantAuthorizationService;
    }

    @GetMapping
    @Operation(
            operationId = "listPermissions",
            summary = "List permissions",
            description =
                    "Lists permission definitions visible to the authenticated actor. Results may be scoped by X-Tenant-Id.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Permissions returned",
                        content =
                                @Content(
                                        array =
                                                @ArraySchema(
                                                        schema =
                                                                @Schema(
                                                                        implementation =
                                                                                PermissionResponse
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
    public ResponseEntity<List<PermissionResponse>> getPermissions(
            @Parameter(description = "Optional tenant UUID used to scope the query")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForRead(tenantId, authentication);
        if (scopedTenantId == null) {
            return ResponseEntity.ok(permissionQueryService.findAll());
        }
        return ResponseEntity.ok(permissionQueryService.findAllByTenant(scopedTenantId));
    }
}
