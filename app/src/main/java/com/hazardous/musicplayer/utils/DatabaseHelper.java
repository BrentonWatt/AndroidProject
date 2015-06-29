package com.hazardous.musicplayer.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nathanael Ama on 6/27/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "music.db";
    public static final int SCHEMA = 1;
    static final String TITLE="title";
    static final String VALUE="value";
    static final String TABLE="music";


    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, SCHEMA);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE music (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, value REAL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        throw new RuntimeException("Method not implemented");

    }
}
