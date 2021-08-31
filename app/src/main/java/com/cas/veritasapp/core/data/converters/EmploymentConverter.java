package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.objects.Employment;
import com.cas.veritasapp.objects.Personal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EmploymentConverter {

    @TypeConverter
    public static Employment restoreEmployment(String personalString){
        return new Gson().fromJson(personalString, new TypeToken<Employment>(){}.getType());
    }

    @TypeConverter
    public static String saveEmployment(Employment employment){
        return new Gson().toJson(employment);
    }
}
