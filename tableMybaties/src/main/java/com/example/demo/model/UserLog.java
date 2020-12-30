package com.example.demo.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLog {

    @ApiModelProperty("id值")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("操作")
    private String operation;

    private String method;

    private String params;

    private Long time;

    private String ip;
    
    private String tableName;


}