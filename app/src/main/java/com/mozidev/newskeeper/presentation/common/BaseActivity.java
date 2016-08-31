package com.mozidev.newskeeper.presentation.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mozidev.newskeeper.NewsKeeper;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @BindView(R.id.recycler)
    protected RecyclerView recycler;
    @Inject
    protected MainPrefs prefs;

    protected abstract int getLayoutId();

    protected int getMenuXML(){
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        NewsKeeper.getComponent().inject(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getPresenter().setView(this);
    }

    protected void setToolbar(String title){
        if (toolbar == null) return;
        toolbar.setTitle(title);
        int menu = getMenuXML();
        if (menu != 0) {
            toolbar.inflateMenu(menu);
        }
        toolbar.setOnMenuItemClickListener(getItemMenuListener());
        toolbar.setNavigationOnClickListener(v -> {
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (upIntent != null) {
                NavUtils.navigateUpTo(this, upIntent);
            } else {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().setRouter(null);
    }

    protected Toolbar.OnMenuItemClickListener getItemMenuListener(){
        return null;
    }

    protected abstract BasePresenter getPresenter();
}
