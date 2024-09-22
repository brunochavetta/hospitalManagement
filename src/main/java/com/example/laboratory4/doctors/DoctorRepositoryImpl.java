package com.example.laboratory4.doctors;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepositoryImpl implements DoctorRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void modifyDoctor(Doctor doctor) throws Exception {
        try {
            if (doctor == null || doctor.getId() == null) {
                throw new Exception("Doctor and ID must be provided.");
            }

            Doctor existingDoctor = mongoTemplate.findById(doctor.getId(), Doctor.class);

            if (existingDoctor == null) {
                throw new Exception("Doctor not found with ID: " + doctor.getId());
            }

            Query query = new Query(Criteria.where("_id").is(doctor.getId()));

            Update update = new Update()
                    .set("fullName", doctor.getFullName())
                    .set("specialty", doctor.getSpecialty())
                    .set("licenseNumber", doctor.getLicenseNumber())
                    .set("yearsOfExperience", doctor.getYearsOfExperience())
                    .set("officeHours", doctor.getOfficeHours())
                    .set("office", doctor.getOffice());

            mongoTemplate.updateFirst(query, update, Doctor.class);

        } catch (Exception e) {
            throw new Exception("Error modifying doctor: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Doctor> listDoctors(int option, String search) throws Exception {
        try {
            Query query = new Query();
            String escapedSearch = search.replaceAll("([\\[\\]{}()*+?.$^|])", "\\\\$1");

            switch (option) {
                case 1:
                    query.addCriteria(Criteria.where("_id").is(convertToObjectId(escapedSearch)));
                    break;
                case 2:
                    query.addCriteria(Criteria.where("fullName").regex(escapedSearch, "i"));
                    break;
                case 3:
                    query.addCriteria(Criteria.where("specialty").regex(escapedSearch, "i"));
                    break;
                case 4:
                    query.addCriteria(Criteria.where("licenseNumber").regex(escapedSearch, "i"));
                    break;
                case 5:
                    int years = Integer.parseInt(search);
                    query.addCriteria(Criteria.where("yearsOfExperience").is(years));
                    break;
                case 6:
                    query.addCriteria(Criteria.where("officeHours").regex(escapedSearch, "i"));
                    break;
                case 7:
                    query.addCriteria(Criteria.where("office").regex(escapedSearch, "i"));
                    break;
                case 8:
                    return mongoTemplate.findAll(Doctor.class);
                default:
                    throw new Exception("Invalid option");
            }

            return mongoTemplate.find(query, Doctor.class);
        } catch (Exception e) {
            throw new Exception("Error listing doctors: " + e.getMessage(), e);
        }
    }

    private ObjectId convertToObjectId(String id) throws DoctorException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new DoctorException("Invalid ObjectId format: " + id);
        }
    }
}
