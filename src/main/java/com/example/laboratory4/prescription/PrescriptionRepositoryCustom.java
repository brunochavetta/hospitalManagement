package com.example.laboratory4.prescription;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepositoryCustom {
    void modifyPrescription(Prescription prescription) throws Exception;

    List<Prescription> listPrescriptions(int option, String search) throws Exception;
}
