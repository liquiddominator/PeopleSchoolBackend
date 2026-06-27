package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.EducationUnitResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateEducationUnitRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetCurrentEducationUnitUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateEducationUnitUseCase;
import com.solveria.peopleschool.governanceregistry.domain.educationunit.EducationUnit;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/governance/education-units")
@RequiredArgsConstructor
@Tag(name = "Governance - Education Unit")
@SecurityRequirement(name = "bearer-jwt")
public class EducationUnitAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final GetCurrentEducationUnitUseCase getCurrentUseCase;
    private final UpdateEducationUnitUseCase updateUseCase;

    @GetMapping("/current")
    @Operation(summary = "Get or auto-create the tenant's education unit")
    public ResponseEntity<EducationUnitResponse> getCurrent(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        EducationUnit unit = getCurrentUseCase.execute(tenantId);
        return ResponseEntity.ok(EducationUnitResponse.from(unit));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update education unit details")
    public ResponseEntity<EducationUnitResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateEducationUnitRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        EducationUnit unit = updateUseCase.execute(id, request, tenantId);
        return ResponseEntity.ok(EducationUnitResponse.from(unit));
    }
}
