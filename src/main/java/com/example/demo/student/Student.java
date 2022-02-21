package com.example.demo.student;

import lombok.Data;

@Data
public class Student {
    private final Integer id;
    private final String name;

    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }
}
