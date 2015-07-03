package com.hazardous.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    MediaPlayer mMediaPlayer;
    MediaScannerConnection mMediaScannerConnection;

    public static final String TAG = "MusicService";


    public MusicService() {
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mMediaScannerConnection.connect();
        } catch (Throwable throwable) {
            Log.d(TAG, "media scanner connection failed", throwable);
        }


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
