package com.example.laboratory4.medicalHistory;

public class MedicalHistoryException extends Exception {

    public MedicalHistoryException(String message) {
        super(message);
    }

    public MedicalHistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}