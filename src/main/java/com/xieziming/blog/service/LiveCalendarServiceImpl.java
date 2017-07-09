/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.service;

import com.xieziming.blog.model.LiveCalendar;
import com.xieziming.blog.repository.LiveCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class LiveCalendarServiceImpl implements LiveCalendarService {
    @Autowired
    LiveCalendarRepository liveCalendarRepository;

    @Override
    public List<LiveCalendar> findAll() {
        return liveCalendarRepository.findAll();
    }
}
