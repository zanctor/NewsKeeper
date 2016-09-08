package com.mozidev.newskeeper.presentation.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @Bind(R.id.recycler)
    protected RecyclerView recycler;
    @Inject
    protected MainPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
        inflateXML();
        if (recycler != null) {
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    protected void initToolbar(String title) {
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
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().setRouter(this);
        getPresenter().setView(this);
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

    private void inflateXML() {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        ButterKnife.bind(this);
    }

    protected Toolbar.OnMenuItemClickListener getItemMenuListener() {
        return null;
    }

    protected abstract BasePresenter getPresenter();

    protected String getToolbarTitle() {
        return null;
    }

    protected int getMenuXML() {
        return 0;
    }
}
