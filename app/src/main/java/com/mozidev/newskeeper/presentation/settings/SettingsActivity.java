package com.mozidev.newskeeper.presentation.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.ImageButton;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;
import com.mozidev.newskeeper.presentation.common.Layout;
import com.mozidev.newskeeper.presentation.injection.DaggerHelper;

import javax.inject.Inject;

import butterknife.Bind;

@Layout(id = R.layout.activity_settings)
public class SettingsActivity extends BaseActivity implements SettingsView, SettingsRouter {

    @Inject
    SettingsPresenter settingsPresenter;

    @Bind(R.id.switch_notifications)
    SwitchCompat notificationsSwitch;
    @Bind(R.id.button_clear)
    Button clearButton;
    @Bind(R.id.button_google_play)
    ImageButton googlePlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHelper.getMainComponent().inject(this);
        initToolbar(getToolbarTitle());
        clearButton.setOnClickListener(v -> settingsPresenter.showClearDialog());
        notificationsSwitch.setOnCheckedChangeListener((compoundButton, b) -> settingsPresenter.setNotfications(b));
        googlePlayButton.setOnClickListener(v -> settingsPresenter.openPlayStore());
    }

    @Override
    protected BasePresenter getPresenter() {
        return settingsPresenter;
    }

    @Override
    public void toggleNotificationsSwitch(boolean toggle) {
        notificationsSwitch.setChecked(toggle);
    }

    @Override
    public void showClearMemoryDialog() {
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setMessage("Are you sure to clear cached memory?")
                .setNeutralButton(android.R.string.cancel, ((dialogInterface, i) -> {
                }))
                .setPositiveButton(android.R.string.yes, ((dialogInterface, i) -> settingsPresenter.clearMemory()))
                .create()
                .show();
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.settings);
    }

    @Override
    public void openPlayStore(Intent intent) {
        startActivity(intent);
    }
}
