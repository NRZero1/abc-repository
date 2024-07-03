package com.phincon.backend.bootcamp.abc_hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private long doctorId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "name must not be blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "specialization must not be blank")
    private String specialization;

    @Column(nullable = false, name = "consultation_fee")
    @Min(value = 1000, message = "min value is 1000")
    private long consultationFee;

    @NotNull(message = "must not be null")
    @Column(nullable = false)
    private boolean active;
}
