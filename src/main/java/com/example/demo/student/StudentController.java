package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    
    private List<Student> studentList = List.of(
        new Student(1, "김원빈"),
        new Student(2, "김정빈")
    );


    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return studentList
                .stream()
                .filter(student -> studentId.equals(student.getId()))
                .findFirst()
                .orElseThrow();
    }

}
