package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.core.data.entities.Personal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PersonalConverter {

    @TypeConverter
    public static Personal restorePersonal(String personalString){
        return new Gson().fromJson(personalString, new TypeToken<Personal>(){}.getType());
    }

    @TypeConverter
    public static String savePersonal(Personal personal){
        return new Gson().toJson(personal);
    }
}
