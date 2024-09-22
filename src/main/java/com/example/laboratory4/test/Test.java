package com.example.laboratory4.test;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.laboratory4.Patients.Patient;

@Document(collection = "Test")
public class Test {
    @Id
    private String id;
    private String testType;
    private String testDescription;
    private String results;
    private String testDate;
    @DBRef
    Patient patient;

    public Test() {
    }

    public Test(String testType, String testDescription, String results, String testDate, Patient patient) {
        this.testType = testType;
        this.testDescription = testDescription;
        this.results = results;
        this.testDate = testDate;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
