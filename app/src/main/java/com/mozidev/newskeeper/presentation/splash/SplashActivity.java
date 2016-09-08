package com.mozidev.newskeeper.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
    }

    @Override
    public void openMainScreen(Intent intent) {
        startActivity(intent);
        finish();
    }

}
