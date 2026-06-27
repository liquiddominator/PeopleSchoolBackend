package com.solveria.peopleschool.studentregistry.infrastructure.persistence.postgres;

import com.solveria.core.shared.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pc_student_profile")
@Getter
@Setter
@NoArgsConstructor
public class StudentProfileJpaEntity extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "student_code", nullable = false, length = 80)
    private String studentCode;

    @Column(name = "student_status", nullable = false, length = 40)
    private String studentStatus;

    @Column(name = "school_entry_date")
    private LocalDate schoolEntryDate;

    @Column(name = "first_school_year_id")
    private Long firstSchoolYearId;

    @Column(name = "current_operational_status", nullable = false, length = 40)
    private String currentOperationalStatus;
}
