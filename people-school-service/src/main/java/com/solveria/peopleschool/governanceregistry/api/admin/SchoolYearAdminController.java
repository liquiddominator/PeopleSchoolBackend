package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.CreateSchoolYearRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.SchoolYearResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateSchoolYearRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.ActivateSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteSchoolYearUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetSchoolYearsUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateSchoolYearUseCase;
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
@RequestMapping("/api/v1/governance/school-years")
@RequiredArgsConstructor
@Tag(name = "Governance - School Years")
@SecurityRequirement(name = "bearer-jwt")
public class SchoolYearAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateSchoolYearUseCase createUseCase;
    private final GetSchoolYearsUseCase getUseCase;
    private final UpdateSchoolYearUseCase updateUseCase;
    private final ActivateSchoolYearUseCase activateUseCase;
    private final DeleteSchoolYearUseCase deleteUseCase;

    @PostMapping
    @Operation(summary = "Create a school year")
    public ResponseEntity<SchoolYearResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateSchoolYearRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SchoolYearResponse.from(createUseCase.execute(request, tenantId)));
    }

    @GetMapping
    @Operation(summary = "List all school years for the tenant")
    public ResponseEntity<List<SchoolYearResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream().map(SchoolYearResponse::from).toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a school year")
    public ResponseEntity<SchoolYearResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateSchoolYearRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                SchoolYearResponse.from(updateUseCase.execute(id, request, tenantId)));
    }

    @PostMapping("/{id}/activate")
    @Operation(summary = "Set a school year as the current default")
    public ResponseEntity<SchoolYearResponse> activate(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(SchoolYearResponse.from(activateUseCase.execute(id, tenantId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a school year")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deleteUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
