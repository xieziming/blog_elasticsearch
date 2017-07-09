/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;

/**
 * Created by Suny on 7/8/17.
 */
public interface ElasticsearchService<T> {
    CreateIndexResponse createIndex() throws IOException;
    IndexResponse save(T t) throws IOException;
    DeleteIndexResponse deleteIndex() throws IOException;
}
