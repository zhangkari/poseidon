package com.class100.poseidon.extension.plugins;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

import com.class100.atropos.generic.AtLog;
import com.class100.poseidon.extension.WebExtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;

public class WebExtContainerPlugin extends WebExtPlugin {
    private static final String TAG = "WebExtContainerPlugin";

    private boolean disableDefaultBack;

    public WebExtContainerPlugin(WebExtPluginEnv webExtPluginEnv) {
        super(webExtPluginEnv);
    }

    @JavascriptInterface
    public void disableDefaultBack(boolean disable) {
        AtLog.d(TAG, "disableDefaultBack()", "disable = " + disable);
        disableDefaultBack = disable;
    }

    @JavascriptInterface
    public void exit() {
        AtLog.d(TAG, "exit()", "");
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