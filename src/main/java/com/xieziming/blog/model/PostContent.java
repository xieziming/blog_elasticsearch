/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Suny on 5/22/17.
 */
@Data
public class PostContent {
    private Integer ID;
    private Date postDate;
    private String postContent;
    private String postTitle;
    private String postExcerpt;
    private String postStatus;
    private Date postModified;
    private String postType;
}
