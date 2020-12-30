package com.example.demo.es;

import com.example.demo.es.inter.ArticleRepository;
import com.example.demo.pojo.Article;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/12/21  20:54
 * @Description  这个controller主要的就是用来实现es中的几种查询
 *1：词条匹配 matchQuery，先分词然后在调用termQuery进行匹配
 *2： 词条匹配，不分词 TermQuery
 *3： 通配符匹配 wildcardQuery
 *4： 模糊匹配 fuzzyQuery
 *5： 范围匹配 rangeQuery
 *6:  布尔匹配 booleanQuery
 */

@RestController
@Api(produces = "es的多种查询代码验证-spring-data-es")
@Slf4j
public class QueryController {

    /**
     * 在我们进行定义一个仓库类之后进行的基础之上对数据进行操作
     */
    @Autowired
    private ArticleRepository articleRespository;


    /**
     * 利用es中的方法来对数据进行批量的数据添加的操作
     */
    @RequestMapping(value = "addList")
    public void addList(){
        List<Article> articleList = new ArrayList<>();
        articleList.add(new Article("22", "forever love", "where is jolin", 12, 520));
        articleList.add(new Article("23", "hello yi qiao ling", "hello", 13, 56));
        articleList.add(new Article("24", "i with you", "not wear", 13, 67));
        Article article = null;
        for(int i = 1; i <= 20; i++){
            article = new Article("主键值为" + i , "jason" , "love" , i, 520 + i);
            articleList.add(article);
        }
        articleRespository.saveAll(articleList);
        log.info("添加数据成功------------------> 这是添加数据之后的结束");
    }


    /**
     * 词条匹配，先分词然后再调用termQuery查询
     * nativeSearchQueryBuilder: spring提供的一个查询条件构件器
     * QueryBuilders.matchQuery("title", "小米手机")
     * 利用QueryBuilders来生成一个查询
     * Page对象中的属性
     * totalElements:总条数
     * totalPages：总页数
     * iterator:迭代器，本身实现了Iterator接口，因此可直接迭代
     * 得到当前页的数据
     */
    @RequestMapping(value = "search")
    public void search(){
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("content", "jolin"));
        //搜索，获取结果
        Page<Article> items = this.articleRespository.search(queryBuilder.build());
        //总条数
        long counts = items.getTotalElements();
        log.info("查询出的总条数为:{}", counts);
        for(Article article : items){
            log.info("查询出来的文章数据为：{}", article);
        }
        System.out.println("查询出的数据为：-------------------》");
    }


    /**
     * 打印出es中的所有数据
     */
    @RequestMapping(value = "selectAll")
    public void  all(){
        //查询es中的全部数据
        Iterable<Article> all = articleRespository.findAll();
        List<Article> es = articleRespository.findByContentLike("love");
        log.info("es中查询出的数据大小为:{}", es.size());
        log.info("es中的具体数据为:{}", es);
    }


    /**
     * 测试词条查询的方法
     * @return
     */
    @RequestMapping(value = "entrySearch")
    public String entrySearch(){
        MatchQueryBuilder mqb = QueryBuilders.matchQuery("title", "i with you");
        //执行查询
        Iterable<Article> search = articleRespository.search(mqb);
        search.forEach(System.out::println);
        return "成功根据词条查询出数据";
    }
}
