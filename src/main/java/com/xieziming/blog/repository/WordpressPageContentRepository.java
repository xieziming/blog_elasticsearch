/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.repository;

import com.xieziming.blog.mapper.WordpressPageContentRowMapper;
import com.xieziming.blog.model.wordpress.WordpressPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordpressPageContentRepository {
    private static final String POST_TYPE = "page";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<WordpressPage> findAll(){
        return jdbcTemplate.query("SELECT * FROM wp_posts WHERE post_status in ('publish','private') AND post_type=? ", new Object[]{POST_TYPE},  new WordpressPageContentRowMapper());
    }

    public WordpressPage findById(Integer id){
        return jdbcTemplate.queryForObject("SELECT * FROM wp_posts WHERE ID=? AND post_type=? ", new Object[]{id, POST_TYPE}, new WordpressPageContentRowMapper());
    }
}
