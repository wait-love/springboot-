//package com.example.demo.Date;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.TemporalAdjuster;
//import java.time.temporal.TemporalAdjusters;
//
///**
// * @Author: Jason
// * @Create: 2020/11/30  20:54
// * @Description jdk8中的测试类
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class LocalDateTest {
//
//
//    /**
//     * 这是简单的使用localDate工具类
//     */
//    @Test
//    public void testLocalDate(){
//        //获取到年月日的日期
//        LocalDate date = LocalDate.now();  //只能用做年月日格式
//        LocalTime time = LocalTime.now();  //只能用作时分秒
//        LocalDateTime now = LocalDateTime.now();  //专门用来处理年月日，时分秒的工具类
//        log.info("年月日为：{}， 时分秒格式为:{}, 年月日时分秒为:{}", date, time, now);
//    }
//
//    /**
//     * 定制的时间格式的测试方法
//     */
//    @Test
//    public void special(){
//        LocalDate localdate = LocalDate.of(2020, 10, 12);
//        //时分秒
//        LocalTime time = LocalTime.of(21, 45, 45);
//        //年月日，时分秒
//        LocalDateTime dateTime = LocalDateTime.of(2020, 9, 23, 19, 34, 45);
//        log.info("年月日：{}， 时分秒：{}， 年月日时分秒：{}", localdate, time, dateTime);
//    }
//
//
//    /**
//     * 常见的redis中方法以及分别对应的功能作用
//     */
//    @Test
//    public void functionTest(){
//        //获取到具体定制的时间
//        LocalDate date = LocalDate.of(2020, 10, 21);
//        //获取本月的第一天，输出2019-10-01
//        date.with(TemporalAdjusters.firstDayOfMonth());
//        //获取本月的最后一天
//        date.with(TemporalAdjusters.lastDayOfMonth());
//        //本年的第一天
//        date.with(TemporalAdjusters.firstDayOfNextYear());
//        //本年的最后一天
//        date.with(TemporalAdjusters.lastDayOfYear());
//        //本月的第2周的周五，输出时：2020-10-11
//        date.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
//        //下一个月的第一天，当
//        date.with(TemporalAdjusters.firstDayOfNextYear());
//        //加一天，输出是
//        date.plusDays(1);
//        //加一周
//        date.plusWeeks(1L);
//        //加一个月
//        date.plusMonths(1L);
//    }
//
//
//    /**
//     * 时间之间的比较
//     */
//    public void compare(){
//        LocalDate first = LocalDate.now();
//        LocalDate of = LocalDate.of(2015, 12, 23);
//        //两个时间之间的比较
//        boolean before = first.isBefore(of);
//        boolean after = first.isAfter(of);
//        boolean equal = first.isEqual(of);
//        log.info("时间之间的比较值为:{}, {}, {}", before, after, equal);
//    }
//
//    /**
//     * dateToString -- 将一个date字符串化
//     */
//    public void dateToString(){
//        LocalDate now = LocalDate.now();
//        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        log.info("其中时间转换为string的结果为:{}", format);
//    }
//}
