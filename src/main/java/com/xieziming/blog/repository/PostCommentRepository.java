/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.repository;

import com.xieziming.blog.mapper.PostCommentRowMapper;
import com.xieziming.blog.model.PostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostCommentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PostComment> findAll(Integer postId){
        return jdbcTemplate.query("SELECT * FROM wp_comments WHERE comment_post_ID = ? AND comment_approved=1 ", new Object[]{postId}, new PostCommentRowMapper());
    }
}
