package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateHouseholdRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.HouseholdResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.HouseholdSummaryResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateHouseholdRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreateHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetHouseholdByIdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetHouseholdsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.household.Household;
import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/households")
@RequiredArgsConstructor
@Tag(name = "Households", description = "Household management")
@SecurityRequirement(name = "bearer-jwt")
public class HouseholdAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateHouseholdUseCase createHouseholdUseCase;
    private final GetHouseholdsUseCase getHouseholdsUseCase;
    private final GetHouseholdByIdUseCase getHouseholdByIdUseCase;
    private final UpdateHouseholdUseCase updateHouseholdUseCase;
    private final DeleteHouseholdUseCase deleteHouseholdUseCase;

    @PostMapping
    @Operation(summary = "Create a new household")
    public ResponseEntity<HouseholdResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateHouseholdRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        Household household = createHouseholdUseCase.execute(request, tenantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(HouseholdResponse.from(household));
    }

    @GetMapping
    @Operation(summary = "List households with optional filters")
    public ResponseEntity<Page<HouseholdSummaryResponse>> list(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam(required = false) String householdTypeCode,
            @RequestParam(required = false) String householdStatus,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        String tenantId = tenantResolver.resolve(jwt);
        Page<Household> page =
                getHouseholdsUseCase.execute(
                        tenantId, householdTypeCode, householdStatus, pageable);
        return ResponseEntity.ok(page.map(HouseholdSummaryResponse::from));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get household by ID")
    public ResponseEntity<HouseholdResponse> getById(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        Household household = getHouseholdByIdUseCase.execute(id, tenantId);
        return ResponseEntity.ok(HouseholdResponse.from(household));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update household")
    public ResponseEntity<HouseholdResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdateHouseholdRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        Household household = updateHouseholdUseCase.execute(id, request, tenantId);
        return ResponseEntity.ok(HouseholdResponse.from(household));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete household")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deleteHouseholdUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
