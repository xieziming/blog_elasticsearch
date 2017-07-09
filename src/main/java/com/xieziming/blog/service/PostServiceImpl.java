/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service;

import com.xieziming.blog.model.Post;
import com.xieziming.blog.model.PostComment;
import com.xieziming.blog.model.PostContent;
import com.xieziming.blog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostContentRepository postContentRepository;

    @Autowired
    PostCategoryRepository postCategoryRepository;

    @Autowired
    PostTagRepository postTagRepository;

    @Autowired
    PostPreviewRepository postPreviewRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        List<PostContent> postContents = postContentRepository.findAll();
        for(PostContent postContent : postContents){
            List<String> postCategories = postCategoryRepository.findByPostId(postContent.getID());
            List<String> postTags = postTagRepository.findByPostId(postContent.getID());
            String postPreview = postPreviewRepository.findByPostId(postContent.getID());
            List<PostComment> postComments = postCommentRepository.findAll(postContent.getID());

            Post post = new Post();
            post.setPostContent(postContent);
            post.setPostCategories(postCategories);
            post.setPostTags(postTags);
            post.setPostPriview(postPreview);
            post.setPostComments(postComments);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public Post findById(Integer id) {
        PostContent postContent = postContentRepository.findById(id);
        List<String> postCategories = postCategoryRepository.findByPostId(postContent.getID());
        List<String> postTags = postTagRepository.findByPostId(postContent.getID());
        String postPreview = postPreviewRepository.findByPostId(postContent.getID());
        List<PostComment> postComments = postCommentRepository.findAll(postContent.getID());

        Post post = new Post();
        post.setPostContent(postContent);
        post.setPostCategories(postCategories);
        post.setPostTags(postTags);
        post.setPostPriview(postPreview);
        post.setPostComments(postComments);

        return post;
    }
}
