package com.example.laboratory4.prescription;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.laboratory4.Patients.Patient;
import com.example.laboratory4.doctors.Doctor;
import com.example.laboratory4.medication.Medication;

@Document(collection = "Prescription")
public class Prescription {
    @Id
    private String id;
    private String creationDate;
    private String dosage;
    private String frequency;
    private String treatmentDuration;
    @DBRef
    Doctor doctor;
    @DBRef
    Patient patient;
    @DBRef
    Medication medication;

    public Prescription() {
    }

    public Prescription(String creationDate, String dosage, String frequency, String treatmentDuration, Doctor doctor,
            Patient patient, Medication medication) {
        this.creationDate = creationDate;
        this.dosage = dosage;
        this.frequency = frequency;
        this.treatmentDuration = treatmentDuration;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

}
