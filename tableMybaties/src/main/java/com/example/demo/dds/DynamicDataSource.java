package com.example.demo.dds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/11/19  19:50
 * @Description  动态数据源必须继承AbstractRoutingDataSource
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 在启动时加载数据源，切换数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourcecontentHolder.getDataSourceKey();
    }

    /**
     * 设置默认数据源
     * @param defaultDataSource
     */
    public void setDefaultDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    /**
     * 设置数据源
     * @param dataSources
     */
    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourcecontentHolder.addDataSourceKeys(dataSources.keySet());
    }
}
