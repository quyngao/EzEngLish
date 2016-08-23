package com.example.mypc.ezenglish.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.controls.Controls;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.service.SongService;
import com.example.mypc.ezenglish.util.PlayerConstants;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AudioPlayerActivity extends Activity {

    Button btnBack;
    static Button btnPause;
    static Button btnNext;
    static Button btnPlay;
    static TextView textNowPlaying;
    static TextView textAlbumArtist;
    static TextView textComposer;
    static TextView textdoc;
    static LinearLayout linearLayoutPlayer;
    ProgressBar progressBar;
    static Context context;
    TextView textBufferDuration, textDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player);
        context = this;
        init();
    }

    private void init() {
        getViews();
        setListeners();
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), Mode.SRC_IN);
        PlayerConstants.PROGRESSBAR_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                textBufferDuration.setText(UtilFunctions.getDuration(i[0]));
                textDuration.setText(UtilFunctions.getDuration(i[1]));
                progressBar.setProgress(i[2]);
            }
        };
    }

    private void setListeners() {
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.previousControl(getApplicationContext());
            }
        });

        btnPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.pauseControl(getApplicationContext());
            }
        });

        btnPlay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Controls.playControl(getApplicationContext());
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.nextControl(getApplicationContext());
            }
        });

    }

    public static void changeUI() {
        updateUI();
        changeButton();
    }

    private void getViews() {

        btnBack = (Button) findViewById(R.id.btnBack);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        textdoc = (TextView) findViewById(R.id.text_doc);

        textNowPlaying = (TextView) findViewById(R.id.textNowPlaying);
        linearLayoutPlayer = (LinearLayout) findViewById(R.id.linearLayoutPlayer);
        textAlbumArtist = (TextView) findViewById(R.id.textAlbumArtist);
        textComposer = (TextView) findViewById(R.id.textComposer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textBufferDuration = (TextView) findViewById(R.id.textBufferDuration);
        textDuration = (TextView) findViewById(R.id.textDuration);
        textNowPlaying.setSelected(true);
        textAlbumArtist.setSelected(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getApplicationContext());
        if (isServiceRunning) {
            updateUI();
        }
        changeButton();
    }

    public static void changeButton() {
        if (PlayerConstants.SONG_PAUSED) {
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else {
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }

    private static void updateUI() {
        try {
            String songName = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getName();
            String artist = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getContext();
            String album = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getLocation();
            String composer = ""+PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getType();
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/original/1/voca.txt");
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
            }
            textdoc.setText(text.toString());
            textNowPlaying.setText(songName);
            textAlbumArtist.setText(artist + " - " + album);
            if (composer != null && composer.length() > 0) {
                textComposer.setVisibility(View.VISIBLE);
                textComposer.setText(composer);
            } else {
                textComposer.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            long albumId = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getId();
            Bitmap albumArt = UtilFunctions.blur("/original/1/avatar.jpg", context);
            linearLayoutPlayer.setBackgroundDrawable(new BitmapDrawable(albumArt));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
