package com.class100.poseidon;

import com.class100.atropos.env.AtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;
import com.class100.poseidon.extension.plugins.WebExtContainerPlugin;
import com.class100.poseidon.extension.plugins.WebExtKeyEventPlugin;
import com.class100.poseidon.extension.plugins.WebExtManagerPlugin;

import java.util.HashMap;
import java.util.Map;

public interface WebExtPluginManifest {
    String MANAGER = "manager";
    String KEY_EVENT = "keyEvent";
    String CONTAINER = "container";

    Map<String, Class<? extends AtPlugin<WebExtPluginEnv>>> extensions = new HashMap<String, Class<? extends AtPlugin<WebExtPluginEnv>>>() {
        {
            put(MANAGER, WebExtManagerPlugin.class);
            put(KEY_EVENT, WebExtKeyEventPlugin.class);
            put(CONTAINER, WebExtContainerPlugin.class);
        }
    };
}