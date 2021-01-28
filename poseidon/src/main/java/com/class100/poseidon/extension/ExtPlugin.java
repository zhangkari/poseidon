package com.class100.poseidon.extension;

import com.class100.atropos.env.AtPlugin;

public abstract class ExtPlugin extends AtPlugin<ExtPluginEnv> {
    public ExtPlugin(ExtPluginEnv extPluginEnv) {
        super(extPluginEnv);
    }
}
