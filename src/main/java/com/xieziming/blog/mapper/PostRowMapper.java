/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.PostContent;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/2/17.
 */
public class PostRowMapper implements RowMapper<PostContent> {
    @Override
    public PostContent mapRow(ResultSet resultSet, int i) throws SQLException {
        PostContent postContent = new PostContent();
        postContent.setID(resultSet.getInt("ID"));
        postContent.setPostDate(DateUtil.timeStampToDate(resultSet.getTimestamp("post_date")));
        postContent.setPostContent(resultSet.getString("post_content"));
        postContent.setPostTitle(resultSet.getString("post_title"));
        postContent.setPostExcerpt(resultSet.getString("post_excerpt"));
        postContent.setPostStatus(resultSet.getString("post_status"));
        postContent.setPostModified(DateUtil.timeStampToDate(resultSet.getTimestamp("post_modified")));
        postContent.setPostType(resultSet.getString("post_type"));
        return postContent;
    }
}
