package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.core.data.entities.NextOfKin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NextOfKinConverter {

    @TypeConverter
    public static NextOfKin restoreNextOfKin(String personalString){
        return new Gson().fromJson(personalString, new TypeToken<NextOfKin>(){}.getType());
    }

    @TypeConverter
    public static String savePersonal(NextOfKin nextOfKin){
        return new Gson().toJson(nextOfKin);
    }
}
