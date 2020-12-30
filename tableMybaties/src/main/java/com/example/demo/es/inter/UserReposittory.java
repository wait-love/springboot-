package com.example.demo.es.inter;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jason
 * @Create: 2020/12/23  20:16
 * @Description 用户仓库
 */

@Repository
public interface UserReposittory extends ElasticsearchRepository<User, String> {
}
