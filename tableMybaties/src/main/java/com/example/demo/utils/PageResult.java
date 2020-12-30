package com.example.demo.utils;

import lombok.Data;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/11/20  20:52
 * @Description 分页结果封装类
 */
@Data
public class PageResult {

    private int pageNUm;


    private int pageSize;

    private long totalSize;

    private long totalPages;

    private List<?> data;
}
