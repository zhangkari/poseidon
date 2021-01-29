package com.class100.poseidon.extension.plugins;

import android.annotation.SuppressLint;
import android.webkit.JavascriptInterface;

import com.class100.atropos.env.AtPlugin;
import com.class100.atropos.generic.AtSerializers;
import com.class100.atropos.generic.AtTexts;
import com.class100.poseidon.WebExtPluginManifest;
import com.class100.poseidon.extension.WebExtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class WebExtManagerPlugin extends WebExtPlugin {
    public WebExtManagerPlugin(WebExtPluginEnv env) {
        super(env);
    }

    @JavascriptInterface
    public String getPlugins() {
        List<String> plugins = new ArrayList<>(WebExtPluginManifest.extensions.size());
        for (Map.Entry<String, Class<? extends AtPlugin<WebExtPluginEnv>>> entry : WebExtPluginManifest.extensions.entrySet()) {
            plugins.add(entry.getKey());
        }
        return AtSerializers.toJson(plugins);
    }

    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    public void load(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(WebExtPluginManifest.MANAGER)) {
            return;
        }
        if (context != null && plugin.equals(context.key)) {
            if (!context.loaded) {
                context.webView.addJavascriptInterface(context.plugin, context.key);
            }
        }
    }

    @JavascriptInterface
    public void unload(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(WebExtPluginManifest.MANAGER)) {
            return;
        }
        if (context != null && plugin.equals(context.key)) {
            if (!context.loaded) {
                context.webView.removeJavascriptInterface(context.key);
            }
        }
    }

    @JavascriptInterface
    public void enable(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(WebExtPluginManifest.MANAGER)) {
            return;
        }
        if (context != null && plugin.equals(context.key)) {
            context.enabled = true;
        }
    }

    @JavascriptInterface
    public void disable(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(WebExtPluginManifest.MANAGER)) {
            return;
        }
        if (context != null && plugin.equals(context.key)) {
            context.enabled = false;
        }
    }
}
