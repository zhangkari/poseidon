package com.class100.poseidon;

import com.class100.atropos.env.AtPlugin;
import com.class100.poseidon.extension.ExtPluginEnv;
import com.class100.poseidon.extension.ExtPluginManager;
import com.class100.poseidon.extension.plugins.ExtKeyEventPlugin;

import java.util.HashMap;
import java.util.Map;

public interface PluginManifest {
    String MODULE = "Poseidon";

    String MANAGER = "window";
    String KEY_EVENT = "keyEvent";

    Map<String, Class<? extends AtPlugin<ExtPluginEnv>>> extensions = new HashMap<String, Class<? extends AtPlugin<ExtPluginEnv>>>() {
        {
            put(MANAGER, ExtPluginManager.class);
            put(KEY_EVENT, ExtKeyEventPlugin.class);
        }
    };
}