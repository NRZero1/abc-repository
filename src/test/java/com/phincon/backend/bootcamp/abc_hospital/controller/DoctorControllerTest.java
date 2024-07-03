package com.phincon.backend.bootcamp.abc_hospital.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.phincon.backend.bootcamp.abc_hospital.service.DoctorService;

@SpringBootTest
public class DoctorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    DoctorService doctorService;

    @InjectMocks
    DoctorController doctorController;

    // @Test

}
