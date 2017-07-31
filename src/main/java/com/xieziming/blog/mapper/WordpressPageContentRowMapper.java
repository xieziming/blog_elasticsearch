/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.wordpress.WordpressPage;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/2/17.
 */
public class WordpressPageContentRowMapper implements RowMapper<WordpressPage> {
    @Override
    public WordpressPage mapRow(ResultSet resultSet, int i) throws SQLException {
        WordpressPage wordpressPage = new WordpressPage();
        wordpressPage.setID(resultSet.getInt("ID"));
        wordpressPage.setPostType(resultSet.getString("post_type"));
        wordpressPage.setPostDate(DateUtil.timeStampToDate(resultSet.getTimestamp("post_date")));
        wordpressPage.setPostContent(resultSet.getString("post_content"));
        wordpressPage.setPostTitle(resultSet.getString("post_title"));
        wordpressPage.setPostStatus(resultSet.getString("post_status"));
        wordpressPage.setCommentStatus(resultSet.getString("comment_status"));
        wordpressPage.setPostModified(DateUtil.timeStampToDate(resultSet.getTimestamp("post_modified")));
        return wordpressPage;
    }
}
