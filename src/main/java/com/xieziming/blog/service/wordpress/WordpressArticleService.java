/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.wordpress;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
public interface WordpressArticleService<T> {
    List<T> findAll();
    T findById(Integer id);
}
