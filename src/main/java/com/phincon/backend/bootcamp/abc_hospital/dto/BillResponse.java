package com.phincon.backend.bootcamp.abc_hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {
    private String patientName;
    private List<String> medicine;
    private long medicinePriceTotal;
}
