package com.example.laboratory4.medicalHistory;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryRepositoryImpl implements MedicalHistoryRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MedicalHistory> listMedicalHistorys(int option, String search) throws MedicalHistoryException {
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
                query.addCriteria(Criteria.where("doctorNotes").regex(escapedSearch, "i"));
                break;
            case 4:
                query.addCriteria(Criteria.where("diagnoses").regex(escapedSearch, "i"));
                break;
            case 5:
                query.addCriteria(Criteria.where("testResults").regex(escapedSearch, "i"));
                break;
            case 6:
                query.addCriteria(Criteria.where("prescriptions").regex(escapedSearch, "i"));
                break;
            case 7:
                query.addCriteria(Criteria.where("patient.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 8:
                return mongoTemplate.findAll(MedicalHistory.class);
            default:
                throw new MedicalHistoryException("Invalid option");
        }

        return mongoTemplate.find(query, MedicalHistory.class);
    }

    private ObjectId convertToObjectId(String id) throws MedicalHistoryException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new MedicalHistoryException("Invalid ObjectId format: " + id);
        }
    }

    @Override
    public void modifyMedicalHistory(MedicalHistory medicalHistory) throws MedicalHistoryException {
        if (medicalHistory == null || medicalHistory.getId() == null) {
            throw new MedicalHistoryException("medicalHistory and ID must be provided.");
        }

        MedicalHistory existingMedicalHistory = mongoTemplate.findById(medicalHistory.getId(), MedicalHistory.class);
        if (existingMedicalHistory == null) {
            throw new MedicalHistoryException("MedicalHistory not found with ID: " + medicalHistory.getId());
        }

        Query query = new Query(Criteria.where("_id").is(medicalHistory.getId()));
        Update update = new Update()
                .set("creationDate", medicalHistory.getCreationDate())
                .set("doctorNotes", medicalHistory.getDoctorNotes())
                .set("diagnoses", medicalHistory.getDiagnoses())
                .set("testResults", medicalHistory.getTestResults())
                .set("patient", medicalHistory.getPatient());

        mongoTemplate.updateFirst(query, update, MedicalHistory.class);
    }
}
