package com.hazardous.musicplayer;

/**
 * Created by Brenton on 6/24/2015.
 */
public class Songs
{
    private long id;
    private String name; //Name of the song
    private String artist; //Name of the artist

    public Songs(long songsID, String songsName, String songsArtist)
    {
        id = songsID;
        name = songsName;
        artist = songsArtist;
    }

    public long getID()
    {
        return id;
    }

    public String getTitle()
    {
        return name;
    }
    public String getArtist()
    {
        return artist;
    }
}
