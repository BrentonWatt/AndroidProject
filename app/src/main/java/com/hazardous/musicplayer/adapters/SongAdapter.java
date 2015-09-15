package com.hazardous.musicplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hazardous.musicplayer.R;
import com.hazardous.musicplayer.Songs;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;



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

    public View getView(int pos, View view, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        }
        else {
            view = songInf.inflate(R.layout.list_song_view, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        //get song using position
        Songs currSong = songs.get(pos);
        //get title and artist strings
        viewHolder.song_title.setText(currSong.getTitle());
        viewHolder.song_artist.setText(currSong.getArtist());
        return view;
    }

    public SongAdapter(Context c, ArrayList<Songs> theSongs)
    {
        songs=theSongs;
        songInf=LayoutInflater.from(c);
    }

    static class ViewHolder{
        @Bind(R.id.list_songTitle) TextView song_title;
        @Bind(R.id.list_songArtist) TextView song_artist;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
