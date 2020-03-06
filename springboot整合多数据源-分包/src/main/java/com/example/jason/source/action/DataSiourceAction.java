package com.example.jason.source.action;

import com.example.jason.source.entity.Score;
import com.example.jason.source.entity.Student;
import com.example.jason.source.service.ScoreService;
import com.example.jason.source.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/2/25  11:58
 * @Description 测试多数据源
 */
@RestController
public class DataSiourceAction {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "hello dataSource")
    public  void dataSource(){
        List<Score> scores =  scoreService.queyAll();
        for(Score score: scores){
            System.out.println(score);
        }

        Student student = new Student(92,12,"刘三",16,1);
        studentService.insert(student);
    }
}
