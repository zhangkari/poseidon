package com.class100.poseidon.extension;

import com.class100.atropos.env.AtPluginEnv;
import com.tencent.smtt.sdk.WebView;

public class WebExtPluginEnv extends AtPluginEnv {
    public Object plugin;
    public WebView webView;

    public WebExtPluginEnv(String key) {
        super(key);
    }
}
