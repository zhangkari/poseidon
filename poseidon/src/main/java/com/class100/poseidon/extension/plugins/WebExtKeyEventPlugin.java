package com.class100.poseidon.extension.plugins;

import android.view.KeyEvent;
import android.webkit.JavascriptInterface;

import com.class100.atropos.generic.AtLog;
import com.class100.atropos.generic.AtTexts;
import com.class100.poseidon.extension.WebExtPlugin;
import com.class100.poseidon.extension.WebExtPluginEnv;

public class WebExtKeyEventPlugin extends WebExtPlugin {
    private static final String MODULE = "Poseidon";
    private static final String TAG = WebExtKeyEventPlugin.class.getSimpleName();

    private String keyEventListener;

    public WebExtKeyEventPlugin(WebExtPluginEnv webExtPluginEnv) {
        super(webExtPluginEnv);
    }

    /**
     * Register a callback function name for js
     * <pre>
     *
     * <script>
     *  function onKeyDown(keyCode) {
     *
     *  }
     *
     *  keyEvent.registerKeyEventListener("onKeyDown")
     *
     * </script>
     *
     * </pre>
     * <p>
     *
     * @param keyEventListener js call back function
     * @see android.view.KeyEvent
     */
    @JavascriptInterface
    public void registerKeyEventListener(String keyEventListener) {
        AtLog.d(MODULE, TAG, " listener:" + keyEventListener);
        this.keyEventListener = keyEventListener;
    }

    public void executeJsCallbackFunction(final KeyEvent event) {
        final String callbackFunction = synthesizeJsFuncName(event);
        AtLog.d(MODULE, TAG, "executeJsCallback:" + callbackFunction);
        if (AtTexts.isEmpty(callbackFunction)) {
            return;
        }
        context.webView.post(new Runnable() {
            @Override
            public void run() {
                context.webView.loadUrl(callbackFunction);
            }
        });
    }

    /**
     * @param event
     * @return
     * @hiden
     */
    private String synthesizeJsFuncName(KeyEvent event) {
        if (AtTexts.isEmpty(keyEventListener)) {
            return "";
        }
        return "javascript:" + keyEventListener + "(" + event.getKeyCode() + ")";
    }
}
