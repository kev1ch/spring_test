package edu.spring_practice.services;

import edu.spring_practice.jpa.entities.Professor;
import edu.spring_practice.jpa.repositories.CourseRepository;
import edu.spring_practice.jpa.repositories.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProfessorServiceTest {

    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    ProfessorService professorService;

    @Test
    void getProfQuantity() {
        Professor prof_1 = new Professor();
        Professor prof_2 = new Professor();
        Mockito.when(professorRepository.findAll()).thenReturn(List.of(prof_1, prof_2));
        int prof_quantity = professorService.getProfQuantity();
        assertEquals(2, prof_quantity);
    }

    @Test
    void getSum() {
        int sum = professorService.getSum(1, 2);
        assertEquals(3, sum);
    }

}
