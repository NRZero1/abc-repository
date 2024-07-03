package com.phincon.backend.bootcamp.abc_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentRequest {
    @NotNull(message = "patientId must not be null")
    private long patientId;
    @NotNull(message = "diseaseId must not be null")
    private List<Long> diseaseId;
    @NotNull(message = "doctorId must not be null")
    private long doctorId;
    @NotNull(message = "treatment cost must not be null")
    private long totalTreatmentCost;
}
