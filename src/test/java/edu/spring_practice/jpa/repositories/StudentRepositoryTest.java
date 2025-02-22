package edu.spring_practice.jpa.repositories;

import edu.spring_practice.jpa.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void saveStudentTest() {
        Student test_student = new Student();
        test_student.setName("James");
        test_student.setGroup(1);
        test_student.setGpa(3.5f);
        studentRepository.save(test_student);
        Optional<Student> optional_student = studentRepository.findById(test_student.getId());
        assertTrue(optional_student.isPresent());
        Student test_student_2 = optional_student.get();
        assertEquals(test_student, test_student_2);
    }

}
