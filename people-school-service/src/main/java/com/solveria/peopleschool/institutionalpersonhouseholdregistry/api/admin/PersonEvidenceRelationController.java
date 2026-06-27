package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateEvidenceRelationRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.EvidenceRelationResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetEvidenceRelationsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterEvidenceRelationUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/evidence-relations")
@RequiredArgsConstructor
@Tag(name = "Person Evidence Relations", description = "Person evidence relation management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonEvidenceRelationController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterEvidenceRelationUseCase registerUseCase;
    private final GetEvidenceRelationsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List evidence relations for a person")
    public ResponseEntity<List<EvidenceRelationResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(EvidenceRelationResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register an evidence relation for a person")
    public ResponseEntity<EvidenceRelationResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateEvidenceRelationRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateEvidenceRelationRequest normalizedRequest =
                new CreateEvidenceRelationRequest(
                        personId,
                        request.householdId(),
                        request.relatedContextType(),
                        request.relatedContextRefId(),
                        request.evidenceRoleCode(),
                        request.assetId(),
                        request.evidenceStatus());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        EvidenceRelationResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
