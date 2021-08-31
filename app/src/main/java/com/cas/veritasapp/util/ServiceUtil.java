package com.cas.veritasapp.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

public class ServiceUtil {

    public static boolean isPrimitive(Object object) {
        String json = new Gson().toJson(object);
        JsonElement element = new JsonParser().parse(json);
        return element.isJsonPrimitive();
    }

    public static <T> T getObjectValue(Object jsonString, Class<T> tClass) {
        Gson gson = new Gson();
        T object = gson.fromJson(gson.toJson(jsonString), (Type) tClass);
        return object;
    }
}
