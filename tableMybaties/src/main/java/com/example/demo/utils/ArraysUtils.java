package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: Jason
 * @Create: 2020/11/29  10:38
 * @Description 数组相关的工具类
 */
@Component
public class ArraysUtils {


    /**
     * 将数组转换为集合的方法
     * @param arrays
     * @return
     */
    private static List arraysToList(Object[] arrays){
        return Arrays.asList(arrays);
        //另外一种转换方法,这个方法不是静态的方法
        //List<?> objects = CollectionUtils.arrayToList(arrays);
    }


    /**
     * 将数组转换为Set类型
     * @param arrays
     * @return
     */
    private static Set arraysToSet(Object[] arrays){
        return new HashSet(Arrays.asList(arrays));
    }


    /**
     * 将集合转换为数组
     * @param list
     * @return
     */
    private static Object[] listToArrays(List list){
        return list.toArray();
    }

    /**
     * 将list转换为Set
     * @param list
     * @return
     */
    private static Set listToSet(List list){
        return new HashSet(list);
    }


    /**
     * set转换为数组
     * @param set
     * @return
     */
    private static Object[] setToArray(Set set){
        return set.toArray();
    }

    /**
     * set转换为list
     * @param set
     * @return
     */
    private static List setToList(Set set){
        return new ArrayList(set);
    }
}
