package com.phincon.backend.bootcamp.abc_hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;
import com.phincon.backend.bootcamp.abc_hospital.service.MedicineService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @GetMapping
    public HttpEntity<List<Medicine>> getAll() {
        return new ResponseEntity<>(medicineService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Medicine> save(@Valid @RequestBody Medicine medicine) {
        return new ResponseEntity<>(medicineService.save(medicine), HttpStatus.CREATED);
    }
}
