package com.example.laboratory4.medicalHistory;

import org.springframework.stereotype.Repository;
import java.util.List; 

@Repository
public interface MedicalHistoryRepositoryCustom {
    void modifyMedicalHistory(MedicalHistory medicalHistory) throws Exception;
    List<MedicalHistory> listMedicalHistorys(int option, String search) throws Exception; 
} 
