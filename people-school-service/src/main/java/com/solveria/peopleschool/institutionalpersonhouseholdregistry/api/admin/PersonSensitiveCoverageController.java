package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateSensitiveCoverageRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.SensitiveCoverageResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetSensitiveCoveragesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterSensitiveCoverageUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/sensitive-coverages")
@RequiredArgsConstructor
@Tag(name = "Person Sensitive Coverages", description = "Person sensitive coverage management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonSensitiveCoverageController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterSensitiveCoverageUseCase registerUseCase;
    private final GetSensitiveCoveragesUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List sensitive coverages for a person")
    public ResponseEntity<List<SensitiveCoverageResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(SensitiveCoverageResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register a sensitive coverage for a person")
    public ResponseEntity<SensitiveCoverageResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateSensitiveCoverageRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateSensitiveCoverageRequest normalizedRequest =
                new CreateSensitiveCoverageRequest(
                        request.personSensitiveProfileId(),
                        personId,
                        request.coverageTypeCode(),
                        request.providerName(),
                        request.policyNumber(),
                        request.coverageStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.notes());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        SensitiveCoverageResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
