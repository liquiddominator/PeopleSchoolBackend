package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AppointmentResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateAppointmentRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreateSchoolOrganizationalAppointmentUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetAppointmentsUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/appointments")
@RequiredArgsConstructor
@Tag(name = "School Appointments", description = "School organizational appointment management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonAppointmentController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateSchoolOrganizationalAppointmentUseCase createUseCase;
    private final GetAppointmentsUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List school organizational appointments for a person")
    public ResponseEntity<List<AppointmentResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(AppointmentResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Create a school organizational appointment for a person")
    public ResponseEntity<AppointmentResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateAppointmentRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateAppointmentRequest normalizedRequest =
                new CreateAppointmentRequest(
                        personId,
                        request.appointmentCode(),
                        request.organizationalUnitId(),
                        request.appointmentStatus(),
                        request.startedAt(),
                        request.endedAt());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AppointmentResponse.from(createUseCase.execute(normalizedRequest, tenantId)));
    }
}
