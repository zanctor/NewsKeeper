package com.mozidev.newskeeper.presentation.settings;

import android.content.Intent;
import android.net.Uri;

import com.mozidev.newskeeper.domain.articles.ClearArticlesInteractor;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

public class SettingsPresenter extends BasePresenter<SettingsView, SettingsRouter> {

    MainPrefs prefs;
    ClearArticlesInteractor interactor;

    @Inject
    public SettingsPresenter(MainPrefs prefs, ClearArticlesInteractor interactor) {
        this.prefs = prefs;
        this.interactor = interactor;
    }

    @Override
    public void onStart() {
        getView().toggleNotificationsSwitch(prefs.isShowNotifications());
    }

    @Override
    public void onStop() {

    }

    public void clearMemory() {
        interactor.execute(null);
    }

    public void setNotfications(boolean b) {
        prefs.setShowNotifications(b);
    }

    public void showClearDialog() {
        getView().showClearMemoryDialog();
    }

    public void openPlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.ballistadigital.tank100"));
        getRouter().openPlayStore(intent);
    }
}
