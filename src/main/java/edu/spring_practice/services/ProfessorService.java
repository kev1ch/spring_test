package edu.spring_practice.services;

import edu.spring_practice.jpa.entities.Course;
import edu.spring_practice.jpa.entities.Professor;
import edu.spring_practice.jpa.repositories.CourseRepository;
import edu.spring_practice.jpa.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Professor assignCoursesToProfessor(Integer id, List<String> course_codes) {
        Professor professor = professorRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Professor not found"));

        List<Course> courses = courseRepository.findAllById(course_codes);

        professor.getCourses().addAll(courses);
        Professor result_professor = professorRepository.saveAndFlush(professor);

        return result_professor;
    }

}
