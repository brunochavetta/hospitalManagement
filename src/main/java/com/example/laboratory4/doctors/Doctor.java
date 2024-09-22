package com.example.laboratory4.doctors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doctor")
public class Doctor {
    @Id
    private String id;
    private String fullName;
    private String specialty;
    private String licenseNumber;
    private int yearsOfExperience;
    private String officeHours;
    private String office;

    public Doctor() {
    }

    public Doctor(String fullName, String specialty, String licenseNumber, int yearsOfExperience, String officeHours,
            String office) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.officeHours = officeHours;
        this.office = office;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "\n - Doctor ID = " + id +
                "\n - Full name = " + fullName +
                "\n - Specialty = " + specialty +
                "\n - License number = " + licenseNumber +
                "\n - Years of experience = " + yearsOfExperience +
                "\n - Office hours = " + officeHours +
                "\n - Office = " + office;
    }

}
