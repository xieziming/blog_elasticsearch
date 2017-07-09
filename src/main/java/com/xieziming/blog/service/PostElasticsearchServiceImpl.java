/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xieziming.blog.model.Post;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Suny on 7/8/17.
 */
@Service
public class PostElasticsearchServiceImpl implements ElasticsearchService<Post> {
    private static final String INDEX_NAME = "xieziming.blog.post";
    private static final String TYPE_POST = "post";
    private static final String TYPE_PAGE = "page";
    @Autowired
    private Client elasticSearchClient;

    @Override
    public CreateIndexResponse createIndex() throws IOException {
        CreateIndexRequestBuilder prepareCreate = elasticSearchClient.admin().indices().prepareCreate(INDEX_NAME);
        prepareCreate.addMapping(TYPE_POST, postMapping());
        return prepareCreate.execute().actionGet();
    }

    @Override
    public DeleteIndexResponse deleteIndex() {
        IndicesExistsResponse indicesExistsResponse = elasticSearchClient.admin().indices()
                .exists(new IndicesExistsRequest(new String[] { INDEX_NAME }))
                .actionGet();
        if (indicesExistsResponse.isExists()) {
            return elasticSearchClient.admin().indices().delete(new DeleteIndexRequest(INDEX_NAME)).actionGet();
        }
        return null;
    }

    XContentBuilder postMapping() throws IOException {
        XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
        return jsonBuilder
                .startObject()
                    .startObject(TYPE_POST)
                        .startObject("properties")
                            .startObject("postContent.postDate")
                                .field("type", "date")
                                .field("format", "yyyy-MM-dd HH:mm:ss")
                            .endObject()
                            .startObject("postContent.postModified")
                                .field("type", "date")
                                .field("format", "yyyy-MM-dd HH:mm:ss")
                            .endObject()
                            .startObject("postComments.commentDate")
                                .field("type", "date")
                                .field("format", "yyyy-MM-dd HH:mm:ss")
                            .endObject()
                            .startObject("postComments.commentAuthorIp")
                                .field("type", "ip")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
    }

    @Override
    public IndexResponse save(Post post) throws IOException {
        IndexResponse response = elasticSearchClient.prepareIndex(INDEX_NAME, TYPE_POST, String.valueOf(post.getPostContent().getID())).setSource(buildJson(post), XContentType.JSON).get();
        return response;
    }

    private String buildJson(Post post) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(post);
    }
}

