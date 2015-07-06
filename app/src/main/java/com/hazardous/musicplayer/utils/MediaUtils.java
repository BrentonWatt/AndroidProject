package com.hazardous.musicplayer.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Nathanael Ama on 6/27/2015.
 */
public class MediaUtils extends Activity {

    Context context;
    ContentResolver contentResolver;
    Uri allMusic;

    public void getMusic() {
        contentResolver = getContentResolver();
        allMusic = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }



}
