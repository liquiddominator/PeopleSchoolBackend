package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroup;
import com.solveria.peopleschool.governanceregistry.domain.orggroup.OrgGroupStatus;
import org.springframework.stereotype.Component;

@Component
public class OrgGroupMapper {

    public OrgGroup toDomain(OrgGroupJpaEntity e) {
        return new OrgGroup(
                e.getId(),
                e.getTenantCode(),
                e.getLegalName(),
                e.getCommercialName(),
                e.getTaxIdentifier(),
                e.getCountryCode(),
                e.getDefaultCurrencyCode(),
                e.getDefaultTimezone(),
                OrgGroupStatus.valueOf(e.getOrgGroupStatus()),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public OrgGroupJpaEntity toNewEntity(OrgGroup d) {
        OrgGroupJpaEntity e = new OrgGroupJpaEntity();
        e.setTenantId(d.tenantId());
        e.setTenantCode(d.tenantCode());
        e.setLegalName(d.legalName());
        e.setCommercialName(d.commercialName());
        e.setTaxIdentifier(d.taxIdentifier());
        e.setCountryCode(d.countryCode());
        e.setDefaultCurrencyCode(d.defaultCurrencyCode());
        e.setDefaultTimezone(d.defaultTimezone());
        e.setOrgGroupStatus(d.orgGroupStatus().name());
        return e;
    }

    public void updateEntity(OrgGroupJpaEntity e, OrgGroup d) {
        e.setTenantCode(d.tenantCode());
        e.setLegalName(d.legalName());
        e.setCommercialName(d.commercialName());
        e.setTaxIdentifier(d.taxIdentifier());
        e.setCountryCode(d.countryCode());
        e.setDefaultCurrencyCode(d.defaultCurrencyCode());
        e.setDefaultTimezone(d.defaultTimezone());
        e.setOrgGroupStatus(d.orgGroupStatus().name());
    }
}
