/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Suny on 5/22/17.
 */
@Data
public class LiveCalendar {
    private Integer id;
    private String title;
    private Date beginTime;
    private Date endTime;
    private String locationName;
    private String locationLongitude;
    private String locationLatitude;
    private String description;
    private String category;
}
