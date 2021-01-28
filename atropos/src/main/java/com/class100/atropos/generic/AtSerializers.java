package com.class100.atropos.generic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public final class AtSerializers extends AtAbilityAdapter {

    private static final Gson _gson = new Gson();

    public static String toJson(Object object) {
        return _gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return _gson.fromJson(json, clazz);
    }

    public static <T> List<T> fromJson2(String json, Class<T> clazz) {
        return _gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }
}
