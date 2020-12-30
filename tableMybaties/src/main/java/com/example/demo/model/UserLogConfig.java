package com.example.demo.model;

import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserLogConfig implements Serializable {
    private Long id;

    private String name;

    private String tableName;

    public UserLogConfig(String name, String tableName) {
        this.name = name;
        this.tableName = tableName;
    }
}