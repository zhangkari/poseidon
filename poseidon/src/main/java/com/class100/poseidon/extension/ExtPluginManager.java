package com.class100.poseidon.extension;

import android.annotation.SuppressLint;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.class100.atropos.env.AtPlugin;
import com.class100.atropos.env.AtPluginManager;
import com.class100.atropos.generic.AtSerializers;
import com.class100.atropos.generic.AtTexts;
import com.class100.poseidon.PluginManifest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ExtPluginManager extends AtPluginManager<ExtPluginEnv> {
    private WebView webView;

    public ExtPluginManager(ExtPluginEnv env) {
        super(env);
    }

    public void initialize(WebView webView) {
        this.webView = webView;
    }

    @JavascriptInterface
    public String getPlugins() {
        List<String> plugins = new ArrayList<>(PluginManifest.extensions.size());
        for (Map.Entry<String, Class<? extends AtPlugin<ExtPluginEnv>>> entry : PluginManifest.extensions.entrySet()) {
            plugins.add(entry.getKey());
        }
        return AtSerializers.toJson(plugins);
    }

    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    public void load(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(PluginManifest.MANAGER)) {
            return;
        }
        ExtPluginEnv env = context.get(plugin);
        if (env != null && plugin.equals(env.key)) {
            if (!env.loaded) {
                webView.addJavascriptInterface(env.plugin, env.key);
            }
        }
    }

    @JavascriptInterface
    public void unload(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(PluginManifest.MANAGER)) {
            return;
        }
        ExtPluginEnv env = context.get(plugin);
        if (env != null && plugin.equals(env.key)) {
            if (!env.loaded) {
                webView.removeJavascriptInterface(env.key);
            }
        }
    }

    @JavascriptInterface
    public void enable(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(PluginManifest.MANAGER)) {
            return;
        }
        ExtPluginEnv env = context.get(plugin);
        if (env != null && plugin.equals(env.key)) {
            env.enabled = true;
        }
    }

    @JavascriptInterface
    public void disable(String plugin) {
        if (AtTexts.isEmpty(plugin) || plugin.equals(PluginManifest.MANAGER)) {
            return;
        }
        ExtPluginEnv env = context.get(plugin);
        if (env != null && plugin.equals(env.key)) {
            env.enabled = false;
        }
    }
}
