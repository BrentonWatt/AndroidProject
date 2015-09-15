package com.hazardous.musicplayer.activities;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hazardous.musicplayer.R;
import com.hazardous.musicplayer.fragments.songInfoDialogFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MusicPlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    public static final String TAG = "MusicPlayerActivity";

    @Bind(R.id.play) ImageButton play;
    @Bind(R.id.pause) ImageButton pause;
    @Bind(R.id.stop) ImageButton stop;
    @Bind(R.id.nextSong) ImageButton nextSong;
    @Bind(R.id.previousSong) ImageButton previousSong;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.bind(this);

        setup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(stop.isEnabled()){
            stop();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp){
        stop();
        Log.d(TAG, "onCompletion");
    }

    @OnClick(R.id.play)public void play() {
        mp.start();
        Log.d(TAG, "play() method");

        play.setEnabled(false);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    @OnClick(R.id.stop)public void stop(){
        mp.stop();
        Log.d(TAG, "stop() method");

        pause.setEnabled(false);
        stop.setEnabled(false);
        play.setEnabled(true);
        nextSong.setEnabled(false);
        previousSong.setEnabled(false);
        try{
            mp.prepareAsync(); //working in a different thread

            Log.d(TAG, "prepareAsync");

            mp.seekTo(0);
            play.setEnabled(true);
        }
        catch (Throwable t){
            goBlooey(t);
        }

    }

    @OnClick(R.id.pause)public void pause() {
        mp.pause();

        play.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(true);
    }

    private void nextSong() {

    }

    private void previousSong() {

    }

    private void loadClip() {
        try {
            mp=MediaPlayer.create(this, R.raw.sample_song);
            mp.setOnCompletionListener(this);

        } catch (Throwable throwable) {
            goBlooey(throwable);
        }
    }

    private void setup() {
        loadClip();
        play.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
        nextSong.setEnabled(true);
        previousSong.setEnabled(true);
    }

    private void goBlooey(Throwable throwable) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder
                .setTitle("Exception!")
                .setMessage(throwable.toString())
                .setPositiveButton("OK", null)
                .show();

    }

    public void showSongList() {
        Intent i= new Intent(this, AudioDemo.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music_player, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_songList) {
            showSongList();
            return true;
        }
        if (id == R.id.songInfo) {
            DialogFragment df = new songInfoDialogFragment();
            TextView artT = (TextView)findViewById(R.id.artistText);
            TextView artS = (TextView)findViewById(R.id.songText);
            MediaMetadataRetriever mediaMetadataRetriever = null;
            ContentResolver contentResolver = this.getContentResolver();
            Uri song = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor musicCursor = contentResolver.query(song, null, null, null, null);
            try{

                mediaMetadataRetriever.setDataSource(this, song);
                artT.setText(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
                artS.setText(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
            }
            catch(Throwable throwable){
                goBlooey(throwable);
            }


            df.show(getFragmentManager(), TAG);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
