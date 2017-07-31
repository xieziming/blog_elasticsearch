/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.elasticsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xieziming.blog.model.wordpress.WordpressArticle;
import com.xieziming.blog.model.wordpress.WordpressPage;
import com.xieziming.blog.model.wordpress.WordpressPost;
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
public class WordpessArticleElasticsearchServiceImpl implements ElasticsearchService<WordpressArticle>{
    public static final String INDEX_NAME = "com.xieziming.blog.article";
    public static final String POST = "post";
    public static final String PAGE = "page";

    @Autowired
    private Client elasticSearchClient;

    @Override
    public CreateIndexResponse createIndex() throws IOException {
        CreateIndexRequestBuilder prepareCreate = elasticSearchClient.admin().indices().prepareCreate(INDEX_NAME);
        prepareCreate.addMapping(POST, createMapping(POST));
        prepareCreate.addMapping(PAGE, createMapping(PAGE));
        prepareCreate.setSettings(createSetting());
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

    @Override
    public IndexResponse save(WordpressArticle wordpressArticle) throws Exception {
        String postType = null;
        if(wordpressArticle instanceof WordpressPost){
            postType = POST;
        }else if(wordpressArticle instanceof WordpressPage){
            postType = PAGE;
        }else {
            throw new Exception("unknown post type!");
        }
        return elasticSearchClient.prepareIndex(INDEX_NAME, postType, String.valueOf(wordpressArticle.getID())).setSource(buildJson(wordpressArticle), XContentType.JSON).get();
    }

    private String buildJson(WordpressArticle wordpressArticle) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(wordpressArticle);
    }

    XContentBuilder createMapping(String postType) throws IOException {
        XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
        return jsonBuilder
                .startObject()
                    .startObject(postType)
                        .startObject("properties")
                            .startObject("postDate")
                                .field("type", "date")
                                .field("format", "yyyy-MM-dd HH:mm:ss")
                            .endObject()
                            .startObject("postModified")
                                .field("type", "date")
                                .field("format", "yyyy-MM-dd HH:mm:ss")
                            .endObject()
                            .startObject("comments.commentDate")
                                .field("type", "date")
                                .field("format", "yyyy-MM-dd HH:mm:ss")
                            .endObject()
                            .startObject("comments.commentAuthorIp")
                                .field("type", "ip")
                            .endObject()
                            .startObject("postTitle")
                                .field("type", "text")
                                .field("index", "analyzed")
                                .field("search_analyzer","ik_smart")
                                .field("analyzer", "ik_max_word")
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("ignore_above", 256)
                                    .endObject()
                                .endObject()
                            .endObject()
                            .startObject("postCategories")
                                .field("type", "text")
                                .field("index", "analyzed")
                                .field("search_analyzer","ik_smart")
                                .field("analyzer", "ik_max_word")
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("ignore_above", 256)
                                    .endObject()
                                .endObject()
                            .endObject()
                            .startObject("postTags")
                                .field("type", "text")
                                .field("index", "analyzed")
                                .field("search_analyzer","ik_smart")
                                .field("analyzer", "ik_max_word")
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("ignore_above", 256)
                                    .endObject()
                                .endObject()
                            .endObject()
                            .startObject("postContent")
                                .field("type", "text")
                                .field("index", "analyzed")
                                .field("search_analyzer","ik_smart")
                                .field("analyzer", "ik_max_word")
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("ignore_above", 256)
                                    .endObject()
                                .endObject()
                            .endObject()
                            .startObject("comments.commentContent")
                                .field("type", "text")
                                .field("index", "analyzed")
                                .field("search_analyzer","ik_smart")
                                .field("analyzer", "ik_max_word")
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("ignore_above", 256)
                                    .endObject()
                                .endObject()
                            .endObject()
                            .startObject("postExcerpt")
                                .field("type", "text")
                                .field("index", "analyzed")
                                .field("search_analyzer","ik_smart")
                                .field("analyzer", "ik_max_word")
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("ignore_above", 256)
                                    .endObject()
                                .endObject()
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
    }

    XContentBuilder createSetting() throws IOException {
        XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
        return jsonBuilder
                .startObject()
                    .startObject("analysis")
                        .startObject("analyzer")
                            .startObject("ik")
                                .field("tokenizer", "ik_smart")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
    }
}

