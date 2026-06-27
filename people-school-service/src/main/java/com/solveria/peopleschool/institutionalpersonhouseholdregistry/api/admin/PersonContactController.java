package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.ContactRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.ContactResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddContactUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteContactUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.ListContactsUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateContactUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/contacts")
@RequiredArgsConstructor
@Tag(name = "Person Contacts", description = "Person contact management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonContactController {

    private final CurrentTenantResolver tenantResolver;
    private final AddContactUseCase addContactUseCase;
    private final ListContactsUseCase listContactsUseCase;
    private final UpdateContactUseCase updateContactUseCase;
    private final DeleteContactUseCase deleteContactUseCase;

    @GetMapping
    @Operation(summary = "List contacts for a person")
    public ResponseEntity<List<ContactResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                listContactsUseCase.execute(personId, tenantId).stream()
                        .map(ContactResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Add a contact to a person")
    public ResponseEntity<ContactResponse> add(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody ContactRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ContactResponse.from(addContactUseCase.execute(personId, request, tenantId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a contact")
    public ResponseEntity<ContactResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @PathVariable Long id,
            @Valid @RequestBody ContactRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                ContactResponse.from(updateContactUseCase.execute(id, request, tenantId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deleteContactUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
