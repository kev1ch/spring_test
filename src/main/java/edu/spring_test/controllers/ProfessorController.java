package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Professor;
import edu.spring_test.jpa.repositories.ProfessorRepository;
import edu.spring_test.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable int id) {
        ResponseEntity<Professor> response;
        Optional<Professor> optional_professor = professorRepository.findById(id);
        if (optional_professor.isPresent()) {
            Professor professor = optional_professor.get();
            response = new ResponseEntity<>(professor, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/new/{name}")
    public ResponseEntity<Integer> newProfessor(@PathVariable String name) {
        ResponseEntity<Integer> response;
        Professor new_professor = new Professor();
        new_professor.setName(name);
        Professor result_professor = professorRepository.saveAndFlush(new_professor);
        response = new ResponseEntity<>(result_professor.getId(), HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Professor>> getAllProfessors() {
        ResponseEntity<List<Professor>> response;
        List<Professor> professor_list = professorRepository.findAll();
        response = new ResponseEntity<>(professor_list, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Professor>> deleteEntry(@PathVariable Integer id) {  //TODO change Integers to ints?
        ResponseEntity<List<Professor>> response;
        Optional<Professor> optional_professor = professorRepository.findById(id);
        if (optional_professor.isPresent()) {
            Professor to_delete = optional_professor.get();
            professorRepository.delete(to_delete);  // save&flush ???
            List<Professor> professor_list = professorRepository.findAll();
            response = new ResponseEntity<>(professor_list, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/assign_courses/{id}")
    public ResponseEntity<Professor> assignCoursesToProfessor(@PathVariable Integer id, @RequestBody List<String> course_codes) {
        ResponseEntity<Professor> response;
        Optional<Professor> optional_professor = professorRepository.findById(id);
        if (optional_professor.isPresent()) {
            Professor result_professor = professorService.assignCoursesToProfessor(id, course_codes);
            response = new ResponseEntity<>(result_professor, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
