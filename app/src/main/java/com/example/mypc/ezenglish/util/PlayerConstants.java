package com.example.mypc.ezenglish.util;

import android.os.Handler;

import com.example.mypc.ezenglish.model.MP3;

import java.util.ArrayList;

public class PlayerConstants {
    //List of Songs
    public static ArrayList<MP3> SONGS_LIST = new ArrayList<MP3>();
    //song number which is playing right now from SONGS_LIST
    public static int SONG_NUMBER = 0;
    //song is playing or paused
    public static boolean SONG_PAUSED = true;
    //song changed (next, previous)
    public static int ID_LEASSON = 1;
    //handler for song changed(next, previous) defined in service(SongService)
    public static Handler SONG_CHANGE_HANDLER;
    //handler for song play/pause defined in service(SongService)
    public static Handler PLAY_PAUSE_HANDLER;
    //handler for showing song progress defined in Activities(MainActivity, AudioPlayerActivity)
    public static Handler PROGRESSBAR_HANDLER;
}
