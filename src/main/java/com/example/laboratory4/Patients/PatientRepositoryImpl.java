package com.example.laboratory4.Patients;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void modifyPatient(Patient patient) throws Exception {
        try {
            // Check if patient object and ID are provided
            if (patient == null || patient.getId() == null) {
                throw new Exception("Patient and ID must be provided.");
            }

            // Find the existing patient record by ID
            Patient existingPatient = mongoTemplate.findById(patient.getId(), Patient.class);
            if (existingPatient == null) {
                throw new Exception("Patient not found with ID: " + patient.getId());
            }

            // Prepare update operations
            Query query = new Query(Criteria.where("_id").is(patient.getId()));
            Update update = new Update()
                    .set("fullName", patient.getFullName())
                    .set("dateOfBirth", patient.getDateOfBirth())
                    .set("gender", patient.getGender())
                    .set("address", patient.getAddress())
                    .set("phone", patient.getPhone())
                    .set("insuranceNumber", patient.getInsuranceNumber())
                    .set("medicalHistory", patient.getMedicalHistory())
                    .set("allergies", patient.getAllergies())
                    .set("bloodType", patient.getBloodType());

            mongoTemplate.updateFirst(query, update, Patient.class);
        } catch (Exception e) {
            throw new Exception("Error modifying patient: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Patient> listPatients(int option, String search) throws Exception {
        try {
            Query query = new Query();

            // Escape special regex characters in search string to prevent regex injection
            String escapedSearch = search.replaceAll("([\\[\\]{}()*+?.$^|])", "\\\\$1");

            switch (option) {
                case 1:
                    query.addCriteria(Criteria.where("_id").is(convertToObjectId(escapedSearch)));
                    break;
                case 2:
                    query.addCriteria(Criteria.where("fullName").regex(escapedSearch, "i"));
                    break;
                case 3:
                    query.addCriteria(Criteria.where("dateOfBirth").regex(escapedSearch, "i"));
                    break;
                case 4:
                    query.addCriteria(Criteria.where("gender").regex(escapedSearch, "i"));
                    break;
                case 5:
                    query.addCriteria(Criteria.where("address").regex(escapedSearch, "i"));
                    break;
                case 6:
                    query.addCriteria(Criteria.where("phone").regex(escapedSearch, "i"));
                    break;
                case 7:
                    query.addCriteria(Criteria.where("insuranceNumber").regex(escapedSearch, "i"));
                    break;
                case 8:
                    query.addCriteria(Criteria.where("medicalHistory").regex(escapedSearch, "i"));
                    break;
                case 9:
                    query.addCriteria(Criteria.where("allergies").regex(escapedSearch, "i"));
                    break;
                case 10:
                    query.addCriteria(Criteria.where("bloodType").regex(escapedSearch, "i"));
                    break;
                case 11:
                    return mongoTemplate.findAll(Patient.class);
                default:
                    throw new Exception("Invalid option");
            }

            return mongoTemplate.find(query, Patient.class);
        } catch (Exception e) {
            throw new Exception("Error listing patients: " + e.getMessage(), e);
        }
    }

    private ObjectId convertToObjectId(String id) throws PatientException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new PatientException("Invalid ObjectId format: " + id);
        }
    }
}
