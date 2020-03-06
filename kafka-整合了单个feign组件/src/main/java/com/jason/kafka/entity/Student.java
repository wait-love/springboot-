package com.jason.kafka.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

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