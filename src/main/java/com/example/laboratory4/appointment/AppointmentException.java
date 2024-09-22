package com.example.laboratory4.appointment;

public class AppointmentException extends Exception {

    public AppointmentException(String message) {
        super(message);
    }

    public AppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
