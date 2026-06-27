package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AffiliationResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateAffiliationRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetAffiliationsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterAffiliationUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/affiliations")
@RequiredArgsConstructor
@Tag(name = "Institutional Affiliations", description = "Institutional affiliation management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonAffiliationController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterAffiliationUseCase registerUseCase;
    private final GetAffiliationsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List institutional affiliations for a person")
    public ResponseEntity<List<AffiliationResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(AffiliationResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register an institutional affiliation")
    public ResponseEntity<AffiliationResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateAffiliationRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateAffiliationRequest normalizedRequest =
                new CreateAffiliationRequest(
                        personId,
                        request.affiliationTypeCode(),
                        request.siteId(),
                        request.organizationalUnitId(),
                        request.affiliationStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        AffiliationResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
