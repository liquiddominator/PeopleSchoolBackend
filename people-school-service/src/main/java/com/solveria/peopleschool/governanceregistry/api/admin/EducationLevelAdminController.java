package com.solveria.peopleschool.governanceregistry.api.admin;

import com.solveria.peopleschool.governanceregistry.application.dto.CreateEducationLevelRequest;
import com.solveria.peopleschool.governanceregistry.application.dto.EducationLevelResponse;
import com.solveria.peopleschool.governanceregistry.application.dto.UpdateEducationLevelRequest;
import com.solveria.peopleschool.governanceregistry.application.usecase.CreateEducationLevelUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.DeleteEducationLevelUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.GetEducationLevelsUseCase;
import com.solveria.peopleschool.governanceregistry.application.usecase.UpdateEducationLevelUseCase;
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
@RequestMapping("/api/v1/governance/education-levels")
@RequiredArgsConstructor
@Tag(name = "Governance - Education Levels")
@SecurityRequirement(name = "bearer-jwt")
public class EducationLevelAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateEducationLevelUseCase createUseCase;
    private final GetEducationLevelsUseCase getUseCase;
    private final UpdateEducationLevelUseCase updateUseCase;
    private final DeleteEducationLevelUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<EducationLevelResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateEducationLevelRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EducationLevelResponse.from(createUseCase.execute(req, tenantId)));
    }

    @GetMapping
    public ResponseEntity<List<EducationLevelResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream().map(EducationLevelResponse::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationLevelResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateEducationLevelRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                EducationLevelResponse.from(updateUseCase.execute(id, req, tenantId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        deleteUseCase.execute(id, tenantResolver.resolve(jwt));
        return ResponseEntity.noContent().build();
    }
}
