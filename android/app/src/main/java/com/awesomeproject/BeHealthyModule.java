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
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmLnVyaWJlQGdsb2JhbnQtb210bC10ZXN0IiwiZW1haWwiOiJmLnVyaWJlQGdsb2JhbnQtb210bC10ZXN0LmNvbSIsImxtc2lkIjoiZi51cmliZUBnbG9iYW50LW9tdGwtdGVzdCIsImV4cCI6MTY3Njg3NjEyMiwiaXNzIjoiT21hbnRlbCIsImlhdCI6MTY3NTg3NjEyMiwiYXVkIjoiQmVIZWFsdGh5In0.W0yaWG01uUX6mgyIO2ECymdhei9UTfUqQPUlEVhkvG8";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            beHealthy.isUserEnrolled(token).whenCompleteAsync((isEnrolled, throwable) -> {
                Timber.tag("isEnrolled").w("User isEnrolled = %s", isEnrolled);
                if (isEnrolled) {
                    beHealthy.startBeHealthy();
                } else {
                    beHealthy.startEnrollment(token);
                }
            });
        }
    }

}
