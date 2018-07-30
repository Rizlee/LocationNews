package com.lnews.evgen.locationnews.features.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseActivityView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectComponent();
        super.onCreate(savedInstanceState);
    }

    protected abstract void injectComponent();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void showActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override public void startNextActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
