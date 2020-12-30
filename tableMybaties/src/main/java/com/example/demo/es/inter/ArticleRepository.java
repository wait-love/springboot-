package com.example.demo.es.inter;

import com.example.demo.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/12/16  20:33
 * @Description 文章中的仓库
 *  这个接口必须要继承elasticsearchRepository这个类
 */

@Repository
public interface ArticleRepository  extends ElasticsearchRepository<Article, String> {


    /**
     * 根据文章内容模糊查询出数据
     * @param name
     * @return
     */
    List<Article> findByContentLike(String name);

}
