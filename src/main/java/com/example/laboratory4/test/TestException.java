package com.example.laboratory4.test;

public class TestException extends Exception {

    public TestException(String message) {
        super(message);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
}
