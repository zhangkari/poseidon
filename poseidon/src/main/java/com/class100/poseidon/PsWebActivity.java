package com.class100.poseidon;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;

import com.class100.atropos.generic.AtLog;
import com.class100.atropos.generic.AtTexts;
import com.class100.oceanides.OcActivity;
import com.class100.poseidon.extension.plugins.WebExtContainerPlugin;
import com.class100.poseidon.extension.plugins.WebExtKeyEventPlugin;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;
import java.util.Map;

public class PsWebActivity extends OcActivity {
    private static final String MODULE = "Poseidon";
    private static final String TAG = "PsWebActivity";

    private View progressView;
    private PsWebView webView;
    private static String interceptScheme;

    private Runnable onPageFinishedListener;

    public static void initialize(Application app) {
        if (QbSdk.isTbsCoreInited()) {
            AtLog.d(MODULE, TAG, "QbSdk init ok");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                AtLog.d(MODULE, TAG, "onDownloadFinish:" + i);
            }

            @Override
            public void onInstallFinish(int i) {
                AtLog.d(MODULE, TAG, "onInstallFinish:" + i);
            }

            @Override
            public void onDownloadProgress(int i) {
                AtLog.d(MODULE, TAG, "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(app, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                AtLog.d(MODULE, TAG, "onCoreInitFinished");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                AtLog.d(MODULE, TAG, "onViewInitFinished:" + b);
            }
        });
    }

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, PsWebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void setInterceptScheme(String scheme) {
        interceptScheme = scheme;
    }

    public void setOnPageFinishedListener(Runnable listener) {
        onPageFinishedListener = listener;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.ps_activity_web;
    }

    @Override
    protected int getTitleLayout() {
        return 0;
    }

    @Override
    protected void init() {
        findViews();
        initWebView();
        String url = getIntent().getStringExtra("url");
        if (!AtTexts.isEmpty(url)) {
            webView.loadUrl(url);
        }
    }

    private void findViews() {
        progressView = findViewById(R.id.ps_web_progress);
        webView = findViewById(R.id.ps_web_webView);
    }

    private void initWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView var1, String var2) {
                progressView.setVisibility(View.GONE);
                if (onPageFinishedListener != null) {
                    onPageFinishedListener.run();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri;
                try {
                    uri = Uri.parse(url);
                } catch (Exception e) {
                    return false;
                }
                if (uri.getScheme() == null) {
                    return false;
                }
                if (uri.getScheme().equalsIgnoreCase(interceptScheme)) {
                    dispatchInterceptScheme(url);
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String scheme = request.getUrl().getScheme();
                if (scheme == null) {
                    return false;
                }
                if (scheme.equalsIgnoreCase(interceptScheme)) {
                    dispatchInterceptScheme(request.getUrl().toString());
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

        });
        WebExtPluginService.getInstance().register(webView);
    }

    private void dispatchInterceptScheme(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            WebExtKeyEventPlugin plugin = WebExtPluginService.getInstance().getPlugin(WebExtPluginManifest.KEY_EVENT);
            if (plugin != null && plugin.isEnabled()) {
                plugin.executeJsCallbackFunction(event);
            }

            if (keyCode == KeyEvent.KEYCODE_BACK) {
                WebExtContainerPlugin containerPlugin = WebExtPluginService.getInstance().getPlugin(WebExtPluginManifest.CONTAINER);
                if (containerPlugin != null && containerPlugin.isEnabled()) {
                    containerPlugin.performDefaultBack();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public PsWebView getWebView() {
        return webView;
    }
}