package com.sharma.pari.project.service.impl;

import com.sharma.pari.project.repository.PatientRepository;
import com.sharma.pari.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public int countByAdmitDate(Date admitDate) {
        return patientRepository.countByAdmitDate(admitDate);
    }
}
