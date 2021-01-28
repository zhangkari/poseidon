package com.class100.poseidon.extension.plugins;

import android.view.KeyEvent;
import android.webkit.JavascriptInterface;

import com.class100.atropos.generic.AtLog;
import com.class100.atropos.generic.AtTexts;
import com.class100.poseidon.PluginManifest;
import com.class100.poseidon.extension.ExtPlugin;
import com.class100.poseidon.extension.ExtPluginEnv;

public class ExtKeyEventPlugin extends ExtPlugin {
    private static final String TAG = ExtKeyEventPlugin.class.getSimpleName();

    private String keyEventListener;

    public ExtKeyEventPlugin(ExtPluginEnv extPluginEnv) {
        super(extPluginEnv);
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
        AtLog.d(PluginManifest.MODULE, TAG, "listener:" + keyEventListener);
        this.keyEventListener = keyEventListener;
    }

    /**
     * @param event
     * @return
     * @hiden
     */
    public String synthesizeJsFuncName(KeyEvent event) {
        if (AtTexts.isEmpty(keyEventListener)) {
            return "";
        }
        return "javascript:" + keyEventListener + "(" + event.getKeyCode() + ")";
    }
}
