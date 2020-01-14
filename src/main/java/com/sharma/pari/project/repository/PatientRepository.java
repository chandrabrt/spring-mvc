package com.sharma.pari.project.repository;

import com.sharma.pari.project.model.Patient;
import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.resource.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select count(*) from Patient p")
    int totalAdmit();

    @Query("SELECT count(*) FROM Patient p WHERE p.province=?1")
    int totalAdmitPatient(Province province);

    @Query("select count(*) from Patient p where p.isDischarge= true")
    int totalDischarge();

    @Query("select count(*) from Patient p where p.admitDate between ?1 and ?2")
    int totalAdmitPatient(String startDate, String endDate);

    @Query("select count(*) from Patient p where p.isDischarge =true and p.dischargeDate between ?1 and ?2")
    int totalDischargePatient(String startDate, String endDate);

    @Query("select count(*) from Patient i where i.insurance.name=?1")
    int findAllPatientByInsuranceName(String name);

    @Query("select new com.sharma.pari.project.resource.PatientDisease(p.disease.id, p.disease.name, count(p.disease.id) as cnt) FROM Patient p group by  p.disease.id order by cnt DESC")
    List<PatientDisease> commonDisease();



}
