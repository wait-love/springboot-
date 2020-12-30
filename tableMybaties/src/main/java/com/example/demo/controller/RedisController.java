package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dds.DataSource;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Message;
import com.example.demo.model.Student;
import com.example.demo.model.UserLogConfig;
import com.example.demo.redis.MessageSend;
import com.example.demo.service.AquiredLockWorker;
import com.example.demo.service.DistributedLocker;
import com.example.demo.service.UserLogConfigService;
import com.example.demo.utils.RedisUtil;
import com.mysql.cj.xdevapi.Collection;
import jodd.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @Author: Jason
 * @Create: 2020/11/22  15:51
 * @Description 分布式锁的Controller
 * redis进行分页操作功能
 */

@RestController
public class RedisController {

    @Autowired
    private DistributedLocker distributedLocker;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MessageSend messageSend;

    @Autowired
    private UserLogConfigService userLogConfigService;


    @RequestMapping(value = "index")
    public String index() throws Exception {
        distributedLocker.lock("test", new AquiredLockWorker<Object>(){

            @Override
            public Object invokeAfterLockAquire() throws Exception {
                System.out.println("这里直接进行逻辑处理");
                Thread.sleep(100);
                return null;
            }
        });

        return "hello redis";
    }


    /**
     * 关于 List<?>与List<Object> 以及 List</Object>
     * List<?>只能与List进行转换它与List<Object>是没有关系的
     * @return
     * @throws Exception
     */
    @DataSource("master")
    @RequestMapping(value = "page")
    public List<Student> redisPage(Integer page1, Integer pageSize){
//        List<Student> students = studentMapper.queryDatas();

        //redis中的数据进行分页数据
        Set studentPage = redisUtil.hZget("studentPage", (page1 - 1) * pageSize + 1, page1 * pageSize);
        Object[] objects = studentPage.toArray();
//        List<?> objects1 = CollectionUtils.arrayToList(objects);
//        List<Object> objects2 = Arrays.asList(objects);
        List<Student> students = new ArrayList<Student>(studentPage);
        //将redis中的数据进行删除操作
        redisUtil.hZDel("studentPage",(page1 - 1) * pageSize + 1, page1 * pageSize);
        return students;
    }


    @DataSource("master")
    @RequestMapping("redisMessage")
    public void sendMessage(){
        List<UserLogConfig> all = userLogConfigService.findAll();
        Message message = new Message("122", JSON.toJSONString(all));
        messageSend.messageSend(message);
    }
}
