package com.example.laboratory4.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Prescription findPrescriptionById(String id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    public List<Prescription> listPrescriptions(int option, String search) throws Exception {
        return prescriptionRepository.listPrescriptions(option, search);
    }

    public void deletePrescription(String id) {
        prescriptionRepository.deleteById(id);
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public void modifyPrescription(Prescription prescription) throws Exception {
        prescriptionRepository.modifyPrescription(prescription);
    }
}
