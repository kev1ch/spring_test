package edu.spring_test.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {

    @Id
    private String code;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<Professor> professors;
}
