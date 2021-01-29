package com.class100.poseidon;

import com.class100.atropos.env.AtPlugin;
import com.class100.atropos.generic.AtRuntime;
import com.class100.poseidon.extension.WebExtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;
import com.tencent.smtt.sdk.WebView;

import java.util.HashMap;
import java.util.Map;

public final class WebExtPluginService {
    private static final WebExtPluginService _instance = new WebExtPluginService();

    public static WebExtPluginService getInstance() {
        return _instance;
    }

    private Map<String, WebExtPlugin> pluginMap;

    private WebExtPluginService() {
        pluginMap = new HashMap<>(32);
    }

    public void register(WebView webView) {
        registerJavascriptInterface(webView);
    }

    @SuppressWarnings("unchecked")
    public <T extends WebExtPlugin> T getPlugin(String key) {
        return (T) pluginMap.get(key);
    }

    private void registerJavascriptInterface(WebView webView) {
        for (Map.Entry<String, Class<? extends AtPlugin<WebExtPluginEnv>>> entry : WebExtPluginManifest.extensions.entrySet()) {
            Class<? extends AtPlugin<WebExtPluginEnv>> clazz = entry.getValue();
            WebExtPluginEnv env = buildPluginEnv(entry.getKey(), clazz, webView);
            pluginMap.put(env.key, (WebExtPlugin) env.plugin);
            webView.addJavascriptInterface(env.plugin, env.key);
        }
    }

    private WebExtPluginEnv buildPluginEnv(String key, Class<? extends AtPlugin<WebExtPluginEnv>> clazz, WebView webView) {
        WebExtPluginEnv env = new WebExtPluginEnv(key);
        env.plugin = AtRuntime.newInstance(clazz, WebExtPluginEnv.class, env);
        env.webView = webView;
        return env;
    }
}
