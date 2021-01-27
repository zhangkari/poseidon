package com.class100.atropos.generic;

import com.class100.atropos.AtAbilityAdapter;

import java.util.List;

public final class AtSerializers extends AtAbilityAdapter {

    // not implemented
//    private static final Gson _gson = new Gson();

    public static String toJson(Object object) {
        /*
        return _gson.toJson(object);
         */

        AtRuntime.throwNPE("not implemented !");

        return null;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
//        return _gson.fromJson(json, clazz);
        AtRuntime.throwNPE("not implemented !");

        return null;
    }

    public static <T> List<T> fromJson2(String json, Class<T> clazz) {
        /*
        return _gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
         */

        AtRuntime.throwNPE("not implemented !");

        return null;
    }
}
