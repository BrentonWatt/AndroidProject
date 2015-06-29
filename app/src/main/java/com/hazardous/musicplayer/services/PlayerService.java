package com.hazardous.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PlayerService extends Service {

    public static final String EXTRA_PLAYLIST="EXTRA_PLAYLIST";
    public static final String EXTRA_SHUFFLE="EXTRA_SHUFFLE";
    private boolean isPlaying=false;

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        String playlist=intent.getStringExtra(EXTRA_PLAYLIST);
        boolean useShuffle=intent.getBooleanExtra(EXTRA_SHUFFLE, false);

        play(playlist, useShuffle);

        return(START_NOT_STICKY);

    }

    @Override
    public void onDestroy() {
        stop();
    }



    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (null);
    }

    private void play(String playlist, boolean useShufle) {
        if (!isPlaying) {
            Log.w(getClass().getName(), "Got to play() method");
            isPlaying=true;
        }
    }

    private void stop() {
        if (isPlaying) {
            Log.w(getClass().getName(), "Got to stop() method");
            isPlaying=false;
        }
    }
}
