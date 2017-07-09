/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service;

import com.xieziming.blog.model.LiveCalendar;
import com.xieziming.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suny on 7/9/17.
 */
@Service
public class TransferService {
    @Autowired
    ElasticsearchService<LiveCalendar> liveCalendarElasticsearchService;

    @Autowired
    ElasticsearchService<Post> postElasticsearchService;

    @Autowired
    PostService postService;

    @Autowired
    LiveCalendarService liveCalendarService;

    public void tranferAllPostFromWordpressToElasticsearch() throws IOException {
        List<Post> posts = postService.findAll();
        for(Post post : posts){
            System.out.println(postElasticsearchService.save(post));
        }
    }

    public void tranferAllCalendarFromWordpressToElasticsearch() throws IOException {
        List<LiveCalendar> liveCalendars = liveCalendarService.findAll();
        for(LiveCalendar liveCalendar : liveCalendars){
            System.out.println(liveCalendarElasticsearchService.save(liveCalendar));
        }
    }

    public void createPostIndex() throws IOException {
        postElasticsearchService.createIndex();
    }

    public void createCalendarIndex() throws IOException {
        liveCalendarElasticsearchService.createIndex();
    }

    public void deletePostIndex() throws IOException {
        postElasticsearchService.deleteIndex();
    }

    public void deleteCalendarIndex() throws IOException {
        liveCalendarElasticsearchService.deleteIndex();
    }

}
