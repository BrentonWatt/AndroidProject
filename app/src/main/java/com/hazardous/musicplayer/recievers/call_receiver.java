package com.hazardous.musicplayer.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.hazardous.musicplayer.services.MusicService;
import com.hazardous.musicplayer.utils.UtilClass;

public class call_receiver extends BroadcastReceiver {
    public call_receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String number = "";
        Bundle bundle = intent.getExtras();

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            // Phone is ringing
            number = bundle.getString("incoming_number");
            Log.e("incoming_number", "incoming_number");
            _stopServices(context);


        }
        else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            // Call received

        }
        else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            // Call Dropped or rejected


        }
    }

    private void _stopServices(Context context) {
        if (UtilClass.playing) {
            Log.e("start services", "start services");
            Intent i = new Intent(context, MusicService.class);
            context.startService(i);
        }
        else {
            Log.e("start not services", "start not services");
        }
    }
}
