package com.hazardous.musicplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ListView;


public class AudioDemo extends Activity implements MediaPlayer.OnCompletionListener {

    private ImageButton play;
    private ImageButton pause;
    private ImageButton stop;
    private MediaPlayer mp;
    private ArrayList<Songs> songsList;
    private ListView songsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songsView = (ListView)findViewById(R.id.song_list);
        songsList = new ArrayList<Songs>();
        getSongList();
        Collections.sort(songsList, new Comparator<Songs>() {
            public int compare(Songs a, Songs b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });
        SongAdapter songAdt = new SongAdapter(this, songsList);
        songsView.setAdapter(songAdt);
        //play = (ImageButton)findViewById(R.id.play);
        //pause = (ImageButton)findViewById(R.id.pause);
        //stop = (ImageButton)findViewById(R.id.stop);

        /*play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();

        });*/

        setup();

    }

    public void getSongList()
    {
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do
            {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songsList.add(new Songs(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
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
    }

    private void play() {
        mp.start();

        play.setEnabled(false);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    private void stop(){
        mp.stop();
        pause.setEnabled(false);
        stop.setEnabled(false);

        try{
            mp.prepare();
            mp.seekTo(0);
            play.setEnabled(true);
        }
        catch (Throwable t){
            goBlooey(t);
        }

    }

    private void pause() {
        mp.pause();

        play.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(true);
    }

    private void loadClip() {
        try {
            mp=MediaPlayer.create(this, R.raw.clip);
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
    }

    private void goBlooey(Throwable throwable) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder
                .setTitle("Exception!")
                .setMessage(throwable.toString())
                .setPositiveButton("OK", null)
                .show();

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audio_demo, menu);
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

        return super.onOptionsItemSelected(item);
    }*/


}
