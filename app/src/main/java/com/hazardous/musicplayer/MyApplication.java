package com.hazardous.musicplayer;

import android.app.Application;

import com.facebook.stetho.Stetho;

import butterknife.ButterKnife;


public class MyApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
