package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.peopleschool.governanceregistry.domain.unitsite.SiteStatus;
import com.solveria.peopleschool.governanceregistry.domain.unitsite.UnitSite;
import org.springframework.stereotype.Component;

@Component
public class UnitSiteMapper {

    public UnitSite toDomain(UnitSiteJpaEntity e) {
        return new UnitSite(
                e.getId(),
                e.getEducationUnitId(),
                e.getSiteCode(),
                e.getSiteName(),
                e.getAddressLine(),
                e.getCityName(),
                e.getDepartmentName(),
                e.getPhoneNumber(),
                e.getEmailAddress(),
                e.isMainSite(),
                SiteStatus.valueOf(e.getSiteStatus()),
                e.getTenantId(),
                e.getCreatedAt(),
                e.getCreatedBy(),
                e.getLastModifiedAt(),
                e.getLastModifiedBy(),
                e.getVersion());
    }

    public UnitSiteJpaEntity toNewEntity(UnitSite d) {
        UnitSiteJpaEntity e = new UnitSiteJpaEntity();
        e.setTenantId(d.tenantId());
        e.setEducationUnitId(d.educationUnitId());
        e.setSiteCode(d.siteCode());
        e.setSiteName(d.siteName());
        e.setAddressLine(d.addressLine());
        e.setCityName(d.cityName());
        e.setDepartmentName(d.departmentName());
        e.setPhoneNumber(d.phoneNumber());
        e.setEmailAddress(d.emailAddress());
        e.setMainSite(d.isMainSite());
        e.setSiteStatus(d.siteStatus().name());
        return e;
    }

    public void updateEntity(UnitSiteJpaEntity e, UnitSite d) {
        e.setSiteCode(d.siteCode());
        e.setSiteName(d.siteName());
        e.setAddressLine(d.addressLine());
        e.setCityName(d.cityName());
        e.setDepartmentName(d.departmentName());
        e.setPhoneNumber(d.phoneNumber());
        e.setEmailAddress(d.emailAddress());
        e.setMainSite(d.isMainSite());
        e.setSiteStatus(d.siteStatus().name());
    }
}
