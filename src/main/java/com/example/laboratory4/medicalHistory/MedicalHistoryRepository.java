package com.example.laboratory4.medicalHistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String>, MedicalHistoryRepositoryCustom{
}
