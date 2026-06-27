package com.solveria.peopleschool.studentregistry.api.admin;

import com.solveria.peopleschool.shared.tenancy.CurrentTenantResolver;
import com.solveria.peopleschool.studentregistry.application.dto.CreateStudentProfileRequest;
import com.solveria.peopleschool.studentregistry.application.dto.StudentProfileResponse;
import com.solveria.peopleschool.studentregistry.application.usecase.CreateStudentProfileUseCase;
import com.solveria.peopleschool.studentregistry.application.usecase.GetStudentProfilesUseCase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Students - Student Profiles (HU 3.1)")
@SecurityRequirement(name = "bearer-jwt")
public class StudentProfileAdminController {

    private final CurrentTenantResolver tenantResolver;
    private final CreateStudentProfileUseCase createUseCase;
    private final GetStudentProfilesUseCase getUseCase;

    @PostMapping
    public ResponseEntity<StudentProfileResponse> create(
            @AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CreateStudentProfileRequest req) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StudentProfileResponse.from(createUseCase.execute(req, tenantId)));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfileResponse>> list(@AuthenticationPrincipal Jwt jwt) {
        String tenantId = tenantResolver.resolve(jwt);
        return ResponseEntity.ok(
                getUseCase.execute(tenantId).stream().map(StudentProfileResponse::from).toList());
    }
}
