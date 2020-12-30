package com.example.demo.es;

import com.example.demo.es.inter.ArticleRepository;
import com.example.demo.pojo.Article;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/12/22  19:48
 * @Description 自定义的controller
 */

@RestController
@Api(produces = "自定义一个controller层")
@RequestMapping(value = "search")
@Slf4j
public class DefinitionController {

    @Autowired
    private ArticleRepository articleRespository;

    /**
     * 自定义查询条件
     * 将内容为hello的数据全部查询出来
     * 记住这是一个模糊查询
     */
    @RequestMapping(value = "definition")
    public void definition(){
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加基本的分词查询，记住matchQuery是一个模糊的查询语句
        queryBuilder.withQuery(QueryBuilders.matchQuery("content", "hello"));
        //执行查询，获取结果
        Page<Article> results = articleRespository.search(queryBuilder.build());
        //打印总条数
        log.info("查询出的总条数为：{}", results.getTotalElements());
        //打印出总页数
        log.info("查询出的总页数为:{}", results.getTotalPages());
        results.forEach(System.out::println);
    }


    /**
     * 分页查询-测试分页查询后的数据
     * 其实是在条件查询之上进行的分页查询结果
     */
    @RequestMapping(value = "pageSearch")
    public void pageSearch(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //添加基本的分页查询，这个查询与前面的不同其中term代表的是精确查询
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("title", "jason"));
        //初始化分页参数
        int page = 0;
        int size = 10;

        //设置分页参数
        //在这地方设置好分页参数的查询以及，注意点就是分页查询是从当前页为0开始的
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page, size));

        //执行搜索，获取结果
        Page<Article> items = articleRespository.search(nativeSearchQueryBuilder.build());
        //打印总条数
        System.out.println(items.getTotalElements());
        //打印总页数
        System.out.println(items.getTotalPages());
        //每页大小
        System.out.println(items.getSize());
        //当前页
        System.out.println(items.getNumber());
        items.forEach(System.out::println);
    }


    /**
     * 查询出tital为jason并且按照用户id升序
     * 查询出来
     */
    @RequestMapping(value = "sortSearch")
    public void sortSearch(){
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("title", "jason"));

        //排序
        queryBuilder.withSort(SortBuilders.fieldSort("userId").order(SortOrder.ASC));

        //执行搜索，获取结果
        Page<Article> items = articleRespository.search(queryBuilder.build());
        //打印总条数
        log.info("打印出的总条数：{}", items.getTotalElements());
        items.forEach(System.out::println);
    }


    /**
     * 聚合查询---其实就是分组查询
     * 功能：
     * 根据title分组进行查询，并且求出每组的总数量
     * 注意点：就是进行分组的那个字段必须要是一个声明为keyWord的字段
     * 否则会报text的错误
     */
    @RequestMapping(value = "aggSearch")
    public void agg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 添加一个新的聚合，聚合类型为terms,聚合名称为userIds, 聚合字段为userId
        queryBuilder.addAggregation(
                AggregationBuilders.terms("userIds").field("title")
        );
        //查询。需要把结果强转为AggregatePage类型
        AggregatedPage<Article> aggPage = (AggregatedPage<Article>)articleRespository.search(queryBuilder.build());
        //解析
        /**
         * 从结果中取出名为userId的聚合
         * 因为是利用String类型子弹来进行的term聚合，所以结果要强转为StringTerm类型
         */
        StringTerms userIds = (StringTerms)aggPage.getAggregation("title");
        //获取桶
        List<StringTerms.Bucket> buckets = userIds.getBuckets();
        //遍历
        for(StringTerms.Bucket bucket : buckets){
            //获取桶中的key，用户id
            String keyAsString = bucket.getKeyAsString();
            log.info("聚合中的key名称为:{}", keyAsString);
            long docCount = bucket.getDocCount();
            log.info("获取桶中的文档数量:{}", docCount);
        }

    }
}
