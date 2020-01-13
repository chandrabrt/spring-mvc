package com.sharma.pari.project.service.impl;

import com.sharma.pari.project.repository.PatientRepository;
import com.sharma.pari.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public int totalDischarge() {
        return patientRepository.totalDischarge();
    }

    @Override
    public int totalAdmit() {
        return patientRepository.totalAdmit();
    }

    @Override
    public int totalAdmitPatient(String startDate, String endDate) {
        return patientRepository.totalAdmitPatient(startDate, endDate);
    }

    @Override
    public int totalDischargePatient(String startDate, String endDate) {
        return patientRepository.totalDischargePatient(startDate, endDate);
    }

    @Override
    public int findAllPatientByInsuranceName(String name) {
        return patientRepository.findAllPatientByInsuranceName(name);
    }

}
