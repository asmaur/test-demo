package com.maur.testdemo.services;

import com.maur.testdemo.exceptions.BadRequestException;
import com.maur.testdemo.models.Gender;
import com.maur.testdemo.models.Student;
import com.maur.testdemo.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStudents() {
        // when
        underTest.getAllStudents();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void CanAddStudent() {
        // given
        String email = "jamila@gmail.com";
        Student student = new Student(
                //1L,
                "Jamila",
                "Andrews",
                email,
                Gender.FEMALE
        );
        // when
        underTest.addStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken(){
        // given
        // given
        String email = "jamila@gmail.com";
        Student student = new Student(
                //1L,
                "Jamila",
                "Andrews",
                email,
                Gender.FEMALE
        );
        given(studentRepository.selectExistsByEmail(student.getEmail())).willReturn(true);
        // then
        assertThatThrownBy(() -> underTest.addStudent(student))
                        .isInstanceOf(BadRequestException.class)
                        .hasMessageContaining("Email " + student.getEmail() + " has been taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}