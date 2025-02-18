package edu.spring_test.controllers;

import edu.spring_test.jpa.entities.Professor;
import edu.spring_test.jpa.repositories.ProfessorRepository;
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

    @GetMapping("/all")
    public ResponseEntity<List<Professor>> getAllProfessors() {
        ResponseEntity<List<Professor>> response;
        List<Professor> professor_list = professorRepository.findAll();
        response = new ResponseEntity<>(professor_list, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Professor>> deleteEntry(Integer id) {
        ResponseEntity<List<Professor>> response;
        Optional<Professor> optional_professor = professorRepository.findById(id);
        if (optional_professor.isPresent()) {
            Professor to_delete = optional_professor.get();
            professorRepository.delete(to_delete);
            List<Professor> professor_list = professorRepository.findAll();
            response = new ResponseEntity<>(professor_list, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
