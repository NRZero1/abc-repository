package com.phincon.backend.bootcamp.abc_hospital.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequest {
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "age must not be null")
    private int age;

    private String address;

    @NotNull(message = "diseaseId must not be null")
    private List<Long> diseaseId;
}
