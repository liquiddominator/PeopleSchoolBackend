package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateGuardianRelationshipRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.GuardianRelationshipResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetGuardianRelationshipsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterGuardianRelationshipUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/guardian-relationships")
@RequiredArgsConstructor
@Tag(name = "Guardian Relationships", description = "Guardian relationship management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonGuardianRelationshipController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterGuardianRelationshipUseCase registerUseCase;
    private final GetGuardianRelationshipsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List guardian relationships where person is the student")
    public ResponseEntity<List<GuardianRelationshipResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.executeByStudent(personId, tenantId).stream()
                        .map(GuardianRelationshipResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register a guardian relationship")
    public ResponseEntity<GuardianRelationshipResponse> register(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateGuardianRelationshipRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateGuardianRelationshipRequest normalizedRequest =
                new CreateGuardianRelationshipRequest(
                        personId,
                        request.guardianPersonId(),
                        request.householdId(),
                        request.guardianTypeCode(),
                        request.legalAuthorityStatus(),
                        request.schoolAuthorityStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.notes());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        GuardianRelationshipResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
