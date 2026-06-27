package com.solveria.peopleschool.schoolregulationsregistry.api.admin;

import com.solveria.peopleschool.schoolregulationsregistry.application.dto.CreateSchoolRegulationRequest;
import com.solveria.peopleschool.schoolregulationsregistry.application.dto.SchoolRegulationResponse;
import com.solveria.peopleschool.schoolregulationsregistry.application.dto.UpdateSchoolRegulationRequest;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.CreateSchoolRegulationUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.DeleteSchoolRegulationUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.GetSchoolRegulationsUseCase;
import com.solveria.peopleschool.schoolregulationsregistry.application.usecase.UpdateSchoolRegulationUseCase;
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
@RequestMapping("/api/v1/regulations/school-regulations")
@RequiredArgsConstructor
@Tag(name = "Regulations - School Regulations (HU 2.1)")
@SecurityRequirement(name = "bearer-jwt")
public class SchoolRegulationAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateSchoolRegulationUseCase createUseCase;
    private final GetSchoolRegulationsUseCase getUseCase;
    private final UpdateSchoolRegulationUseCase updateUseCase;
    private final DeleteSchoolRegulationUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<SchoolRegulationResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateSchoolRegulationRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SchoolRegulationResponse.from(createUseCase.execute(req, tenantId)));
    }

    @GetMapping
    public ResponseEntity<List<SchoolRegulationResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream().map(SchoolRegulationResponse::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolRegulationResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateSchoolRegulationRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                SchoolRegulationResponse.from(updateUseCase.execute(id, req, tenantId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        deleteUseCase.execute(id, tenantResolver.resolve(jwt));
        return ResponseEntity.noContent().build();
    }
}
