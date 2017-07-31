/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.model.wordpress;

import lombok.Data;

import java.util.Date;

/**
 * Created by Suny on 5/22/17.
 */
@Data
public class WordpressArticleComment {
    private Date commentDate;
    private String commentAuthor;
    private String commentAuthorIp;
    private String commentAuthorEmail;
    private String commentAuthorUrl;
    private String commentContent;
}
