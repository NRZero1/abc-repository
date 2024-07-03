package com.phincon.backend.bootcamp.abc_hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.phincon.backend.bootcamp.abc_hospital.exception.NoSuchObjectExists;
import com.phincon.backend.bootcamp.abc_hospital.exception.ObjectAlreadyExists;
import com.phincon.backend.bootcamp.abc_hospital.model.Doctor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.phincon.backend.bootcamp.abc_hospital.repository.DoctorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> get(long id) {
        try {
            return doctorRepository.findById(id);
        } catch (NoSuchElementException exception) {
            throw new NoSuchObjectExists("No doctor found");
        }
    }

    public Doctor save(Doctor doctor) {
        try {
            Doctor object = doctorRepository.save(doctor);
            if (object != null) {
                log.info("New doctor entry is saved");
            }
            return object;
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException: " + e);
            throw new ObjectAlreadyExists("Doctor already exists!");
        } catch (Exception e) {
            log.error("An unexpected error occured: " + e);
            throw e;
        }
    }
}
