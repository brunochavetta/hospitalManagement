package com.example.laboratory4.prescription;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionRepositoryImpl implements PrescriptionRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Prescription> listPrescriptions(int option, String search) throws PrescriptionException {
        String escapedSearch = search.replaceAll("([\\[\\]{}()*+?.$^|])", "\\\\$1");
        Query query = new Query();

        switch (option) {
            case 1:
                query.addCriteria(Criteria.where("_id").is(convertToObjectId(escapedSearch)));
                break;
            case 2:
                query.addCriteria(Criteria.where("creationDate").regex(escapedSearch, "i"));
                break;
            case 3:
                query.addCriteria(Criteria.where("dosage").regex(escapedSearch, "i"));
                break;
            case 4:
                query.addCriteria(Criteria.where("frequency").regex(escapedSearch, "i"));
                break;
            case 5:
                query.addCriteria(Criteria.where("treatmentDuration").regex(escapedSearch, "i"));
                break;
            case 6:
                query.addCriteria(Criteria.where("patient.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 7:
                query.addCriteria(Criteria.where("doctor.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 8:
                query.addCriteria(Criteria.where("medication.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 9:
                return mongoTemplate.findAll(Prescription.class);
            default:
                throw new PrescriptionException("Invalid option");
        }

        return mongoTemplate.find(query, Prescription.class);
    }

    private ObjectId convertToObjectId(String id) throws PrescriptionException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new PrescriptionException("Invalid ObjectId format: " + id);
        }
    }

    @Override
    public void modifyPrescription(Prescription prescription) throws PrescriptionException {
        if (prescription == null || prescription.getId() == null) {
            throw new PrescriptionException("prescription and ID must be provided.");
        }

        Prescription existingPrescription = mongoTemplate.findById(prescription.getId(), Prescription.class);
        if (existingPrescription == null) {
            throw new PrescriptionException("prescription not found with ID: " + prescription.getId());
        }

        Query query = new Query(Criteria.where("_id").is(prescription.getId()));
        Update update = new Update()
                .set("creationDate", prescription.getCreationDate())
                .set("dosage", prescription.getDosage())
                .set("frequency", prescription.getFrequency())
                .set("treatmentDuration", prescription.getTreatmentDuration())
                .set("patient", prescription.getPatient())
                .set("doctor", prescription.getDoctor())
                .set("medication", prescription.getMedication());

        mongoTemplate.updateFirst(query, update, Prescription.class);
    }
}
