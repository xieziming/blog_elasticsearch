/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.wordpress.WordpressPost;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/2/17.
 */
public class WordpressPostContentRowMapper implements RowMapper<WordpressPost> {
    @Override
    public WordpressPost mapRow(ResultSet resultSet, int i) throws SQLException {
        WordpressPost wordpressPost = new WordpressPost();
        wordpressPost.setID(resultSet.getInt("ID"));
        wordpressPost.setPostType(resultSet.getString("post_type"));
        wordpressPost.setPostDate(DateUtil.timeStampToDate(resultSet.getTimestamp("post_date")));
        wordpressPost.setPostContent(resultSet.getString("post_content"));
        wordpressPost.setPostTitle(resultSet.getString("post_title"));
        wordpressPost.setPostStatus(resultSet.getString("post_status"));
        wordpressPost.setCommentStatus(resultSet.getString("comment_status"));
        wordpressPost.setPostModified(DateUtil.timeStampToDate(resultSet.getTimestamp("post_modified")));
        wordpressPost.setPostExcerpt(resultSet.getString("post_excerpt"));
        return wordpressPost;
    }
}
