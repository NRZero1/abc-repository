package com.phincon.backend.bootcamp.abc_hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.backend.bootcamp.abc_hospital.dto.DiseaseRequest;
import com.phincon.backend.bootcamp.abc_hospital.model.Disease;
import com.phincon.backend.bootcamp.abc_hospital.service.DiseaseService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/disease")
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;

    @GetMapping
    public HttpEntity<List<Disease>> getAll() {
        return new ResponseEntity<>(diseaseService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Disease> save(@Valid @RequestBody DiseaseRequest disease) {
        log.debug(disease.toString());
        return new ResponseEntity<>(diseaseService.save(disease), HttpStatus.CREATED);
    }
}
