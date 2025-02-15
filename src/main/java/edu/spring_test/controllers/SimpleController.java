package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Student;
import edu.spring_test.jpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/sum")
    public int testSum(int a, int b) {
        Student student_1 = new Student();
        student_1.setName("Mark");
        student_1.setGroup(3);
        student_1.setGpa(3.0f);
        Student student_2 = studentRepository.saveAndFlush(student_1);
        return a + b;
    }

}
