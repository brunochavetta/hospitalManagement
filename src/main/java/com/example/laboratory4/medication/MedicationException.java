package com.example.laboratory4.medication;

public class MedicationException extends Exception {

    public MedicationException(String message) {
        super(message);
    }

    public MedicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
