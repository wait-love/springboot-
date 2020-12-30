package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.example.demo.dds.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/11/18  21:47
 * @Description  配置了多个数据源
 */

@SpringBootConfiguration
@MapperScan("com.example.demo.dao")
public class MybatiesConfig {

    /**
     * 定义一个默认的数据库
     * 从配置文件中直接拿取配置好的主数据库配置
     * @return
     */
    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master(){
        return new DruidDataSource();
    }


    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave(){
        return new DruidDataSource();
    }

    /**
     * 配置动态数据源类
     * @return
     * @throws Exception
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() throws Exception {
        DynamicDataSource sqlSessionFactoryBean = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        //将配置好的数据源添加到动态数据源中去
        dataSourceMap.put("master", master());
        dataSourceMap.put("slave", slave());
        //指定默认的数据源
        sqlSessionFactoryBean.setDefaultDataSource(master());
        //将已经配置好的类添加
        sqlSessionFactoryBean.setDataSources(dataSourceMap);
        return sqlSessionFactoryBean;
    }

    /**
     * 创建一个sqlSessionFactory类
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //设置好动态数据类
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.example.demo.model");
        //扫描到xml位置的并且注入到工厂中去
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));
        return sessionFactory;
    }

    /**
     * 对动态数据源开启事务管理
     * @return
     * @throws Exception
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws Exception {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    /**
     * 配置一个Druid的监控
     * 配置一个管理后台的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();

        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", ""); //默认就是允许所有的访问
        initParams.put("deny", "");

        bean.setInitParameters(initParams);
        return bean;
    }


    /**
     * 这是设置一个filter类
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclutions", "*.js, *.css, /druid/*");
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setInitParameters(initParams);
        return bean;
    }
}
