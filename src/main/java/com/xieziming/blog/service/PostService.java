/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service;

import com.xieziming.blog.model.Post;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
public interface PostService {
    List<Post> findAll();
    Post findById(Integer id);
}
