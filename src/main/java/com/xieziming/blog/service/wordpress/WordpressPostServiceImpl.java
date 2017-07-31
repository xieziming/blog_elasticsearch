/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.wordpress;

import com.xieziming.blog.model.wordpress.WordpressPost;
import com.xieziming.blog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class WordpressPostServiceImpl implements WordpressArticleService {
    @Autowired
    WordpressPostContentRepository wordpressPostContentRepository;

    @Autowired
    WordpressPostCategoryRepository wordpressPostCategoryRepository;

    @Autowired
    WordpressPostTagRepository wordpressPostTagRepository;

    @Autowired
    WordpressPostPreviewRepository wordpressPostPreviewRepository;

    @Autowired
    WordpressArticleCommentRepository wordpressArticleCommentRepository;

    @Override
    public List<WordpressPost> findAll() {
        List<WordpressPost> wordpressPosts = wordpressPostContentRepository.findAll();
        for(WordpressPost wordpressPost : wordpressPosts){
            extractMetas(wordpressPost);
        }
        return wordpressPosts;
    }

    @Override
    public WordpressPost findById(Integer id) {
        WordpressPost wordpressPost = wordpressPostContentRepository.findById(id);
        extractMetas(wordpressPost);
        return wordpressPost;
    }

    private void extractMetas(WordpressPost wordpressPost){
        wordpressPost.setPostPriview(wordpressPostPreviewRepository.findByPostId(wordpressPost.getID()));
        wordpressPost.setPostCategories(wordpressPostCategoryRepository.findByPostId(wordpressPost.getID()));
        wordpressPost.setPostTags(wordpressPostTagRepository.findByPostId(wordpressPost.getID()));
        wordpressPost.setComments(wordpressArticleCommentRepository.findAll(wordpressPost.getID()));
    }
}
