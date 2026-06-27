package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api.admin;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.AddMemberRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.HouseholdMemberResponse;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto.UpdateMemberRequest;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.AddMemberToHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.GetHouseholdMembersUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.RemoveMemberFromHouseholdUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.usecase.UpdateMembershipUseCase;
import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.householdmembership.HouseholdMembership;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/households/{householdId}/members")
@RequiredArgsConstructor
@Tag(name = "Household Members", description = "Household membership management")
@SecurityRequirement(name = "bearer-jwt")
public class HouseholdMemberController {

    private final CurrentTenantResolver tenantResolver;
    private final AddMemberToHouseholdUseCase addMemberUseCase;
    private final GetHouseholdMembersUseCase getMembersUseCase;
    private final UpdateMembershipUseCase updateMembershipUseCase;
    private final RemoveMemberFromHouseholdUseCase removeMemberUseCase;

    @PostMapping
    @Operation(summary = "Add a person to a household")
    public ResponseEntity<HouseholdMemberResponse> addMember(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long householdId,
            @Valid @RequestBody AddMemberRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        HouseholdMembership membership = addMemberUseCase.execute(householdId, request, tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(HouseholdMemberResponse.from(membership));
    }

    @GetMapping
    @Operation(summary = "List members of a household")
    public ResponseEntity<Page<HouseholdMemberResponse>> listMembers(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long householdId,
            @PageableDefault(size = 20) Pageable pageable) {
        String tenantId = tenantResolver.resolve(jwt);
        Page<HouseholdMembership> page = getMembersUseCase.execute(householdId, tenantId, pageable);
        return ResponseEntity.ok(page.map(HouseholdMemberResponse::from));
    }

    @PutMapping("/{membershipId}")
    @Operation(summary = "Update a household membership")
    public ResponseEntity<HouseholdMemberResponse> updateMember(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long householdId,
            @PathVariable Long membershipId,
            @Valid @RequestBody UpdateMemberRequest request) {
        String tenantId = tenantResolver.resolve(jwt);
        HouseholdMembership membership =
                updateMembershipUseCase.execute(membershipId, request, tenantId);
        return ResponseEntity.ok(HouseholdMemberResponse.from(membership));
    }

    @DeleteMapping("/{membershipId}")
    @Operation(summary = "Remove a person from a household")
    public ResponseEntity<Void> removeMember(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long householdId,
            @PathVariable Long membershipId) {
        String tenantId = tenantResolver.resolve(jwt);
        removeMemberUseCase.execute(membershipId, tenantId);
        return ResponseEntity.noContent().build();
    }
}
