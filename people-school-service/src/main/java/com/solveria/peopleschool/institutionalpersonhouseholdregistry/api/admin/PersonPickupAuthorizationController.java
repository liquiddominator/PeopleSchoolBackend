package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreatePickupAuthorizationRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.PickupAuthorizationResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPickupAuthorizationsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterPickupAuthorizationUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/pickup-authorizations")
@RequiredArgsConstructor
@Tag(name = "Pickup Authorizations", description = "Pickup authorization management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonPickupAuthorizationController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterPickupAuthorizationUseCase registerUseCase;
    private final GetPickupAuthorizationsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List pickup authorizations for a student person")
    public ResponseEntity<List<PickupAuthorizationResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(PickupAuthorizationResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register a pickup authorization")
    public ResponseEntity<PickupAuthorizationResponse> register(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreatePickupAuthorizationRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreatePickupAuthorizationRequest normalizedRequest =
                new CreatePickupAuthorizationRequest(
                        personId,
                        request.authorizedPersonId(),
                        request.authorizationStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.authorizationScopeCode(),
                        request.notes());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        PickupAuthorizationResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
