package com.solveria.peopleschool;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

class PeopleSchoolServiceApplicationTest {

    @Test
    void applicationTypeIsAvailable() {
        assertThat(SpringApplication.class).isNotNull();
        assertThat(PeopleSchoolServiceApplication.class).isNotNull();
    }
}
