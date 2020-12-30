package com.example.demo.es;

import com.alibaba.fastjson.JSON;
import com.example.demo.dds.DataSource;
import com.example.demo.es.inter.ArticleRepository;
import com.example.demo.pojo.Article;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Jason
 * @Create: 2020/12/16  20:30
 * @Description 对es中的crud
 */

@RestController
@Slf4j
public class EsController {


    //注入进去到controller中去
    @Autowired
    private ArticleRepository articleRespository;


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;


    /**
     * 根据id来查询数据
     * @param id
     * @return
     */
    @GetMapping("query/{id}")
    public String findById(@PathVariable String id){
        Optional<Article> byId = articleRespository.findById(id);
        log.info("es中的数据为：{}", byId);
        return "hello es";
    }


    /**
     * 根据id来删除数据（这个方法是接口中自定义的删除方法,可以在此基础之上自定义一个操作方法)
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public String delete(@PathVariable String id){
        //根据id删除
        articleRespository.deleteById(id);
        return "es中的数据已经删除";

    }


    /**
     * 对文章类中的内容进行校验
     * @param article
     * @return
     */
    @PostMapping("saveToEs")
    public String save(@RequestBody Article article){
        //数据新增或者是更新
        String result = verifySaveForm(article);
        if (!StringUtils.isEmpty(result)){
            return "文章中的字段有一些是null值";
        }
        
        if (StringUtils.isEmpty(article.getId())){
            article.setCreateTime(12);
        }
        
        //将文章存储进仓库中去，注意点就是es在存储数据时，它的返回值是存储的document
        Article a = articleRespository.save(article);

        return "es中的数据已经保存成功";
    }


    /**
     * 查询出所有的数据(也是接口中的固定死的方法)
     * @return
     */
    @RequestMapping(value = "queryAllData")
    public  String queryAll(){
        Iterable<Article> all = articleRespository.findAll();
        Iterator<Article> iterator = all.iterator();
        while(iterator.hasNext()){
            Article next = iterator.next();
            log.info("------------->查询出的文章数据为:{}", next);
        }
        return "查询出所有的数据结果";
    }


    /**
     * 对文章中的字段进行校验
     * @param article
     * @return
     */
    private String verifySaveForm(Article article){
        if (article == null || StringUtils.isEmpty(article.getTitle())){
            return "标题不能为空";
        }else if (StringUtils.isEmpty(article.getContent())){
            return "内容不能为空";
        }

        return null;
    }


    /**
     * 对es中的数据进行分页查询
     * @param currentPage
     * @param limit
     * @return
     */
    @GetMapping("list")
    public String list(@RequestParam  Integer currentPage,  @RequestParam Integer limit){
        if (currentPage == null || currentPage < 0 || limit == null || limit <= 0){
            return "请输入合法的分页参数";
        }

        //分页列表查询
        //这里采用ElasticSearchRestTemplate或ElasticsearchOperations来进行分页
        NativeSearchQuery query = new NativeSearchQuery(new BoolQueryBuilder());
        query.setPageable(PageRequest.of(currentPage, limit));

        //方法一：
        SearchHits<Article> searchHits = elasticsearchRestTemplate.search(query, Article.class);

        //方法二：
//        SearchHits<Article> search = elasticsearchOperations.search(query, Article.class);

        List<Article> articles = searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());

        //将数据存储进map中去
        Map<String, Object> map = new HashMap<>();
        map.put("count", searchHits.getTotalHits());
        map.put("articles", articles);
        return JSON.toJSONString(map);
    }

}
