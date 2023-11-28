package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    List<Student> getStudents() {
        List<Student> studentsList = new ArrayList<Student>();
        studentsList.add(new Student("sumit", "kumar", 1));
        studentsList.add(new Student("dodo", "jojo", 2));
        studentsList.add(new Student("deepa", "soni", 3));
        // jackson will auto convert the studentsList(java pojo) into json objects array
        return studentsList;
    }

}
