package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.CreateOrgGroupRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.OrgGroupResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateOrgGroupRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateOrgGroupUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetCurrentOrgGroupUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateOrgGroupUseCase;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/governance/org-groups")
@RequiredArgsConstructor
@Tag(name = "Governance - Org Group")
@SecurityRequirement(name = "bearer-jwt")
public class OrgGroupAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateOrgGroupUseCase createUseCase;
    private final GetCurrentOrgGroupUseCase getCurrentUseCase;
    private final UpdateOrgGroupUseCase updateUseCase;

    @PostMapping
    @Operation(summary = "Register an organization root group")
    public ResponseEntity<OrgGroupResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateOrgGroupRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrgGroupResponse.from(createUseCase.execute(request, tenantId)));
    }

    @GetMapping("/current")
    @Operation(summary = "Get or auto-create tenant org group")
    public ResponseEntity<OrgGroupResponse> getCurrent(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(OrgGroupResponse.from(getCurrentUseCase.execute(tenantId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update organization root group")
    public ResponseEntity<OrgGroupResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrgGroupRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                OrgGroupResponse.from(updateUseCase.execute(id, request, tenantId)));
    }
}
