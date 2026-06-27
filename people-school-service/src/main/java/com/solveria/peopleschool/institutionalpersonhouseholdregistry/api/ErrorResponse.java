package com.solveria.peopleschool.institutionalpersonhouseholdregistry.api;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public record ErrorResponse(
        int status,
        String code,
        String message,
        Map<String, Object> args,
        String path,
        String timestamp) {

    public static ErrorResponse of(
            int status, String code, String message, Map<String, Object> args, String path) {
        return new ErrorResponse(
                status, code, message, args, path, OffsetDateTime.now(ZoneOffset.UTC).toString());
    }
}
