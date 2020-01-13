package com.sharma.pari.project.repository;

import com.sharma.pari.project.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

    @Query("select count(*) from Insurance i")
    int totalInsurance();

    @Query("select i from Insurance i")
    List<Insurance> findAllInsurance();
}
