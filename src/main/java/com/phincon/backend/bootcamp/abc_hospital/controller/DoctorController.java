package com.phincon.backend.bootcamp.abc_hospital.controller;

import org.springframework.web.bind.annotation.RestController;

import com.phincon.backend.bootcamp.abc_hospital.model.Doctor;
import com.phincon.backend.bootcamp.abc_hospital.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping
    public HttpEntity<List<Doctor>> getAll() {
        return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Doctor> save(@Valid @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.save(doctor), HttpStatus.CREATED);
    }
}
