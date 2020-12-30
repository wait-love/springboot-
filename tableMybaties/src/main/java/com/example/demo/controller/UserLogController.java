package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dds.DataSource;
import com.example.demo.model.UserLogConfig;
import com.example.demo.service.UserLogConfigService;
import com.example.demo.service.UserLogService;
import com.example.demo.utils.PageRequest;
import com.example.demo.utils.PageResult;
import com.example.demo.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Jason
 * @Create: 2020/11/18  22:07
 * @Description
 */

@RestController
@Api(produces = "用户日志配置表")
public class UserLogController {

    @Autowired
    private UserLogConfigService userLogConfigService;


    @Autowired
    private UserLogService userLogService;


    @Autowired
    private RedisUtil redisUtil;

    @DataSource("master")
    @RequestMapping(value = "table", method = RequestMethod.POST)
    public void insert(){
        LocalDate localDate = LocalDate.now();
        String mdd = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(mdd);
        UserLogConfig userLogConfig = new UserLogConfig( "u2u", "log" + mdd);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(userLogConfig);
            String user = JSON.toJSONString(userLogConfig);
            redisUtil.hset("user", "ues", s, 40000);
            userLogConfigService.save(userLogConfig);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


    @DataSource("master")
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public PageResult pagehelper(@RequestBody PageRequest pageRequest, String table){
        PageResult all = userLogService.findAll(pageRequest, table);
        //将结果存储进缓存中去
        return all;
    }

}
