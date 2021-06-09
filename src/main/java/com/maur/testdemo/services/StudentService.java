package com.maur.testdemo.services;

import com.maur.testdemo.exceptions.BadRequestException;
import com.maur.testdemo.exceptions.StudentNotFountException;
import com.maur.testdemo.models.Student;
import com.maur.testdemo.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();

    }

    public Student addStudent(Student student){
        Boolean existsEmail = studentRepository.selectExistsByEmail(student.getEmail());

        if(existsEmail){
            throw new BadRequestException("Email " + student.getEmail() + " has been taken");
        }
        return studentRepository.save(student);

    }

    public void deleteStudent(Long id){
        if(!studentRepository.existsById(id)) {
           throw  new StudentNotFountException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
