package edu.spring_practice.services;

import edu.spring_practice.jpa.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentFileListenerTest {

    @Autowired
    private StudentFileListener studentFileListener;

    @Test
    void readFileToStudentList() {
        assertNotNull(studentFileListener);
        List<Student> students = studentFileListener.readFileToStudentList("src/test/resources/test_data/students.txt");
        assertEquals(3, students.size());
    }

}
