package com.example.laboratory4.medicalHistory;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.laboratory4.Patients.Patient;

@Document(collection = "MedicalHistory")
public class MedicalHistory {
    @Id
    private String id;
    private String creationDate;
    private String doctorNotes;
    private String diagnoses;
    private String testResults;
    private String prescriptions;
    @DBRef
    private Patient patient;

    public MedicalHistory() {
    }

    public MedicalHistory(String creationDate, String doctorNotes, String diagnoses, String testResult,
            String prescriptions, Patient patient) {
        this.creationDate = creationDate;
        this.doctorNotes = doctorNotes;
        this.diagnoses = diagnoses;
        this.testResults = testResult;
        this.prescriptions = prescriptions;
        this.patient = patient;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}
