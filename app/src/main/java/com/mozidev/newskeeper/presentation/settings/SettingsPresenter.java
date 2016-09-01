package com.mozidev.newskeeper.presentation.settings;

import com.mozidev.newskeeper.domain.articles.CleanArticlesInteractor;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 01.09.16.
 */
public class SettingsPresenter extends BasePresenter<SettingsView, SettingsRouter> {

    MainPrefs prefs;
    CleanArticlesInteractor interactor;

    @Inject
    public SettingsPresenter(MainPrefs prefs, CleanArticlesInteractor interactor) {
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

    public void clearMemory(){
        interactor.execute(null);
    }

    public void setNotfications(boolean b){
        prefs.setShowNotifications(b);
    }

    public void showClearDialog() {
        getView().showClearMemoryDialog();
    }
}
