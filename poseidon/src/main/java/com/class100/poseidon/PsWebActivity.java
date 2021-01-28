package com.class100.poseidon;

import android.app.Application;
import android.os.Bundle;

import com.class100.atropos.generic.AtLog;
import com.class100.oceanides.OcActivity;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.util.HashMap;
import java.util.Map;

public class PsWebActivity extends OcActivity {
    private static final String TAG = "PsWebActivity";

    public static void initialize(Application app) {
        Map<String, Object> map = new HashMap<>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                AtLog.d(PluginManifest.MODULE, TAG, "onDownloadFinish:" + i);
            }

            @Override
            public void onInstallFinish(int i) {
                AtLog.d(PluginManifest.MODULE, TAG, "onInstallFinish:" + i);
            }

            @Override
            public void onDownloadProgress(int i) {
                AtLog.d(PluginManifest.MODULE, TAG, "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(app, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                AtLog.d(PluginManifest.MODULE, TAG, "onCoreInitFinished");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                AtLog.d(PluginManifest.MODULE, TAG, "onCoreInitFinished:" + b);
            }
        });
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ps_activity_web);
    }
}