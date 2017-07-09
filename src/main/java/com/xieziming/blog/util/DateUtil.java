/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.blog.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Suny on 7/8/17.
 */
public class DateUtil {
    public static Date timeStampToDate(Timestamp timestamp) {
        if(timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(sdf.format(timestamp));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date formDate(String date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date.trim() + " " + time.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
