package com.sharma.pari.project.service;

import com.sharma.pari.project.model.Insurance;

import java.util.List;

public interface InsuranceService {
    int totalInsurance();
    List<Insurance> findAllInsurance();

}
