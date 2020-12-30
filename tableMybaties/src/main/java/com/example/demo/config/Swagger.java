package com.example.demo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Jason
 * @Create: 2020/11/18  21:41
 * @Description
 */

@SpringBootConfiguration
@EnableSwagger2  //开启swagger文档
public class Swagger {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("springboot api doc")
                .description("springboot 动态创建表格的api")
                .version("1.0")
                .build();
    }
}


