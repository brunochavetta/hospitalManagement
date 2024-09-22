package com.example.laboratory4.Patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findPatientById(String id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        System.out.println(patient);
        return patient;
    }

    public List<Patient> listPatients(int option, String search) throws Exception {
        return patientRepository.listPatients(option, search);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void modifyPatient(Patient patient) throws Exception {
        patientRepository.modifyPatient(patient);
    }
}
