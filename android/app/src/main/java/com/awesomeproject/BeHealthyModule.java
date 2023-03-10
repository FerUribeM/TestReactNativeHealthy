package com.awesomeproject;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.globant.behealthylibrary.data.model.SupportColors;
import com.globant.behealthylibrary.hostapp.BeHealthyClient;
import com.globant.behealthylibrary.hostapp.BeHealthyEnvironment;


import timber.log.Timber;

public class BeHealthyModule extends ReactContextBaseJavaModule {

    Context context;

    BeHealthyModule(ReactApplicationContext context) {
        super(context);
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public String getName() {
        return "BeHealthyModule";
    }

    @ReactMethod
    public void testBeHealthy() {
        MainApplication mainApplication = (MainApplication) context;
        BeHealthyClient beHealthy = mainApplication.getBeHealthyClient();
        beHealthy.setEnvironment(BeHealthyEnvironment.STAGE);
        beHealthy.setCommunityId("6");
        beHealthy.setProgramName("Makasib");
        beHealthy.supportFirebaseAnalytics(false);
        beHealthy.setSupportColors(new SupportColors(
                "FF9E1B",
                "004C97",
                "FF9E1B"));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZXJvbWFudGVsQHlvcG1haWwuY29tIiwiZW1haWwiOiJmZXJvbWFudGVsQHlvcG1haWwuY29tIiwibG1zaWQiOiJmZXJvbWFudGVsQHlvcG1haWwuY29tIiwiZXhwIjoxNjc5NDY3NjI4LCJpc3MiOiJPbWFudGVsIiwiaWF0IjoxNjc4NDY3NjI4LCJhdWQiOiJCZUhlYWx0aHkifQ.MslWJgyJfMV1tCWXc-LEbX3wSmCl8X1LF9FE1mENF48";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            beHealthy.isUserEnrolled(token).whenCompleteAsync((isEnrolled, throwable) -> {
                if (isEnrolled) {
                    beHealthy.startBeHealthy();
                } else {
                    beHealthy.startEnrollment(token);
                }
            });
        }
    }

}
