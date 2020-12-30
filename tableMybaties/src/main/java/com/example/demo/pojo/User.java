package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: Jason
 * @Create: 2020/12/23  20:11
 * @Description 用户类中的定义
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "item", shards = 1, replicas = 0)
public class User {

    /**
     * 定义主键id
     */
    @Id
    private String id;

    /**
     * 定义用户名称
     */
    @Field(type = FieldType.Keyword)
    private String userName;


    /**
     * 定义密码
     */
    @Field(type = FieldType.Keyword)
    private String password;

    /**
     * 定义价格
     */
    @Field(type = FieldType.Double)
    private Double price;
}
