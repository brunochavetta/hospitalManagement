package com.example.laboratory4.doctors;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, String>, DoctorRepositoryCustom {
}
