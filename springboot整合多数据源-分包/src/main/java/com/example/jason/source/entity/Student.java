package com.example.jason.source.entity;

import lombok.Data;

@Data
public class Student {

    private Integer id;

    private Integer studentId;

    private String studentName;

    private Integer studentAge;

    private Integer studentSex;

    public Student(Integer id, Integer studentId, String studentName, Integer studentAge, Integer studentSex) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentSex = studentSex;
    }

    public Student() {
    }
}