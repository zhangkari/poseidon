package com.class100.hades.http;

public abstract class HaApiRequest extends HaRequest {
    @Override
    protected String getGroup() {
        return HadesManifest.mainApp;
    }
}