package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateFinancialResponsibleRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.FinancialResponsibleResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetFinancialResponsiblesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterFinancialResponsibleUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/financial-responsibles")
@RequiredArgsConstructor
@Tag(name = "Financial Responsibles", description = "Financial responsible relationship management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonFinancialResponsibleController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterFinancialResponsibleUseCase registerUseCase;
    private final GetFinancialResponsiblesUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List financial responsibles for a person (as beneficiary)")
    public ResponseEntity<List<FinancialResponsibleResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(FinancialResponsibleResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register a financial responsible relationship")
    public ResponseEntity<FinancialResponsibleResponse> register(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateFinancialResponsibleRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateFinancialResponsibleRequest normalizedRequest =
                new CreateFinancialResponsibleRequest(
                        personId,
                        request.responsiblePersonId(),
                        request.householdId(),
                        request.responsibilityStatus(),
                        request.effectiveFrom(),
                        request.effectiveTo(),
                        request.notes());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        FinancialResponsibleResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
