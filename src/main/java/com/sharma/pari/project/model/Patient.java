package com.sharma.pari.project.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String admitDate;

    @Column(name = "discharge_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dischargeDate;

    @Column(name = "is_discharge")
    private Boolean isDischarge=false;

}