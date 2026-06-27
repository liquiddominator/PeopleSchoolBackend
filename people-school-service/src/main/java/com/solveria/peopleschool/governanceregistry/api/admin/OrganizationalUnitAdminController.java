package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.CreateOrganizationalUnitRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.OrganizationalUnitResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateOrganizationalUnitRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateOrganizationalUnitUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteOrganizationalUnitUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetOrganizationalUnitsUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateOrganizationalUnitUseCase;
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
@RequestMapping("/api/v1/governance/organizational-units")
@RequiredArgsConstructor
@Tag(name = "Governance - Organizational Units")
@SecurityRequirement(name = "bearer-jwt")
public class OrganizationalUnitAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateOrganizationalUnitUseCase createUseCase;
    private final GetOrganizationalUnitsUseCase getUseCase;
    private final UpdateOrganizationalUnitUseCase updateUseCase;
    private final DeleteOrganizationalUnitUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<OrganizationalUnitResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateOrganizationalUnitRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrganizationalUnitResponse.from(createUseCase.execute(req, tenantId)));
    }

    @GetMapping
    public ResponseEntity<List<OrganizationalUnitResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream()
                        .map(OrganizationalUnitResponse::from)
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationalUnitResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrganizationalUnitRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                OrganizationalUnitResponse.from(updateUseCase.execute(id, req, tenantId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        deleteUseCase.execute(id, tenantResolver.resolve(jwt));
        return ResponseEntity.noContent().build();
    }
}
