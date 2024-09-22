package com.example.laboratory4.prescription;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, String>, PrescriptionRepositoryCustom {
}
