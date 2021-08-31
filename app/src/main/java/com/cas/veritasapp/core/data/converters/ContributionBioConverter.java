package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.objects.ContributionBio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ContributionBioConverter {

    @TypeConverter
    public static ContributionBio restoreContributionBio(String personalString){
        return new Gson().fromJson(personalString, new TypeToken<ContributionBio>(){}.getType());
    }

    @TypeConverter
    public static String saveContributionBio(ContributionBio contributionBio){
        return new Gson().toJson(contributionBio);
    }
}
