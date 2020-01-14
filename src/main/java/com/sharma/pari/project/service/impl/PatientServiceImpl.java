package com.sharma.pari.project.service.impl;

import com.sharma.pari.project.repository.PatientRepository;
import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.resource.Province;
import com.sharma.pari.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int totalAdmitPatient(Province province) {
        return patientRepository.totalAdmitPatient(province);
    }

    @Override
    public List<PatientDisease> commonDisease() {
        return patientRepository.commonDisease();
    }

}
