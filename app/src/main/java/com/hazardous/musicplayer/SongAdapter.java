package com.hazardous.musicplayer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by Brenton on 6/24/2015.
 */

public class SongAdapter extends BaseAdapter
{
    private ArrayList<Songs> songs;
    private LayoutInflater songInf;
    public int getCount()
    {
        return songs.size();
    }

    public Object getItem(int arg0)
    {
        return null;
    }

    public long getItemId(int arg0)
    {
        return 0;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {
        LinearLayout songLay = (LinearLayout)songInf.inflate(R.layout.songs, parent, false);
        //get title and artist views
        TextView songView = (TextView)songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);
        //get song using position
        Songs currSong = songs.get(pos);
        //get title and artist strings
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        //set position as tag
        songLay.setTag(pos);
        return songLay;
    }

    public SongAdapter(Context c, ArrayList<Songs> theSongs)
    {
        songs=theSongs;
        songInf=LayoutInflater.from(c);
    }
}
