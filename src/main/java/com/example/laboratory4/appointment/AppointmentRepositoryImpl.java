package com.example.laboratory4.appointment;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRepositoryImpl implements AppointmentRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Appointment> listAppointments(int option, String search) throws AppointmentException {
        String escapedSearch = search.replaceAll("([\\[\\]{}()*+?.$^|])", "\\\\$1");
        Query query = new Query();

        switch (option) {
            case 1:
                query.addCriteria(Criteria.where("_id").is(convertToObjectId(escapedSearch)));
                break;
            case 2:
                query.addCriteria(Criteria.where("appointmentDate").regex(escapedSearch, "i"));
                break;
            case 3:
                query.addCriteria(Criteria.where("appointmentTime").regex(escapedSearch, "i"));
                break;
            case 4:
                query.addCriteria(Criteria.where("appointmentReason").regex(escapedSearch, "i"));
                break;
            case 5:
                query.addCriteria(Criteria.where("status").regex(escapedSearch, "i"));
                break;
            case 6:
                query.addCriteria(Criteria.where("patient.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 7:
                query.addCriteria(Criteria.where("doctor.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 8:
                return mongoTemplate.findAll(Appointment.class);
            default:
                throw new AppointmentException("Invalid option");
        }

        return mongoTemplate.find(query, Appointment.class);
    }

    private ObjectId convertToObjectId(String id) throws AppointmentException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new AppointmentException("Invalid ObjectId format: " + id);
        }
    }

    @Override
    public void modifyAppointment(Appointment appointment) throws AppointmentException {
        if (appointment == null || appointment.getId() == null) {
            throw new AppointmentException("Appointment and ID must be provided.");
        }

        Appointment existingAppointment = mongoTemplate.findById(appointment.getId(), Appointment.class);
        if (existingAppointment == null) {
            throw new AppointmentException("Appointment not found with ID: " + appointment.getId());
        }

        Query query = new Query(Criteria.where("_id").is(appointment.getId()));
        Update update = new Update()
                .set("appointmentDate", appointment.getAppointmentDate())
                .set("appointmentTime", appointment.getAppointmentTime())
                .set("appointmentReason", appointment.getAppointmentReason())
                .set("status", appointment.getStatus())
                .set("patient", appointment.getPatient())
                .set("doctor", appointment.getDoctor());

        mongoTemplate.updateFirst(query, update, Appointment.class);
    }
}
