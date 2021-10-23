package com.mazharulsabbir.sinchjavaexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String APPLICATION_KEY = "d5a80f00-908e-4fb0-b3f8-309b270ed6c4";
    private static final String APPLICATION_SECRET = "OergPfZaGkGkP6otBuFaMQ==";
    private static final String USER_ID = "mazharulsabbir_1";
    private static final String ACCOUNT_ID = "b984d9347503426689e1a7f921188345";

    Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
                .userId(USER_ID)
                .applicationKey(APPLICATION_KEY)
                .environmentHost("ocra.api.sinch.com")
                .build();

        sinchClient.setSupportManagedPush(true);
        sinchClient.startListeningOnActiveConnection();

        sinchClient.addSinchClientListener(new MySinchClientListener());
        sinchClient.start();

        makeCall(sinchClient);
    }

    private void makeCall(SinchClient sinchClient) {
        TextView callState = findViewById(R.id.callState);
        Button callButton = findViewById(R.id.callButton);

        callButton.setOnClickListener(view -> {
            //make a call!
            if (sinchClient.isStarted()) {
                if (call == null) {
                    call = sinchClient.getCallClient().callPhoneNumber("+8801611594448");
                    callButton.setText("Hang Up");

                    Log.d(TAG, "makeCall: Making call..");
                    call.addCallListener(new MySinchCallListener());
                } else {
                    Log.d(TAG, "makeCall: Hangup call");
                    callButton.setText("Call");
                    call.hangup();
                }
            } else {
                Toast.makeText(this, "Call client not started!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}