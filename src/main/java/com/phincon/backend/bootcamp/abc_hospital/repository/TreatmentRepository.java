package com.phincon.backend.bootcamp.abc_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phincon.backend.bootcamp.abc_hospital.model.Patient;
import com.phincon.backend.bootcamp.abc_hospital.model.Treatment;
import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    Optional<Treatment> findByPatient(Patient patient);
}
