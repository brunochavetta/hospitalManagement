package com.example.laboratory4.test;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class TestRepositoryImpl implements TestRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Test> listTests(int option, String search) throws TestException {
        String escapedSearch = search.replaceAll("([\\[\\]{}()*+?.$^|])", "\\\\$1");
        Query query = new Query();

        switch (option) {
            case 1:
                query.addCriteria(Criteria.where("_id").is(convertToObjectId(escapedSearch)));
                break;
            case 2:
                query.addCriteria(Criteria.where("testType").regex(escapedSearch, "i"));
                break;
            case 3:
                query.addCriteria(Criteria.where("testDescription").regex(escapedSearch, "i"));
                break;
            case 4:
                query.addCriteria(Criteria.where("results").regex(escapedSearch, "i"));
                break;
            case 5:
                query.addCriteria(Criteria.where("testDate").regex(escapedSearch, "i"));
                break;
            case 6:
                query.addCriteria(Criteria.where("patient.$id").is(convertToObjectId(escapedSearch)));
                break;
            case 7:
                return mongoTemplate.findAll(Test.class);
            default:
                throw new TestException("Invalid option");
        }

        return mongoTemplate.find(query, Test.class);
    }

    private ObjectId convertToObjectId(String id) throws TestException {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new TestException("Invalid ObjectId format: " + id);
        }
    }

    @Override
    public void modifyTest(Test test) throws TestException {
        if (test == null || test.getId() == null) {
            throw new TestException("Test and ID must be provided.");
        }

        Test existingTest = mongoTemplate.findById(test.getId(), Test.class);
        if (existingTest == null) {
            throw new TestException("Test not found with ID: " + test.getId());
        }

        Query query = new Query(Criteria.where("_id").is(test.getId()));
        Update update = new Update()
                .set("testType", test.getTestType())
                .set("testDescription", test.getTestDescription())
                .set("results", test.getResults())
                .set("testDate", test.getTestDate())
                .set("patient", test.getPatient());

        mongoTemplate.updateFirst(query, update, Test.class);
    }
}
