package com.example.laboratory4.appointment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String>, AppointmentRepositoryCustom {
}
