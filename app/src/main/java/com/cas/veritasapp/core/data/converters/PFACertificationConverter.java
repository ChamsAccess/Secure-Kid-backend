package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.objects.NextOfKin;
import com.cas.veritasapp.objects.PFACertification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PFACertificationConverter {

    @TypeConverter
    public static PFACertification restorePFACertification(String personalString){
        return new Gson().fromJson(personalString, new TypeToken<NextOfKin>(){}.getType());
    }

    @TypeConverter
    public static String savePFACertification(PFACertification pfaCertification){
        return new Gson().toJson(pfaCertification);
    }
}
