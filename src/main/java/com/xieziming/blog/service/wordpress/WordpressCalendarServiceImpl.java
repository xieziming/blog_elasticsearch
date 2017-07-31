/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service.wordpress;

import com.xieziming.blog.model.wordpress.WordpressCalendar;
import com.xieziming.blog.repository.WordpressCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class WordpressCalendarServiceImpl implements WordpressCalendarService {
    @Autowired
    WordpressCalendarRepository wordpressCalendarRepository;

    @Override
    public List<WordpressCalendar> findAll() {
        return wordpressCalendarRepository.findAll();
    }
}
