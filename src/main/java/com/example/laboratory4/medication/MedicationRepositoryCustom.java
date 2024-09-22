package com.example.laboratory4.medication;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepositoryCustom {
    void modifyMedication(Medication medication) throws Exception;
    List<Medication> listMedications(int option, String search) throws Exception;
}
