/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.controller;

import com.xieziming.blog.model.wordpress.WordpressPost;
import com.xieziming.blog.service.elasticsearch.WordpessArticleElasticsearchServiceImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Suny on 7/30/17.
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private Client elasticSearchClient;

    @RequestMapping("/")
    String hello(){
        return "hello world!";
    }
    @RequestMapping("/{tag}")
    List<WordpressPost> getPosts(@PathVariable String tag){
        QueryBuilder queryBuilder = QueryBuilders.termQuery("postTags", tag);
        SearchResponse searchResponse = elasticSearchClient.prepareSearch(WordpessArticleElasticsearchServiceImpl.INDEX_NAME).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(queryBuilder).execute().actionGet();
        //System.out.println(searchResponse.toString());
        SearchHits searchHits = searchResponse.getHits();
        for(SearchHit searchHit : searchHits){
            System.out.println(searchHit.getSourceAsString());
        }
        return null;
    }
}
