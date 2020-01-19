package com.sharma.pari.project.service;

import com.sharma.pari.project.model.Patient;
import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.resource.PatientDto;
import com.sharma.pari.project.resource.Province;

import java.util.List;

public interface PatientService {
    int totalDischarge();

    int totalAdmit();

    int totalAdmitPatient(String startDate, String endDate);

    int totalDischargePatient(String startDate, String endDate);

    int findAllPatientByInsuranceName(String name);

    int totalAdmitPatient(Province province);

    List<PatientDisease> commonDisease();

    void addPatient(PatientDto patient);

    List<Patient> findAllPatient();

    List<Patient>  findAllDischargePatient();

    Patient findById(int id);

    void updatePatient(Patient patient) throws Exception;

    int averageLengthOfStay();




}
