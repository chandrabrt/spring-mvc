package com.sharma.pari.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="disease")
@Data
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "disease_id")
    private int id;

    private String name;
}
