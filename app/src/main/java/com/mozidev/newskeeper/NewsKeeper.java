package com.mozidev.newskeeper;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.mozidev.newskeeper.presentation.injection.DaggerMainComponent;
import com.mozidev.newskeeper.presentation.injection.DataModule;
import com.mozidev.newskeeper.presentation.injection.MainComponent;

import io.fabric.sdk.android.Fabric;

public class NewsKeeper extends Application {

    private static MainComponent component;

    public static MainComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
        initDagger();
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }

    private void initDagger() {
        component = DaggerMainComponent.builder()
                .dataModule(new DataModule(this))
                .build();
    }
}
