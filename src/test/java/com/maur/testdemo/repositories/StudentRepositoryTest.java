package com.maur.testdemo.repositories;

import com.maur.testdemo.models.Gender;
import com.maur.testdemo.models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        String email = "jamila@gmail.com";
        Student student = new Student(
                //1L,
                "Jamila",
                "Andrews",
                email,
                Gender.FEMALE
        );
        underTest.save(student);
        // when
        boolean expected = underTest.selectExistsByEmail(email);
        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        // given
        String email = "jamila@gmail.com";

        // when
        boolean expected = underTest.selectExistsByEmail(email);
        // then
        assertThat(expected).isFalse();
    }
}