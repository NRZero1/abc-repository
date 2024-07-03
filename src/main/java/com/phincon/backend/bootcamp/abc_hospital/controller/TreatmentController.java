package com.phincon.backend.bootcamp.abc_hospital.controller;

import org.springframework.web.bind.annotation.RestController;

import com.phincon.backend.bootcamp.abc_hospital.dto.BillResponse;
import com.phincon.backend.bootcamp.abc_hospital.dto.TreatmentRequest;
import com.phincon.backend.bootcamp.abc_hospital.model.Treatment;
import com.phincon.backend.bootcamp.abc_hospital.service.TreatmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/treatment")
@Slf4j
public class TreatmentController {
    @Autowired
    TreatmentService treatmentService;

    @GetMapping
    public HttpEntity<List<Treatment>> getAll() {
        return new ResponseEntity<>(treatmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Treatment> save(@Valid @RequestBody TreatmentRequest treatment) {
        log.info(treatment.toString());
        return new ResponseEntity<>(treatmentService.save(treatment), HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public HttpEntity<Treatment> getDetailByName(@RequestBody Map<String, String> requestBody) {
        String patientName = requestBody.get("name");
        return new ResponseEntity<>(treatmentService.getOne(patientName), HttpStatus.OK);
    }

    @PostMapping("/bill")
    public HttpEntity<BillResponse> getBill(@RequestBody Map<String, String> requestBody) {
        String patientName = requestBody.get("name");
        return new ResponseEntity<BillResponse>(treatmentService.createBill(patientName), HttpStatus.OK);
    }

    // @PostMapping("/validate")
    // public HttpEntity<Map<String, String>> validateDoctorAndMedicine(@RequestBody
    // Map<String, String> requestBody) {
    // String doctorName = requestBody.get("name");
    // String medicineName = requestBody.get("name");
    // return new ResponseEntity<>(treatmentService.getOne(patientName),
    // HttpStatus.OK);
    // }
}
