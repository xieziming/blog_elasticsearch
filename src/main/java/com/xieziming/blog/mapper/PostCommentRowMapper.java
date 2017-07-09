/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.PostComment;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/2/17.
 */
public class PostCommentRowMapper implements RowMapper<PostComment> {
    @Override
    public PostComment mapRow(ResultSet resultSet, int i) throws SQLException {
        PostComment postComment = new PostComment();
        postComment.setCommentId(resultSet.getInt("comment_ID"));
        postComment.setCommentDate(DateUtil.timeStampToDate(resultSet.getTimestamp("comment_date")));
        postComment.setCommentContent(resultSet.getString("comment_content"));
        postComment.setCommentAuthor(resultSet.getString("comment_author"));
        postComment.setCommentAuthorEmail(resultSet.getString("comment_author_email"));
        postComment.setCommentAuthorUrl(resultSet.getString("comment_author_url"));
        postComment.setCommentAuthorIp(resultSet.getString("comment_author_IP"));
        return postComment;
    }


}
