package com.sharma.pari.project.service.impl;

import com.sharma.pari.project.model.Patient;
import com.sharma.pari.project.repository.PatientRepository;
import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.resource.PatientDto;
import com.sharma.pari.project.resource.Province;
import com.sharma.pari.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public void addPatient(PatientDto patientDto) {
        patientRepository.save(convertToPatient(patientDto));
    }

    @Override
    public void updatePatient(Patient patient) {
        if (StringUtils.isEmpty(patient.getDischargeDate())) {
            patient.setIsDischarge(false);
        } else {
            patient.setIsDischarge(true);
            patient.setInsurance(patientRepository.findById(patient.getId()).getInsurance());
            patient.setDisease(patientRepository.findById(patient.getId()).getDisease());
            patientRepository.save(patient);
        }

    }

    private Patient convertToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setId(21);
        patient.setAdmitDate(patientDto.getAdmitDate());
        patient.setFullName(patientDto.getFullName());
        patient.setDisease(patientDto.getDisease());
        patient.setInsurance(patientDto.getInsurance());
        patient.setIsDischarge(false);
        patient.setProvince(patientDto.getProvince());
        return patient;
    }

    @Override
    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findAllDischargePatient() {
        return patientRepository.findAllDischargePatient();
    }

    @Override
    public Patient findById(int id) {
        return patientRepository.findById(id);
    }


}
