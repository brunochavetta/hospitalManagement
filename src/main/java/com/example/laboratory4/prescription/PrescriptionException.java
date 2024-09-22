package com.example.laboratory4.prescription;

public class PrescriptionException extends Exception {

    public PrescriptionException(String message) {
        super(message);
    }

    public PrescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
