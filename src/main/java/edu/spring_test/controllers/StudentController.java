package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Student;
import edu.spring_test.jpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    // 3 methods: save new student, get student by id, get all students

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<Student> getStudent(int id) {
        ResponseEntity<Student> response;
        Optional<Student> optional_student = studentRepository.findById(id);
        if (optional_student.isPresent()) {
            Student student = optional_student.get();
            response = new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }



}
