/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostPreviewRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String findByPostId(Integer postId){
        try{
            return jdbcTemplate.queryForObject("SELECT meta_value FROM wp_postmeta WHERE meta_key='entry-preview' and post_id =? LIMIT 1", new Object[]{postId}, String.class);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
