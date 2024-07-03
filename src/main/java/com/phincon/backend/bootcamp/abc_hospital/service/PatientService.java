package com.phincon.backend.bootcamp.abc_hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.phincon.backend.bootcamp.abc_hospital.repository.PatientRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.DiseaseRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

import com.phincon.backend.bootcamp.abc_hospital.dto.PatientRequest;
import com.phincon.backend.bootcamp.abc_hospital.exception.NoSuchObjectExists;
import com.phincon.backend.bootcamp.abc_hospital.exception.ObjectAlreadyExists;
import com.phincon.backend.bootcamp.abc_hospital.model.Disease;
import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;
import com.phincon.backend.bootcamp.abc_hospital.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    MedicineRepository medicineRepository;

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient save(PatientRequest request) {
        try {
            Patient patient = new Patient();
            Optional<Disease> diseaseCheck;
            List<Disease> diseaseList = new ArrayList<>();

            for (long id : request.getDiseaseId()) {
                diseaseCheck = diseaseRepository.findById(id);

                if (!diseaseCheck.isPresent()) {
                    throw new NoSuchObjectExists("Medicine not found with id: " + request.getDiseaseId());
                }

                diseaseList.add(diseaseCheck.get());
            }

            patient.setName(request.getName());
            patient.setAge(request.getAge());
            patient.setAddress(request.getAddress());
            patient.setDisease(diseaseList);

            log.info(patient.toString());

            Patient object = patientRepository.save(patient);

            if (object != null) {
                log.info("New patient entry is saved");
            }

            return object;
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException: " + e);
            throw new ObjectAlreadyExists("Patient already exists!");
        } catch (Exception e) {
            log.error("An unexpected error occured: " + e);
            throw e;
        }
    }
}
