package com.hazardous.musicplayer;

/**
 * Created by Brenton on 6/24/2015.
 */
public class Songs
{
    private long _ID;
    private String mName; //Name of the song
    private String mArtist; //Name of the mArtist
    private String mAlbum;

    public Songs(long songsID, String songsName, String songsArtist)
    {
        _ID = songsID;
        mName = songsName;
        mArtist = songsArtist;
    }

    public long getID()
    {
        return _ID;
    }

    public String getTitle()
    {
        return mName;
    }
    public String getArtist()
    {
        return mArtist;
    }
}
