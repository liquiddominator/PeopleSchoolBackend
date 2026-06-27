package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreatePersonRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.PersonResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.PersonSummaryResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.RegistryStatsResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdatePersonRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.CreatePersonUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeletePersonUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPersonByIdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPersonsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetRegistryStatsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdatePersonUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.person.Person;
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
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
@Tag(name = "Persons", description = "Person management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreatePersonUseCase createPersonUseCase;
    private final GetPersonsUseCase getPersonsUseCase;
    private final GetPersonByIdUseCase getPersonByIdUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;
    private final GetRegistryStatsUseCase getRegistryStatsUseCase;

    @PostMapping
    @Operation(summary = "Create a new person")
    public ResponseEntity<PersonResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreatePersonRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        Person person = createPersonUseCase.execute(request, tenantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonResponse.from(person));
    }

    @GetMapping
    @Operation(summary = "List persons with optional filters")
    public ResponseEntity<Page<PersonSummaryResponse>> list(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam(required = false) String personTypeCode,
            @RequestParam(required = false) String coreStatus,
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        String tenantId = tenantResolver.resolve(jwt);
        Page<Person> page =
                getPersonsUseCase.execute(tenantId, personTypeCode, coreStatus, search, pageable);
        return ResponseEntity.ok(page.map(PersonSummaryResponse::from));
    }

    @GetMapping("/stats")
    @Operation(summary = "Get aggregate registry stats for Chapter 0 dashboard")
    public ResponseEntity<RegistryStatsResponse> getStats(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(getRegistryStatsUseCase.execute(tenantId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID")
    public ResponseEntity<PersonResponse> getById(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        Person person = getPersonByIdUseCase.execute(id, tenantId);
        return ResponseEntity.ok(PersonResponse.from(person));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update person")
    public ResponseEntity<PersonResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @Valid @RequestBody UpdatePersonRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        Person person = updatePersonUseCase.execute(id, request, tenantId);
        return ResponseEntity.ok(PersonResponse.from(person));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete person")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deletePersonUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
