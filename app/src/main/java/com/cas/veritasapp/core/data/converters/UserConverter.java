package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;


import com.cas.veritasapp.core.data.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by funmiayinde on 2019-09-27.
 */
public class UserConverter {

    @TypeConverter
    public static User retrieveUser(String staffLevel) {
        return new Gson().fromJson(staffLevel, new TypeToken<User>() {
        }.getType());
    }

    @TypeConverter
    public static String saveStaffLevel(User user) {
        return new Gson().toJson(user);
    }
}
