/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.mapper;

import com.xieziming.blog.model.LiveCalendar;
import com.xieziming.blog.util.DateUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 7/8/17.
 */
public class LiveCalendarRowMapper implements RowMapper<LiveCalendar> {
    @Override
    public LiveCalendar mapRow(ResultSet resultSet, int i) throws SQLException {
        LiveCalendar liveCalendar = new LiveCalendar();
        liveCalendar.setId(resultSet.getInt("event_id"));
        liveCalendar.setBeginTime(DateUtil.formDate(resultSet.getString("event_date_begin"), resultSet.getString("event_time_begin")));
        liveCalendar.setEndTime(DateUtil.formDate(resultSet.getString("event_date_end"), resultSet.getString("event_time_end")));
        liveCalendar.setTitle(resultSet.getString("event_title"));
        liveCalendar.setDescription(resultSet.getString("event_desc"));
        liveCalendar.setLocationLongitude(resultSet.getString("event_location_longitude"));
        liveCalendar.setLocationLatitude(resultSet.getString("event_location_latitude"));
        liveCalendar.setLocationName(resultSet.getString("event_location_name"));
        liveCalendar.setCategory(resultSet.getString("category_name"));
        return liveCalendar;
    }


}
