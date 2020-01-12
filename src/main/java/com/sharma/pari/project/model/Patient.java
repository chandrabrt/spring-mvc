package com.sharma.pari.project.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="patient")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private int id;

    @Column(name = "fullname")
    private String fullName;

    @ManyToOne
    private Disease disease;

    @ManyToOne
    private Insurance insurance;

    @Column(name = "admit_date")
    private Date admitDate;

    @Column(name = "discharge_date")
    private Date dischargeDate;

}
