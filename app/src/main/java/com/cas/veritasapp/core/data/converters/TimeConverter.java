package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by funmiayinde on 2019-09-27.
 */
public class TimeConverter {

    @TypeConverter
    public static Date fromTimeStamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
