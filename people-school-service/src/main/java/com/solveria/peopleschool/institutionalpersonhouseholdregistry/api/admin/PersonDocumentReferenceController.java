package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateDocumentReferenceRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.DocumentReferenceResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetDocumentReferencesUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RegisterDocumentReferenceUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/document-references")
@RequiredArgsConstructor
@Tag(name = "Person Document References", description = "Person document reference management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonDocumentReferenceController {

    private final CurrentTenantResolver tenantResolver;
    private final RegisterDocumentReferenceUseCase registerUseCase;
    private final GetDocumentReferencesUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List document references for a person")
    public ResponseEntity<List<DocumentReferenceResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(DocumentReferenceResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Register a document reference for a person")
    public ResponseEntity<DocumentReferenceResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateDocumentReferenceRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateDocumentReferenceRequest normalizedRequest =
                new CreateDocumentReferenceRequest(
                        personId,
                        request.documentReferenceTypeCode(),
                        request.assetId(),
                        request.referenceStatus(),
                        request.notes());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        DocumentReferenceResponse.from(
                                registerUseCase.execute(normalizedRequest, tenantId)));
    }
}
