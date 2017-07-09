/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Suny on 7/2/17.
 */
@Data
public class Post {
    private PostContent postContent;
    private List<String> postCategories;
    private List<String> postTags;
    private String postPriview;
    private List<PostComment> postComments;
}
