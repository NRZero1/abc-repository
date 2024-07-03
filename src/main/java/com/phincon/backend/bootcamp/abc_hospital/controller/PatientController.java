package com.phincon.backend.bootcamp.abc_hospital.controller;

import org.springframework.web.bind.annotation.RestController;

import com.phincon.backend.bootcamp.abc_hospital.service.PatientService;

import jakarta.validation.Valid;

import com.phincon.backend.bootcamp.abc_hospital.dto.PatientRequest;
import com.phincon.backend.bootcamp.abc_hospital.model.Patient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    public HttpEntity<List<Patient>> getAll() {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Patient> save(@Valid @RequestBody PatientRequest patient) {
        return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED);
    }
}
