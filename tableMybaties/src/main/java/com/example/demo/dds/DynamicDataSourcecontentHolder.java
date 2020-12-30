package com.example.demo.dds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/11/19  19:53
 * @Description  数据源的上下文类
 */
public class DynamicDataSourcecontentHolder{

    /**
     * 确保多个数据源可见的，互不影响的
     */
    protected static final ThreadLocal<String> contentHolder =   new ThreadLocal<String>() {

        /**
         * 确保只能有一个数据源在其中，不可混用
         * @return
         */
        @Override
        protected String initialValue() {
            return "master";
        }
    };

    /**
     * 数据源的 key集合，用于切换时判断数据源是否存在
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * 切换数据源
     * @param key
     */
    public static void setDataSourceKey(String key) {
        contentHolder.set(key);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceKey() {
        return contentHolder.get();
    }

    /**
     * 重置数据源
     */
    public static void clearDataSourceKey() {
        contentHolder.remove();
    }

    /**
     * 判断是否包含数据源
     * @param key 数据源key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    /**
     * 添加数据源keys
     * @param keys
     * @return
     */
    public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
        return dataSourceKeys.addAll(keys);
    }
}