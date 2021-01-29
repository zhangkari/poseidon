package com.class100.poseidon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

public class PsWebView extends WebView {
    private OnKeyDownEventListener keyEventListener;

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
    
    public void setOnKeyDownEventListener(OnKeyDownEventListener listener) {
        keyEventListener = listener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyEventListener != null) {
                keyEventListener.onKeyDown(event);
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void init() {
        initStyle();
        initWebViewSettings();
    }

    protected void initStyle() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initWebViewSettings() {
        WebSettings webSettings = this.getSettings();
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSettings.setSupportZoom(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(webSettings.getMixedContentMode());
        }
    }

    public interface OnKeyDownEventListener {
        void onKeyDown(KeyEvent event);
    }
}
