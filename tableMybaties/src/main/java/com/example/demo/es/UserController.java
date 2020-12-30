package com.example.demo.es;

import com.example.demo.es.inter.UserReposittory;
import com.example.demo.pojo.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/12/23  20:18
 * @Description 用户controller层
 */

@RestController
@Api(produces = "用户控制层的测试方法")
@RequestMapping(value = "user")
@Slf4j
public class UserController {

    @Autowired
    private UserReposittory userReposittory;


    /**
     * 将用户数据进行批量保存
     */
    @RequestMapping(value = "saveAll")
    public void save(){
        List<User> list = new ArrayList<>();
        User user = null;
        //在循环中添加数据
        for(int i = 0; i < 10; i++){
            user = new User("主键为:" + i, "用户：" + i, "密码为：" + i, new Double(i));
            User user1 = new User("主键为:" + (i+10), "用户：" , "密码为：" + i, new Double(i));
            list.add(user1);
            list.add(user);
        }
        userReposittory.saveAll(list);
        log.info("------------------>添加数据成功");
    }


    /**
     * 在这里进行es聚合查询
     * 再对价格进行平均求值
     * 因为前面的那个文档是没有实现的，所以在这里需要对它重新进行编写
     * 注意点：
     *记住价格是用parseAvg来接收的，而不是用InternalAvg来接收的
     */
    @RequestMapping(value = "aggreation")
    public void aggreation(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //添加查询的条件
        //添加一个新的聚合
        nativeSearchQueryBuilder.addAggregation(
                AggregationBuilders.terms("userNames").field("userName")
                .subAggregation(AggregationBuilders.avg("priceAvg").field("price"))
        );
        //查询，需要把结果强转为AggreagatedPage类型
        AggregatedPage<User> userPage = (AggregatedPage<User>)userReposittory.search(nativeSearchQueryBuilder.build());
        //对聚合查询的结果进行解析
        Terms userNames = (Terms)userPage.getAggregation("userNames");
        //获取桶
        List<? extends Terms.Bucket> buckets1 = userNames.getBuckets();
        //3.3遍历
        for(Terms.Bucket bucket : buckets1){
            log.info("从桶中获取到的key为----------->{}", bucket.getKeyAsString());
            //从桶中获取到的文档数量
            log.info("从桶中的获取到的文档数量为------------->{}", bucket.getDocCount());

            //从子聚合结果中获取数据
            ParsedAvg priceAvg = (ParsedAvg)bucket.getAggregations().get("priceAvg");
            log.info("打印出的原数据为：{}", priceAvg.getValue());
        }
    }

}
