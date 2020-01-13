package com.sharma.pari.project.service;

public interface PatientService {
    int totalDischarge();

    int totalAdmit();

    int totalAdmitPatient(String startDate, String endDate);

    int totalDischargePatient(String startDate, String endDate);

    int findAllPatientByInsuranceName(String name);

}
