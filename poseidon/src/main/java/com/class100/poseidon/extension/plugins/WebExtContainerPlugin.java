package com.class100.poseidon.extension.plugins;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

import com.class100.poseidon.extension.WebExtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;

public class WebExtContainerPlugin extends WebExtPlugin {
    private boolean disableDefaultBack;

    public WebExtContainerPlugin(WebExtPluginEnv webExtPluginEnv) {
        super(webExtPluginEnv);
    }

    @JavascriptInterface
    public void disableDefaultBack(boolean disable) {
        disableDefaultBack = disable;
    }

    @JavascriptInterface
    public void exit() {
        Context ctx = context.webView.getContext();
        if (ctx instanceof Activity) {
            ((Activity) ctx).finish();
        }
    }

    public void performDefaultBack() {
        if (!disableDefaultBack) {
            if (context.webView.canGoBack()) {
                context.webView.goBack();
            } else {
                exit();
            }
        }
    }
}