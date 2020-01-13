package com.sharma.pari.project.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="insurance")
@Data
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "insurance_id")
    private int id;

    private String name;
}
