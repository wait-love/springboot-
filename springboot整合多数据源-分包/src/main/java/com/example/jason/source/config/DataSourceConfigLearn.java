package com.example.jason.source.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author: Jason
 * @Create: 2020/2/25  10:10
 * @Description 从数据源的配置文件类
 */
@Configuration
@MapperScan(basePackages = "com.example.jason.source.mapper.learn", sqlSessionFactoryRef = "learnSqlSessionFactory")
public class DataSourceConfigLearn{

    @Bean(name = "learnDataSource")                              //将对象注入到ioc容器中
    @ConfigurationProperties(prefix = "spring.datasource.learn")
    public DataSource getDataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "learnSqlSessionFactory")
    public SqlSessionFactory loexSqlSessionFactory(@Qualifier("learnDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/learn/*.xml"));
        return bean.getObject();

    }

    @Bean("learnSqlSessionTemplate")
    public SqlSessionTemplate test1sqlsessiontemplate(@Qualifier("learnSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}