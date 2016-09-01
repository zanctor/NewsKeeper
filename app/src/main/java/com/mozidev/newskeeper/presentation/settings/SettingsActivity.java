package com.mozidev.newskeeper.presentation.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.CompoundButton;

import com.mozidev.newskeeper.R;
import com.mozidev.newskeeper.presentation.common.BaseActivity;
import com.mozidev.newskeeper.presentation.common.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mozi on 01.09.16.
 */
public class SettingsActivity extends BaseActivity implements SettingsView, SettingsRouter {

    @Inject
    SettingsPresenter settingsPresenter;

    @BindView(R.id.switch_notifications)
    SwitchCompat notificationsSwitch;
    @BindView(R.id.button_clear)
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clearButton.setOnClickListener(v -> settingsPresenter.showClearDialog());
        notificationsSwitch.setOnCheckedChangeListener((compoundButton, b) -> settingsPresenter.setNotfications(b));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
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
        new AlertDialog.Builder(this)
                .setMessage("Are you sure to clear cached memory?")
                .setNeutralButton(android.R.string.cancel, ((dialogInterface, i) -> {}))
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
