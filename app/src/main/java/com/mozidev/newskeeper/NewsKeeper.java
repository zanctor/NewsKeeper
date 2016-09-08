package com.mozidev.newskeeper;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class NewsKeeper extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(true);
        initDagger();
        initFabric();
    }

    private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }

    private void initDagger() {
        DaggerHelper.init(this);
    }
}
