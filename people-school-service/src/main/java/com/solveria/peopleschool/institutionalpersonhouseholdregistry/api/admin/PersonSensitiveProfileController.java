package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateSensitiveProfileRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.SensitiveProfileResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateSensitiveProfileRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreateSensitiveProfileUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetSensitiveProfileUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateSensitiveProfileUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/sensitive-profile")
@RequiredArgsConstructor
@Tag(name = "Person Sensitive Profile", description = "Person sensitive profile management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonSensitiveProfileController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateSensitiveProfileUseCase createUseCase;
    private final UpdateSensitiveProfileUseCase updateUseCase;
    private final GetSensitiveProfileUseCase getUseCase;

    @GetMapping
    @Operation(summary = "Get sensitive profile for a person")
    public ResponseEntity<SensitiveProfileResponse> get(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return getUseCase
                .execute(personId, tenantId)
                .map(p -> ResponseEntity.ok(SensitiveProfileResponse.from(p)))
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @Operation(summary = "Create sensitive profile for a person")
    public ResponseEntity<SensitiveProfileResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateSensitiveProfileRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateSensitiveProfileRequest normalizedRequest =
                new CreateSensitiveProfileRequest(
                        personId,
                        request.bloodTypeCode(),
                        request.emergencyMedicalNotes(),
                        request.sensitiveProfileStatus(),
                        request.lastReviewedAt(),
                        request.lastReviewedBy());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        SensitiveProfileResponse.from(
                                createUseCase.execute(normalizedRequest, tenantId)));
    }

    @PutMapping("/{profileId}")
    @Operation(summary = "Update sensitive profile for a person")
    public ResponseEntity<SensitiveProfileResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @PathVariable Long profileId,
            @Valid @RequestBody UpdateSensitiveProfileRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                SensitiveProfileResponse.from(
                        updateUseCase.execute(profileId, personId, request, tenantId)));
    }
}
