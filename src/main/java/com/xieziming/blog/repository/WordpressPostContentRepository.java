/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */
package com.xieziming.blog.repository;

import com.xieziming.blog.mapper.WordpressPostContentRowMapper;
import com.xieziming.blog.model.wordpress.WordpressPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordpressPostContentRepository {
    private static final String POST_TYPE = "post";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<WordpressPost> findAll(){
        return jdbcTemplate.query("SELECT * FROM wp_posts WHERE post_status in ('publish','private') AND post_type=? ", new Object[]{POST_TYPE},  new WordpressPostContentRowMapper());
    }

    public WordpressPost findById(Integer id){
        return jdbcTemplate.queryForObject("SELECT * FROM wp_posts WHERE ID=? AND post_type=? ", new Object[]{id, POST_TYPE}, new WordpressPostContentRowMapper());
    }
}
