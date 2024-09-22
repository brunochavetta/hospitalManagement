package com.example.laboratory4.Patients;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Patient")
public class Patient {
    @Id
    private String id;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String phone;
    private String insuranceNumber;
    private String medicalHistory;
    private String allergies;
    private String bloodType;

    public Patient() {
    }

    public Patient(String fullName, String dateOfBirth, String gender, String address, String phone,
            String insuranceNumber, String medicalHistory, String allergies, String bloodType) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.insuranceNumber = insuranceNumber;
        this.medicalHistory = medicalHistory;
        this.allergies = allergies;
        this.bloodType = bloodType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String toString() {
        return "\n - Patient ID = " + id +
                "\n - Full Name = " + fullName +
                "\n - Date of Birth = " + dateOfBirth +
                "\n - Gender = " + gender +
                "\n - Address = " + address +
                "\n - Phone = " + phone +
                "\n - Insurance Number = " + insuranceNumber +
                "\n - Medical History = " + medicalHistory +
                "\n - Allergies = " + allergies +
                "\n - Blood Type = " + bloodType;
    }
}
