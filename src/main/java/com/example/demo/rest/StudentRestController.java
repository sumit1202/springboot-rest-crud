package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentsList;

    @PostConstruct
    public void loadData() {
        studentsList = new ArrayList<Student>();
        studentsList.add(new Student("sumit", "kumar", 1));
        studentsList.add(new Student("dodo", "jojo", 2));
        studentsList.add(new Student("deepa", "soni", 3));
    }

    @GetMapping("/students")
    List<Student> getStudents() {
        // jackson will auto convert the studentsList(java pojo) into json objects array
        return studentsList;
    }

    @GetMapping("/students/{studentId}")
    Student getStudent(@PathVariable Integer studentId) {
        if (studentId >= studentsList.size() || studentId < 0) {
            throw new StudentNotFoundException("Oops! Student with id " + studentId + " not found");
        } else {
            // jackson will auto convert the studentsList(java pojo) into json objects array
            return studentsList.get(studentId);
        }
    }

    // add expetion handler to catch StudentNotFoundException specifically
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        // jackson will convert pojo response to nice json response
        return new ResponseEntity<StudentErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // add expetion handler to catch other edge exception cases
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        // jackson will convert pojo response to nice json response
        return new ResponseEntity<StudentErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
