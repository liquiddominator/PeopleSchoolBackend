package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.CreateUnitSiteRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.UnitSiteResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateUnitSiteRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateUnitSiteUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteUnitSiteUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetUnitSitesUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateUnitSiteUseCase;
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
@RequestMapping("/api/v1/governance/sites")
@RequiredArgsConstructor
@Tag(name = "Governance - Unit Sites")
@SecurityRequirement(name = "bearer-jwt")
public class UnitSiteAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateUnitSiteUseCase createUseCase;
    private final GetUnitSitesUseCase getUseCase;
    private final UpdateUnitSiteUseCase updateUseCase;
    private final DeleteUnitSiteUseCase deleteUseCase;

    @PostMapping
    @Operation(summary = "Register a site for education unit")
    public ResponseEntity<UnitSiteResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateUnitSiteRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UnitSiteResponse.from(createUseCase.execute(request, tenantId)));
    }

    @GetMapping
    @Operation(summary = "List governance sites for tenant")
    public ResponseEntity<List<UnitSiteResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream().map(UnitSiteResponse::from).toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update site")
    public ResponseEntity<UnitSiteResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateUnitSiteRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                UnitSiteResponse.from(updateUseCase.execute(id, request, tenantId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete site")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deleteUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
