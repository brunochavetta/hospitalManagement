package com.example.laboratory4.medication;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MedicationRepositoryImpl implements MedicationRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void modifyMedication(Medication medication) throws Exception {
        try {
            if (medication == null || medication.getId() == null) {
                throw new Exception("Medication and ID must be provided.");
            }

            Medication existingMedication = mongoTemplate.findById(medication.getId(), Medication.class);

            if (existingMedication == null) {
                throw new Exception("Medication not found with ID: " + medication.getId());
            }

            Query query = new Query(Criteria.where("_id").is(medication.getId()));

            Update update = new Update()
                    .set("name", medication.getName())
                    .set("presentation", medication.getPresentation())
                    .set("dosage", medication.getDosage())
                    .set("instruction", medication.getInstruction())
                    .set("contraindications", medication.getContraindications());

            mongoTemplate.updateFirst(query, update, Medication.class);

        } catch (Exception e) {
            throw new Exception("Error modifying medication: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Medication> listMedications(int option, String search) throws Exception {
        try {
            Query query = new Query();
            String escapedSearch = search.replaceAll("([\\[\\]{}()*+?.$^|])", "\\\\$1"); // Escaping special characters
                                                                                         // in regex

            switch (option) {
                case 1:
                    query.addCriteria(Criteria.where("_id").is(convertToObjectId(escapedSearch)));
                    break;
                case 2:
                    query.addCriteria(Criteria.where("name").regex(escapedSearch, "i"));
                    break;
                case 3:
                    query.addCriteria(Criteria.where("presentation").regex(escapedSearch, "i"));
                    break;
                case 4:
                    query.addCriteria(Criteria.where("dosage").regex(escapedSearch, "i"));
                    break;
                case 5:
                    query.addCriteria(Criteria.where("instruction").regex(escapedSearch, "i"));
                    break;
                case 6:
                    query.addCriteria(Criteria.where("contraindication").regex(escapedSearch, "i"));
                    break;
                case 7:
                    return mongoTemplate.findAll(Medication.class);
                default:
                    throw new Exception("Invalid option");
            }

            return mongoTemplate.find(query, Medication.class);
        } catch (Exception e) {
            throw new Exception("Error listing medications: " + e.getMessage(), e);
        }
    }

    private ObjectId convertToObjectId(String id) throws MedicationException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new MedicationException("Invalid ObjectId format: " + id);
        }
    }
}
