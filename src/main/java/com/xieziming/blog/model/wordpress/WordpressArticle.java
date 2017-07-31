/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.model.wordpress;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 7/30/17.
 */
@Data
public class WordpressArticle {
    protected Integer ID;
    protected Date postDate;
    protected String postContent;
    protected String postTitle;
    protected String postStatus;
    protected String commentStatus;
    protected String postName;
    protected Date postModified;
    protected String postType;
    private List<WordpressArticleComment> comments;
}
