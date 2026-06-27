package com.solveria.peopleschool.governanceregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_school_year")
@Getter
@Setter
@NoArgsConstructor
public class SchoolYearJpaEntity extends BaseEntity {

    @Column(name = "education_unit_id", nullable = false)
    private Long educationUnitId;

    @Column(name = "school_year_code", nullable = false, length = 20)
    private String schoolYearCode;

    @Column(name = "school_year_name", nullable = false, length = 120)
    private String schoolYearName;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "lifecycle_status", nullable = false, length = 30)
    private String lifecycleStatus;

    @Column(name = "is_current_default", nullable = false)
    private boolean isCurrentDefault;

    @Column(name = "is_visible_for_query", nullable = false)
    private boolean isVisibleForQuery = true;

    @Column(name = "is_editable", nullable = false)
    private boolean isEditable = true;

    @Column(name = "is_reportable", nullable = false)
    private boolean isReportable = true;

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived = false;

    @Column(name = "context_priority", nullable = false)
    private int contextPriority = 0;
}
