package com.solveria.iamservice.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(@NotBlank(message = "refreshToken is required") String refreshToken) {}
