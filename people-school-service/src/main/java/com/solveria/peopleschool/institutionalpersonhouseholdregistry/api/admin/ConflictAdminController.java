package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.ConflictResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateConflictStatusRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetConflictsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateConflictStatusUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.conflict.PersonConflict;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conflicts")
@RequiredArgsConstructor
@Tag(name = "Conflicts", description = "Person conflict management")
@SecurityRequirement(name = "bearer-jwt")
public class ConflictAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final GetConflictsUseCase getConflictsUseCase;
    private final UpdateConflictStatusUseCase updateConflictStatusUseCase;

    @GetMapping
    @Operation(summary = "List all conflicts for the tenant")
    public ResponseEntity<List<ConflictResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        List<PersonConflict> conflicts = getConflictsUseCase.execute(tenantId);
        return ResponseEntity.ok(conflicts.stream().map(ConflictResponse::from).toList());
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update conflict status")
    public ResponseEntity<ConflictResponse> updateStatus(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateConflictStatusRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        PersonConflict conflict = updateConflictStatusUseCase.execute(id, request, tenantId);
        return ResponseEntity.ok(ConflictResponse.from(conflict));
    }
}
