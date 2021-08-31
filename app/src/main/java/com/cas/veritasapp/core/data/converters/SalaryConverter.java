package com.cas.veritasapp.core.data.converters;

import androidx.room.TypeConverter;

import com.cas.veritasapp.objects.Personal;
import com.cas.veritasapp.objects.Salary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SalaryConverter {

    @TypeConverter
    public static Salary restorePersonal(String personalString){
        return new Gson().fromJson(personalString, new TypeToken<Salary>(){}.getType());
    }

    @TypeConverter
    public static String saveSalary(Salary salary){
        return new Gson().toJson(salary);
    }
}
