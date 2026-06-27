package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateRoleAssignmentRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.RoleAssignmentResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AssignInstitutionalRoleUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetRoleAssignmentsUseCase;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons/{personId}/roles")
@RequiredArgsConstructor
@Tag(name = "Role Assignments", description = "Institutional role assignment management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonRoleAssignmentController {

    private final CurrentTenantResolver tenantResolver;
    private final AssignInstitutionalRoleUseCase assignUseCase;
    private final GetRoleAssignmentsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List institutional role assignments for a person")
    public ResponseEntity<List<RoleAssignmentResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(RoleAssignmentResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Assign an institutional role to a person")
    public ResponseEntity<RoleAssignmentResponse> assign(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateRoleAssignmentRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateRoleAssignmentRequest normalizedRequest =
                new CreateRoleAssignmentRequest(
                        personId,
                        request.roleCode(),
                        request.roleStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        RoleAssignmentResponse.from(
                                assignUseCase.execute(normalizedRequest, tenantId)));
    }
}
