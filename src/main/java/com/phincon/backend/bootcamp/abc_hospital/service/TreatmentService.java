package com.phincon.backend.bootcamp.abc_hospital.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.backend.bootcamp.abc_hospital.dto.BillResponse;
import com.phincon.backend.bootcamp.abc_hospital.dto.TreatmentRequest;
import com.phincon.backend.bootcamp.abc_hospital.exception.NoSuchObjectExists;
import com.phincon.backend.bootcamp.abc_hospital.exception.ObjectAlreadyExists;
import com.phincon.backend.bootcamp.abc_hospital.model.Disease;
import com.phincon.backend.bootcamp.abc_hospital.model.Medicine;
import com.phincon.backend.bootcamp.abc_hospital.model.Doctor;
import com.phincon.backend.bootcamp.abc_hospital.model.Patient;
import com.phincon.backend.bootcamp.abc_hospital.model.Treatment;
import com.phincon.backend.bootcamp.abc_hospital.repository.DiseaseRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.DoctorRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.MedicineRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.PatientRepository;
import com.phincon.backend.bootcamp.abc_hospital.repository.TreatmentRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TreatmentService {
    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    MedicineService medicineService;

    @Autowired
    MedicineRepository medicineRepository;

    public List<Treatment> getAll() {
        return treatmentRepository.findAll();
    }

    public Treatment getOne(String name) {
        Optional<Patient> selectedRow = patientRepository.findByName(name);
        if (!selectedRow.isPresent()) {
            throw new NoSuchObjectExists("No patient exist with name: " + name);
        }
        Patient patient = selectedRow.get();
        Optional<Treatment> result = treatmentRepository.findByPatient(patient);
        if (!result.isPresent()) {
            throw new NoSuchObjectExists("No treatment exists with patient name: " + name);
        }
        log.info("Treatment data found");
        return result.get();
    }

    @Transactional
    public Treatment save(TreatmentRequest request) {
        try {
            Treatment treatment = new Treatment();
            Optional<Doctor> doctorRow = doctorRepository.findById(request.getDoctorId());
            Optional<Disease> diseaseCheck;
            List<Disease> diseaseRow = new ArrayList<>();
            Optional<Patient> patientRow = patientRepository.findById(request.getPatientId());

            for (long id : request.getDiseaseId()) {
                diseaseCheck = diseaseRepository.findById(id);

                if (!diseaseCheck.isPresent()) {
                    throw new NoSuchObjectExists("Disease not found with id: " +
                            request.getDiseaseId());
                }

                diseaseRow.add(diseaseCheck.get());
                // Disease disease = diseaseCheck.get();
                // Medicine medicine = disease.getMedicine();
                // medicineService.decrementStock(medicine);
            }

            if (!doctorRow.isPresent()) {
                throw new NoSuchObjectExists("Doctor not found with id: " + request.getDoctorId());
            }

            if (!patientRow.isPresent()) {
                throw new NoSuchObjectExists("Patient not found with id: " + request.getDoctorId());
            }

            Doctor doctor = doctorRow.get();
            Patient patient = patientRow.get();

            treatment.setPatient(patient);
            treatment.setDoctor(doctor);
            treatment.setDisease(diseaseRow);
            treatment.setTotalTreatmentCost(request.getTotalTreatmentCost());

            Treatment object = treatmentRepository.save(treatment);

            if (object != null) {
                log.info("New treatment entry is saved");
            }

            return object;
        } catch (DataIntegrityViolationException e) {
            log.error("DataIntegrityViolationException: " + e);
            throw new ObjectAlreadyExists("Treatment already exists!");
        } catch (Exception e) {
            log.error("An unexpected error occured: " + e);
            throw e;
        }
    }

    public BillResponse createBill(String name) {
        Treatment treatment = getOne(name);

        List<String> medicineName = new ArrayList<>();
        int total = 0;

        for (var iterate : treatment.getDisease()) {
            medicineName.add(iterate.getMedicine().getName());
            total += iterate.getMedicine().getPrice();
        }
        return billDto(treatment.getPatient().getName(), medicineName, total);
    }

    private BillResponse billDto(String patientName, List<String> medicineName, int medicinePriceTotal) {
        return new BillResponse(patientName, medicineName, medicinePriceTotal);
    }
}
