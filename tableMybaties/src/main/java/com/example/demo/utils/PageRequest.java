package com.example.demo.utils;

import lombok.Data;

/**
 * @Author: Jason
 * @Create: 2020/11/20  20:51
 * @Description 分页请求的类
 */

@Data
public class PageRequest {

    private int pageNum;

    private int  pageSize;
}
