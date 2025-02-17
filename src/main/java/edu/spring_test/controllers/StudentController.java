package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Student;
import edu.spring_test.jpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/get")
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

    @PostMapping("/new")
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

    @PostMapping("/update")
    public ResponseEntity<Student> editStudent(Integer id, String name, Integer group, Float gpa) {
        ResponseEntity<Student> response;
        Optional<Student> optional_student = studentRepository.findById(id);
        if (optional_student.isPresent()) {
            Student student = optional_student.get();
            student.setName(name);
            student.setGroup(group);
            student.setGpa(gpa);
            Student result_student = studentRepository.saveAndFlush(student);
            response = new ResponseEntity<>(result_student, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Student>> deleteEntry(Integer id) {
        ResponseEntity<List<Student>> response;
        Optional<Student> optional_student = studentRepository.findById(id);
        if (optional_student.isPresent()) {
            Student to_delete = optional_student.get();
            studentRepository.delete(to_delete);
            List<Student> student_list = studentRepository.findAll();
            response = new ResponseEntity<>(student_list, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
