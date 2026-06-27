package com.solveria.iamservice.application.dto;

import java.util.List;

public record AuthMeResponse(
        Long userId,
        String username,
        String email,
        boolean active,
        String tenantId,
        List<String> roles,
        List<String> scopes) {}
