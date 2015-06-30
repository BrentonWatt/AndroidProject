package com.hazardous.musicplayer.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

/**
 * Created by Nathanael Ama on 6/27/2015.
 */
public class MediaUtils extends Activity {

    public void getMusic(View view) {
//        Context context = getActivity();
        ContentResolver contentResolver = getContentResolver();
        Uri allMusic = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }



}
