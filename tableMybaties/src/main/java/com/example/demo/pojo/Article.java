package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @Author: Jason
 * @Create: 2020/12/16  20:22
 * @Description 文章测试类
 * 主要的就是用来对es进行测试
 * document:简单的理解为mysql关系型数据库中的表
 * index:索引可以理解为数据库 database其中名称必须要以indexName来称呼
 *
 */

@Document(indexName = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    /**
     * 对于文档定义的类，我是没有在字段上对它进行定义的属性的
     * 所以才会在后面的查询中导致出现很多的坑
     */

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String content;

    private Integer userId;
    private Integer createTime;
}
