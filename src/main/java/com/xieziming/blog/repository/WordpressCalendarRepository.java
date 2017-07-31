/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.repository;

import com.xieziming.blog.mapper.WordpressCalendarRowMapper;
import com.xieziming.blog.model.wordpress.WordpressCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordpressCalendarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<WordpressCalendar> findAll(){
        return jdbcTemplate.query("SELECT c.*, g.category_name FROM wp_live_calendar c LEFT JOIN wp_live_calendar_categories g ON c.event_category = g.category_id", new WordpressCalendarRowMapper());
    }
}
