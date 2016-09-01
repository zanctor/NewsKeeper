package com.mozidev.newskeeper.presentation.splash;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.domain.common.RefreshDataInteractor;
import com.mozidev.newskeeper.domain.common.gcm.RegistrationIntentService;
import com.mozidev.newskeeper.presentation.articles.ArticlesListActivity;
import com.mozidev.newskeeper.presentation.categories.CategoriesListActivity;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by mozi on 31.08.16.
 */
public class SplashPresenter extends BasePresenter<Void, SplashRouter> {

    MainPrefs prefs;
    Context context;

    private static final int SPLASH_DELAY = 2500;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private final RefreshDataInteractor refreshDataInteractor;


    @Inject
    public SplashPresenter(RefreshDataInteractor refreshDataInteractor, MainPrefs prefs, Context context) {
        this.refreshDataInteractor = refreshDataInteractor;
        this.prefs = prefs;
        this.context = context;
    }

    @Override
    public void onStart() {
        if (checkPlayServices()) {
            context.startService(new Intent(context, RegistrationIntentService.class));
        }
        refreshDataInteractor.execute(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object obj) {
                EventBus.getDefault()
                        .post(new Object()); //todo
            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(context, prefs.isFirstRun() ? CategoriesListActivity.class : ArticlesListActivity.class);
                getRouter().openMainScreen(intent);
            }
        }, SPLASH_DELAY);
    }

    @Override
    public void onStop() {

    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog((BaseActivity) getRouter(), resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            }
            return false;
        }
        return true;
    }
}
