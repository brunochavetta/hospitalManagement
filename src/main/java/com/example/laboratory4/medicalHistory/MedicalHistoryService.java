package com.example.laboratory4.medicalHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryService {
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory savemedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    public MedicalHistory findmedicalHistoryById(String id) {
        return medicalHistoryRepository.findById(id).orElse(null);
    }

    public List<MedicalHistory> listmedicalHistorys(int option, String search) throws Exception {
        return medicalHistoryRepository.listMedicalHistorys(option, search);
    }

    public void deletemedicalHistory(String id) {
        medicalHistoryRepository.deleteById(id);
    }

    public List<MedicalHistory> findAll() {
        return medicalHistoryRepository.findAll();
    }

    public void modifymedicalHistory(MedicalHistory medicalHistory) throws Exception {
        medicalHistoryRepository.modifyMedicalHistory(medicalHistory);
    }
}
