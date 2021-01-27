package com.class100.atropos;

import android.app.Application;

public class AtContextEnv extends AtEnv {
    public final Application _app;

    public AtContextEnv(Application app) {
        _app = app;
    }
}