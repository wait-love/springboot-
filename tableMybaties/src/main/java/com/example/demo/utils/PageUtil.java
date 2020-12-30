package com.example.demo.utils;

import com.github.pagehelper.PageInfo;

/**
 * @Author: Jason
 * @Create: 2020/11/20  20:54
 * @Description 分页工具类
 */
public class PageUtil {


    public static PageResult getPageResult(PageInfo<?> pageInfo){
        PageResult pageResult = new PageResult();
        pageResult.setPageNUm(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setData(pageInfo.getList());
        return pageResult;
    }
}
