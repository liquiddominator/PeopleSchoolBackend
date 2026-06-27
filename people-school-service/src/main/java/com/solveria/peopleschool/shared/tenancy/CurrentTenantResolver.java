package com.solveria.peopleschool.shared.tenancy;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CurrentTenantResolver {

    private static final String GLOBAL_TENANT_ID = "00000000-0000-0000-0000-000000000000";
    private static final String GLOBAL_ADMIN_ROLE = "ADMIN_GLOBAL";
    private static final String TENANT_HEADER = "X-Tenant-Id";

    public String resolve(Jwt jwt) {
        String tokenTenantId = requiredUuid(jwt.getClaimAsString("tenant_id"), "token tenant_id");
        if (!isGlobalAdmin(jwt)) {
            return tokenTenantId;
        }

        String requestedTenantId = currentRequestTenantHeader();
        if (requestedTenantId == null || requestedTenantId.isBlank()) {
            return tokenTenantId;
        }
        return requiredUuid(requestedTenantId, TENANT_HEADER);
    }

    public boolean isGlobalAdmin(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        return roles != null
                && roles.stream().anyMatch(role -> GLOBAL_ADMIN_ROLE.equalsIgnoreCase(role));
    }

    private String currentRequestTenantHeader() {
        if (!(RequestContextHolder.getRequestAttributes()
                instanceof ServletRequestAttributes attributes)) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(TENANT_HEADER);
    }

    private String requiredUuid(String value, String source) {
        if (value == null || value.isBlank()) {
            throw new AccessDeniedException(source + " is required");
        }
        try {
            return UUID.fromString(value).toString();
        } catch (IllegalArgumentException ex) {
            throw new AccessDeniedException(source + " must be a valid UUID");
        }
    }
}
