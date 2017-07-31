/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.scheduled;

import com.xieziming.blog.model.wordpress.WordpressArticle;
import com.xieziming.blog.model.wordpress.WordpressCalendar;
import com.xieziming.blog.model.wordpress.WordpressPage;
import com.xieziming.blog.model.wordpress.WordpressPost;
import com.xieziming.blog.service.elasticsearch.ElasticsearchService;
import com.xieziming.blog.service.wordpress.WordpressCalendarService;
import com.xieziming.blog.service.wordpress.WordpressPageServiceImpl;
import com.xieziming.blog.service.wordpress.WordpressPostServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Suny on 7/9/17.
 */
@Service
@Slf4j
public class Wordpress2ElasticsearchTransferService {
    @Autowired
    ElasticsearchService<WordpressCalendar> wordpressCalendarElasticsearchService;

    @Autowired
    ElasticsearchService<WordpressArticle> wordpressArticleElasticsearchService;

    @Autowired
    WordpressPageServiceImpl wordpressPageService;

    @Autowired
    WordpressPostServiceImpl wordpressPostService;

    @Autowired
    WordpressCalendarService wordpressCalendarService;

    public void tranferAllPosts() throws Exception {
        List<WordpressPost> wordpressPosts = wordpressPostService.findAll();
        log.info("transferring posts, size: "+wordpressPosts.size());
        for(WordpressPost wordpressPost : wordpressPosts){
            wordpressArticleElasticsearchService.save(wordpressPost);
        }
    }

    public void tranferAllPages() throws Exception {
        List<WordpressPage> wordpressPages = wordpressPageService.findAll();
        log.info("transferring poges, size: "+wordpressPages.size());
        for(WordpressPage wordpressPage : wordpressPages){
            wordpressArticleElasticsearchService.save(wordpressPage);
        }
    }


    public void tranferAllCalendars() throws Exception {
        List<WordpressCalendar> wordpressCalendars = wordpressCalendarService.findAll();
        log.info("transfer calendars, size: "+wordpressCalendars.size());
        for(WordpressCalendar wordpressCalendar : wordpressCalendars){
            wordpressCalendarElasticsearchService.save(wordpressCalendar);
        }
    }

    public void createWordpressArticleIndex() throws IOException {
        wordpressArticleElasticsearchService.createIndex();
    }

    public void createWordpressCalendarIndex() throws IOException {
        wordpressCalendarElasticsearchService.createIndex();
    }

    public void deleteWordpressArticleIndex() throws IOException {
        wordpressArticleElasticsearchService.deleteIndex();
    }

    public void deleteWordpressCalendarIndex() throws IOException {
        wordpressCalendarElasticsearchService.deleteIndex();
    }

}
