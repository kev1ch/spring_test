package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Professor;
import edu.spring_test.jpa.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @GetMapping("/get")
    public ResponseEntity<Professor> getProfessor(int id) {
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

    @PostMapping("/new")
    public ResponseEntity<Integer> newProfessor(String name) {
        ResponseEntity<Integer> response;
        Professor new_professor = new Professor();
        new_professor.setName(name);
        Professor result_professor = professorRepository.saveAndFlush(new_professor);
        response = new ResponseEntity<>(result_professor.getId(), HttpStatus.OK);
        return response;
    }

}
