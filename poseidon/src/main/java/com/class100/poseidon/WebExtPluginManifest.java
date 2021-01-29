package com.class100.poseidon;

import com.class100.atropos.env.AtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;
import com.class100.poseidon.extension.plugins.WebExtManagerPlugin;
import com.class100.poseidon.extension.plugins.WebExtKeyEventPlugin;

import java.util.HashMap;
import java.util.Map;

public interface WebExtPluginManifest {
    String MANAGER = "manager";
    String KEY_EVENT = "keyEvent";

    Map<String, Class<? extends AtPlugin<WebExtPluginEnv>>> extensions = new HashMap<String, Class<? extends AtPlugin<WebExtPluginEnv>>>() {
        {
            put(MANAGER, WebExtManagerPlugin.class);
            put(KEY_EVENT, WebExtKeyEventPlugin.class);
        }
    };
}