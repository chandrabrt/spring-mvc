package com.sharma.pari.project.service;

import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.resource.Province;

import java.util.List;
import java.util.Map;

public interface PatientService {
    int totalDischarge();

    int totalAdmit();

    int totalAdmitPatient(String startDate, String endDate);

    int totalDischargePatient(String startDate, String endDate);

    int findAllPatientByInsuranceName(String name);

    int totalAdmitPatient(Province province);

    List<PatientDisease> commonDisease();

}
