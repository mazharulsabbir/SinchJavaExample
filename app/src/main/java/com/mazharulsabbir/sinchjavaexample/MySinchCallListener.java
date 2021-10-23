package com.mazharulsabbir.sinchjavaexample;

import android.util.Log;

import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallListener;

public class MySinchCallListener implements CallListener {
    private static final String TAG = "MySinchCallListener";

    @Override
    public void onCallProgressing(Call call) {
        Log.d(TAG, "onCallProgressing: "+call.getCallId());
        //set call state to "ringing" in the view
        //callState.setText("ringing");
    }

    @Override
    public void onCallEstablished(Call call) {
        Log.d(TAG, "onCallEstablished: "+call.getCallId());

        //change the call state in the view
        //callState.setText("connected");

        //the hardware volume buttons should control the voice stream volume
        //setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
    }

    @Override
    public void onCallEnded(Call call) {
        Log.d(TAG, "onCallEnded: "+call.getCallId());
        //call = null; //no longer a current call
        //callButton.setText("Call"); //change text on button
        //callState.setText(""); //empty call state

        //hardware volume buttons should revert to their normal function
        //setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
    }
}
