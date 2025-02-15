package edu.spring_test.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "group_num")
    private Integer group;
    private Float gpa;

}
