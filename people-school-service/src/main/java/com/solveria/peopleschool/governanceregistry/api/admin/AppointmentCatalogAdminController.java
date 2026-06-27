package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.AppointmentCatalogResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.CreateAppointmentCatalogRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateAppointmentCatalogRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetAppointmentCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateAppointmentCatalogUseCase;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
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
@RequestMapping("/api/v1/governance/appointment-catalog")
@RequiredArgsConstructor
@Tag(name = "Governance - Appointment Catalog")
@SecurityRequirement(name = "bearer-jwt")
public class AppointmentCatalogAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateAppointmentCatalogUseCase createUseCase;
    private final GetAppointmentCatalogUseCase getUseCase;
    private final UpdateAppointmentCatalogUseCase updateUseCase;
    private final DeleteAppointmentCatalogUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<AppointmentCatalogResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateAppointmentCatalogRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AppointmentCatalogResponse.from(createUseCase.execute(req, tenantId)));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentCatalogResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream()
                        .map(AppointmentCatalogResponse::from)
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentCatalogResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentCatalogRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                AppointmentCatalogResponse.from(updateUseCase.execute(id, req, tenantId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        deleteUseCase.execute(id, tenantResolver.resolve(jwt));
        return ResponseEntity.noContent().build();
    }
}
