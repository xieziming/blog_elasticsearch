/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.elasticsearch;

import com.xieziming.blog.model.wordpress.WordpressCalendar;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Suny on 7/8/17.
 */
@Service
public class WordpressCalendarElasticsearchServiceImpl implements ElasticsearchService<WordpressCalendar> {
    private static final String INDEX_NAME = "com.xieziming.blog.calendar";
    private static final String TYPE = "event";
    @Autowired
    private Client elasticSearchClient;

    @Override
    public CreateIndexResponse createIndex() throws IOException {
        CreateIndexRequestBuilder prepareCreate = elasticSearchClient.admin().indices().prepareCreate(INDEX_NAME);
        prepareCreate.addMapping(TYPE, calendarMapping());
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
    public IndexResponse save(WordpressCalendar wordpressCalendar) throws IOException {
        IndexResponse response = elasticSearchClient.prepareIndex(INDEX_NAME, TYPE, String.valueOf(wordpressCalendar.getId())).setSource(buildJson(wordpressCalendar)).get();
        return response;
    }


    XContentBuilder calendarMapping() throws IOException {
        XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
        return jsonBuilder.startObject()
                            .startObject(TYPE)
                                .startObject("properties")
                                    .startObject("coordinate").field("type", "geo_point").endObject()
                                    .startObject("startTime").field("type", "date").endObject()
                                    .startObject("endTime").field("type", "date").endObject()
                                .endObject()
                            .endObject()
                .endObject();
    }

    XContentBuilder buildJson(WordpressCalendar wordpressCalendar) throws IOException {
        if(wordpressCalendar.getLocationLatitude().isEmpty() || wordpressCalendar.getLocationLongitude().isEmpty()){
            System.out.println(wordpressCalendar.getId());
        }
        XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
        jsonBuilder.startObject().field("title", wordpressCalendar.getTitle())
                .field("description", wordpressCalendar.getDescription())
                .field("category", wordpressCalendar.getCategory())
                .field("startTime", wordpressCalendar.getBeginTime())
                .field("endTime", wordpressCalendar.getEndTime())
                .field("location", wordpressCalendar.getLocationName())
                .field("coordinate", wordpressCalendar.getLocationLatitude() + ", " + wordpressCalendar.getLocationLongitude())
                .endObject();
        return jsonBuilder;

    }
}

