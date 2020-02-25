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
 * @Create: 2020/2/25  9:48
 * @Description 数据源的配置文件类
 * 表示第一个数据源的配置文件类
 * MapperScan是指代mapper中的loex包接口的类路径
 */
@Configuration
@MapperScan(basePackages = "com.example.jason.source.mapper.loex", sqlSessionFactoryRef = "loexSqlSessionFactory")
public class DataSourceConfigLoex {

    @Bean(name = "loexDataSource")                              //将对象注入到ioc容器中
    @Primary                                                    //表示这是一个基本的数据源-默认数据源
    @ConfigurationProperties(prefix = "spring.datasource.loex") //prefix表示参数的前缀
    public DataSource getDataSource1(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "loexSqlSessionFactory")
    @Primary     //Qualifier表示查找spring  ioc容器中名为loexDataSource的数据源
    public SqlSessionFactory loexSqlSessionFactory(@Qualifier("loexDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/loex/*.xml"));
        return bean.getObject();

    }

    @Bean("loexSqlSessionTemplate")
    @Primary //表示默认数据源
    public SqlSessionTemplate test1sqlsessiontemplate(@Qualifier("loexSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
