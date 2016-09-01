package com.mozidev.newskeeper.domain.common;

/**
 * Created by mozi on 31.08.16.
 */
@ds.gendalf.PrefsConfig("MainPrefs")
public interface PrefsConfig {

    boolean firstRun = true;
    boolean showNotifications = true;
    boolean sentTokenToServer = false;

}
