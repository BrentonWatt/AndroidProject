package com.hazardous.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.hazardous.musicplayer.utils.MusicRetriever;

public class MusicService extends Service {

    MediaPlayer mMediaPlayer;
    MediaScannerConnection mMediaScannerConnection;
    MusicRetriever mMusicRetriever;

    public static final String TAG = "MusicService";


    public MusicService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mMusicRetriever = new MusicRetriever(getContentResolver());
        try {
            mMediaPlayer.prepare();
            mMusicRetriever.prepare();
            mMediaScannerConnection.connect();
            if (mMediaScannerConnection.isConnected()) {
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStorageDirectory())));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///Removable")));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///Removable/SD")));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///Removable/MicroSD")));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///mnt/Removable/MicroSD")));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///mnt")));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///storage")));
                sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file:///Removable")));
            }
            else {
                Toast.makeText(this, "Media card is not present", Toast.LENGTH_LONG);
            }

        } catch (Throwable throwable) {
            Log.d(TAG, "media scanner connection failed", throwable);
        }

    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
