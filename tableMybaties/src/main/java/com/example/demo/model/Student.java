package com.example.demo.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Integer id;

    private Integer studentId;

    private String studentName;

    private Integer studentAge;

    private Integer studentSex;

}