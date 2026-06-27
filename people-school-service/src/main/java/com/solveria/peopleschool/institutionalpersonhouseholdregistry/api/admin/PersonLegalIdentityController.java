package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.LegalIdentityRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.LegalIdentityResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddLegalIdentityUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteLegalIdentityUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.ListLegalIdentitiesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateLegalIdentityUseCase;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons/{personId}/legal-identities")
@RequiredArgsConstructor
@Tag(name = "Person Legal Identities", description = "Person legal identity management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonLegalIdentityController {

    private final CurrentTenantResolver tenantResolver;
    private final AddLegalIdentityUseCase addLegalIdentityUseCase;
    private final ListLegalIdentitiesUseCase listLegalIdentitiesUseCase;
    private final UpdateLegalIdentityUseCase updateLegalIdentityUseCase;
    private final DeleteLegalIdentityUseCase deleteLegalIdentityUseCase;

    @GetMapping
    @Operation(summary = "List legal identities for a person")
    public ResponseEntity<List<LegalIdentityResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                listLegalIdentitiesUseCase.execute(personId, tenantId).stream()
                        .map(LegalIdentityResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Add a legal identity to a person")
    public ResponseEntity<LegalIdentityResponse> add(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody LegalIdentityRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        LegalIdentityResponse.from(
                                addLegalIdentityUseCase.execute(personId, request, tenantId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a legal identity")
    public ResponseEntity<LegalIdentityResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @PathVariable Long id,
            @Valid @RequestBody LegalIdentityRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                LegalIdentityResponse.from(
                        updateLegalIdentityUseCase.execute(id, request, tenantId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a legal identity")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deleteLegalIdentityUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
