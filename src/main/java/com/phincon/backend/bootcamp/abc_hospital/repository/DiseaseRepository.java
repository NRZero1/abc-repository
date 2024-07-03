package com.phincon.backend.bootcamp.abc_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phincon.backend.bootcamp.abc_hospital.model.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
