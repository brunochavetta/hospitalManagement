package com.example.laboratory4.Patients;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String>, PatientRepositoryCustom {
}
