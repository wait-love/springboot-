package com.example.demo.dds;

/**
 * @Author: Jason
 * @Create: 2020/12/7  21:16
 * @Description 枚举类
 */
public enum DelayTypeEnum {

    /**
     * 延迟的10秒数
     */
    Delay_10(10)
    ,

    /**
     * 延迟的60秒
     */
    Delay_60(60);

    private Integer type;

    public  Integer getType() {
        return type;
    }

    private DelayTypeEnum(Integer type){
        this.type = type;
    }

}
