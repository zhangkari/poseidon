package com.class100.poseidon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.class100.atropos.env.AtPlugin;
import com.class100.atropos.generic.AtRuntime;
import com.class100.poseidon.extension.ExtPluginEnv;
import com.class100.poseidon.extension.plugins.ExtKeyEventPlugin;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.util.Map;

public class PsWebView extends WebView {
    private ExtKeyEventPlugin extKeyEvent;

    public PsWebView(Context context, boolean b) {
        super(context, b);
        init();
    }

    public PsWebView(Context context) {
        super(context);
        init();
    }

    public PsWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PsWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public PsWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        init();
    }

    public PsWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
        init();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (extKeyEvent != null) {
                loadUrl(extKeyEvent.synthesizeJsFuncName(event));
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void init() {
        initStyle();
        initWebViewSettings();
        registerJavascriptInterface();
    }

    protected void initStyle() {
        getView().setClickable(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initWebViewSettings() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    private void registerJavascriptInterface() {
        for (Map.Entry<String, Class<? extends AtPlugin<ExtPluginEnv>>> entry : PluginManifest.extensions.entrySet()) {
            Class<? extends AtPlugin<ExtPluginEnv>> clazz = entry.getValue();
            ExtPluginEnv env = buildPluginEnv(entry.getKey(), clazz);
            addJavascriptInterface(env.plugin, env.key);
            if (env.key.equals(PluginManifest.KEY_EVENT)) {
                extKeyEvent = (ExtKeyEventPlugin) env.plugin;
            }
        }
    }

    private ExtPluginEnv buildPluginEnv(String key, Class<? extends AtPlugin<ExtPluginEnv>> clazz) {
        ExtPluginEnv env = new ExtPluginEnv(key);
        env.plugin = AtRuntime.newInstance(clazz, ExtPluginEnv.class, env);
        return env;
    }
}
