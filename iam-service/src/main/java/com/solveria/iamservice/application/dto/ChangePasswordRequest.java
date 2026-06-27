package com.solveria.iamservice.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "currentPassword is required") String currentPassword,
        @NotBlank(message = "newPassword is required")
                @Size(
                        min = 8,
                        max = 120,
                        message = "newPassword must be between 8 and 120 characters")
                String newPassword) {}
