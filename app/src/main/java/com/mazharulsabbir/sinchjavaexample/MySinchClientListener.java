package com.mazharulsabbir.sinchjavaexample;

import android.util.Base64;
import android.util.Log;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class MySinchClientListener implements SinchClientListener {
    private static final String TAG = "MySinchClientListener";
    private static final String APPLICATION_KEY = "d5a80f00-908e-4fb0-b3f8-309b270ed6c4";
    private static final String APPLICATION_SECRET = "OergPfZaGkGkP6otBuFaMQ==";
    private static final String USER_ID = "mazharulsabbir_1";
    private static final String ACCOUNT_ID = "b984d9347503426689e1a7f921188345";

    @Override
    public void onClientStarted(SinchClient sinchClient) {
        Log.d(TAG, "onClientStarted: true");
    }

    @Override
    public void onClientFailed(SinchClient sinchClient, SinchError sinchError) {
        Log.d(TAG, "onClientFailed: " + sinchError.getMessage());
    }

    @Override
    public void onLogMessage(int i, String s, String s1) {
        Log.d(TAG, "onLogMessage: Code:" + i + "\t S:" + s + "\t S1:" + s1);
    }

    @Override
    public void onPushTokenRegistered() {
        Log.d(TAG, "onPushTokenRegistered: true");
    }

    @Override
    public void onPushTokenRegistrationFailed(SinchError sinchError) {
        Log.d(TAG, "onPushTokenRegistrationFailed: " + sinchError.getMessage());
    }

    @Override
    public void onCredentialsRequired(ClientRegistration clientRegistration) {
        Log.d(TAG, "onCredentialsRequired: Creating...");
        UserRegistrationToken userRegistrationToken = new UserRegistrationToken(
                APPLICATION_KEY,
                APPLICATION_SECRET,
                USER_ID,
                UUID.randomUUID().toString(),
                OffsetDateTime.now(),
                OffsetDateTime.parse("2021-10-24T21:55:51.387+06:00")
        );

        String jwt = userRegistrationToken.toJwt();
        Log.d(TAG, "onCredentialsRequired: " + userRegistrationToken.toString());
        clientRegistration.register(jwt);
    }

    @Override
    public void onUserRegistered() {
        Log.d(TAG, "onUserRegistered: true");
    }

    @Override
    public void onUserRegistrationFailed(SinchError sinchError) {
        Log.i(TAG, "onUserRegistrationFailed: " + sinchError.getMessage());
    }
}
