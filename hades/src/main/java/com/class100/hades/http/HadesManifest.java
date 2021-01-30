package com.class100.hades.http;

import java.util.HashMap;
import java.util.Map;

public interface HadesManifest {
    String mainApp = "100";

    // todo
    Map<String, String[]> HostTable = new HashMap<String, String[]>() {
        {
            put(mainApp, new String[]{
                "http://class100.com",
                "http://class100-qa.com",
                "http://class100-dev.com"}
            );
        }
    };
}