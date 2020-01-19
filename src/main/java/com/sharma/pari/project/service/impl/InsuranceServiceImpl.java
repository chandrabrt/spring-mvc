package com.sharma.pari.project.service.impl;

import com.sharma.pari.project.model.Insurance;
import com.sharma.pari.project.repository.InsuranceRepository;
import com.sharma.pari.project.service.InsuranceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }


    @Override
    public int totalInsurance() {
        return insuranceRepository.totalInsurance();
    }

    @Override
    public List<Insurance> findAllInsurance() {
        return insuranceRepository.findAllInsurance();
    }
}
