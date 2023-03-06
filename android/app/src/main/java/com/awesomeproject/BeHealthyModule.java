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
        beHealthy.setCommunityId("1");
        //beHealthy.setProgramName("Makasib");
        //beHealthy.supportFirebaseAnalytics(false);
        //beHealthy.setSupportColors(new SupportColors(
        //        "FF9E1B",
        //        "004C97",
        //        "FF9E1B"));
        //String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJmYWJlMDY2ZS1kMWE2LTRkMDMtOTlkZi1hMDI1N2VjOGYxNjgiLCJzdWIiOiI1NDU4MSIsImlzcyI6ImJlLWhlYWx0aHktYXV0aCIsImlkZW50aWZpY2F0aW9uIjoiYmVoZWFsdGh5c3RnYW5kQGdtYWlsLmNvbSIsImNvbW11bml0eUlkIjoxLCJhY2wiOnsicm9sZXMiOlsiVVNFUiJdfSwiaWF0IjoxNjc3NjE4Mjk3LCJleHAiOjE2Nzc2MjkwOTd9.fGulnN0NxQEeQUkHmkmLMhrOfUi3VPCn7No-4oxRTy36mw6iiP5AOQs1AvYgyvMpU6KfSqUkDpFrOwCsH3L8ig";
        beHealthy.startBeHealthy();
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        //    beHealthy.isUserEnrolled(token).whenCompleteAsync((isEnrolled, throwable) -> {
        //        Timber.tag("isEnrolled").w("User isEnrolled = %s", isEnrolled);
        //        if (isEnrolled) {
        //            beHealthy.startBeHealthy();
        //        } else {
        //            beHealthy.startEnrollment(token);
        //        }
        //    });
        //}
    }

}
