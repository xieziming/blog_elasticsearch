/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.wordpress.WordpressCalendar;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/8/17.
 */
public class WordpressCalendarRowMapper implements RowMapper<WordpressCalendar> {
    @Override
    public WordpressCalendar mapRow(ResultSet resultSet, int i) throws SQLException {
        WordpressCalendar wordpressCalendar = new WordpressCalendar();
        wordpressCalendar.setId(resultSet.getInt("event_id"));
        wordpressCalendar.setBeginTime(DateUtil.formDate(resultSet.getString("event_date_begin"), resultSet.getString("event_time_begin")));
        wordpressCalendar.setEndTime(DateUtil.formDate(resultSet.getString("event_date_end"), resultSet.getString("event_time_end")));
        wordpressCalendar.setTitle(resultSet.getString("event_title"));
        wordpressCalendar.setDescription(resultSet.getString("event_desc"));
        wordpressCalendar.setLocationLongitude(resultSet.getString("event_location_longitude"));
        wordpressCalendar.setLocationLatitude(resultSet.getString("event_location_latitude"));
        wordpressCalendar.setLocationName(resultSet.getString("event_location_name"));
        wordpressCalendar.setCategory(resultSet.getString("category_name"));
        wordpressCalendar.setStatus(resultSet.getString("event_status"));
        return wordpressCalendar;
    }


}
