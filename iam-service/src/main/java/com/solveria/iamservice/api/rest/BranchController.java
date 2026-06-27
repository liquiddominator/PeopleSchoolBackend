package com.solveria.iamservice.api.rest;

import com.solveria.iamservice.api.exception.dto.ApiErrorResponse;
import com.solveria.iamservice.application.dto.BranchResponse;
import com.solveria.iamservice.application.dto.CreateBranchRequest;
import com.solveria.iamservice.application.service.BranchManagementService;
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
@RequestMapping("/api/v1/branches")
@Tag(name = "Branches", description = "Branch management operations")
@SecurityRequirement(name = "bearer-jwt")
public class BranchController {

    private final BranchManagementService branchManagementService;
    private final TenantAuthorizationService tenantAuthorizationService;

    public BranchController(
            BranchManagementService branchManagementService,
            TenantAuthorizationService tenantAuthorizationService) {
        this.branchManagementService = branchManagementService;
        this.tenantAuthorizationService = tenantAuthorizationService;
    }

    @GetMapping
    @Operation(
            operationId = "listBranches",
            summary = "List tenant branches",
            description =
                    "Lists branches for the resolved tenant. X-Tenant-Id is required when the authenticated actor is not already tenant-scoped.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Branches returned",
                        content =
                                @Content(
                                        array =
                                                @ArraySchema(
                                                        schema =
                                                                @Schema(
                                                                        implementation =
                                                                                BranchResponse
                                                                                        .class)))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Missing or invalid tenant scope",
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
                        description = "Insufficient permissions",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<List<BranchResponse>> getBranches(
            @Parameter(description = "Tenant UUID used to scope the query")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForRead(tenantId, authentication);
        if (scopedTenantId == null) {
            throw new IllegalArgumentException("X-Tenant-Id header is required for branches");
        }
        return ResponseEntity.ok(branchManagementService.findAllByTenant(scopedTenantId));
    }

    @PostMapping
    @Operation(
            operationId = "createBranch",
            summary = "Create a tenant branch",
            description = "Creates a branch for the resolved tenant.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Branch created",
                        content =
                                @Content(schema = @Schema(implementation = BranchResponse.class))),
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
                        description = "Insufficient permissions",
                        content =
                                @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<BranchResponse> createBranch(
            @Parameter(description = "Optional tenant UUID used to scope branch creation")
                    @RequestHeader(value = "X-Tenant-Id", required = false)
                    String tenantId,
            Authentication authentication,
            @Valid @RequestBody CreateBranchRequest request) {
        String scopedTenantId =
                tenantAuthorizationService.resolveTenantForWrite(
                        tenantId, request.tenantId(), authentication);
        BranchResponse response =
                branchManagementService.create(scopedTenantId, request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
