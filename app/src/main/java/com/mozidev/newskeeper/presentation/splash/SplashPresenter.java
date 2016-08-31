package com.mozidev.newskeeper.presentation.splash;

import com.mozidev.newskeeper.presentation.common.BasePresenter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mozi on 31.08.16.
 */
public class SplashPresenter extends BasePresenter<Void, SplashRouter> {

    private static final int SPLASH_DELAY = 2500;

    @Override
    public void onStart() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getRouter().openMainScreen();
            }
        }, SPLASH_DELAY);
    }

    @Override
    public void onStop() {

    }
}
