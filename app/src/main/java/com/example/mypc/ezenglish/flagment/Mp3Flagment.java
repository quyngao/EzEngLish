package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.CustomAdapter;
import com.example.mypc.ezenglish.adapter.MP3Adapter;
import com.example.mypc.ezenglish.controls.Controls;
import com.example.mypc.ezenglish.service.SongService;
import com.example.mypc.ezenglish.util.PlayerConstants;
import com.example.mypc.ezenglish.util.UtilFunctions;

/**
 * Created by Quylt on 8/17/2016.
 */
public class Mp3Flagment extends Fragment {

    MP3Adapter customAdapter = null;
    Button btnBack;
    static Button btnPause;
    static Button btnNext;
    static Button btnPlay;
    static TextView textNowPlaying;
    static TextView textAlbumArtist;
    static TextView textComposer;
    static LinearLayout linearLayoutPlayer;
    ProgressBar progressBar;
    static Context context;
    TextView textBufferDuration, textDuration;
    ListView mediaListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mp3, container, false);
    }

    public static void changeUI() {
        updateUI();
        changeButton();
    }

    private void setListItems() {
        customAdapter = new MP3Adapter(getActivity(), R.layout.custom_list, PlayerConstants.SONGS_LIST);
        mediaListView.setAdapter(customAdapter);
        mediaListView.setFastScrollEnabled(true);
    }

    private void init() {
        getViews();
        setListeners();
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        PlayerConstants.PROGRESSBAR_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                textBufferDuration.setText(UtilFunctions.getDuration(i[0]));
                textDuration.setText(UtilFunctions.getDuration(i[1]));
                progressBar.setProgress(i[2]);
            }
        };
        setListItems();
    }

    private void setListeners() {
        mediaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Log.d("TAG", "TAG Tapped INOUT(IN)");
                PlayerConstants.SONG_PAUSED = false;
                PlayerConstants.SONG_NUMBER = position;
                boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getActivity().getApplication());
                if (!isServiceRunning) {
                    Intent i = new Intent(getActivity(), SongService.class);
                    getActivity().startService(i);
                } else {
                    PlayerConstants.SONG_CHANGE_HANDLER.sendMessage(PlayerConstants.SONG_CHANGE_HANDLER.obtainMessage());
                }
                updateUI();
                changeButton();
                Log.d("TAG", "TAG Tapped INOUT(OUT)");
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.previousControl(getActivity().getApplicationContext());
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.pauseControl(getActivity().getApplicationContext());
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Controls.playControl(getActivity().getApplicationContext());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.nextControl(getActivity().getApplicationContext());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        init();
        boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getActivity().getApplicationContext());
        if (isServiceRunning) {
            updateUI();
        }
        changeButton();
    }
    private static void updateUI() {
        try {
            String songName = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getName();
            String artist = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getContext();
            String album = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getLocation();
            String composer = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getType();

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

    public static void changeButton() {
        if (PlayerConstants.SONG_PAUSED) {
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else {
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }

    private void getViews() {

        btnBack = (Button) getActivity().findViewById(R.id.btnBack);
        btnPause = (Button) getActivity().findViewById(R.id.btnPause);
        btnNext = (Button) getActivity().findViewById(R.id.btnNext);
        btnPlay = (Button) getActivity().findViewById(R.id.btnPlay);

        mediaListView = (ListView) getActivity().findViewById(R.id.listViewMusic);
        textNowPlaying = (TextView) getActivity().findViewById(R.id.textNowPlaying);
        linearLayoutPlayer = (LinearLayout) getActivity().findViewById(R.id.linearLayoutPlayer);
        textAlbumArtist = (TextView) getActivity().findViewById(R.id.textAlbumArtist);
        textComposer = (TextView) getActivity().findViewById(R.id.textComposer);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        textBufferDuration = (TextView) getActivity().findViewById(R.id.textBufferDuration);
        textDuration = (TextView) getActivity().findViewById(R.id.textDuration);
        textNowPlaying.setSelected(true);
        textAlbumArtist.setSelected(true);
    }
}
