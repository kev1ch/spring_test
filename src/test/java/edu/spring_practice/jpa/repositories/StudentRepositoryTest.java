package edu.spring_practice.jpa.repositories;

import edu.spring_practice.jpa.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Sql(scripts = {"/ddl/clean_student_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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

    @Sql(scripts = {"/ddl/add_student.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/ddl/clean_student_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void addStudent() {
        Optional<Student> optional_student = studentRepository.findById(9);
        assertTrue(optional_student.isPresent());
        Student test_student = optional_student.get();
        assertEquals("Ben", test_student.getName());
        assertEquals(6, test_student.getGroup());
        assertEquals(3.6f, test_student.getGpa());

        optional_student = studentRepository.findById(404);
        assertFalse(optional_student.isPresent());
    }

}
