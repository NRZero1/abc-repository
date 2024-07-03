package com.phincon.backend.bootcamp.abc_hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.phincon.backend.bootcamp.abc_hospital.dto.DiseaseRequest;
import com.phincon.backend.bootcamp.abc_hospital.exception.NoSuchObjectExists;
import com.phincon.backend.bootcamp.abc_hospital.exception.ObjectAlreadyExists;
import com.phincon.backend.bootcamp.abc_hospital.model.Disease;
import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;
import com.phincon.backend.bootcamp.abc_hospital.repository.DiseaseRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiseaseService {
    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    MedicineRepository medicineRepository;

    public List<Disease> getAll() {
        return diseaseRepository.findAll();
    }

    public Disease save(DiseaseRequest request) {
        try {
            Disease disease = new Disease();
            Optional<Medicine> medicineRow = medicineRepository.findById(request.getMedicineId());

            if (!medicineRow.isPresent()) {
                throw new NoSuchObjectExists("Medicine not found with id: " + request.getMedicineId());
            }

            Medicine medicine = medicineRow.get();

            disease.setName(request.getName());
            disease.setMedicine(medicine);

            Disease object = diseaseRepository.save(disease);

            if (object != null) {
                log.info("New disease entry is saved");
            }

            return object;
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException: " + e);
            throw new ObjectAlreadyExists("Disease already exists!");
        } catch (Exception e) {
            log.error("An unexpected error occured: " + e);
            throw e;
        }
    }
}
