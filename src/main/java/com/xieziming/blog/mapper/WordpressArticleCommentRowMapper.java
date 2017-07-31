/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.wordpress.WordpressArticleComment;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/2/17.
 */
public class WordpressArticleCommentRowMapper implements RowMapper<WordpressArticleComment> {
    @Override
    public WordpressArticleComment mapRow(ResultSet resultSet, int i) throws SQLException {
        WordpressArticleComment wordpressArticleComment = new WordpressArticleComment();
        wordpressArticleComment.setCommentDate(DateUtil.timeStampToDate(resultSet.getTimestamp("comment_date")));
        wordpressArticleComment.setCommentContent(resultSet.getString("comment_content"));
        wordpressArticleComment.setCommentAuthor(resultSet.getString("comment_author"));
        wordpressArticleComment.setCommentAuthorEmail(resultSet.getString("comment_author_email"));
        wordpressArticleComment.setCommentAuthorUrl(resultSet.getString("comment_author_url"));
        wordpressArticleComment.setCommentAuthorIp(resultSet.getString("comment_author_IP"));
        return wordpressArticleComment;
    }


}
