package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.objects.StaffLevel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by funmiayinde on 2019-09-27.
 */
public class StaffLevelConverter {

    @TypeConverter
    public static StaffLevel restoreStaffLevel(String staffLevel) {
        return new Gson().fromJson(staffLevel, new TypeToken<StaffLevel>() {
        }.getType());
    }

    @TypeConverter
    public static String saveStaffLevel(StaffLevel level) {
        return new Gson().toJson(level);
    }
}
