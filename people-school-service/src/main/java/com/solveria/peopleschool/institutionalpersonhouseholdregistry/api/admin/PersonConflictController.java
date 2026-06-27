package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.PersonConflictDetailResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetConflictsUseCase;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons/{personId}/conflicts")
@RequiredArgsConstructor
@Tag(name = "Person Conflicts", description = "Person-scoped conflict management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonConflictController {

    private final CurrentTenantResolver tenantResolver;
    private final GetConflictsUseCase getConflictsUseCase;

    @GetMapping
    @Operation(summary = "List conflicts for a specific person")
    public ResponseEntity<List<PersonConflictDetailResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getConflictsUseCase.executeByPerson(personId, tenantId).stream()
                        .map(PersonConflictDetailResponse::from)
                        .toList());
    }
}
