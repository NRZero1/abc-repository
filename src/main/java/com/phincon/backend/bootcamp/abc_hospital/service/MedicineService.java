package com.phincon.backend.bootcamp.abc_hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.phincon.backend.bootcamp.abc_hospital.repository.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

import com.phincon.backend.bootcamp.abc_hospital.exception.NoSuchObjectExists;
import com.phincon.backend.bootcamp.abc_hospital.exception.ObjectAlreadyExists;
import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    public Medicine save(Medicine medicine) {
        try {
            Medicine object = medicineRepository.save(medicine);
            if (object != null) {
                log.info("New medicine entry is saved");
            }
            return object;
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException: " + e);
            throw new ObjectAlreadyExists("Medicine already exists!");
        } catch (Exception e) {
            log.error("An unexpected error occured: " + e);
            throw e;
        }
    }

    public boolean decrementStock(Medicine medicine) {
        try {
            Optional<Medicine> medicineData = medicineRepository.findById(medicine.getMedicineId());
            if (!medicineData.isPresent()) {
                throw new NoSuchObjectExists("No medicine data found with id: " + medicine.getMedicineId());
            }
            return medicineRepository.decrementStock(medicineData.get().getMedicineId()) > 0;
        } catch (DataAccessException e) {
            log.error("Data access erorr: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
        }
        return false;
    }
}
