package com.mozidev.newskeeper.presentation.splash;

import android.content.Intent;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.categories.CategoriesListActivity;
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
    public void openMainScreen() {
        startActivity(new Intent(this, prefs.isFirstRun() ? CategoriesListActivity.class : ArticlesListActivity.class));
        prefs.setFirstRun(false); // TODO
    }
}
