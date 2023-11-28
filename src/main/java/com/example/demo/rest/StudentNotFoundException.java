package com.example.demo.rest;

public class StudentNotFoundException extends RuntimeException {
    StudentNotFoundException(String message) {
        super(message);
    }

}
