package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.CreateIdentityLinkRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.IdentityLinkResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetPersonIdentityLinksUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.LinkPersonIdentityUseCase;
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
@RequestMapping("/api/v1/persons/{personId}/identity-links")
@RequiredArgsConstructor
@Tag(name = "Person Identity Links", description = "Person IAM identity link management")
@SecurityRequirement(name = "bearer-jwt")
public class PersonIdentityLinkController {

    private final CurrentTenantResolver tenantResolver;
    private final LinkPersonIdentityUseCase linkUseCase;
    private final GetPersonIdentityLinksUseCase getUseCase;

    @GetMapping
    @Operation(summary = "List identity links for a person")
    public ResponseEntity<List<IdentityLinkResponse>> list(
            @AuthenticationPrincipal Jwt jwt, @PathVariable Long personId) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(personId, tenantId).stream()
                        .map(IdentityLinkResponse::from)
                        .toList());
    }

    @PostMapping
    @Operation(summary = "Link a person to an IAM identity")
    public ResponseEntity<IdentityLinkResponse> create(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long personId,
            @Valid @RequestBody CreateIdentityLinkRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        CreateIdentityLinkRequest normalizedRequest =
                new CreateIdentityLinkRequest(
                        personId,
                        request.iamSubjectId(),
                        request.identityProviderCode(),
                        request.identityLinkStatus(),
                        request.linkedAt(),
                        request.linkedBy());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IdentityLinkResponse.from(linkUseCase.execute(normalizedRequest, tenantId)));
    }
}
