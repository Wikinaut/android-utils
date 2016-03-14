package com.mcakir.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;

public class AppUtil extends Application {

    private String appName = "";

    private final String SHARED_PREFERENCES_NAME = "my_android_spf_name";

    private static AppUtil mInstance;

    private SharedPreferences mPreferences;

    private Activity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        appName = getResources().getString(R.string.app_name);

    }

    public void toast(String message) {

        if (!TextUtils.isEmpty(message)) {

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        }
    }

    public boolean isNetworkConnected() {

        // TODO: soon

        return true;
    }

    public <T> boolean doRequest(Request<T> request) {

        if (!isNetworkConnected()) {

            toast("Check your connection!");

            return false;

        } else {

            VolleyUtil.getInstance(getApplicationContext()).addToRequestQueue(request);

            return true;
        }
    }

    public SharedPreferences getSharedPreferences() {

        if (mPreferences == null) {

            mPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        }

        return mPreferences;
    }


    public static synchronized AppUtil getInstance() {

        return mInstance;

    }

    public String getAppName() {

        return appName;

    }

}
