package com.example.laboratory4.Patients;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepositoryCustom {
    void modifyPatient(Patient patient) throws Exception;

    List<Patient> listPatients(int option, String search) throws Exception;
}
