package com.sharma.pari.project.repository;

import com.sharma.pari.project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    int countByAdmitDate(Date admitDate);
}
