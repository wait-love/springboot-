package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Jason
 * @Create: 2020/11/30  19:46
 * @Description 用作redis中的消息实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    //会话id
    private String sessionId;

    //消息体
    private String message;
}
