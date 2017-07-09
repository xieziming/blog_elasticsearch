/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */
package com.xieziming.blog.repository;

import com.xieziming.blog.mapper.PostRowMapper;
import com.xieziming.blog.model.PostContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostContentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PostContent> findAll(){
        return jdbcTemplate.query("SELECT * FROM wp_posts WHERE post_status in ('publish','private') AND post_type='post' ", new PostRowMapper());
    }

    public PostContent findById(Integer id){
        return jdbcTemplate.queryForObject("SELECT * FROM wp_posts WHERE ID=? AND post_type='post' ", new Object[]{id}, new PostRowMapper());
    }

}
