package com.mozidev.newskeeper.domain.common.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.data.APIDataProviderImpl;
import com.mozidev.newskeeper.domain.common.MainPrefs;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import java.io.IOException;

import javax.inject.Inject;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    @Inject
    MainPrefs prefs;
    @Inject
    APIDataProviderImpl rest;

    public RegistrationIntentService() {
        super(TAG);
        DaggerHelper.getMainComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.sender_id),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            Log.i(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer();

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            prefs.setSentTokenToServer(true);
            // [END register_for_gcm]
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            prefs.setSentTokenToServer(false);
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent();
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer() {
        String id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        rest.postRegisterDevice(id)
                .subscribe();
    }

}
