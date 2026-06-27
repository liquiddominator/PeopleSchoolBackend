package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateEmergencyContactRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.EmergencyContactResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetEmergencyContactsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterEmergencyContactUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/emergency-contacts")
@RequiredArgsConstructor
@Tag(name = "Emergency Contacts", description = "Emergency contact management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonEmergencyContactController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterEmergencyContactUseCase registerUseCase;
    private final GetEmergencyContactsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List emergency contacts for a person")
    public ResponseEntity<List<EmergencyContactResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(EmergencyContactResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Add an emergency contact to a person")
    public ResponseEntity<EmergencyContactResponse> register(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateEmergencyContactRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateEmergencyContactRequest normalizedRequest =
                new CreateEmergencyContactRequest(
                        personId,
                        request.contactPersonId(),
                        request.emergencyPriority(),
                        request.relationshipLabel(),
                        request.emergencyStatus());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        EmergencyContactResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
