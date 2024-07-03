package com.phincon.backend.bootcamp.abc_hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;

import jakarta.transaction.Transactional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query("""
            UPDATE
                Medicine m
            SET
                m.stock = m.stock - 1
            WHERE
                m.medicineId = :medicineId
            """)
    @Transactional
    int decrementStock(long medicineId);
}
