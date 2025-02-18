package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Course;
import edu.spring_test.jpa.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/get")
    public ResponseEntity<Course> getCourse(String code) {
        ResponseEntity<Course> response;
        Optional<Course> optional_course = courseRepository.findById(code);
        if (optional_course.isPresent()) {
            Course course = optional_course.get();
            response = new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/new")
    public ResponseEntity<String> newCourse(String code, String name) {
        ResponseEntity<String> response;
        Course new_course = new Course();
        new_course.setCode(code);
        new_course.setName(name);
        Course result_course = courseRepository.saveAndFlush(new_course);
        response = new ResponseEntity<>(result_course.getCode(), HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        ResponseEntity<List<Course>> response;
        List<Course> course_list = courseRepository.findAll();
        response = new ResponseEntity<>(course_list, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Course>> deleteEntry(String code) {
        ResponseEntity<List<Course>> response;
        Optional<Course> optional_course = courseRepository.findById(code);
        if (optional_course.isPresent()) {
            Course to_delete = optional_course.get();
            courseRepository.delete(to_delete);
            List<Course> course_list = courseRepository.findAll();
            response = new ResponseEntity<>(course_list, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
