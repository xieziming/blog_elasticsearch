/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.wordpress;

import com.xieziming.blog.model.wordpress.WordpressPage;
import com.xieziming.blog.repository.WordpressArticleCommentRepository;
import com.xieziming.blog.repository.WordpressPageContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class WordpressPageServiceImpl implements WordpressArticleService {
    @Autowired
    WordpressPageContentRepository wordpressPageContentRepository;


    @Autowired
    WordpressArticleCommentRepository wordpressArticleCommentRepository;

    @Override
    public List<WordpressPage> findAll() {
        List<WordpressPage> wordpressPages = wordpressPageContentRepository.findAll();
        for(WordpressPage wordpressPage : wordpressPages){
            extractMetas(wordpressPage);
        }
        return wordpressPages;
    }

    @Override
    public WordpressPage findById(Integer id) {
        WordpressPage wordpressPage = wordpressPageContentRepository.findById(id);
        extractMetas(wordpressPage);
        return wordpressPage;
    }

    private void extractMetas(WordpressPage wordpressPage){
        wordpressPage.setComments(wordpressArticleCommentRepository.findAll(wordpressPage.getID()));
    }
}
