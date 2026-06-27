package com.solveria.iamservice.infrastructure.persistence.repository;

import com.solveria.iamservice.infrastructure.persistence.entity.RefreshTokenEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByTokenIdAndTenantIdAndRevokedFalse(
            String tokenId, String tenantId);
}
