package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AddressRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AddressResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddAddressUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.DeleteAddressUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.ListAddressesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateAddressUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/addresses")
@RequiredArgsConstructor
@Tag(name = "Person Addresses", description = "Person address management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonAddressController {

    private final CurrentTenantResolver tenantResolver;
    private final AddAddressUseCase addAddressUseCase;
    private final ListAddressesUseCase listAddressesUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    @GetMapping
    @Operation(summary = "List addresses for a person")
    public ResponseEntity<List<AddressResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                listAddressesUseCase.execute(personId, tenantId).stream()
                        .map(AddressResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Add an address to a person")
    public ResponseEntity<AddressResponse> add(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody AddressRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AddressResponse.from(addAddressUseCase.execute(personId, request, tenantId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an address")
    public ResponseEntity<AddressResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @PathVariable Long id,
            @Valid @RequestBody AddressRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                AddressResponse.from(updateAddressUseCase.execute(id, request, tenantId)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an address")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId, @PathVariable Long id) {
        String tenantId = tenantResolver.resolve(jwt);
        deleteAddressUseCase.execute(id, tenantId);
        return ResponseEntity.noContent().build();
    }
}
