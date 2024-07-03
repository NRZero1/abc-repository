package com.phincon.backend.bootcamp.abc_hospital.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiseaseRequest {
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "medicine id must not be null")
    private long medicineId;
}
