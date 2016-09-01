package com.mozidev.newskeeper.presentation.splash;

import android.content.Intent;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class SplashActivity extends BaseActivity implements SplashRouter {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter getPresenter() {
        return splashPresenter;
    }

    @Override
    public void openMainScreen(Intent intent) {
        startActivity(intent);
        finish();
    }

}
