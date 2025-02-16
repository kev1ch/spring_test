package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Student;
import edu.spring_test.jpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @PostMapping
    public ResponseEntity<Integer> newStudent(String name, Integer group, Float gpa) {
        ResponseEntity<Integer> response;
        Student new_student = new Student();
        new_student.setName(name);
        new_student.setGroup(group);
        new_student.setGpa(gpa);
        Student result_student = studentRepository.saveAndFlush(new_student);
        response = new ResponseEntity<>(result_student.getId(), HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        ResponseEntity<List<Student>> response;
        List<Student> student_list = studentRepository.findAll();
        response = new ResponseEntity<>(student_list, HttpStatus.OK);
        return response;
    }

}
