package com.example.demo.student;

import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.*;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

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

    @PostMapping(path = "{studentTd}")
    public Student updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody String name){
        logger.info("new post request " + studentId + " : " + name);
        return new Student(studentId, name);
    }



}
