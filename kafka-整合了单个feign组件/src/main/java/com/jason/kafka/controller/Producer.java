package com.jason.kafka.controller;

import com.jason.kafka.entity.Student;
import com.jason.kafka.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/3/2  14:15
 * @Description 消息的生产者
 */
@RestController
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public  String  send(){
        kafkaTemplate.send("demo", "hello kafka");
        return "success";
    }

    @RequestMapping(value = "message2", method = RequestMethod.GET)
    public List<Student> send2(){
       return studentMapper.selectAll();
    }
}
