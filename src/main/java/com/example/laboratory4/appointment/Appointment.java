package com.example.laboratory4.appointment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.example.laboratory4.Patients.Patient;
import com.example.laboratory4.doctors.Doctor;

@Document(collection = "Appointment")
public class Appointment {
    @Id
    private String id;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentReason;
    private String status;
    @DBRef
    private Patient patient;
    @DBRef
    private Doctor doctor;

    public Appointment() {

    }

    public Appointment(String appointmentDate, String appointmentTime, String appointmentReason,
            String status, Patient patient, Doctor doctor) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentReason = appointmentReason;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentReason() {
        return appointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
