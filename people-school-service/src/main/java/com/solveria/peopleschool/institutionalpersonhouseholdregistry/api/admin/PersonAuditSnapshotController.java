package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AuditSnapshotResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetAuditSnapshotsUseCase;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons/{personId}/audit-snapshots")
@RequiredArgsConstructor
@Tag(name = "Person Audit Snapshots", description = "Person audit snapshot management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonAuditSnapshotController {

    private final CurrentTenantResolver tenantResolver;
    private final GetAuditSnapshotsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List audit snapshots for a person")
    public ResponseEntity<List<AuditSnapshotResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(AuditSnapshotResponse::from)
                        .toList());
    }
}
