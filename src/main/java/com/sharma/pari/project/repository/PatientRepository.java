package com.sharma.pari.project.repository;

import com.sharma.pari.project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select count(*) from Patient p")
    int totalAdmit();

    @Query("select count(*) from Patient p where p.isDischarge= true")
    int totalDischarge();

    @Query("select count(*) from Patient p where p.admitDate between ?1 and ?2")
    int totalAdmitPatient(String startDate, String endDate);

    @Query("select count(*) from Patient p where p.isDischarge =true and p.dischargeDate between ?1 and ?2")
    int totalDischargePatient(String startDate, String endDate);

    @Query("select count(*) from Patient i where i.insurance.name=?1")
    int findAllPatientByInsuranceName(String name);
}
