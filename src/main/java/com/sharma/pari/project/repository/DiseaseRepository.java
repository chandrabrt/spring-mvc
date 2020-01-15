package com.sharma.pari.project.repository;

import com.sharma.pari.project.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
}
