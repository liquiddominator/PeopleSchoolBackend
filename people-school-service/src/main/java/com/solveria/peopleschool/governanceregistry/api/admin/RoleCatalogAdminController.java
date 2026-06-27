package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.CreateRoleCatalogRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.RoleCatalogResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateRoleCatalogRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetRoleCatalogUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateRoleCatalogUseCase;
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
@RequestMapping("/api/v1/governance/role-catalog")
@RequiredArgsConstructor
@Tag(name = "Governance - Role Catalog")
@SecurityRequirement(name = "bearer-jwt")
public class RoleCatalogAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateRoleCatalogUseCase createUseCase;
    private final GetRoleCatalogUseCase getUseCase;
    private final UpdateRoleCatalogUseCase updateUseCase;
    private final DeleteRoleCatalogUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<RoleCatalogResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateRoleCatalogRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RoleCatalogResponse.from(createUseCase.execute(req, tenantId)));
    }

    @GetMapping
    public ResponseEntity<List<RoleCatalogResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream().map(RoleCatalogResponse::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleCatalogResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoleCatalogRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                RoleCatalogResponse.from(updateUseCase.execute(id, req, tenantId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        deleteUseCase.execute(id, tenantResolver.resolve(jwt));
        return ResponseEntity.noContent().build();
    }
}
