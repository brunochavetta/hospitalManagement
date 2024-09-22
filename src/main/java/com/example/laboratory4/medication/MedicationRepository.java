package com.example.laboratory4.medication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends MongoRepository<Medication, String>, MedicationRepositoryCustom {
}

