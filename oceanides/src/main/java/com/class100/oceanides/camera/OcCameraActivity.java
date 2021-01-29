package com.class100.oceanides.camera;

import android.content.Context;
import android.content.Intent;

import com.class100.oceanides.OcActivity;
import com.class100.oceanides.R;

public class OcCameraActivity extends OcActivity {
    public static void launch(Context context) {
        context.startActivity(new Intent(context, OcCameraActivity.class));
    }

    @Override
    public int getContentLayout() {
        return R.layout.oc_camera_activity;
    }

    @Override
    public int getTitleLayout() {
        return 0;
    }

    @Override
    public void init() {

    }
}
