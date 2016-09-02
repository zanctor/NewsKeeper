package com.mozidev.newskeeper.presentation.splash;

import android.content.Intent;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;

import javax.inject.Inject;

@Layout(id = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements SplashRouter {

    @Inject
    SplashPresenter splashPresenter;

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
