package com.class100.hades.http;

import androidx.annotation.IntDef;

import com.class100.atropos.generic.AtCollections;
import com.class100.atropos.generic.AtSerializers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

public abstract class HaRequest {

    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    public static final int METHOD_DELETE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({METHOD_GET, METHOD_POST, METHOD_DELETE})
    @interface RequestMethod {

    }

    String id;
    String group;
    String url;
    int method;
    Map<String, String> headers;
    Map<String, String> parameters;

    public HaRequest() {
        headers = new HashMap<>(8);
        parameters = new HashMap<>(8);
    }

    protected String getId() {
        return "";
    }

    protected String getGroup() {
        return "";
    }

    protected abstract String getUrl();

    protected int getMethod() {
        return METHOD_GET;
    }

    protected Map<String, String> getParameters() {
        return null;
    }

    protected Map<String, String> getHeaders() {
        return null;
    }

    final String emit() {
        id = getId();
        group = getGroup();
        url = getUrl();
        method = getMethod();
        Map<String, String> map = getParameters();
        if (!AtCollections.isEmpty(map)) {
            parameters.putAll(map);
        }
        Map<String, String> header = getHeaders();
        if (!AtCollections.isEmpty(header)) {
            headers.putAll(header);
        }
        return AtSerializers.toJson(this);
    }
}
