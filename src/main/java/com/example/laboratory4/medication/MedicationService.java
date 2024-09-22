package com.example.laboratory4.medication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public Medication findMedicationById(String id) {
        return medicationRepository.findById(id).orElse(null);
    }

    public void deleteMedication(String id) {
        medicationRepository.deleteById(id);
    }

    public List<Medication> listMedications(int option, String search) throws Exception {
        return medicationRepository.listMedications(option, search);
    }

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public void modifyMedication(Medication medication) throws Exception {
        medicationRepository.modifyMedication(medication);
    }

}
